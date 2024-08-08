# JDBC

Utilities for working with the raw JDBC api. 

Includes

* Utilities for reading data from `ResultSet`s
* A `SettableParameter` interface, for when String Templates are re-previewed.

## Dependency Information

### Maven

```xml
<dependency>
    <groupId>dev.mccue</groupId>
    <artifactId>jdbc</artifactId>
    <version>2024.08.08</version>
</dependency>
```


### Gradle

```groovy
dependencies {
    implementation("dev.mccue:jdbc:2024.08.08")
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

### Read nullable primitive types

`ResultSets` includes helpers for reading potentially null
primitive types from a `ResultSet`

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
            var number = ResultSets.getIntegerNullable(rs, "number");
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
            var number = ResultSets.getIntegerNotNull(rs, "number");
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