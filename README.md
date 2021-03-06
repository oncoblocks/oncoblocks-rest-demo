Oncoblocks REST API Demo
========================

Note
-----
This application is an early, experimental build of a RESTful web service meant to simulate an implementation of the Oncoblocks API.  This application tries to provide an example implementation in the Spring Framework that adheres to commong REST API best practices, as a means of testing their feasability for future Oncoblocks implementations.  This application is not meant to be representative of the current or future direction of the Oncoblocks project. 

About
-----
This is an example implementation of the Oncoblocks API in Java, built with:

- Spring MVC 4
- Spring HATEOAS (https://github.com/spring-projects/spring-hateoas)
- Swagger Spring MVC (https://github.com/martypitt/swagger-springmvc)
- Swagger UI (https://github.com/swagger-api/swagger-ui)

Running
-------
To run locally, no configuration is needed.  Navigating to the application's root URL will redirect to the Swagger API documentation main page, allowing for easy testing of the web services capabilities.

Build the project with Maven and then deploy the WAR file output in `/target` to Tomcat or another web app container:

```
mvn -U clean install
```

Navigate in your browser to the application's root URL to use the Swagger UI web service tools.

To deploy to another machine, update the application URLs in the following files:

- `src/main/resources/config/app.properties`
- `src/main/webapp/docs/index.html`
