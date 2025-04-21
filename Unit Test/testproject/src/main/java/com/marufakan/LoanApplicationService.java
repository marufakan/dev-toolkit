package com.marufakan;

import java.util.concurrent.TimeUnit;

public class LoanApplicationService {
    private CreditScoreService creditScoreService;
    private static final int MAX_RETRIES = 3;

    public LoanApplicationService(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    public boolean isLoanApproved(Customer customer) {
        double creditScore = creditScoreService.getCreditScore(customer);
        return creditScore > 700;
    }

    public String applyForLoan(Customer customer) throws InterruptedException {
        int attempt = 0;
        while (attempt < MAX_RETRIES) {
            try {
                double score = creditScoreService.getCreditScore(customer);
                if (score >= 700) {
                    return "Loan Approved";
                } else {
                    return "Loan Rejected";
                }
            } catch (RuntimeException e) {
                if (attempt == MAX_RETRIES - 1) {
                    throw new RuntimeException("Service unavailable after retries");
                }
                attempt++;
                TimeUnit.SECONDS.sleep(2); // Retry after waiting for 2 seconds
            }
        }
        throw new RuntimeException("Service failed after maximum retries");
    }
}
