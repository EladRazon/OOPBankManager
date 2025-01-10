import java.text.SimpleDateFormat;

public class SavingsAccount extends Account {
    private final double depositAmount;
    private final int years;

    // Constructor
    public SavingsAccount(int accountNumber, int bankNumber, String managerName, double depositAmount, int years) throws DuplicationException {
        super(accountNumber, bankNumber, managerName);
        this.depositAmount = depositAmount;
        this.years = years;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%-25s {accountNumber=%d, dateOpened=%s, bankNumber=%d, managerName='%s', balance=%.2f ₪, depositAmount=%.2f ₪, years=%d}",
                getAccountType(),
                getAccountNumber(),
                dateFormat.format(getDateOpened()),
                getBankNumber(),
                getManagerName(),
                getBalance(),
                depositAmount, years);
    }

}
