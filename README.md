# SpringAuthentication
##Custom Authentication Using SpringBoot, User Credentials stored/retrieved in Mongo.

####Steps to Run:
1. Install Monogo
2. Create a database Orders
3. Create a collection UserAccounts
4. Insert the following records in UserAccounts (password is sample)

>>`{
    "userName" : "admin",
    "password" : "$2a$10$T4f05olrX1IJlB4rI/JRtOqYOJh.9QXz0ZfHcSFLYjFG/Ihj0RePe",
    "email" : "test@abc.com",
    "displayName" : "Admin",
    "enabled" : "true",
    "roles" : [ 
        {
            "role" : "ROLE_ADMIN"
        }
    ]
}`
>>`{
    "userName" : "user",
    "password" : "$2a$10$T4f05olrX1IJlB4rI/JRtOqYOJh.9QXz0ZfHcSFLYjFG/Ihj0RePe",
    "email" : "user@abc.com",
    "displayName" : "User",
    "enabled" : "true",
    "roles" : [ 
        {
            "role" : "ROLE_USER"
        }
    ]
}`

5. Run the application
>>mvn exec:java -Dexec.mainClass="com.example.DemoApplication
6. Using postman  try to access the following URL
>>http://localhost:8080/auth
>>method post
>>Content-Type appliction/json
>>body:{"username":"user","password":"sample"}
>>Response should be a jwt token

7. Try the autheticated url  http://localhost:8080/order
>>Header"
>>Authorization:{$jwtToken from step 6}
>>Actual Result: :(
>>Error : 403 forbidden, this should be fully authenticated and should let the user access this api.

>>Expected Result:
>>"Hello here is my order"
-----------


