import java.text.SimpleDateFormat;

public class MortgageAccount extends Account implements ProfitAccount, ManagementFeeAccount {
    private final double originalMortgageAmount;
    private final double monthlyPayment;
    private final int years;
    private static final double MORTGAGE_PROFIT_FACTOR = 0.8;
    private static final double RATE_DIFFERENCE = 0.10;
    private static final double MANAGEMENT_FEE_PERCENTAGE = 0.10; // 10% of the original mortgage amount
    private double profit = 0;


    // Constructor
    public MortgageAccount(int accountNumber, int bankNumber, String managerName, double originalMortgageAmount, double monthlyPayment, int years) throws DuplicationException {
        super(accountNumber, bankNumber, managerName);
        this.originalMortgageAmount = originalMortgageAmount;
        this.monthlyPayment = monthlyPayment;
        this.years = years;
        calculateProfit(); // Calculate profit when the account is created
    }

    @Override
    public double getManagementFee() {
        return originalMortgageAmount * MANAGEMENT_FEE_PERCENTAGE; // Calculate based on 10% of the original amount
    }
    @Override
    public String getAccountType() {
        return "Mortgage Account";
    }

    @Override
    public void calculateProfit() {
        profit = ((MORTGAGE_PROFIT_FACTOR * this.originalMortgageAmount) / this.years) * RATE_DIFFERENCE;
    }

    @Override
    public double getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%-25s {accountNumber=%d, dateOpened=%s, bankNumber=%d, managerName='%s', balance=%.2f ₪, originalMortgageAmount=%.2f ₪, monthlyPayment=%.2f ₪, years=%d}",
                getAccountType(),
                getAccountNumber(),
                dateFormat.format(getDateOpened()),
                getBankNumber(),
                getManagerName(),
                getBalance(),
                originalMortgageAmount,
                monthlyPayment,
                years);
    }
}
