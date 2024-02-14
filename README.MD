# Assurance Auto Project
This project help Assurance to manage its autos
1. Manage autos

## Technical Information
It's a Restful service API backend developed with [springBoot](https://spring.io/projects/spring-boot/) framework, using the library of dependency management maven.
- The following project using the following java version ([Java 17](ww.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- To lean more about maven you can visit the following link [maven](https://maven.apache.org/)

## Docker
This Project is dockerized using [docker](https://www.docker.com/).
- To dockerise this project you must flow this two steps:
    - build the docker image using :

      ```` mvn clean install ````

      ```` docker build -t assurance_Auto_Image . ````
    - Run the docker container using:

      ````docker run assurance_Auto -p 8080:8080 -d assurance_Auto_Image````
    - Or by running the [docker file](/Users/oumaima/Downloads/AssuranceAuto/Dockerfile)

- To learn more about docker file you can visit the following link [docker file](https://spring.io/guides/topicals/spring-boot-docker/)


## Types

You can format the project using the plugin :
```` 
<plugin>
      <groupId>com.coveo</groupId>
      <artifactId>fmt-maven-plugin</artifactId>
      <version>2.5.0</version>
      <executions>
          <execution>
                <goals>
                        <goal>format</goal>
                </goals>
          </execution>
      </executions>
</plugin>   
````

## Swagger

The API is documented using [Swagger](https://swagger.io/).

## Health Check

To check if the service is up, we enable to a GET call to our service via the health check Controller.
Please execute the following command to the check the status of the app ```` curl localhost:8080/healthCheck````

If we receive the msg  ```` AssuranceAuto  ```` then the app is ready to be consulted.
If no msg is received, then a problem has occurred.