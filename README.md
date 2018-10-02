# YourPhone

Implementation of a 3-tier Java web application for a telephone company. 

## Functionality Overview

This application has the following features:
  * There are three types of users, admins, clients and sellers.
  * Admins have access to all the details about users and programs.
  * Admins have the ability to create new users and new programs. 
  * Sellers can create/view and modify client account.
  * Clients can view their history call log and their debt(if there is any).
  * Users can login to the application using their username and password.

## Prerequisites

  * Eclipse or another Java IDE
  * MySQL
  * Apache Tomcat
  * MySQL JDBC driver - Connector/J

## Getting Started

1. Clone or download repository
2. Import project into Eclipse
3. Import the sample into Eclipse using File -> Import -> Maven -> Existing Maven Projects option.
4. Right click on the project and go to Properties > Project Facets and select Dynamic Web Module (if not already selected).
5. Deploy the sample into Liberty server. Right click on the servlet sample and select Run As -> Run on Server option. Find and select the Liberty profile server and press Finish.
6. Navigate to: http://localhost:8080/YourPhone