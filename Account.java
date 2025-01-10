import java.util.Date;
public abstract class Account {
    private static int[] usedAccountNumbers = new int[2]; // Array to store used account numbers
    private static int usedCount = 0; // Track the number of used account numbers
    private final int accountNumber;
    private final Date dateOpened;
    private final int bankNumber;
    private final double balance;
    private final String managerName;
    protected Client[] clients; // Array for clients
    private int clientCount; // Counter for how many clients are in the array

    // Constructor
    public Account(int accountNumber, int bankNumber, String managerName) throws DuplicationException {
        // Check if the account number is already in use
        if (isAccountNumberUsed(accountNumber)) {
            throw new DuplicationException(accountNumber);
        }
        this.accountNumber = accountNumber;
        this.dateOpened = new Date(); // Set to current date
        this.bankNumber = bankNumber;
        this.balance = 20.0; // Default balance
        this.managerName = managerName;
        this.clients = new Client[2]; // Initialize with a size of 2
        this.clientCount = 0; // No clients yet

        // Mark the account number as used
        addUsedAccountNumber(accountNumber);
    }

    // Method to check if the account number is already used
    public static boolean isAccountNumberUsed(int accountNumber) {
        for (int i = 0; i < usedCount; i++) {
            if (usedAccountNumbers[i] == accountNumber) {
                return true;
            }
        }
        return false;
    }

    // Method to add a used account number to the array
    private static void addUsedAccountNumber(int accountNumber) {
        if (usedCount == usedAccountNumbers.length) {
            expandUsedAccountNumbers();
        }
        usedAccountNumbers[usedCount++] = accountNumber;
    }

    // Method to expand the used account numbers array
    private static void expandUsedAccountNumbers() {
        int[] newUsedAccountNumbers = new int[usedAccountNumbers.length + 2]; // Increase array size by 2
        System.arraycopy(usedAccountNumbers, 0, newUsedAccountNumbers, 0, usedAccountNumbers.length);
        usedAccountNumbers = newUsedAccountNumbers;
    }

    // Getters and Setters for each attribute
    public int getAccountNumber() {
        return accountNumber;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getManagerName() {
        return managerName;
    }

    public Client[] getClients() {
        return clients;
    }

    // Method to add a client to the account
    public void addClient(Client client) {
        if (clientCount == clients.length) {
            expandClientArray();
        }
        clients[clientCount++] = client;
    }

    // Method to expand the client array when full
    private void expandClientArray() {
        Client[] newClients = new Client[clients.length + 2]; // Increase array size by 2
        System.arraycopy(clients, 0, newClients, 0, clients.length);
        clients = newClients;
    }

    public abstract String getAccountType();

}
