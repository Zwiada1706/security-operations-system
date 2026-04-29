# PostgreSQL Local Setup

## Database

Database used for local development:

```text
securitydb

spring.datasource.url=jdbc:postgresql://localhost:5432/securitydb
spring.datasource.username=postgres
spring.datasource.password=hidden
spring.datasource.driver-class-name=org.postgresql.Driver


## 4.3 Important security check

Do **not** push your real PostgreSQL password to GitHub.

If your `application.properties` has your real password, that is okay locally for now, but we should soon change it to environment variables.

For now, continue only if your repo is private or you are not worried yet. Later we fix it properly.

## 4.4 Test backend again

From `backend/`:

```bash
./mvnw spring-boot:run