# MySQL data generation tool

The purpose of this project is to have a tool for populating an empty database with data.

## Configuration

Database connection params should be put in `config/database.yml`

```
database: <DB_NAME>
hostname: <DB_HOST>
username: <DB_USER>
password: <DB_PASSWORD>
port:     <DB_PORT>
```

## Commands

./gradlew run --args='100' - will insert 100 records

