package com.p2p.service;

import java.math.BigDecimal;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;

public class LoanService {
    public Loan createLoan(Borrower borrower, BigDecimal amount) {
        // =========================
        // VALIDASI (delegasi ke domain)
        // =========================
        validateBorrower(borrower);
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        Loan loan = new Loan();

        // =========================
        // BUSINESS ACTION
        // =========================
        if (borrower.getCreditScore() >= 600) {
            loan.approve();
        } else {
            loan.reject();
        }

        return loan;
    }

    // =========================
    // PRIVATE VALIDATION METHOD
    // =========================
    private void validateBorrower(Borrower borrower) {
        if (!borrower.canApplyLoan()) {
            throw new IllegalArgumentException("Borrower not verified");
        }
    }
}
