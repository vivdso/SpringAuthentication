# SpringAuthentication
##Custom Authentication Using SpringBoot, User Credentials stored/retrieved from custom repository.


####Steps to Run:
1. Run the application
>>mvn exec:java -Dexec.mainClass="com.example.DemoApplication

2. Using postman  try to access the following URL
>>http://localhost:8080/auth
>>method post
>>Content-Type appliction/json
>>body:{"username":"user","password":"sample"}
>>Response should be a jwt token

3. Try the autheticated url  http://localhost:8080/order
>>Header"
>>Authorization:{$jwtToken from step 6}
>>Actual Result: :(
>>Error : 403 forbidden, this should be fully authenticated and should let the user access this api.

>>Expected Result:
>>"Hello here is my order"
