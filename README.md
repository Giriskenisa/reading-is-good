Please run following commands(Be sure that Maven is installed on your system):

-mvn clean install

I've used Atlas:Mongo Db so you dont need to setup mongo db on your local system. 

And then you can run this command to run the server:
 mvn spring-boot:run     

You can find my post man collections under the reading-is-good directory you can import this to postman. 

First of all please run the "get-token" request copy the result(token) and in the other requests paste to Authentication>Bearer Token field.

