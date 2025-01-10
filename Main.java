// Robert Ifraimov 209326263 and Elad Razon 207296617

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            String choice = getValidChoice(scanner);

            switch (choice.toLowerCase()) {
                case "e":
                    running = false; // Exit the loop
                    System.out.println("Exiting the Bank Management System. Goodbye!");
                    break;
                case "1":
                    System.out.println(bank.createInitialAccounts());
                    break;
                case "2":
                    addNewAccountManually(bank, scanner);
                    break;
                case "3":
                    System.out.println("Adding a client to an account.");
                    addClientToAccount(bank, scanner);
                    break;
                case "4":
                    System.out.println("List of All Accounts:\n");
                    System.out.println(bank.getAllAccounts());
                    break;
                case "5":
                    System.out.println("Displaying accounts with annual profit (excluding savings accounts) in descending order by profit.\n");
                    System.out.println(bank.getProfitAccounts());
                    break;
                case "6":
                    showAccountTypes(Bank.getAccountTypes());
                    int accountTypeChoice = getValidIntInput(scanner, "Enter account type (1-4) to display: (Example of input: 3) ", 1, Bank.getAccountTypes().length);
                    String accountType = Bank.getAccountTypes()[accountTypeChoice - 1];
                    System.out.println(bank.getAccountsByType(accountType));
                    break;
                case "7":
                    System.out.println("Enter the account number to show annual profit: (Example of input: 6) ");
                    int accountNumber = getValidIntInput(scanner, "Account number: ", 1, Integer.MAX_VALUE);
                    System.out.println(bank.getAccountProfit(accountNumber));
                    break;
                case "8":
                    System.out.println("Displaying total annual profit from all relevant accounts.");
                    System.out.println(bank.getTotalAnnualProfit());
                    break;
                case "9":
                    System.out.println("Displaying top checking account by profit.");
                    System.out.println(bank.getTopCheckingAccountByProfit());
                    break;
                case "10":
                    System.out.println("Enter the business account number for VIP profit check: (Example of input: 6) ");
                    int businessAccountNumber = getValidIntInput(scanner, "Account number: ", 1, Integer.MAX_VALUE);
                    String vipProfitStatus = bank.checkBusinessVIPProfit(businessAccountNumber);
                    System.out.println(vipProfitStatus);
                    break;
                case "11":
                    System.out.println("Management Fees for Accounts:");
                    System.out.println(bank.printManagementFees());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n============ Bank Management System Menu ============");
        System.out.println("Please choose an option:");
        System.out.println("------------------------------------------------------");
        System.out.println("E/e- Exit");
        System.out.println(" 1.  Create initial accounts automatically");
        System.out.println(" 2.  Add a new account manually");
        System.out.println(" 3.  Add a client to an existing account");
        System.out.println(" 4.  Display all accounts");
        System.out.println(" 5.  Display accounts with annual profit");
        System.out.println(" 6.  Display accounts of a specific type");
        System.out.println(" 7.  Calculate annual profit for a specific account");
        System.out.println(" 8.  Display total annual profit of the bank");
        System.out.println(" 9.  Display top checking account by profit");
        System.out.println("10.  Display VIP profit status for a business account");
        System.out.println("11.  Print all management fees");
        System.out.println("------------------------------------------------------");
    }

    private static int getAccountNumber(Scanner scanner, Bank bank) throws DuplicationException {
        String choice;
        while (true) {
            System.out.print("Do you want to generate an account number automatically? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no")) {
                break; // Exit loop if valid input
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        int accountNumber;
        if (choice.equals("yes")) {
            int lastAccountNumber = 0;
            for (int i = 0; i < bank.getAccountCount(); i++) {
                if (bank.getAccounts()[i] != null) {
                    lastAccountNumber = bank.getAccounts()[i].getAccountNumber();
                }
            }
            //The next account number is the last account number + 1
            accountNumber = lastAccountNumber + 1;

            while (Account.isAccountNumberUsed(accountNumber)) {
                accountNumber++;
            }
        } else {
            while (true) {
                try {
                    System.out.print("Enter account number: (Example of input: 6) ");
                    accountNumber = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer

                    if (accountNumber < 0) {
                        System.out.println("Account number cannot be negative. Please enter a positive number.");
                        continue;
                    }

                    // Check if the entered account number is already in use
                    if (Account.isAccountNumberUsed(accountNumber)) {
                        throw new DuplicationException(accountNumber);
                    } else {
                        break; // Valid account number entered
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }
        return accountNumber; // Return the unique account number
    }

    private static void addNewAccountManually(Bank bank, Scanner scanner) {
        System.out.println("Adding a new account manually.\n");
        System.out.println("Choose account type to add: ");
        System.out.println("1. Regular Checking Account");
        System.out.println("2. Business Checking Account");
        System.out.println("3. Mortgage Account");
        System.out.println("4. Savings Account");

        int accountType;
        while (true) {
            try {
                System.out.print("Enter account type (1-4):  (Example of input: 1) ");
                accountType = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                if (accountType < 1 || accountType > 4) {
                    System.out.println("Invalid account type. Please choose a number between 1 and 4.\n");
                    continue; // Repeat if the input is out of range
                }
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.\n");
                scanner.next(); // Clear the invalid input
            }
        }

        boolean success = false;

        while (!success) {
            try {
                switch (accountType) {
                    case 1:
                        addRegularCheckingAccount(bank, scanner);
                        break;
                    case 2:
                        addBusinessCheckingAccount(bank, scanner);
                        break;
                    case 3:
                        addMortgageAccount(bank, scanner);
                        break;
                    case 4:
                        addSavingsAccount(bank, scanner);
                        break;
                }
                success = true; // If the account is added successfully, exit the loop
            } catch (DuplicationException e) {
                System.out.println("\nError creating account: " + e.getMessage());
                System.out.println("Please try adding the account again.\n"); // Prompt the user to try again
            }
        }
    }

    private static String getManagerName(Scanner scanner) {
        while (true) {
            System.out.print("Enter manager name (Example of input: David): ");
            String managerName = scanner.nextLine().trim();

            if (managerName.isEmpty()) {
                System.out.println("Manager name cannot be empty. Please enter a valid name.");
            } else if (!isValidName(managerName)) {
                System.out.println("Manager name can only contain letters and spaces. Please enter a valid name.");
            } else {
                return managerName; // Valid name entered
            }
        }
    }

    private static String getClientName(Scanner scanner) {
        while (true) {
            System.out.print("Enter client name (Example of input: David): ");
            String clientName = scanner.nextLine().trim();

            if (clientName.isEmpty()) {
                System.out.println("Client name cannot be empty. Please enter a valid name.");
            } else if (!isValidName(clientName)) {
                System.out.println("Client name can only contain letters and spaces. Please enter a valid name.");
            } else {
                return clientName; // Valid name entered
            }
        }
    }

    private static void addRegularCheckingAccount(Bank bank, Scanner scanner) throws DuplicationException {
        String managerName = getManagerName(scanner);
        int accountNumber = getAccountNumber(scanner, bank);
        double creditLimit = getValidDoubleInput(scanner, "Enter credit limit: (Example of input: 1500) ");

        Account account = new RegularCheckingAccount(accountNumber, bank.getBankNumber(), managerName, creditLimit);
        bank.addAccount(account);

        System.out.println("Regular Checking Account added successfully.");

        // Add client to the new account
        addFirstClientToAccount(account, scanner);
    }

    private static void addBusinessCheckingAccount(Bank bank, Scanner scanner) throws DuplicationException {
        String managerName = getManagerName(scanner);
        int accountNumber = getAccountNumber(scanner, bank);
        double creditLimit = getValidDoubleInput(scanner, "Enter credit limit: (Example of input: 1500) ");
        double businessRevenue = getValidDoubleInput(scanner, "Enter business revenue: ");

        Account account = new BusinessCheckingAccount(accountNumber, bank.getBankNumber(), managerName, creditLimit, businessRevenue);
        bank.addAccount(account);

        System.out.println("Business Checking Account added successfully.");

        // Add client to the new account
        addFirstClientToAccount(account, scanner);
    }

    private static void addMortgageAccount(Bank bank, Scanner scanner) throws DuplicationException {
        String managerName = getManagerName(scanner);
        int accountNumber = getAccountNumber(scanner, bank);
        double originalMortgageAmount = getValidDoubleInput(scanner, "Enter original mortgage amount: (Example of input: 150000) ");
        double monthlyPayment = getValidDoubleInput(scanner, "Enter monthly payment: (Example of input: 1500) ");
        int years = getValidIntInput(scanner, "Enter mortgage years: (Example of input: 20) ", 1, 100);

        Account account = new MortgageAccount(accountNumber, bank.getBankNumber(), managerName, originalMortgageAmount, monthlyPayment, years);
        bank.addAccount(account);

        System.out.println("Mortgage Account added successfully.");

        // Add client to the new account
        addFirstClientToAccount(account, scanner);
    }

    private static void addSavingsAccount(Bank bank, Scanner scanner) throws DuplicationException {
        String managerName = getManagerName(scanner);
        int accountNumber = getAccountNumber(scanner, bank);
        double depositAmount = getValidDoubleInput(scanner, "Enter deposit amount: (Example of input: 50000) ");
        int years = getValidIntInput(scanner, "Enter savings years: (Example of input: 5) ", 1, 100); // Restricting years range

        Account account = new SavingsAccount(accountNumber, bank.getBankNumber(), managerName, depositAmount, years);
        bank.addAccount(account);

        System.out.println("Savings Account added successfully.");

        // Add client to the new account
        addFirstClientToAccount(account, scanner);
    }

    // Function to prompt for client details and add the first client to the account
    private static void addFirstClientToAccount(Account account, Scanner scanner) {
        System.out.println("Enter details for a client to associate with this account. Note: Each account must have at least one client.");

        String clientName = getClientName(scanner);
        int clientRank = getValidIntInput(scanner, "Enter client rank (0-10): (Example of input: 5) ", 0, 10);

        // Create the client and add it to the account
        Client client = new Client(clientName, clientRank);
        account.addClient(client);

        System.out.println("Client added successfully to " + account.getAccountType() + " #" + account.getAccountNumber());
    }

    private static void addClientToAccount(Bank bank, Scanner scanner) {
        int accountNumber;

        if (bank.getAccountCount() == 0) {
            System.out.println("No accounts available. Please create an account first.");
            return;
        }

        while (true) {
            System.out.print("Enter account number to add a client to: (Example of input: 5) ");
            // Check if the input is a valid integer
            if (scanner.hasNextInt()) {
                accountNumber = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                // Check if the account exists
                if (bank.findAccountByNumber(accountNumber) == null) {
                    System.out.println("Account number " + accountNumber + " does not exist. Please enter a valid account number.");
                } else {
                    break; // Exit the loop if the account exists
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Use the getClientName function to obtain a valid client name
        String clientName = getClientName(scanner);

        int clientRank = getValidIntInput(scanner, "Enter client rank (0-10): (Example of input: 5) ", 0, 10); // Validate client rank

        // Use the Bank class to add the client
        String resultMessage = bank.registerClientToAccount(accountNumber, clientName, clientRank);
        System.out.println(resultMessage);
    }

    private static String getValidChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Your choice: ");
                String input = scanner.next().trim();

                // Check if the input is "e" or "E" for exit
                if (input.equalsIgnoreCase("e")) {
                    return input;
                }

                // Convert input to an integer and check if it is within the valid range
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 11) {
                    return input; // Return valid input
                } else {
                    throw new InvalidChoiceException("Invalid input. Please enter a number between 1 and 11 or 'E'/'e' to exit. (Example of input: 5) ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 11 or 'E'/'e' to exit. (Example of input: 5) ");
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage()); // Display error message
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);

                if (!scanner.hasNextDouble()) {
                    scanner.next(); // Clear the invalid input
                    throw new InvalidChoiceException("Invalid input. Please enter a valid number.");
                }

                double value = scanner.nextDouble();
                scanner.nextLine(); // Clear the line

                if (value <= 0) {
                    throw new InvalidChoiceException("The value must be a positive number. Please try again.");
                }
                return value; // Return the valid input

            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage()); // Display the error message
            }
        }
    }

    private static int getValidIntInput(Scanner scanner, String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);

                if (!scanner.hasNextInt()) {
                    scanner.next(); // Clear the invalid input
                    throw new InvalidChoiceException("Invalid input. Please enter a valid integer.");
                }

                int value = scanner.nextInt();
                scanner.nextLine(); // Clear the line

                if (value < min || value > max) {
                    throw new InvalidChoiceException("The value must be between " + min + " and " + max + ". Please try again.");
                }

                return value; // Return the valid input

            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage()); // Display the error message
            }
        }
    }

    private static boolean isValidName(String managerName) {
        for (char c : managerName.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false; // Return false immediately if an invalid character is found
            }
        }
        return true; // Return true if all characters are valid
    }

    private static void showAccountTypes(String[] accountTypes) {
        System.out.println("Choose account type to display:");
        for (int i = 0; i < accountTypes.length; i++) {
            System.out.println((i + 1) + ". " + accountTypes[i]);
        }
    }

}
