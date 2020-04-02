# shopping-list-app
PS! If you run the apk the first time it takes time for Heroku to boot up the server!

## The goal of the application

This application is made as a test assignment for Mobilab. The application should make everyday shopping easier by enabling the user to list down items to buy. While shopping, the application should also help to track items that are already collected.

## Server/db/backend
### Running it on you own computer locally
Firstly you must set up the server on your own system (https://github.com/rebekkabb/shopping-list-app-server), the same instructions are there too.
1) Make sure an instance of Postgre is running
2) Define the right env variables (ones in application.properties)
```
SHOPPING_LIST_APP_DB_URL - url of the host containing port and database name
SHOPPING_LIST_APP_DB_USERNAME
SHOPPING_LIST_APP_DB_PASSWORD
```
3) Initialize the tables in the database using the `db/init.sql` file. 
*NB:* Make sure schema the data is initialized in is the default schema for the database
4) The server can be ran with `gradlew.bat bootRun`

#### Link to the one I already deployed on Heroku: https://desolate-tundra-54863.herokuapp.com/

## Application
The apk for the application is provided in the releases.

To run the application for development:
1) Have the right server addres in RealApi.kt
2) Connect your phone to your computer or use a vm to run the application
3) Enjoy

## How to run tests for the application
I explain my reasoning on the lack of tests in the last sections. You can run one espresso test I managed to create by making your way to the file AddingATestList.kt and run it there.

## A few sentences about the overall architecture of the application.
From the five options of platforms I chose the option of Native Android application written in Kotlin. The front end was developed in Android Studio. I decided to make my own server to handle the endpoints, because I have done that before and did not come to the idea of outsourcing earlier. The backend is written in Kotlin and uses Spring boot, the database in use is Postgre. I have already deployed the server to Heroku so when you install the apk it is already connected to the database.

## Any other instructions and notes you have (1.A Java usage reasoning, etc)
There are not many tests due to my lack of knowledge in this field. I did not know where to start with testing the system, picking out the important bits and the unimportant bits. In university I have written very few tests and most projects do not require tests.  This is a skill I would definitely want to improve. 

#### PS! I did not expect that putting my server up on Heroku would be this slow :( 
