   $(document).ready(function() {
	   
	   	
	   		
            // Fetch and display all employees on page load
            fetchAllEmployees();
            
            $("#redirectToResults").click(function() {
		        window.location.href = "results";
		    });
		    
		    
		    

            // Fetch and display employees by name when search button is clicked
             $("#searchForm").submit(function(event) {
				 event.preventDefault();
		        var name = $("#searchName").val(); // Get the entered search name
		        searchEmployeesByName(name); // Call the function to perform the search
		    });

            // Add employee using AJAX when add button is clicked
            $("#addEmployee").click(function() {
				var department = {
					did: $("#edepartment").val(),
				};
				
                var employee = {
                    ename: $("#ename").val(),
                    eage: $("#eage").val(),
                    edob: $("#edob").val(),
                    email: $("#email").val(),
                    edepartment: department,
                    esalary: $("#esalary").val(),
                    // Add other properties as needed
                };
                addEmployee(employee);
            });
            
            // Modify the "Save Changes" button click event
			$("#saveChangesButton").click(function() {
			    // Get the updated employee data from the modal
			    var employeeId = $("#editEmployeeModal #eid").val();
			    
			   	var department = {
					did: $("#editEmployeeModal #edepartment").val(),
				};
			    var updatedEmployee = {
			        ename: $("#editEmployeeModal #ename").val(),
			        eage: $("#editEmployeeModal #eage").val(),
			        edob: $("#editEmployeeModal #edob").val(),
			        email: $("#editEmployeeModal #email").val(),
			       	edepartment: department,
			        esalary: $("#editEmployeeModal #esalary").val()
			    };
			    
			     console.log(updatedEmployee);
			
			    // Send a PUT request to update the employee details
			    $.ajax({
			        type: "PUT", // Use the appropriate HTTP method (e.g., PUT)
			        url: "/api/v1/employee/employee/update/" + employeeId, // Replace with your API endpoint
			        contentType: "application/json",
			        data: JSON.stringify(updatedEmployee),
			        success: function() {
			            // Optionally, you can update the UI to reflect the changes
			            // For example, you can update the table row with the new data
			            // and close the modal
			            fetchAllEmployees(); // Update the employee table
			            $("#editEmployeeModal").modal("hide"); // Close the modal
			        },
			        error: function(error) {
			            console.error("Error updating employee:", error);
			        }
			    });
			});
			
			// delete employee confirmation 
			
			$("#confirmDeleteButton").click(function() {
		    var employeeId = $("#deleteEmployeeModal").data("employeeId");
		
		    // Send a DELETE request to delete the employee from the database
		    $.ajax({
		        type: "DELETE",
		        url: "/api/v1/employee/employee/delete/" + employeeId, // Replace with your API endpoint
		        success: function() {
		            fetchAllEmployees(); // Update the employee table
		            $("#deleteEmployeeModal").modal("hide"); // Close the modal
		        },
		        error: function(error) {
		            console.error("Error deleting employee:", error);
			        }
			    });
			});


            function fetchAllEmployees() {
                $.get("/api/v1/employee/employees", function(data) {
                    var tBody = $("#employeeTableBody");
                    tBody.empty();
                    data.forEach(function(employee) {
                    	// Create a new table row
                        var row = $("<tr>");

                        // Create table cells for employee data
                        var id = $("<td>").text(employee.eid);
                        var name = $("<td>").text(employee.ename);
                        var age = $("<td>").text(employee.eage);
                        var dob = $("<td>").text(employee.edob);
                        var email = $("<td>").text(employee.email);
                        var department = $("<td>").text(employee.edepartment ? employee.edepartment.dname : "");
                        var salary = $("<td>").text(employee.esalary);
                       	
                       	var action = $("<td>");
                       	var editButton = $("<button>").text("Edit").addClass("btn btn-primary")
                       								.attr("id", "editButton").attr("type","button").attr("data-toggle","modal").attr("data-target", "#editEmployeeModal");
                       	var deleteButton = $("<button >").text("Delete").addClass("btn btn-danger");
                       	
                       
                       	editButton.click(function(){
							   
							 	var employeeId = $(this).closest("tr").find("td:eq(0)").text(); 
							    var employeeName = $(this).closest("tr").find("td:eq(1)").text(); 
							    var employeeAge = $(this).closest("tr").find("td:eq(2)").text(); 
							    var employeeDob = $(this).closest("tr").find("td:eq(3)").text(); 
							    var employeeEmail = $(this).closest("tr").find("td:eq(4)").text(); 
							    var employeeDepartment = $(this).closest("tr").find("td:eq(5)").text(); 
							    var employeeSalary = $(this).closest("tr").find("td:eq(6)").text(); 
							
							    $("#editEmployeeModal #eid").val(employeeId);
							    $("#editEmployeeModal #ename").val(employeeName);
							    $("#editEmployeeModal #eage").val(employeeAge);
							    $("#editEmployeeModal #edob").val(employeeDob);
							    $("#editEmployeeModal #email").val(employeeEmail);
							    
							    selectDepartmentOption(employeeDepartment);
							    
							    $("#editEmployeeModal #esalary").val(employeeSalary);
							    
							  	});
						
						deleteButton.click(function(){
								var employeeId = $(this).closest("tr").find("td:eq(0)").text(); 
	
							    $("#deleteEmployeeModal").data("employeeId", employeeId);
							
							    $("#deleteEmployeeModal").modal("show");
							   
						   });
						   
						action.append(editButton, deleteButton);
						   
                        // Add more cells for other data if needed

                        // Append cells to the row
                        row.append(id, name, age, dob, email, department, salary, action);

                        // Append the row to the table body
                        tBody.append(row);
                        
                    });
                });
            }

           
		  
		
		    // Function to search employees by name using AJAX
		    function searchEmployeesByName(name) {
		        $.get("/api/v1/employee/employees/search?name=" + name, function(data) {
		            var tBody = $("#employeeTableBody");
		            tBody.empty(); // Clear previous search results
		
		            // Loop through the fetched data and update the HTML
		            data.forEach(function(employee) {
						 console.log("Employee:", employee);
		                // Create a new list item element for each search result
		                 var row = $("<tr>");

                        // Create table cells for employee data
                         var id = $("<td>").text(employee.eid);
                        var name = $("<td>").text(employee.ename);
                        var age = $("<td>").text(employee.eage);
                        var dob = $("<td>").text(employee.edob);
                        var email = $("<td>").text(employee.email);
                        var department = $("<td>").text(employee.edepartment ? employee.edepartment.dname : "");
                        var salary = $("<td>").text(employee.esalary);
                       	
                       	var action = $("<td>");
                       	var editButton = $("<button>").text("Edit").addClass("btn btn-primary")
                       								.attr("id", "editButton").attr("type","button").attr("data-toggle","modal").attr("data-target", "#editEmployeeModal");
                       	var deleteButton = $("<button >").text("Delete").addClass("btn btn-danger");
                       	
                       	editButton.click(function(){
							   
							    var employeeId = $(this).closest("tr").find("td:eq(0)").text(); 
							    var employeeName = $(this).closest("tr").find("td:eq(1)").text(); 
							    var employeeAge = $(this).closest("tr").find("td:eq(2)").text(); 
							    var employeeDob = $(this).closest("tr").find("td:eq(3)").text(); 
							    var employeeEmail = $(this).closest("tr").find("td:eq(4)").text(); 
							    var employeeDepartment = $(this).closest("tr").find("td:eq(5)").text(); 
							    var employeeSalary = $(this).closest("tr").find("td:eq(6)").text(); 
							
							    $("#editEmployeeModal #eid").val(employeeId);
							    $("#editEmployeeModal #ename").val(employeeName);
							    $("#editEmployeeModal #eage").val(employeeAge);
							    $("#editEmployeeModal #edob").val(employeeDob);
							    $("#editEmployeeModal #email").val(employeeEmail);
							    
							    selectDepartmentOption(employeeDepartment);
							    
							    $("#editEmployeeModal #esalary").val(employeeSalary);
							   
						   });
						
						deleteButton.click(function(){
							
								var employeeId = $(this).closest("tr").find("td:eq(0)").text(); 
	
							    $("#deleteEmployeeModal").data("employeeId", employeeId);
							
							    $("#deleteEmployeeModal").modal("show");
							   
						   });
						   
						action.append(editButton, deleteButton);
						  
                        row.append(id, name, age, dob, email, department, salary, action);

                        tBody.append(row); 
                        				
		            });
		        });
		    }


            function addEmployee(employee) {
				console.log("Employee:", employee);
                $.ajax({
                    type: "POST",
                    url: "/api/v1/employee/employee/add",
                    contentType: "application/json",
                    data: JSON.stringify(employee),
                    success: function() {
                        fetchAllEmployees();
                    },
                    error: function(error) {
                        console.error("Error adding employee:", error);
                    }
                });
            }
            
            
            function selectDepartmentOption(selectedDepartment) {
			    var departmentDropdown = $("#edepartment");
			
			    // Loop through the options and select the one that matches the department
			    departmentDropdown.find("option").each(function() {
			        if ($(this).text() === selectedDepartment) {
			            $(this).prop("selected", true);
			        }
			    });
			}
            
            
            
            
            
            
            
            
        });