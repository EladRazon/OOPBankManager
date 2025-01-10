import java.text.SimpleDateFormat;

public class RegularCheckingAccount extends CheckingAccount {
    private double profit = 0;

    // Constructor
    public RegularCheckingAccount(int accountNumber, int bankNumber, String managerName, double creditLimit) throws DuplicationException {
        super(accountNumber, bankNumber, managerName, creditLimit);
        calculateProfit(); // Calculate profit when the account is created
    }

    @Override
    public double getProfit() {
        return this.profit; // Return the profit that was already calculated
    }

    // Override getAccountType method to return the account type as a string
    @Override
    public String getAccountType() {
        return "Regular Checking Account";
    }

    // Override calculateProfit method to calculate the profit based on credit limit
    @Override
    public void calculateProfit() {
        this.profit = this.getCreditLimit() * RATE_DIFFERENCE;
    }

    // Override toString method to format the account details as a string
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format(
                "%-25s {accountNumber=%d, dateOpened=%s, bankNumber=%d, managerName='%s', balance=%.2f ₪, creditLimit=%.2f ₪}",
                getAccountType(),
                getAccountNumber(),
                dateFormat.format(getDateOpened()),
                getBankNumber(),
                getManagerName(),
                getBalance(),
                getCreditLimit()
        );
    }
}
