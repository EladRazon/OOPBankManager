OOP Bank Management System
________________________________________
📋 Project Overview
This project is a Java-based banking system designed to manage various account types. It offers:
•	Account creation and management
•	Profit calculations
•	Management fees handling
•	Customer registration and tracking
The project follows Object-Oriented Programming (OOP) principles and adheres to SOLID design standards, ensuring modular, readable, and extendable code.
________________________________________
📄 Project Description
The system simulates a banking environment with diverse account types:
•	Savings Accounts
•	Business Checking Accounts
•	Mortgage Accounts
Through an intuitive menu interface, users can:
1.	Create new accounts.
2.	Add clients to accounts.
3.	Calculate annual profits.
4.	Verify VIP status for Business Checking accounts.
Additionally, the system handles errors robustly with custom exceptions for:
•	Duplicate account numbers.
•	Invalid menu choices.
________________________________________
⚙️ Program Structure
The project is modular, with each class representing an independent system component. For instance:
•	BusinessCheckingAccount encapsulates business-related features.
•	SavingsAccount focuses on savings-specific logic.
•	The Main class serves as the user interface, presenting a comprehensive menu-driven system.
________________________________________
🛠️ Programming Principles
This project is built on the SOLID principles:
✅ Single Responsibility Principle
Each class has a single responsibility. For instance, BusinessCheckingAccount is solely responsible for managing business accounts.
🔄 Open/Closed Principle
Classes are designed to be extensible (e.g., adding new account types) but not modifiable, protecting the integrity of the existing code.
🔄 Liskov Substitution Principle
Derived classes (e.g., SavingsAccount) can replace their base class (Account) seamlessly.
📏 Interface Segregation Principle
Interfaces like ManagementFeeAccount ensure that only necessary methods are implemented.
🏭 Dependency Inversion Principle
Factories (e.g., AccountsFactory) manage object creation, reducing direct dependencies between classes.
________________________________________
🏦 Key Classes and Features
🔑 Core Classes
1.	Account:
Base class for all accounts. Features include:
o	Account number
o	Manager name
o	Balance
o	Profit calculation
2.	RegularCheckingAccount:
Manages credit limits and regular checking-specific logic.
3.	BusinessCheckingAccount:
Includes:
o	Business revenue tracking
o	VIP profit calculations
4.	SavingsAccount:
Handles:
o	Deposit amounts
o	Savings durations
5.	MortgageAccount:
Calculates:
o	Loan terms
o	Monthly payments
o	Annual management fees
6.	Client:
Represents clients with:
o	Name
o	Rank (used for profit adjustments)
7.	Bank:
Centralized account and client management, including:
o	Account creation
o	Profit summaries
o	Fee calculations
8.	AccountsFactory:
Automates account creation with predefined configurations.
9.	ManagementFeeAccount:
An interface that standardizes fee management methods.
________________________________________
🔔 Custom Exceptions
•	DuplicationException: Handles duplicate account numbers.
•	InvalidChoiceException: Catches invalid menu choices.
________________________________________
🔧 Key Methods and Functionalities
Account Management
•	createInitialAccounts: Generates predefined accounts automatically.
•	addAccount: Adds a new account, ensuring unique account numbers.
•	getAllAccounts: Displays all accounts with associated clients.
Profit and Fee Calculations
•	checkProfitVIP: Calculates profits for VIP business accounts.
•	printManagementFees: Displays management fees and calculates the CEO's annual bonus.
•	getProfitAccounts: Lists accounts by descending annual profit.
Client Operations
•	registerClientToAccount: Links clients to accounts with validation.
User Interface
•	showMenu: Displays a comprehensive menu for user interaction.
•	addClientToAccount: Validates and adds new clients.
________________________________________
🚀 Usage Instructions
Running the System
1.	Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
2.	Run the Main.java file.
3.	Follow the menu prompts to perform operations.
________________________________________
Menu Structure
The system provides the following menu options:
1.	Create Initial Accounts: Generate accounts automatically.
2.	Add a New Account: Manually create an account (e.g., Regular, Business, Savings, or Mortgage).
3.	Add a Client: Register a client to an account.
4.	View All Accounts: Display all account details.
5.	Profit Accounts: Show accounts sorted by annual profits.
6.	Filter Accounts by Type: Display accounts of a selected type.
7.	Calculate Account Profit: Calculate annual profit for a specific account.
8.	Total Annual Profit: Display total bank profits.
9.	Top Profit Account: Identify the most profitable regular checking account.
10.	VIP Profit Check: Verify VIP status for business accounts.
11.	Management Fees: Display applicable fees and calculate bonuses.
________________________________________
🔍 Input Validation
The system ensures input correctness:
•	Numeric inputs (e.g., account numbers, deposit amounts) must be valid.
•	Invalid inputs trigger error messages and re-entry prompts.
________________________________________
📈 Future Expandability
The system is designed with scalability in mind:
•	Easily add new account types.
•	Introduce additional functionalities without altering existing structures.

