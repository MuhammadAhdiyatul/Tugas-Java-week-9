package com.p2p.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;

public class LoanServiceTest {

    private static final Logger logger = Logger.getLogger(LoanServiceTest.class);
    
    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {
        logger.info("Menjalankan TC-01: Verifikasi Borrower");
        Borrower borrower = new Borrower(false, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });
        logger.info("TC-01 Sukses");
    }

    
    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        logger.info("Menjalankan TC-02: Validasi Amount <=0");
        Borrower validBorrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();
        BigDecimal invalidAmount = BigDecimal.ZERO; 

        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(validBorrower, invalidAmount);
        });
        logger.info("TC-02 Sukses");
    }

    
    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {
        logger.info("Menjalankan TC-03: Score Tinggi (>=600)");
        Borrower highScoreBorrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Loan loan = loanService.createLoan(highScoreBorrower, amount);

        assertEquals(Loan.Status.APPROVED, loan.getStatus());
        logger.info("TC-03 Sukses:");
    }

    
    @Test
    void shouldRejectLoanWhenCreditScoreLow() {
        logger.info("Menjalankan TC-04: Score Rendah (<600)");
        Borrower lowScoreBorrower = new Borrower(true, 500);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Loan loan = loanService.createLoan(lowScoreBorrower, amount);

        assertEquals(Loan.Status.REJECTED, loan.getStatus());
        logger.info("TC-04 Sukses");
    }
}