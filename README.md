# oauth_api_rest_mongodb

Simple springboot oauth server, with spring security and maven, with storage in mongodb. Include login and register through RESTful web services. 

This project is based on these libraries:
- https://github.com/caelcs/spring-mvc-utils
- https://github.com/caelcs/base-auth2-server
On these links is more information and documentation about the available functionalities of the library. 


This project include more implementations to work with Restful web services and get a bearer token using the password and refresh-token grant-type; check the token validity and register a new user. It works great on a microservices architecture and mobile applications.

## Database settings on mongodb
To get this working with a mongodb server, it's necessary to create a database with the data set in the application.yml file. By default, the database connection setting are working with this data: 

db.createUser(
  {
    user: "testUser",
    pwd: "testPass",
    roles: [ { role: "readWrite", db: "test_db" } ]
  }
)

And will be necessary to create this collections: 

- mongoClientDetails
- user

And include at least one document with the next structure: 

**Collection: user:**

´{
    "_id" : "testUser",
    "password" : "$2a$10$8KmNZd6st1dCvtrU2pu0heTHTndDJDSZjW7M2btMlDbZm7vaWNNhu",
    "userUUID" : LUUID("274a6071-6687-2b1b-d87c-2279d07f5685"),
    "authorities" : [ 
        {
            "role" : "ROLE_USER",
            "_class" : "org.springframework.security.core.authority.SimpleGrantedAuthority"
        }
    ],
    "accountNonExpired" : true,
    "accountNonLocked" : true,
    "credentialsNonExpired" : true,
    "enabled" : true,
    "_class" : "uk.co.caeldev.springsecuritymongo.domain.User"
}´

**Collection: mongoClientDetails**

´{
    "_id" : "turist",
    "clientSecret" : "$2a$10$j6Xnq6JFmn/8kM8jcSPNNeqHzFgYQjsf3P/Ok8Xp/0X96iA7e3Cri",
    "scope" : [
        "read",
        "write"
    ],
    "resourceIds" : [
        "oauth2-resource"
    ],
    "authorizedGrantTypes" : [
        "refresh_token",
        "authorization_code",
        "password",
        "client_credentials"
    ],
    "registeredRedirectUris" : [
        "http://localhost:9080"
    ],
    "authorities" : [
        {
            "role" : "ROLE_CLIENT",
            "_class" : "org.springframework.security.core.authority.SimpleGrantedAuthority"
        }
    ],
    "accessTokenValiditySeconds" : 30000.0,
    "refreshTokenValiditySeconds" : 30000.0,
    "additionalInformation" : {},
    "autoApproveScopes" : [
        ""
    ],
    "_class" : "uk.co.caeldev.springsecuritymongo.domain.MongoClientDetails"
}´

_PS. It's my first complete oauth server, so it's possible to find misconfigurations; and probabily, there are so many ways to improve this project. So, i don't recommend to use it as it is on this repository, but to take it as a basic example to understand the way oauth server works when using a different db server and how to integrate the services and repositories that are necessary to implement in that case. Don't forget to review the caelcs' source code to get a better overview of the server._
