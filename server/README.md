## Its Spring boot based service which enables its client to manage vaccination appointment.

# Server-side technologies
Spring Boot - 2.0.5.RELEASE
JDK - 1.8 or later
Spring Framework - 5.0.8 RELEASE
Hibernate - 5.2.17.Final
Spring Data JPA - 2+

# Front end technologies
Angular 9
Bootstrap 3/4
npm- 6.9.0
JQuery
Node js 10+

# Tools
Maven - 3.2+
IDE - Intellij (STS) // Spring boot API development
Visual Studio Code  // Angular App development
Angular CLI 11

# Important commands for Server
1. to build the backend server execute the below command.
   mvn clean install
   
2. to execute all the test cases execute the below command
   mvn test
   
3. to start the server execute the below command,
   java -jar target/server-0.0.1-SNAPSHOT.jar 

# Seeding Data for Demo purpose
After successful start of server , execute below shell script to seed some data for demo purpose,
$ bash seed-data.sh
 
# Important commands for Client
  1. Execute the below command to add angular devkit to build the application
     npm install --save-dev @angular-devkit/build-angular
     
  2. Execute the below command to start client application which is being developed in Angular.
     ng -serve --open
     
  3. The above command will automatically open the web client in the browser URL- 
     http://localhost:4200/branches
  
 
 