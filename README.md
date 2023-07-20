## Project Information

This project is a **Phone Numbers REST API** built using **Spring Boot** with **JDK 17**. It provides a set of endpoints to manage phone numbers and their associations with customers. The application uses an in-memory **H2 database** for data storage and includes Swagger for API documentation.

### Key Features:
1. **Retrieve All Phone Numbers**: Fetch a list of all phone numbers stored in the system.
2. **Retrieve Phone Numbers by Customer**: Fetch all phone numbers associated with a specific customer.
3. **Activate a Phone Number**: Update the status of a phone number to active.

### Technical Details:
- **Backend Framework**: Spring Boot 2.7
- **Database**: H2 (in-memory)
- **API Documentation**: Swagger UI
- **Testing**: JUnit 5 and Mockito
- **Build Tool**: Maven


#### It is a Spring Boot application using JDK 17 and Spring Boot 2.7 version

### REST API Overview

## Get All Phone Numbers
* Endpoint: `/api/phoneNumbers`
* Method: GET
* Description: Retrieve all phone numbers.
* Response: List of phone numbers.

## Get all phone numbers of a single customer
* Endpoint: `/api/phoneNumbers/customers/{customerId}`
* Method: GET
* Description: Retrieve all phone numbers associated with a specific customer.
* Parameters: customerId - ID of the customer. 
*  Response: List of phone numbers associated with the customer.

## Activate a phone number
* Endpoint: `/api/phoneNumbers/{phoneNumberId}/activate`
* Method: POST
* Description: Activate a phone number.
* Path Parameter: Phone number to activate.
* Response: Updated phone number details.

## Build and Run the Project
* Build the project using Maven
* `mvn clean install`
* Run the project using the Spring Boot Maven plugin
* `mvn spring-boot:run`
* Import the Postman collection from project Path
`https://github.com/amarpreetbhatia/phonenumbers/blob/main/postman_collection/telstra_phonenumbers_api.postman_collection.json`
* View Swagger Specs API
`http://localhost:8080/swagger-ui.html`
* Data Model used
![img.png](umlscripts/img.png)
* Context Diagram
![img_1.png](umlscripts/img_1.png)
