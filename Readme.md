RouteX - Comprehensive AI Agent Prompt Guide

Agent Instructions: You are an expert Enterprise Java Developer and Software Architect. I am building "RouteX," a Logistics and Fleet Management system using Java, JavaFX, and SQL Server. You must strictly follow a 3-Tier Layered Architecture (UI, Business Logic, Database).

You must apply OOP Principles, GRASP (Controller, Creator, Information Expert), and GOF Design Patterns (Singleton, Factory, Strategy, Observer) and document their usage in the comments.

The project belongs to two developers (Aimen and Mohsin). We are implementing exactly 6 Use Cases to meet assignment constraints (3 per member, max 1 CRUD each).

Aimen's UCs: UC01 (Login/Auth), UC02 (Manage Inventory - CRUD), UC04 (Generate Shipment Order - Operation/Observer trigger).

Mohsin's UCs: UC11 (Manage Users - CRUD), UC06 (Assign Vehicle to Shipment - Operation), UC08 (Consolidate Routes - Algorithm/Operation).

Please execute the following steps sequentially. Wait for my command to proceed to the next step. DO NOT output the whole project at once.

Phase 1: Project Setup & Configuration

Prompt to AI:
"Initialize a standard Maven project structure for a JavaFX + SQL Server application.

Create the pom.xml with dependencies for JavaFX (controls, fxml), SQL Server JDBC driver (mssql-jdbc), and jBCrypt (for password hashing).

Create the package structure:

com.routex.main

com.routex.domain (Entities)

com.routex.dao (Database Access)

com.routex.service (Business Logic)

com.routex.ui (JavaFX Controllers)

Create a DatabaseConnection class in the dao package using the Singleton GOF Pattern to ensure only one database connection pool exists."

Phase 2: Domain Layer (Entities)

Prompt to AI:
"Generate the Java Domain/Entity classes in com.routex.domain mapping exactly to the provided SQL schema.

Include: User, Warehouse, InventoryItem, ShipmentOrder, Vehicle, Shipment, AuditLog.

Use correct Java data types (e.g., UUID for UNIQUEIDENTIFIER, LocalDateTime for DATETIME, enum for statuses like 'ACTIVE', 'PENDING_APPROVAL').

Include standard Constructors, Getters, Setters, and toString() methods.

Enforce Encapsulation (private fields)."

Phase 3: Data Access Layer (DAOs)

Prompt to AI:
"Create the DAO (Data Access Object) classes in com.routex.dao using the Factory GOF pattern (e.g., DaoFactory to get instances).
Implement the following DAOs with standard CRUD and specific query methods using PreparedStatement to prevent SQL Injection:

UserDao: authenticateUser(email, hash), createUser(), updateUser(), deleteUser(), getAllUsers().

InventoryDao: addItem(), updateQuantity(), getItemsBelowThreshold().

ShipmentOrderDao: createOrder(), getPendingOrders(), updateOrderStatus().

VehicleDao: getAvailableVehicles(minCapacity), updateVehicleStatus().

AuditLogDao: logEvent(actorId, action, entityType).
Note: Ensure all DB calls use the DatabaseConnection Singleton."

Phase 4: Business Logic Layer (Services) - Aimen's Use Cases

Prompt to AI:
"Create the Business Logic Service classes in com.routex.service. Apply the Information Expert (GRASP) principle. Add extensive comments explaining the OOP principles used.
Implement Aimen's 3 Use Cases:

AuthService (UC01): Method login(email, password). Must fetch user from UserDao, verify password via jBCrypt, log the event via AuditLogDao, and maintain a static active session (Singleton).

InventoryService (UC02): Methods addInventoryItem(), updateInventoryStock().

ShipmentOrderService (UC04): Method checkThresholdsAndGenerateOrders(). Use the Observer GOF Pattern here; when inventory drops below the threshold, automatically generate a ShipmentOrder (Status: PENDING_APPROVAL) and log it."

Phase 5: Business Logic Layer (Services) - Mohsin's Use Cases

Prompt to AI:
"Continue building the Business Logic Service classes in com.routex.service for Mohsin's Use Cases:

UserService (UC11): Methods registerUser(), deactivateUser(). Prevent deletion if only one SYSTEM_ADMIN is left. Log all actions.

DispatchService (UC06): Method assignVehicleToShipment(orderId, vehicleId, driverId). Ensure atomic transaction logic (start transaction, update Vehicle to 'IN_TRANSIT', create Shipment, update Order to 'DISPATCHED', commit transaction).

RouteConsolidationService (UC08): Method consolidateOrders(List<UUID> orderIds). Use the Strategy GOF Pattern for route consolidation. Create an interface ConsolidationStrategy and a concrete class WeightBasedConsolidation that groups orders to fit a single vehicle's max capacity."

Phase 6: UI Layer (JavaFX FXML & Controllers)

Prompt to AI:
"Generate the JavaFX User Interface components in com.routex.ui. Create .fxml files and their corresponding Controller.java classes using the MVC Pattern.

Login.fxml & LoginController: Form with Email/Password. On success, route to appropriate dashboard based on Role.

AdminDashboard.fxml & AdminController: View to handle UC11 (Manage Users - TableView and Form).

InventoryDashboard.fxml & InventoryController: View to handle UC02 (Stock Table) and UC04 (Button/Trigger to Generate Orders).

DispatchDashboard.fxml & DispatchController: View to handle UC06 (Assign Vehicle - ComboBoxes for Orders and Vehicles) and UC08 (Consolidate Button).
Ensure all controllers inject the Service layer objects. Show JavaFX Alert dialogs for success/failure messages."

Phase 7: Main Application & Integration

Prompt to AI:
"Finally, create the Main.java class in com.routex.main.

Initialize the JavaFX Application.

Load the Login.fxml scene first.

Provide a SceneManager utility class to easily switch between dashboards (Admin, Inventory, Dispatch) based on the logged-in user's role.

Add a final pass review comment block in the code stating how Layered Architecture, GRASP, and GOF patterns were integrated successfully as per the assignment requirements."
