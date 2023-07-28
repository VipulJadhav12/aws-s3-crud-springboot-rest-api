
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

- AWS Account with **Root User** access.
- Enabled [AWS IAM Identity Center](https://docs.aws.amazon.com/singlesignon/latest/userguide/what-is.html) (successor to AWS Single Sign-On).
- AWS Organizations setup. 
- Properly configured **User** under AWS IAM Identity Center.
- Properly configured **Permission set** with least privileges.
- **AWS access portal** for the new User (configured under AWS IAM Identity Center).
- A suitable installation of Java 11 and Maven 3.0+.
- **Temporary credentials** set up in your local credentials file i.e. <home-dir>/.aws/credentials file.


## Steps for enabling and configuring (AWS IAM Identity Center based) Single Sign-On access for the AWS SDK for Java 2.x

#### Step 1: Enable IAM Identity Center

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) as the account owner by choosing "Root User".
- Go to the [AWS IAM Identity Center Console](https://console.aws.amazon.com/singlesignon).
- Choose **Enable** under **Enable IAM Identity Center**.
- IAM Identity Center requires **AWS Organizations** to be setup under your account. While enabling the IAM Identity Center, AWS will ask you to create the same. Choose **Create AWS organization** to complete this process.
- AWS Organizations will send a "Verification Email" to the registered email associated with your AWS Root User account. Verify your email address within 24 hours.

#### Step 2: Choose your identity source

As per AWS IAM Identity Center's User Guide -

- After you enable IAM Identity Center, you must choose your identity source. The identity source that you choose determines where IAM Identity Center searches for users and groups that need single sign-on access. After you choose your identity source, you'll create or specify a user and assign them administrative permissions to your AWS account.

- Identity Center directory – When you enable IAM Identity Center for the first time, it is automatically configured with an Identity Center directory as your default identity source. This is where you create your users and groups, and assign their level of access to your AWS accounts and applications.

#### Step 3: Use the default directory and create a user in IAM Identity Center

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) as the account owner by choosing "Root User".
- Go to the [AWS IAM Identity Center Console](https://console.aws.amazon.com/singlesignon).
- Choose **Users**.
- Choose **Add user** and provide the following required information:
    * **Username** – This user name is required to sign in to the AWS access portal and can't be changed later. It must be between 1 and 100 characters.
	* **Password** – You can either send an email with the password setup instructions (this is the default option) or generate a one-time password. If you are creating an administrative user and you choose to send an email, make sure that you specify an email address that you can access.
		* **Send an email to this user with password setup instructions** – This option automatically sends the user an email addressed from Amazon Web Services, with the subject line Invitation to join AWS Single Sign-On. The email invites the user on behalf of your company to access the IAM Identity Center AWS access portal.
		* **Generate a one-time password that you can share with this user** – This option provides you with the AWS access portal URL and password details that you can manually send to the user from your email address.
	* **Email address** – The email address must be unique.
	* **Confirm email address**.
	* Enter basic details like: **First name**, **Last name**, and **Display name**.
- Choose **Next**. And review the information that you specified then choose **Add user**.
- If you kept the default option to send an email with the password setup instructions while adding new user, do the following:
	* You'll receive an email with the subject Invitation to join AWS Single Sign-On. Open the email and choose Accept invitation.
	* On the New user sign up page, enter and confirm a password, and then choose Set new password.

#### Step 4: Create a permission set that applies least-privilege permissions in IAM Identity Center

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) as the account owner by choosing "Root User".
- Go to the [AWS IAM Identity Center Console](https://console.aws.amazon.com/singlesignon).
- In the IAM Identity Center navigation pane, under **Multi-account permissions**, choose **Permission sets**.
- Choose Create permission set.
	* On the **Select permission set type** page, in the Permission set type section, choose Predefined permission set.
	* In the **Policy for predefined permission set** section, choose **PowerUserAccess** and choose **Next**.

The default settings grant full access to AWS services and resources using the PowerUserAccess predefined permission set. The predefined PowerUserAccess permission set uses the PowerUserAccess AWS managed policy.

- On the Specify permission set details page, keep the default settings and choose **Next**. The default setting limits your session to one hour.
- On the Review and create page, confirm the following:
	* **For Step 1: Select permission set type**, the AWS managed policy is PowerUserAccess.
	* **For Step 2: Define permission set details**, the permission set name is PowerUserAccess.
	* Choose **Create**.

#### Step 5: Set up AWS account access for the new user

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) as the account owner by choosing "Root User".
- Go to the [AWS IAM Identity Center Console](https://console.aws.amazon.com/singlesignon).
- In the navigation pane, under Multi-account permissions, choose AWS accounts.
- Select the check box next to the AWS account to which you want to assign PowerUserAccess access.
- Choose **Assign users or groups**.
- **For Step 1: Select users and groups**, click on the Users tab and select the user to whom you want to grant the permissions. Choose Next.
- **For Step 2: Select permission sets**, under Permission sets, select the PowerUserAccess permission set. Choose Next.
- **For Step 3: Review and Submit**, review the selected user and permission set. Choose **Submit**.

When you set up account access for the new user, IAM Identity Center creates a corresponding IAM role. This role, which is controlled by IAM Identity Center, is created in the relevant AWS account, and the policies specified in the permission set are attached to the role.

#### Step 6: Sign in to the AWS access portal and retrieve temporary credentials

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) as the account owner by choosing "Root User".
- Go to the [AWS IAM Identity Center Console](https://console.aws.amazon.com/singlesignon).
- In the navigation pane, choose **Dashboard**.
- On the Dashboard page, under Settings summary, copy the AWS access portal URL.
- Open a separate browser, paste the AWS access portal URL and press Enter.
- If you're using the default Identity Center directory as your identity source, sign in by using the user name that you specified when you created the user and the new password that you specified for the user.
- After you are signed in, an AWS account icon appears in the portal.
- When you select the AWS account icon, the account name, account ID, and email address associated with the account appear.
- Choose the name of the account to display the PowerUserAccess permission set, and select the **Command line or programmatic access** link to the right of PowerUserAccess.
- Under **Get credentials for PowerUserAccess**, select the desired OS tab and follow the given instructions from the given options to access AWS resources programmatically or from the AWS CLI.

Before running the above spring-boot (or any other) application that accesses AWS services, you need an active AWS access portal session in order for the SDK to use IAM Identity Center authentication to resolve credentials. Make sure you're always signed-in to the AWS access portal before running an application.

## Steps for enabling and configuring AWS S3 Bucket permissions

#### Step 1: Retrieve the IAM role created by IAM Identity Center

When you set up account access for the new user, IAM Identity Center creates a corresponding IAM role. This role, which is controlled by IAM Identity Center, is created in the relevant AWS account, and the policies specified in the permission set are attached to the role.

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) of the account which was used to assign PowerUserAccess access to the new IAM Identity Center user.
- Go to the **IAM** dashboard.
- Under **Access management**, choose **Roles**.
- Select the role starting with: **_AWSReservedSSO_PowerUserAccess_XXXX_**
- Copy the **ARN** of this role.

#### Step 2: Configure AWS S3 Bucket permissions using Bucket policy

- Sign in to the [AWS Management Console](https://console.aws.amazon.com/) of the account which was used to assign PowerUserAccess access to the new IAM Identity Center user.
- Go to the **S3** dashboard.
- In the S3 navigation pane, choose **Buckets**.
- Select the bucket to which you want to assign the permissions.
- Under **Permissions** tab, go to **Bucket policy** and **Edit** it with the following policy:

```http
  {
    "Version": "2012-10-17",
    "Id": "ExamplePolicy01",
    "Statement": [
        {
            "Sid": "ExampleStatement01",
            "Effect": "Deny",
            "Principal": "*",
            "Action": "s3:*",
            "Resource": [
                "arn:aws:s3:::<s3-bucket-name>/*",
                "arn:aws:s3:::<s3-bucket-name>"
            ],
            "Condition": {
                "ArnLike": {
                    "aws:PrincipalArn": "arn:aws:iam::<account-id>:role/aws-reserved/sso.amazonaws.com/<region>/AWSReservedSSO_PowerUserAccess_*"
                }
            }
        }
    ]
}
```

Make sure you _paste_ the same **ARN** under the **Condition** section of the above policy which was _copied_ in the previous step.


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

