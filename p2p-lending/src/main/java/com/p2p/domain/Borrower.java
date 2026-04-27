package com.p2p.domain;

public class Borrower {
    private boolean verified;
    private int creditScore;

    public Borrower(boolean verified, int creditScore) {
        this.verified = verified;
        this.creditScore = creditScore;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public boolean canApplyLoan() {
        return verified;
    }

}