# JDBC

Utilities for working with the raw JDBC api. Notably a template processor
for making prepared statements.

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
    <version>0.0.1-alpha1</version>
</dependency>
```


### Gradle

```groovy
dependencies {
    implementation("dev.mccue:jdbc:0.0.1-alpha1")
}
```


## Usage

These examples use [sqlite](https://central.sonatype.com/artifact/org.xerial/sqlite-jdbc). 

### Select rows by id


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
you can make an instance of `SettableParameter`

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
