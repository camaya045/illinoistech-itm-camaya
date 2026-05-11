This app manages employees and their respective departments. It allows users to view other employees along with thier name, id, salary, and department. The app uses JSP files 
for the UI, CDI for business logic and transaction boundries, JPA for persistence, uses MySQL for the database, and a payara server for the deployment. As the admin you will 
be able to edit employees salaries and filter them by departments. In order to run the app you have to launch you Payara server using Intellij, which will then take you to the 
departments page where you will be able to navigate to all other pages. 

URLS:
Departments: http://localhost:8080/ITMD415Final-1.0-SNAPSHOT/departments.xhtml
Employees: http://localhost:8080/ITMD415Final-1.0-SNAPSHOT/employees.xhtml
EditEmployees: http://localhost:8080/ITMD415Final-1.0-SNAPSHOT/editEmployee.xhtml?empId=1001
