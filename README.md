Oncoblocks REST API Demo
========================

Note
-----
This application is an early, experimental build of a RESTful web service meant to simulate an implementation of the Oncoblocks API.  This application tries to provide an example implementation in the Spring Framework that adheres to commong REST API best practices, as a means of testing their feasability for future Oncoblocks implementations.  This application is not meant to be representative of the current or future direction of the Oncoblocks project. 

Running
-------
To run locally, no configuration is needed.  This demo uses an embedded in-memory H2 database, which is automatically built when deployed.  Navigating to the application's root URL will redirect to the Swagger API documentation main page, allowing for easy testing of the web services capabilities.

Build the project with maven and deploy in Tomcat or another web app container:

```
mvn -U clean install
```

To run:

```
sh target/bin/webapp
```

Then go to:  http://localhost:8080/

To deploy to another machine, update the application URLs in the following files:

- `src/main/resources/config/app.properties`
- `src/main/webapp/docs/index.html`
