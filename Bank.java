import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Bank {
    private Account[] accounts;
    private static final String[] accountTypes = {
            "Regular Checking Account",
            "Business Checking Account",
            "Mortgage Account",
            "Savings Account"
    };
    private int accountCount = 0;

    public Bank() {
        accounts = new Account[2];
    }

    // Method for creating initial accounts automatically
    protected String createInitialAccounts() {
        StringBuilder sb = new StringBuilder();
        AccountsFactory factory = new AccountsFactory();
        try {
            Account[] initialAccounts = factory.createAccounts();
            for (Account account : initialAccounts) {
                addAccount(account);
            }

            sb.append(initialAccounts.length).append(" initial accounts created successfully.");
        } catch (DuplicationException e) {
            sb.append("Error creating accounts: ").append(e.getMessage());
        }
        return sb.toString();
    }

    // Method to add an account to the array, expanding it if necessary
    protected void addAccount(Account account) throws DuplicationException {
        if (accountCount >= accounts.length) {
            expandAccountsArray();
        }
        accounts[accountCount] = account;
        accountCount++;
    }

    // Method to expand the accounts array by increasing its size by 2
    private void expandAccountsArray() {
        Account[] newAccounts = new Account[accounts.length + 2];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        accounts = newAccounts;
    }

    protected int getAccountCount() {
        return accountCount;
    }
    protected Account[] getAccounts() {
        return accounts;
    }

    protected static String[] getAccountTypes() {
        return accountTypes;
    }

    // Method to display all accounts
    protected String getAllAccounts() {
        StringBuilder sb = new StringBuilder();

        if (accountCount == 0) {
            sb.append("No accounts available.");
            return sb.toString();
        }

        Arrays.sort(accounts, 0, accountCount, Comparator.comparingInt(Account::getAccountNumber));

        for (int i = 0; i < accountCount; i++) {
            Account account = accounts[i];
            if (account != null) {
                sb.append(account).append("\n");

                // Display the list of clients
                Client[] clients = account.getClients();
                if (clients != null && clients.length > 0) {
                    sb.append("Clients:\n");
                    for (Client client : clients) {
                        if (client != null) {
                            sb.append("- ").append(client).append("\n");
                        }
                    }
                } else {
                    sb.append("No clients associated with this account.\n");
                }
            } else {
                sb.append("Account at index ").append(i).append(" is null.\n");
            }
            // Add a newline between accounts only if it's not the last account
            if (i < accountCount - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    protected Account findAccountByNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null; // Account not found
    }

    protected int getBankNumber() {
        return 1; // Default bank number, can be customized
    }

    // Method to register a client to an existing account
    protected String registerClientToAccount(int accountNumber, String clientName, int clientRank) {
        StringBuilder sb = new StringBuilder();

        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            sb.append("Account number ").append(accountNumber).append(" does not exist.");
            return sb.toString();
        }

        for (Client client : account.getClients()) {
            if (client != null && client.getName().equalsIgnoreCase(clientName)) {
                sb.append("A client with the name '")
                        .append(clientName)
                        .append("' already exists for this account.");
                return sb.toString();
            }
        }

        Client newClient = new Client(clientName, clientRank);
        account.addClient(newClient);

        sb.append("Client added successfully to account number ").append(accountNumber);
        return sb.toString();
    }

    protected String getProfitAccounts() {
        StringBuilder sb = new StringBuilder();
        // Define a predicate class for filtering accounts
        Predicate<Account> filterPredicate = new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                // Check if the account is not null and is not an instance of SavingsAccount
                return !(account instanceof SavingsAccount) && account instanceof ProfitAccount;
            }
        };

        // Apply the filter using the predicate class
        Account[] profitAccounts = Arrays.stream(accounts)
                .filter(filterPredicate)
                .toArray(Account[]::new);

        if (profitAccounts.length == 0) {
            sb.append("No profit accounts available.");
            return sb.toString();
        }

        // Sort by profit in descending order using Comparator class
        Arrays.sort(profitAccounts, new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                return Double.compare(((ProfitAccount) a2).getProfit(), ((ProfitAccount) a1).getProfit());
            }
        });

        // Get the accounts
        for (Account account : profitAccounts) {
            if (account != null) {
                sb.append(account).append("\n");
                sb.append("Profit: ").append(String.format("%.2f", ((ProfitAccount) account).getProfit())).append("₪\n");

                // Display the clients associated with the account
                Client[] clients = account.getClients();
                if (clients != null && clients.length > 0) {
                    sb.append("Clients:\n");
                    for (Client client : clients) {
                        if (client != null) {
                            sb.append("- ").append(client.getName())
                                    .append(" (Rank: ").append(client.getRank()).append(")\n");
                        }
                    }
                } else {
                    sb.append("No clients associated with this account.\n");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    protected String getAccountsByType(String accountType) {
        StringBuilder sb = new StringBuilder();
        // Define a predicate class for filtering accounts by type
        Predicate<Account> filterPredicate = new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                // Check if the account is not null and matches the specified account type
                return account != null && account.getAccountType().equalsIgnoreCase(accountType);
            }
        };

        // Apply the filter using the predicate class
        Account[] filteredAccounts = Arrays.stream(accounts)
                .filter(filterPredicate)
                .toArray(Account[]::new);

        // Sort by account number in ascending order using Comparator class
        Arrays.sort(filteredAccounts, new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                return Integer.compare(a1.getAccountNumber(), a2.getAccountNumber());
            }
        });

        // Get the accounts
        if (filteredAccounts.length == 0) {
            sb.append("No accounts found for the specified type: ").append(accountType);
            return sb.toString();
        }

        for (Account account : filteredAccounts) {
            sb.append(account.getAccountType()).append(" ").append(account).append("\n");

            // Display the clients associated with the account
            Client[] clients = account.getClients();
            if (clients != null && clients.length > 0) {
                sb.append("Clients:\n");
                for (Client client : clients) {
                    if (client != null) {
                        sb.append("- ").append(client.getName())
                                .append(" (Rank: ").append(client.getRank()).append(")\n");
                    }
                }
            } else {
                sb.append("No clients associated with this account.\n");
            }
        }
        return sb.toString();
    }

    protected String getAccountProfit(int accountNumber) {
        StringBuilder sb = new StringBuilder();
        Account account = findAccountByNumber(accountNumber);

        if (account == null) {
            sb.append("Account number ").append(accountNumber).append(" does not exist.");
        } else if (account instanceof ProfitAccount) { // Check if account implements ProfitAccount
            double profit = ((ProfitAccount) account).getProfit(); // Cast to ProfitAccount
            sb.append("Annual profit for account number ").append(accountNumber)
                    .append(" is: ").append(String.format("%.2f", profit)).append("₪");
        } else {
            sb.append("Account number ").append(accountNumber).append(" does not have an associated profit.");
        }

        return sb.toString();
    }

    protected String getTotalAnnualProfit() {
        StringBuilder sb = new StringBuilder();
        double totalProfit = 0.0;
        boolean hasRelevantAccounts = false;

        for (Account account : accounts) {
            if (account instanceof ProfitAccount && !(account instanceof SavingsAccount)) {
                totalProfit += ((ProfitAccount) account).getProfit(); // Cast to ProfitAccount
                hasRelevantAccounts = true;
            }
        }

        if (hasRelevantAccounts) {
            sb.append("Total annual profit of the bank: ").append(String.format("%.2f", totalProfit)).append("₪");
        } else {
            sb.append("No relevant accounts with annual profit have been added to the system yet.");
        }
        return sb.toString();
    }

    protected String getTopCheckingAccountByProfit() {
        CheckingAccount topAccount = null;
        double maxProfit = 0.0;

        for (Account account : accounts) {
            if (account instanceof CheckingAccount) {
                double profit = ((ProfitAccount) account).getProfit(); // Cast to ProfitAccount
                if (profit > maxProfit) {
                    maxProfit = profit;
                    topAccount = (CheckingAccount) account;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (topAccount != null) {
            sb.append("Top checking account by profit: ")
                    .append(topAccount.getAccountType())
                    .append(" (Account #").append(topAccount.getAccountNumber())
                    .append(") - Profit: ")
                    .append(String.format("%.2f", maxProfit))
                    .append("₪");
        } else {
            sb.append("No checking accounts found with a profit contribution.");
        }

        return sb.toString();
    }

    // Method to retrieve management fees for all applicable accounts and calculate the CEO's annual bonus.
    // Returns a string containing the management fee details for each applicable account and the total bonus for the CEO.
    protected String printManagementFees() {
        StringBuilder sb = new StringBuilder();
        double totalBonus = 0.0; // Variable to accumulate the total bonus for the CEO

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof ManagementFeeAccount) {
                ManagementFeeAccount feeAccount = (ManagementFeeAccount) accounts[i];
                double managementFee = feeAccount.getManagementFee();
                sb.append("Account #").append(accounts[i].getAccountNumber())
                        .append(" - Management Fee: ").append(String.format("%.2f", managementFee)).append("₪\n");
                totalBonus += managementFee;
            }
        }
        sb.append("Total annual bonus for the CEO: ").append(String.format("%.2f", totalBonus)).append("₪\n");
        return sb.toString();
    }

    protected String checkBusinessVIPProfit(int accountNumber) {
        Account account = findAccountByNumber(accountNumber);

        if (account == null) {
            return "Account number " + accountNumber + " does not exist.";
        }

        if (account instanceof BusinessCheckingAccount) {
            BusinessCheckingAccount businessAccount = (BusinessCheckingAccount) account;

            // Check if the account is VIP
            boolean isVIP = businessAccount.getBusinessRevenue() >= BusinessCheckingAccount.VIP_REVENUE_THRESHOLD;
            for (Client client : businessAccount.getClients()) {
                if (client != null && client.getRank() != BusinessCheckingAccount.VIP_CLIENT_RANK) {
                    isVIP = false; // At least one client does not meet the VIP rank requirement
                    break;
                }
            }

            if (isVIP) {
                double vipProfit = businessAccount.checkProfitVIP();
                return "VIP Profit for Business Account #" + accountNumber + ": " + vipProfit + "₪";
            } else {
                return "Account number " + accountNumber + " does not qualify as a VIP Business Account.";
            }
        } else {
            return "Account number " + accountNumber + " is not a Business Checking Account.";
        }
    }

}
