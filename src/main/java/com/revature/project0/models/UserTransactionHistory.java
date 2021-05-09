package com.revature.project0.models;

public class UserTransactionHistory {
    private int userId;
    private int transactionId;
    private String transactionType;
    private String transactionAmount;
    private double transactionDate;
    private String accountType;
    private int accountId;

    public UserTransactionHistory() {
        super();
    }

    public UserTransactionHistory(int userId, int transactionId, String transactionType, String transactionAmount, double transactionDate, String accountType, int accountId) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.accountType = accountType;
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(double transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTransactionHistory{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", transactionID='").append(transactionId).append('\'');
        sb.append(", transactionType='").append(transactionType).append('\'');
        sb.append(", transactionAmount='").append(transactionAmount).append('\'');
        sb.append(", transactionDate='").append(transactionDate).append('\'');
        sb.append(", accountType=").append(accountType);
        sb.append(", accountId=").append(accountId);
        sb.append('}');
        return sb.toString();
    }
}


