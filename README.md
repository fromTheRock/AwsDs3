# AwsDs3

This Project is a simpe Command Line Application that check unfinished Multi-part AWS Uploads.

## Getting Started

Make sure to configure the file: `/AwsDs3/src/main/resources/application.properties`
 
The compile file run test and connection to the bucket with the data in: _application.properties_ 

Compile the project with the command (

´´´cmd
mvnw package
´´´

Run the program with:

´´´cmd
AwsDs3.cmd
´´´ 

## Project description

This project seek for all the unfinished multi-part uploads, check in the upload in partial or complete and if the file is partial, it Asks to Abort the upload.

It is a develper application, not well documented, and not easy to use from users without some java knowledge.  
However this App has worked in a real case.

This project still need some refactoring lacks a good user interface and many units tests.

## Application Use Case

During a Sync command of a big folder to my bucket on Cubbit with Cyperduck some big files upload was splitted in multi-part uploads but sometimes the upload did not complete.

I needed a way to check all this muiltipart uploads, check them and finally Abort them.

AWS-CLI is a good command line tool but raally boaring to use on many files.

I started working in a clean way, building tests and Services and thinking to add a user interface easy to use. 

But I really need the work done on my backups, so I reverted to a Command Line application with a sequential steps of commands only asking confirmations in few occasions.

## Reference Documentation

Document reference used for this project.

* [Amazon Developer Guide - AWS SDK for Java 2.x](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/#build-image)

