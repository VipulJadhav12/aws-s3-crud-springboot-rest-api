
# aws-s3-crud-springboot-rest-api

In this spring-boot project, we'll learn how to use [AWS SDK for Java](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/welcome.html) to create spring-boot based REST APIs to perform [AWS S3](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Welcome.html) service related operations (like: Put Object, Get Object, Delete Object) programmatically.


## Features

This project provides HTTP endpoints for the following operations:

- List Buckets - Returns a list of all buckets owned by the authenticated and authorised sender of the request.
- List Objects - Returns some or all of the objects in a bucket with each request.
- Put Object - Adds an object to a bucket. You must have WRITE permissions on a bucket to add an object to it.
- Get Object - Retrieves objects from Amazon S3. To use GET, you must have READ access to the object.
- Delete Object - Deletes an object from a bucket.


## Tech Stack

* Java 11
* Maven 3.0+
* Spring Boot >= 2.7.5 and < 3.0
* [AWS SDK for Java V2](https://github.com/aws/aws-sdk-java-v2)


## Pre-requisites

To use the AWS SDK for Java, you must have:

- AWS Account with "Root User" access.
- Enabled [AWS IAM Identity Center](https://docs.aws.amazon.com/singlesignon/latest/userguide/what-is.html) (successor to AWS Single Sign-On).
- AWS Organizations setup. 
- Properly configured User under AWS IAM Identity Center.
- Properly configured "Permission Set" with least privileges.
- AWS access portal for the User (configured under AWS IAM Identity Center).
- A suitable installation of Java 11 and Maven 3.0+.
- Temporary credentials set up in your local credentials file i.e. <home-dir>/.aws/credentials file.


## API Reference

#### Get all s3 buckets

```http
  GET /api/v1/bucket-list
```

#### Get objects list from a bucket

```http
  GET /api/v1/bucket-content
```


## Run Locally

Clone the project

```bash
  git clone https://github.com/VipulJadhav12/aws-s3-crud-springboot-rest-api.git/
```

Go to the project directory

```bash
  cd aws-s3-crud-springboot-rest-api
```

Open and edit the src/main/resources/application.properties file

```bash
  aws.region=<region-name>
  aws.bucket=<s3-bucket-name>
  aws.profile=<aws-cli-profile-name>
```

Run the above source code through mvn command line

```bash
  mvn spring-boot:run
```

Compile and Package the above source code as a JAR

```bash
  mvn clean package
```
or
```bash
  mvn clean package -Dmaven.test.skip=true
```

Run the above packaged source code through java command line. For that, go to the project's target directory

```bash
  java -jar aws-s3-crud-springboot-rest-api-0.0.1-SNAPSHOT.jar
```

By default, the API will be available at

```bash
  http://localhost:8080/api/v1/
```

