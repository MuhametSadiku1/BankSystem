package main.java.com.bank;

public class Transaction {
    private double amount;
    private int originatingAccountId;
    private int resultingAccountId;
    private String reason;
    private boolean isFlatFee;

    public Transaction(double amount, int originatingAccountId, int resultingAccountId, String reason,
            boolean isFlatFee) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
        this.isFlatFee = isFlatFee;
    }

    public double getAmount() {
        return amount;
    }

    public int getOriginatingAccountId() {
        return originatingAccountId;
    }

    public int getResultingAccountId() {
        return resultingAccountId;
    }

    public String getReason() {
        return reason;
    }

    public boolean isFlatFee() {
        return isFlatFee;
    }

    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", fromAccountId=" + originatingAccountId +
                ", toAccountId=" + resultingAccountId +
                ", reason='" + reason + '\'' +
                ", isFlatFee=" + isFlatFee +
                '}';
    }
}
