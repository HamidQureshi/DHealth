# ActiveDapp

A POC of Health Application built over Activeledger. This application shows the sustainability of Activeledger when used for the developement of applications.

It currently has the following features.

-       Adding doctors and patients to the network

-       Patients can upload reports

-       Reports can be shared with multiple doctors

-       Reports can be viewed on downloaded from the application

This project has 4 components.

-      Backend Services

-      Smart Contracts

-      Web Frontend

-      Android Application
      

The Web frontend and Android application are not directly interacting with the Activeledger. Common backend services are imlpemented which acts as intermediary between the
application and Activeledger. 


The Applications have login and registration use cases as well. The backend services uses security framework to generate tokens for each user which later are used in transactions.
A local database is used by the backend services to store credentials and the identity of the user. Everytime the user logs in , the identity and the token generated is returned,
which is later used to form and send other transactions.

Android application uses the Android SDK and the Web Application uses the Node SDK to form the transactions. Private and public keys are currently locally stored, which are used to
sign each transaction.

Two smart contracts are currently used for this application which are uploaded on the testnet as well. The smart contracts contains the logic of adding users, uploading reports and
sharing of reports between the users.



