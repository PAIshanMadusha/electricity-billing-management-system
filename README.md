# Electricity Billing Management System

## Overview
The Electricity Billing Management System is a Java-based application designed to streamline the process of managing electricity bills for customers. The system supports two user roles: **Admin** and **Customer**, each with distinct features and functionalities.

## Backend Connection with MySQL
The system backend connects to a MySQL database for storing and retrieving customer data, bills, and payment statuses.

### Email Validation
The system includes email validation logic with regex patterns to ensure only valid email addresses can be added.

### Number Validation
The system validates numeric fields for unit consumption to ensure only numbers are accepted.

## Features

### Admin Features
- **Create New Customers**: Add new customer records with essential details.
- **Search and View Customer Details**: Search for customers by meter number or name to view their details.
- **View Deposit Details**: Check customer deposit records, including units consumed, billing month, total bill amount, and payment status (paid/not paid).
- **Calculate Bills**: Enter the number of units consumed and select the billing month to calculate bills.
- **Utility Access**: Open Calculator and Notepad directly from the system.
- **Exit**: Safely log out and exit the system.

### Customer Features
- **View and Update Personal Details**
- **View Bill Details**
- **Pay Bill**: Redirects the customer to the payment site.
- **Generate Bills**: Generate bill reports for specific months.
- **Utility Access**: Open Calculator and Notepad directly from the system.
- **Exit**: Safely log out and exit the system.

## MySQL Database Setup
- **Database Name**: `electricity_bill_system`
- **Username**: `root`
- **Password**: `""` (empty password)

### Importing the Database
1. Open XAMPP and start the MySQL service.
2. Open phpMyAdmin and select the "Import" option.
3. Choose the file `electricity_bill_system.sql` and import it.

### Updating Database Configuration
If there is any issue with the database connection, navigate to `electricity/billing/management/system/Db.java` and update the username or password as required.

## Account Creation Instructions

### Creating a Customer Account
1. Log in as an Admin. You cannot create a customer account directly using the signup option.
2. After logging in as an Admin, use the option to create a new customer.
3. Copy the generated meter number and fill in the other required details.
4. Go back to the signup option.
5. Select "Customer" in the user type option, paste the meter number, and complete the remaining fields.
6. Use the provided username and password to log in as a Customer.

## Sample Login Information

### Admin Login
- **Username**: `admin`
- **Password**: `ishan123`
- **UserType**: `Admin`
- **AdminCode**: `admin123` (required for signup)

### Customer Login
- **Username**: `text1`
- **Password**: `text1`
- **UserType**: `Customer`

## Here are some screenshots of the system
---
<p align="center">
  <img src="https://github.com/user-attachments/assets/243b970a-1550-4d8e-9d81-9665b19fb305" alt="Screenshot 1" width="280">&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/cc7eabd8-a9f8-4933-a036-b1fd7ba7b976" alt="Screenshot 2" width="280">
  <br><br>
  <img src="https://github.com/user-attachments/assets/34547fff-c846-435c-9c54-2c03bc45e724" alt="Screenshot 3" width="280">&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/d5ca8862-1b11-4ca1-8064-6c90f0fb8268" alt="Screenshot 4" width="280">
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/cfc9cf72-612d-4268-8420-23dd4945ee93" alt="Screenshot 5" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/f16a3c70-cf72-434c-a769-592d1a4a6228" alt="Screenshot 6" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/a226ea98-c6a3-4552-8a8a-70998a83f0b3" alt="Screenshot 7" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/34d7483c-ca5d-4ba2-9af3-a4d0918da2ea" alt="Screenshot 8" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/2e45071a-903f-4ee9-8f55-6fa1f60c67f1" alt="Screenshot 9" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/592f903b-2b4a-4461-becf-39b275da34b6" alt="Screenshot 10" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/9ce6d28a-384a-49fa-9ebc-59aaeda5703f" alt="Screenshot 11" width="600">
  <br><br>
  <img src="https://github.com/user-attachments/assets/67d131c1-bf93-430c-993c-05896186d3f4" alt="Screenshot 12" width="600">
  <br>
</p>
## Additional Notes!
- Ensure XAMPP is running before starting the application.
