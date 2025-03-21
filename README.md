# XIMPLE Library System Challenge

## Development details
- An MVC application was created;
- The authentication and authorization part is using jwt token, here a
  important point when the login request is executed, the response is the token to be used
  and the userId (some requests use the user ID).
- URI that does not require login execution:
    - "/api/v1/login";
    - "/api/v1/users";
    - "/h2-console/**"; -> database
    - "/swagger-ui/**". -> doc
- For the logout process in case we are in a test application, it was created
  a black list for the tokens and when the user logs out the token is saved on this black list
  and after that, when the user tries to execute something with this black list token, it is not allowed.
- The package config contains all the application settings: documentation, exception handler, security and api versioning.
- To create some objects, the pattern Builder was used.
- The Controllers are annotated with @RestController, and as much as possible I tried my best to make good use of rest concepts such as:
    - having a standardized return using spring's ResponseEntity;
    - returning the correct statuses for each type of request;
    - use HTTP verbs correctly;
    - contain api version information in the route.
- To enter the token in swagger, click Authorize.


## Prerequisites
To run and build this project, the following dependencies must be installed:

* Windows, Linux or macOS

* Java 21 JDK (https://www.oracle.com/br/java/technologies/downloads/#java21)

Check if java is installed using the terminal:

```java -version```

It should output something like:

```
java version "21.0.3" 2024-04-16 LTS
Java(TM) SE Runtime Environment (build 21.0.3+7-LTS-152)
64-bit HotSpot(TM) Java Server VM (build 21.0.3+7-LTS-152, mixed mode, sharing)
```

* Maven 3.x (https://maven.apache.org/download.cgi)

Extract maven to a folder and add it to your PATH environment variable.

```set PATH=%PATH%;installdir\apache-maven-3.x.x\bin```

Check if maven is installed using the terminal:

```mvn --version```

It should output something like:


```
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: /opt/homebrew/Cellar/maven/3.9.6/libexec
Java version: 21.0.2, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/21.0.2/libexec/openjdk.jdk/Contents/Home
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "14.4.1", arch: "aarch64", family: "mac"
```
Note: Some information may differ depending on the operating system.
The example above was taken from Mac OS.

## Dev

To upload the application via terminal

```
cd ximple-library-system
mvn spring-boot:run
```

With the standing application you were able to access the documentation:

```http://localhost:8080/swagger-ui/index.html```

Access database:

```http://localhost:8080/h2-console/login.jsp```

JDBC Url:

```jdbc:h2:mem:ximple```

Note: You do not need to enter a password, just user name = sa