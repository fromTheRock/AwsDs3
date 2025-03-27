# AwsDs3

This Project is a simpe Command Line Application that check unfinished Multi-part AWS Uploads.

The program works but it isn't finished, because it does not happen any more to have unfinished Multi-part Uploads in my Cubbit DS3 archive.

## Getting Started

Make sure to configure the file: `AwsDs3/src/main/resources/application.properties`  
The compiled program runs with the connection infot contained in: `application.properties` 

Compile the project with the command:

```cmd
mvnw package
```

Run the program with:

```cmd
AwsDs3.cmd
``` 

## Project description

This project seek for all the unfinished multi-part uploads, check in the upload in partial or complete and if the file is partial, it Asks to Abort the upload.

It is a develper application, not well documented, and not easy to use from users without some java knowledge.  
However this App has worked in a real case.

This project still need some refactoring and lacks a good user interface and many units tests.

## Application Use Case

During a Sync command of a big folder to my bucket on Cubbit with Cyperduck some big files upload was splitted in multi-part uploads but sometimes the upload did not complete.

I needed a way to check all this multipart uploads, check them and finally Abort them.

AWS-CLI is a good command line tool but really boaring to use on many files.

I started working in a easier way, building tests and Services and thinking to add a user interface easy to use. 

Eventually, I really needed the work done to fix my backups, so I took a shortcut, building just a Command Line application that automate a sequence of steps, only asking confirmations in few occasions.

## Reference Documentation

Document reference used for this project.

* [Amazon Developer Guide - AWS SDK for Java 2.x](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/#build-image)

