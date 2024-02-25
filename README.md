# JDBC

Utilities for working with the raw JDBC api. 

Includes

* A template processor for making prepared statements.
* Utilities for reading data from `ResultSet`s

**NOTE:** Template processors are currently a preview feature,
and as such releases of this library are tied to a specific
release of Java.

Expect to have to upgrade.

## Dependency Information

### Maven

```xml
<dependency>
    <groupId>dev.mccue</groupId>
    <artifactId>jdbc</artifactId>
    <version>0.0.1-alpha3</version>
</dependency>
```


### Gradle

```groovy
dependencies {
    implementation("dev.mccue:jdbc:0.0.1-alpha3")
}
```


## Usage

These examples use [sqlite](https://central.sonatype.com/artifact/org.xerial/sqlite-jdbc). 

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

### Read nullable primitive types

`ResultSets` includes helpers for reading potentially null
primitive types from a `ResultSet`

```java
import dev.mccue.jdbc.ResultSets;

void main() throws Exception {
    var db = new SQLiteDataSource();
    db.setUrl("jdbc:sqlite:test.db");

    var name = "bob";
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

    var name = "bob";
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
