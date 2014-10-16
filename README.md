Oncoblocks REST API Demo
========================

Note
-----
This application is an early, experimental build of a RESTful web service meant to simulate an implementation of the Oncoblocks API.  This application tries to provide an example implementation in the Spring Framework that adheres to commong REST API best practices, as a means of testing their feasability for future Oncoblocks implementations.  This application is not meant to be representative of the current or future direction of the Oncoblocks project. 

Running
-------
Build the project with maven and deploy in Tomcat or another web app container:

```
mvn -U clean install
```

To deploy to another machine, update the application URLs in the following files:

- `src/main/resources/config/app.properties`
- `src/main/webapp/docs/index.html`
