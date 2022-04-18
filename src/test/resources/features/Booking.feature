 Feature: Verify Booking Functionality

   Background: Create Token
     Given user uses token

   @smoke
   Scenario: Get Booking Ids
     When user send get request to GetBookingIds
     Then status code should be 200

   @smoke
   Scenario: Get Booker Information
     And user send get request to GetBookingIds
     When user send get request to GetBooking with ID from GetBookingIds
     Then status code should be 418

   @smoke
   Scenario: Create Booking
     When user send post request to CreateBooking
     Then status code should be 418
