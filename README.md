![IronHack Logo](https://s3-eu-west-1.amazonaws.com/ih-materials/uploads/upload_d5c5793015fec3be28a63c4fa3dd4d55.png)

# Unit6 Microservices

<p align="center"><strong> Cristian Saavedra</strong></p>

* [Goal](#goal)

* [Methodology](#methodology)

* [Tools](#tools)

* [Security](#security)

* [How it works](#how-it-works)

* [Documentation](#documentation)


## <a name="goal"></a>Goal

As a final project for this java development enterprise bootcamp I have developed an E-commerce application based on the idea of genetic testing for animals.

Pawprints is a business centered on the elaboration and distribution of genetic kits for animal pets (INIT_KIT, RESULT_KIT)*, with a focus on dogs, cats and horses.
Centered firstly on the study of various markers, the analysis of Pawprints turns complex genetic analysis into dynamical and user-friendly reports. 
The owner will know not only data about the breed and possible genetic diseases of their pet, but also will have a study of its genome and how this affects traits such as its character, health and behavior. In addition, guidelines for the best care of the pet are provided according to the combined results of the different tests. In this sense, Pawprints is not simply dedicated to offering specific tests, but rather aims at a holistic approach that allows the owner-pet bond to grow stronger, and also ensure better care of your pet.

The genetic testing is a three phase process:

1. First: recollect a sample of the animal material (send a INIT_KIT).
2. Once users have recollected the sample, the INIT_KIT is sent back so the sample can be analyzed. Status is changed (RECEIVED_INIT_KIT).
3. Once the sample arrives, it's tested and a final kit is sent to the client with a gift and the resulting genetic report. Status is changed to CLOSED, however it can be updated to REOPEN if needed.

In order to develop the idea, I have used Java Spring-Boot framework to develop the back-end based on microservices and netflix cloud, and Angular framework along with bootstrap to front-end.

*: INIT_KIT: a practical kit for sampling. RESULT_KIT: final product in the shape of a report.

## <a name="methodology"></a>Methodology

To apply the microservices architecture I decided to break down the project into 5 services:
- Edge service
- User service
- Cart Service
- Material Service
- Client Service

**User service contains all the user data and its protected using JWT token.**

## <a name="tools"></a>Tools used
- IntelliJ
- Spring Boot
- H2 DB
- Mongo (Mongo Atlas)
- Postman
- Swagger (API Document with HTML)
- Netflix Cloud
- JWT token
- Angular
- Bootstrap
- Char.js
- Heroku
- Firebase


## <a name="security"></a>Security

* I implemented BasicAuth security on the edge service for classic credential identification. There are two users to start with:

````
user: admin
password: admin

user: client
password: client

````

* User microservice has JWT based auth. Token is generated by the edge-service. This way it is ensured that user microservice is not unprotected and cannot be accessed externally. Even if you know the route for the user service you will not be able to access it directly. You need to get the token or access it through the edge-service.

## <a name="how-it-works"></a>How it works

1- Running the application on [cloud](#cloud)

### <a name="cloud"></a> Running it on the Cloud

In order to develop this project I have deployed at **heroku** all the microservices (5 in total), and the front has been deployed at **firebase**.

Follow the next indications to test the project:

1. You should open all of following links:

| Microservice |  Link        |                
| ------       | ------------ |
| Edge         | [Edge-Service](https://edge-pawprints-service.herokuapp.com/)             | 
| User         | [User-Service](https://user-pawprint-service.herokuapp.com/)            |        
| Material     | [Material-Service](https://material-pawprint-service.herokuapp.com/)         |        
| Cart         | [Cart-Service](https://cart-pawprint-service.herokuapp.com/)             |
|Comments     | [Comment-Service](http://client-comments-pawprints-serv.herokuapp.com/)     |
| Front-End       | [Front-Deployed](https://pawprints-final.web.app) |

2. [![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/0d6596bbccc3114aa71f).

3. Or, test by checking out the front clicking [here](https://pawprints-final.web.app) and test all functionality.


Functionality: 

In terms of benefits, there are two economic fluxes.

1. Incomes: they are defined by the amount of purchases made in the shop.

2. Expenses: in terms of kits there are two product types, each of them associated with a certain cost depending on the stock necessary to produce them: 

- INIT_KIT: sample boxes, test sticks, sterilized bags, brochures.

- PRODUCT_KIT: shipping boxes, product brochure, gift

When the stock drops below a certain critical amount kits can no longer be produced. In order to continue producing kits all materials must be restocked first (making a provider order).

Once the provider order is made, the admin can check when the order has been fulfilled, and then update the actual stock numbers: immediately, the value of purchase_units pending to arrive is reduced and added into total_remaining_units.

User functionality:

1. Third party view: as an unlogged user you can see all products (Health and Linage), the comparison between the three products (Pawprints merged with features from both health and linage) and also register as an user. To access into the functionality you must be registered.

2. Registered user: as a logged user you can buy the products, register your pets (only one registered animal is necessary to successfully complete the purchase), and watch all your orders' status. You can also add any comment and watch other users comments. 

3. Admin user: as a admin user you can:

- Watch reports as: incomes by date (sum of all incomes), expenses by date (expenses made when you buy products to make kits), order of clients group by status and report of poroduct sold.

- Update client order status and make kits to send them.

- Make provider orders (products to make kits)

- Accept provider orders updating the status to "FULL". When the status is updated, automatically the back-end updates the actual status of the products to make kits.

## <a name="documentation"></a>Documentation

* For cloud use the collection created in Postman where all routes are already created [![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/0d6596bbccc3114aa71f)

* **Swagger**:The documentation for all requests is available in [Swagger](https://edge-pawprints-service.herokuapp.com/swagger-ui.html#/) by entering the following link (https://edge-pawprints-service.herokuapp.com/swagger-ui.html#/).

If any doubts do not hesitate to contact me! I look forward to your feedback.

## Developer 
<table>
<td align="center"><a href="https://github.com/ccsi923"><img src="https://avatars2.githubusercontent.com/u/65124499?s=400&v=4" width="100px;" alt="Cristian avatar"/><br/><sub><b>Cristian Saavedra</b></sub></a><br/><a href="https://github.com/ccsi923"></a>
</table>

