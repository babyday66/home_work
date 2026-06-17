# md5

Spring Boot service written in Kotlin for calculating an MD5 hash of an uploaded file.

## Requirements

- Java 21
- Maven 3.9+

## Run

```bash
mvn spring-boot:run
```

## API

Calculate a file hash:

```bash
curl -F "file=@/path/to/file" http://localhost:8080/api/v1/md5
```

Successful response:

```json
{
  "md5": "5eb63bbbe01eeed093cb22bb8f5acdc3"
}
```

Empty files return `400 Bad Request` with an error message.
