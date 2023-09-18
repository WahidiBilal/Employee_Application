<h1>Employee Management</h1>

<p>Employee Management is a web application developed using Java, Maven, and the Spring Boot framework. It offers a RESTful API for employee management and includes a straightforward HTML frontend for user interaction. Docker is employed to containerize the application, simplifying deployment across various environments</p>


| Badge                                                     | URL                                                                                           |
|-----------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| Sonarcloud Quality                                        | [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| SonarCloud Quality Gates                                  | [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=WahidiBilal_Employee_Application)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Reliability Rating                                        | [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Duplicated Lines (%)                                      | [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Vulnerabilities                                           | [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Bugs                                                      | [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=bugs)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Security Rating                                           | [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Maintainability Rating                                    | [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Code Smells                                               |[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=WahidiBilal_Employee_Application&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=WahidiBilal_Employee_Application) |
| Coverage Status                                           | <a href='https://coveralls.io/github/WahidiBilal/Employee_Application'><img src='https://coveralls.io/repos/github/WahidiBilal/Employee_Application/badge.svg' alt='Coverage Status' /></a> |

<h2>Getting Started</h2>

<p>To run the application, follow the steps below:</p>

<ol>
  <li>Clone the repository: <code>git clone https://github.com/WahidiBilal/Employee_Application.git </code></li>
  <li>Import the project into Eclipse:
    <ul>
      <li>Open Eclipse and select <strong>File -> Import</strong>.</li>
      <li>Choose <strong>Maven -> Existing Maven Projects</strong> and click <strong>Next</strong>.</li>
      <li>Browse to the directory where you cloned the repository and select the project.</li>
      <li>Click <strong>Finish</strong> to import the project into Eclipse.</li>
    </ul>
  </li>
  <li>In <a href="https://www.eclipse.org/downloads/packages/" target="_blank">Eclipse</a>, right-click on the project and select <strong>Run As -> Maven Install</strong> to build the project and download the required dependencies.</li>
  <li>Once the Maven build is successful, you can proceed with running the application using Docker.</li>
  <li>Build docker image and run docker container: <code>docker-compose up </code></li>
  <li>The application will now be accessible at <a href="http://localhost:8081" target="_blank">http://localhost:8081</a>.</li>
</ol>

<h2>API Endpoints</h2>

<p>The REST API provides the following endpoints:</p>

<ul>
  <li><strong>GET localhost:8081/api/v1/employee/employees/</strong>: Retrieves a list of all contacts.</li> 
  <li><strong>GET localhost:8081/api/v1/employee/employee/{id}</strong>: Retrieves a specific employee by ID.</li>
  <li><strong>POST localhost:8081/api/v1/employee/employee/add</strong>: Creates a new employee.</li>
  <li><strong>GET localhost:8081/api/v1/employee/employees/search</strong>: Search employee by Name.</li>  
  <li><strong>DELETE localhost:8081/api/v1/employee/employee/delete/{id}</strong>: Deletes a employee.</li>
</ul>

<p>Please refer to the API documentation included in the project for detailed information on the request and response formats.</p>

<h2>HTML Frontend</h2>

<p>The HTML frontend allows users to interact with the API using a user-friendly interface. It is accessible at <a href="http://localhost:8081">http://localhost:8081</a>.</p>

<h2>Project Structure</h2>

<p>The project follows a standard Maven structure. Key files and directories include:</p>

<ul>
    <li><strong>src/main/java</strong>: Contains the Java source code.</li>
    <li><strong>src/main/resources</strong>: Contains application properties and static files.</li>
    <li><strong>src/test/java</strong>: Contains unit tests, integration tests, and end-to-end tests.</li>
    <li><strong>src/test/resources</strong>: Contains test properties and test scenarios like <code>employee.feature</code> for end-to-end testing.</li>
    <li><strong>pom.xml</strong>: Contains Maven configuration and project dependencies.</li>
</ul>
