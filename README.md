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

3. Test Url (for user role)  http://localhost:8080/order
>>Header"
>>Authorization:{$jwtToken from step 2}
>>output "Hello here is my order"

------------------------------------

>>http://localhost:8080/auth
>>method post
>>Content-Type application/json
>>body:{"username":"admin","password":"sample"}
>>Response should be a jwt token

>> Test(for admin role) URL http://localhost:8080/customer 
>>Header"
>>Authorization:{$jwtToken}
>>output "Hello Test Customer"

