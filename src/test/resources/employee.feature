Feature: Add Employee

  Scenario: User submits the Add Employee form with valid data
    Given the Employee Management application is running
    When the user opens the Employee List page
    And the user clicks on the "Create New Employee" button
    And the user enter the Name "ali"
    And the user enter the Age "24"
    And the user enter the Date of Birth "1993-05-02"
    And the user enter the Email "ali@gmail.com"
    And the user select the department Development "3"
    And the user enter the Salary "20000"
    And the user click the Submit button 
    Then the new employee should be added successfully
    

  Scenario: User opens the index page and sees employee data
    Given the application is running
    When the user navigates to the index page
    Then the user should see employee data displayed