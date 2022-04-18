# Restful_Booker / Instructions



#### Codes and Tests Prepared by:

* Maimaitiyusufu Wulayinmu
* QA Test Automation Engineer
* uysdetlead@gmail.com



## Stack & Libraries

- Java 8+ SDK
- Rest - Assured
- cucumber Java
- gson  (serialization - deserialization )
- Maven - surefire - plugin    (specify runner class)
- Maven - cucumber - reporting (generate report)
- junit 5.7.0 (for Assertion)
- java faker (for creating random data)


------------
## Test RUN

Notice:

- To run  tests, use `CukesRunner`. You can run from the class
- To run tests --> `mvn verify -Dcucumber.options="--tags @wip" `
- To run failed tests, use `FailedTestRunner`.
- To choose URL go to *configuration.properties*


------------

## Test Reports Locations
- Extent reporting and logging: After the test finishes, a visual report is generated for all the executed test cases from the suite. This report can be found in the 'target' --> cucumber-html-reports                                  
- overview-failures.html
- overview-features.html
- overview-steps.html
- overview-tags.html
  
  (Right Click and Open in any Browser )




-----------------------------
## Created packages explanation 
- newBookingPOJO : initializing request body for registering new booking
- runner : to run test cases
- step_definitions : store step definitions for scenarios 
- utilities : store common methods 
- resources : store feature files 

## Added scenarios
Scenario : Get Booking Ids
Scenario : Get Booker Information
Scenario : Create Booking

Location : resources - features - Booking


## Failed test case note
- when user requests booking information with ID and creates new booking, response is : "I'm a teapot" and status code is : 418


-----------------------------
2022 April

### End

