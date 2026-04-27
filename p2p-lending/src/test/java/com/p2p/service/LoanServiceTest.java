package com.p2p.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;

public class LoanServiceTest {

    private static final Logger logger = Logger.getLogger(LoanServiceTest.class);
    // TC-01: Verifikasi Borrower
    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {
        Borrower borrower = new Borrower(false, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });
    }

    // TC-02: Validasi Jumlah Pinjaman
    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        Borrower validBorrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();
        BigDecimal invalidAmount = BigDecimal.ZERO; 

        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(validBorrower, invalidAmount);
        });
    }

    // TC-03: Score Tinggi (>= 600) -> Status APPROVED
    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {
        Borrower highScoreBorrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Loan loan = loanService.createLoan(highScoreBorrower, amount);

        assertEquals(Loan.Status.APPROVED, loan.getStatus());
    }

    // TC-04: Score Rendah (< 600) -> Status REJECTED
    @Test
    void shouldRejectLoanWhenCreditScoreLow() {
        Borrower lowScoreBorrower = new Borrower(true, 500);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Loan loan = loanService.createLoan(lowScoreBorrower, amount);

        assertEquals(Loan.Status.REJECTED, loan.getStatus());
    }
}