# AwsDs3

This Project is a simple Command Line Application that check and fix unfinished Multi-part AWS Uploads.

The program works but it isn't finished because it do not happen any more to have unfinished Multi-pert Uploads in my S3 archive.

## Getting Started

Compile the project with the command:

´´´cmd
mvnw package
´´´

Run the program with:

´´´cmd
AwsDs3.cmd
´´´

## Scope of this project

This project seek for all the unfinished multi-part uploads, check in the upload in partial or complete and if the file is partial, it Asks to Abort the upload.

It is a develper application, not well documented, and not easy to be used from users who cannot read java code.  
However this App has worked in ra real case.

For sure it nead a lot of refactoring ad to add a good user interface and test units.

## Why I started developping this App

During a Sync command of a big folder to my bucket on Cubbit with Cyperduck some big files upload was splitted in multi-part uploads but sometimes the upload did not complete.

I really need a way to check all this muiltipart uploads, check them and finally Abort them.

AWS-CLI is a good command line tool but rally boaring to use so many times.

I started working in a orderd way, building tests and Services and thinking to add a user interface easy to use. 

But I really need the work done on my backups, so I reverted to a Command Line application with a sequential steps of commands only asking confirmations in few occasions.

## Reference Documentation

Document reference used for this project.

* [Amazon Developer Guide - AWS SDK for Java 2.x](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/#build-image)

