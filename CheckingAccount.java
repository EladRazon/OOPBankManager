public abstract class CheckingAccount extends Account implements ProfitAccount {
    private final double creditLimit;
    protected static final double RATE_DIFFERENCE = 0.10;

    // Constructor
    public CheckingAccount(int accountNumber, int bankNumber, String managerName, double creditLimit) throws DuplicationException {
        super(accountNumber, bankNumber, managerName);
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    // Abstract method for profit calculation
    @Override
    public abstract void calculateProfit();
}
