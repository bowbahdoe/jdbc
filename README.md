# JDBC

Utilities for working with the raw JDBC api. 

Includes

* Utilities for reading data from `ResultSet`s
* An `UncheckedSQLException` for when throwing a `SQLException` is inconvenient, but might need to be recovered later.
* A `SQLFragment` class for basic query composition
* A `SettableParameter` interface, useful with `SQLFragment` (but way more useful whenever String Templates are re-previewed).

## Dependency Information

### Maven

```xml
<dependency>
    <groupId>dev.mccue</groupId>
    <artifactId>jdbc</artifactId>
    <version>2025.03.09</version>
</dependency>
```


### Gradle

```groovy
dependencies {
    implementation("dev.mccue:jdbc:2025.03.09")
}
```


## Usage

These examples use [sqlite](https://central.sonatype.com/artifact/org.xerial/sqlite-jdbc). 

<!--

### Select rows by id

Any variables injected into the template will be replaced with `?`s in the
SQL and will be set with `.setObject` on the returned `PreparedStatement`.

```java
import dev.mccue.jdbc.StatementPreparer;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    var id = 1;
    try (var conn = db.getConnection()) {
        try (var stmt = StatementPreparer.of(conn)."""
                SELECT *
                FROM widget
                WHERE id = \{id}
                """) {
            var rs = stmt.executeQuery();
        }
    }
}
```

### Select rows by ids

List parameters are automatically expanded into `(?, ?, ?)` with
one question mark for each element in the list.

```java
import dev.mccue.jdbc.StatementPreparer;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    var ids = List.of(1, 2, 3);
    try (var conn = db.getConnection()) {
        try (var stmt = StatementPreparer.of(conn)."""
                SELECT *
                FROM widget
                WHERE id IN \{ids}
                """) {
            var rs = stmt.executeQuery();
        }
    }
}
```

### Inject parameters with custom logic

To inject a parameter that needs to be set with something other than `setObject`,
you can make an instance of `SettableParameter`.

```java
import dev.mccue.jdbc.SettableParameter;
import dev.mccue.jdbc.StatementPreparer;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    var name = "bob";
    try (var conn = db.getConnection()) {
        try (var stmt = StatementPreparer.of(conn)."""
                SELECT *
                FROM widget
                WHERE name = \{SettableParameter.ofNString(name)}
                """){
            var rs = stmt.executeQuery();
        }
    }
}
```
-->

### Wrap SQLExceptions

In many contexts the checked-ness of `SQLException` can be inconvenient. Just as the standard library
provides `UncheckedIOException` to wrap an `IOException`, this library provides an `UncheckedSQLException`
to wrap `SQLException`.

```java
import dev.mccue.jdbc.UncheckedSQLException;

import java.sql.SQLException;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT name
                FROM widget
                LIMIT 1
                """)) {
            var rs = stmt.executeQuery();

            var name = rs.getString("name");
        }
    } catch (SQLException e) {
        throw new UncheckedSQLException(e);
    }
}
```

### Read nullable primitive types

`ResultSets` includes helpers for reading potentially null
primitive types from a `ResultSet`.

```java
import dev.mccue.jdbc.ResultSets;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT number
                FROM widget
                LIMIT 1
                """)) {
            var rs = stmt.executeQuery();

            // Methods exist for all primitives except char 
            // (which doesn't have a method on ResultSet)
            Integer number = ResultSets.getIntegerNullable(rs, "number");
        }
    }
}
```

### Read non-null primitive types

If you want to read a column that is primitive, but you assume
is not null, there are helpers which will throw a `SQLException`
early if that assumption is violated.

```java
import dev.mccue.jdbc.ResultSets;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT number
                FROM widget
                LIMIT 1
                """)) {
            var rs = stmt.executeQuery();

            // Methods exist for all primitives except char 
            // (which doesn't have a method on ResultSet)
            int number = ResultSets.getIntegerNotNull(rs, "number");
        }
    }
}
```

### Read a row as a `Record`

Often when going through a `ResultSet` you will want to materialize a whole row.

```java
import dev.mccue.jdbc.ResultSets;

public record Widget(int number) {}

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT number
                FROM widget
                LIMIT 1
                """)) {
            var rs = stmt.executeQuery();
            var widget = ResultSets.getRecord(rs, Widget.class);

            System.out.println(widget);
        }
    }
}
```


### Read a row as a `Record` with customized mappings

If the name of a record component doesn't line up with what you want pulled from a
`ResultSet`, you can use the `@Column` annotation.

```java
import dev.mccue.jdbc.Column;
import dev.mccue.jdbc.ResultSets;

public record Widget(@Column(label = "number") int n) {
}

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT number
                FROM widget
                LIMIT 1
                """)) {
            var rs = stmt.executeQuery();
            var widget = ResultSets.getRecord(rs, Widget.class);

            System.out.println(widget);
        }
    }
}
```

### Read rows as a stream

If you want to iterate over the results of a query without the classic
`while (rs.next())` pattern, there is a helper to get the results as a
stream.

```java
import dev.mccue.jdbc.Column;
import dev.mccue.jdbc.ResultSets;

public record Widget(int number) {
}

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT number
                FROM widget
                LIMIT 1
                """)) {
            ResultSets.stream(rs, ResultSets.getRecord(Widget.class))
                    .forEach(System.out::println);
        }
    }
}
```

### Compose fragments of SQL

You can use `SQLFragment` to implement some relatively basic conditional query building logic.


```java
import dev.mccue.jdbc.ResultSets;
import dev.mccue.jdbc.SQLFragment;

import java.util.ArrayList;
import java.util.List;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    var queryFragments = new ArrayList<SQLFragment>();
    queryFragments.add(SQLFragment.of("""
            SELECT name
            FROM widget
            WHERE id = ?
            """, List.of(1)));
    
    Integer limit = Math.random() > 0.5 ? null : 1;
    if (limit != null) {
        queryFragments.add(SQLFragment.of("""
                LIMIT ?
                """, List.of(limit)));
    }
    
    var query = SQLFragment.join("", queryFragments);
    
    try (var conn = db.getConnection()) {
        try (var stmt = query.prepareStatement(conn)) {
            var rs = stmt.executeQuery();
            Integer number = ResultSets.getIntegerNullable(rs, "number");
            System.out.println(number);
        }
    }
}
```

By default, parameters are set with `.setObject`. If your particular database driver won't do the right
thing with that you can wrap them with `SettableParameter`. The different `.ofX` methods on there match up 1-1
with the `.setX` methods on `PreparedStatement`.

```java
import dev.mccue.jdbc.ResultSets;
import dev.mccue.jdbc.SQLFragment;
import dev.mccue.jdbc.SettableParameter;

import java.util.ArrayList;
import java.util.List;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    var queryFragments = new ArrayList<SQLFragment>();
    queryFragments.add(SQLFragment.of("""
            SELECT name
            FROM widget
            WHERE id = ?
            """, List.of(SettableParameter.ofInt(1))));

    Integer limit = Math.random() > 0.5 ? null : 1;
    if (limit != null) {
        queryFragments.add(SQLFragment.of("""
                LIMIT ?
                """, List.of(SettableParameter.ofInt(limit))));
    }

    var query = SQLFragment.join("", queryFragments);

    try (var conn = db.getConnection()) {
        try (var stmt = query.prepareStatement(conn)) {
            var rs = stmt.executeQuery();
            Integer number = ResultSets.getIntegerNullable(rs, "number");
            System.out.println(number);
        }
    }
}
```

<!--
### Read a row as a `Record`, customizing how a column is gotten from a `ResultSet`.

```java
import dev.mccue.jdbc.Column;
import dev.mccue.jdbc.DefaultRecordComponentGetter;
import dev.mccue.jdbc.ResultSets;

import java.lang.reflect.RecordComponent;
import java.sql.ResultSet;
import java.sql.SQLException;

public record Text(String contents) {}

public static final class CustomRecordComponentGetter 
        extends DefaultRecordComponentGetter {
    @Override
    protected Object getIndexedRecordComponent(
            ResultSet rs, 
            RecordComponent recordComponent, 
            int index
    ) throws SQLException {
        return new Text(rs.getString(index));
    }

    @Override
    protected Object getLabeledRecordComponent(
            ResultSet rs, 
            RecordComponent recordComponent, 
            String label
    ) throws SQLException {
        return new Text(rs.getString(label));
    }
}

public record Widget(
        @Column(label = "number") 
        int n,
        @Column(
                recordComponentGetter = CustomRecordComponentGetter.class
        )
        Text name) {
}

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    try (var conn = db.getConnection()) {
        try (var stmt = conn.prepareStatement("""
                SELECT number, name
                FROM widget
                LIMIT 1
                """)) {
            var rs = stmt.executeQuery();
            var widget = ResultSets.getRecord(rs, Widget.class);

            System.out.println(widget);
        }
    }
}
```

-->