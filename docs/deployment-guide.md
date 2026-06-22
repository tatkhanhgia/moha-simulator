# Deployment Guide

## Prerequisites

- JDK 17 or higher
- Maven 3.9 or higher
- Docker (optional)
- 512MB RAM minimum (1GB recommended)

## Build

```bash
mvn clean package
```

Produces `target/hdld-integration-service-0.0.1-SNAPSHOT.jar`.

## Run

### Development

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production (local JAR)

```bash
java -jar target/hdld-integration-service-0.0.1-SNAPSHOT.jar
```

### With custom storage path

```bash
java -jar target/*.jar --storage.base-path=/var/hdld/data
```

## Configuration

### application.properties

```properties
server.port=8080
storage.base-path=./data
jwt.secret=change-me-in-production
jwt.expiration=3600
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
```

### application-dev.properties

```properties
logging.level.org.springframework.security=DEBUG
storage.base-path=./data-dev
```

## Database (H2 File-Based)

Default: H2 file-based database for zero-config development. Easy switch to PostgreSQL via profile.

- JDBC URL: `jdbc:h2:file:./data/hdldb`
- Console: `http://localhost:8080/h2-console` (dev only)
- Schema auto-created by JPA/Hibernate

## Verification

1. Start the service
2. Open `http://localhost:8080/swagger-ui.html`
3. Execute `POST /hdld/login` with sample credentials
4. Use returned token for other endpoints

## Docker (Optional)

### Local / Development

```bash
docker build -t hdld-service .
docker run -p 8080:8080 -v hdld-data:/data hdld-service
```

Or use the development compose file:

```bash
docker-compose up --build
```

### Production VPS Deployment

Use `docker-compose.prod.yml` (includes restart policy, log rotation, and detach mode):

```bash
export JWT_SECRET=$(openssl rand -base64 32)
docker-compose -f docker-compose.prod.yml up -d --build
```

Or use the provided deploy script:

```bash
# On the VPS, inside the project root
export JWT_SECRET=$(openssl rand -base64 32)
./scripts/deploy-hdld-api-to-vps.sh
```

The script performs preflight checks, validates `JWT_SECRET`, builds the image, waits for the service to become healthy, and prints access URLs.

### Deploy Without Docker Hub

If the VPS cannot pull from a registry, build locally and transfer the image:

```bash
# On build machine
docker build -t hdld-api .
docker save hdld-api -o hdld-api.tar
scp hdld-api.tar user@vps:/opt/hdld-api/

# On VPS
docker load -i hdld-api.tar
docker run -d -p 8080:8080 --name hdld-api -e JWT_SECRET=<secret> hdld-api
```

## Unresolved Questions
- What are the production JVM flags (heap, GC)?
