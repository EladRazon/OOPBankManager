# **OOP Bank Management System**

---

## **📋 Project Overview**
This project is a Java-based banking system designed to manage various account types. It offers:  
- Account creation and management  
- Profit calculations  
- Management fees handling  
- Customer registration and tracking  

The project follows Object-Oriented Programming (OOP) principles and adheres to SOLID design standards, ensuring modular, readable, and extendable code.

---

## **📄 Project Description**
The system simulates a banking environment with diverse account types:  
- Savings Accounts  
- Business Checking Accounts  
- Mortgage Accounts  

Through an intuitive menu interface, users can:  
- Create new accounts.  
- Add clients to accounts.  
- Calculate annual profits.  
- Verify VIP status for Business Checking accounts.  

Additionally, the system handles errors robustly with custom exceptions for:  
- Duplicate account numbers.  
- Invalid menu choices.

---

## **⚙️ Program Structure**  
📄 **[View the complete Class Diagram (PDF)](Class%20diagram.pdf)**  

The project is modular, with each class representing an independent system component. Below are some key examples:  
- **BusinessCheckingAccount**: Encapsulates business-related features.  
- **SavingsAccount**: Focuses on savings-specific logic.  
- **Main**: Serves as the user interface, presenting a comprehensive menu-driven system.

---

## **🛠️ Programming Principles**  
This project is built on the SOLID principles:  
- ✅ **Single Responsibility Principle**: Each class has a single responsibility. For instance, BusinessCheckingAccount is solely responsible for managing business accounts.  
- 🔄 **Open/Closed Principle**: Classes are designed to be extensible (e.g., adding new account types) but not modifiable, protecting the integrity of the existing code.  
- 🔄 **Liskov Substitution Principle**: Derived classes (e.g., SavingsAccount) can replace their base class (Account) seamlessly.  
- 📏 **Interface Segregation Principle**: Interfaces like ManagementFeeAccount ensure that only necessary methods are implemented.  
- 🏭 **Dependency Inversion Principle**: Factories (e.g., AccountsFactory) manage object creation, reducing direct dependencies between classes.

---

## **🏦 Key Classes and Features**

### 🔑 **Core Classes**  
- **Account**: Base class for all accounts. Features include:  
  - Account number  
  - Manager name  
  - Balance  
  - Profit calculation  

- **RegularCheckingAccount**: Manages credit limits and regular checking-specific logic.  
- **BusinessCheckingAccount**: Includes:  
  - Business revenue tracking  
  - VIP profit calculations  
- **SavingsAccount**: Handles:  
  - Deposit amounts  
  - Savings durations  
- **MortgageAccount**: Calculates:  
  - Loan terms  
  - Monthly payments  
  - Annual management fees  
- **Client**: Represents clients with:  
  - Name  
  - Rank (used for profit adjustments)  
- **Bank**: Centralized account and client management, including:  
  - Account creation  
  - Profit summaries  
  - Fee calculations  
- **AccountsFactory**: Automates account creation with predefined configurations.  
- **ManagementFeeAccount**: An interface that standardizes fee management methods.

---

## **🔔 Custom Exceptions**
- **DuplicationException**: Handles duplicate account numbers.  
- **InvalidChoiceException**: Catches invalid menu choices.

---

## **🔧 Key Methods and Functionalities**

### **Account Management**  
- **createInitialAccounts**: Generates predefined accounts automatically.  
- **addAccount**: Adds a new account, ensuring unique account numbers.  
- **getAllAccounts**: Displays all accounts with associated clients.  

### **Profit and Fee Calculations**  
- **checkProfitVIP**: Calculates profits for VIP business accounts.  
- **printManagementFees**: Displays management fees and calculates the CEO's annual bonus.  
- **getProfitAccounts**: Lists accounts by descending annual profit.  

### **Client Operations**  
- **registerClientToAccount**: Links clients to accounts with validation.  

### **User Interface**  
- **showMenu**: Displays a comprehensive menu for user interaction.  
- **addClientToAccount**: Validates and adds new clients.

---

## **🚀 Usage Instructions**

### **Running the System**  
1. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).  
2. Run the Main.java file.  
3. Follow the menu prompts to perform operations.  

---

### **Menu Structure**  
The system provides the following menu options:  
1. **Create Initial Accounts**: Generate accounts automatically.  
2. **Add a New Account**: Manually create an account (e.g., Regular, Business, Savings, or Mortgage).  
3. **Add a Client**: Register a client to an account.  
4. **View All Accounts**: Display all account details.  
5. **Profit Accounts**: Show accounts sorted by annual profits.  
6. **Filter Accounts by Type**: Display accounts of a selected type.  
7. **Calculate Account Profit**: Calculate annual profit for a specific account.  
8. **Total Annual Profit**: Display total bank profits.  
9. **Top Profit Account**: Identify the most profitable regular checking account.  
10. **VIP Profit Check**: Verify VIP status for business accounts.  
11. **Management Fees**: Display applicable fees and calculate bonuses.

---

## **🔍 Input Validation**  
The system ensures input correctness:  
- Numeric inputs (e.g., account numbers, deposit amounts) must be valid.  
- Invalid inputs trigger error messages and re-entry prompts.

---

## **📈 Future Expandability**  
The system is designed with scalability in mind:  
- Easily add new account types.  
- Introduce additional functionalities without altering existing structures.
