# MessengerServiceEmployee

# Localhost:8080

# Problem

A business needs a software that manages all the business logic, for this you need customers to interact with the application to be able to generate services with the company, you need to have a record and control of employees and allow the management of processes relating to the services offered by the company, you need a package management to have control over shipments and inventory, in addition to having a more reliable delivery system, and you need to be able to manage shipments to finish with your business logic.

# Description

This repository is a microservice about application MessengerService, in this microservice, you will find all management about Employee, When somebody want to use this
application, The Employee must to register in the system, in this microservice, Employees can register, update their information, get their information and delete their 
susbscription.

#Technologies
The project was built in: 

-Java
-SpringBoot
-SpringJPA
-SpringSecurity
-MySQL
-JUnit
-Swagger
-CI/CD

# Funcionalities
[POST] Create: An Employee can register in the database.
RequestBody:
{
        "dni": 789,
        "nameEmployee": "Juan",
        "lastNameEmployee": "Manuel",
        "phoneEmployee": "3333333",
        "emailEmployee": "juanma@gmail.com",
        "residencyAddressEmployee": "Carrera 787 # 23",
        "cityLocationEmployee": "Bogota",
        "lengthServiceEmployee": "18 years",
        "rhEmployee": "O+",
        "typeEmployee": "DRIVER"
}

[PUT] Update: An Employee can update their information.
Param: The param need an Employee registered in the database: {"dni":789}
RequestBody:
{
      
        "nameEmployee": "Juan",
        "lastNameEmployee": "Manuel",
        "phoneEmployee": "3333333",
        "emailEmployee": "juanma@gmail.com",
        "residencyAddressEmployee": "Carrera 787 # 23",
        "cityLocationEmployee": "Bogota",
        "lengthServiceEmployee": "18 years",
        "rhEmployee": "O+",
        "typeEmployee": "DRIVER"
}

[DELETE] Delete: An Employee can delete their susbscription.
Param: The param need a Employee registered in the database: {"dni":789}

[GET] Check Employee: An Employee can consult their information in the database.
Param: The param need an Employee registered in the database: {"dni":789}

![employeemsservice](https://user-images.githubusercontent.com/119947948/234170522-0c12938a-7d56-4af9-9263-bcea8ba5779f.png)
