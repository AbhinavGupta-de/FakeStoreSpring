# FakeStore Service

This is a simple service that provides a REST API to manage a store. This service provides two implementation to manage a store, one is the third party FakeStore API and the other is postgres database.

# Endpoints

The endpoints can be viewed using the swagger UI. 
The swagger UI can be accessed at `http://localhost:8080/swagger-ui/index.html`

# Running the service

Before running the service, you need do the following configurations:
- Add your postgres database configurations in the `application.properties` file.

**Or**
- Install Docker and run the following command to start a postgres database container:
```
docker compose up
```

Then you can run the service.

To run the service, you can use the following command:

```
mvn spring-boot:run
```