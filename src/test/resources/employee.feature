Feature: Employee Management

  Scenario: User submits the Add Employee form with valid data
    Given the user is on the Employee List page
    When the user clicks on the "Create New Employee" button
    And the user enters the Name "Ali"
    And the user enters the Age "24"
    And the user enters the Date of Birth "1993-05-02"
    And the user enters the Email "ali@gmail.com"
    And the user selects the department Development "3"
    And the user enters the Salary "20000"
    And the user clicks the Submit button
    Then the new employee should be added successfully

  Scenario: User edits the Employee form with valid data
    Given the user is on the Employee List page
    When the user clicks on the "Edit Employee" button for the newly added employee
    And the user updates the Name to "John Smith"
    And the user clicks the Update button
    Then the employee should be edited successfully

  Scenario: User deletes the Added Employee form with valid data
    Given the user is on the Employee List page
    When the user clicks on the "Delete Employee" button for the edited employee
    And the user confirms the deletion
    Then the employee should be deleted successfully

  Scenario: User opens the index page and sees employee data
    Given the application is running
    When the user navigates to the index page
    Then the user should see employee data displayed
