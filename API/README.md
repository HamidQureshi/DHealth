# Health APP API

Underlying API for the health App.



## Description

Health App is using this API as an intermidiary to interact with the Activeledger.
It uses Java SDK to form transactions. API is also using H2 database to store user information. Flyway is used for database migration.

Spring security is also added in API. Once you have registered or logged in using your credentials, you will recieve a token 
in response header. This token is used when sending all other transactions. The token needs to be appended in request header for all transactions.

## Build
API is configured to run in 5555 port. This can be changed from properties file.

- gradle build
- gradle bootRun

## API Documentation
API documentation and all the endpoints can be found using the below endpoint
#### - /swagger-ui.html
