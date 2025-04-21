package com.marufakan;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoanApplicationServiceTest {
    private CreditScoreService mockCreditScoreService;
    private LoanApplicationService loanApplicationService;
    private Customer customer;

    @Before
    public void setUp() {
        mockCreditScoreService = mock(CreditScoreService.class);
        loanApplicationService = new LoanApplicationService(mockCreditScoreService);
        customer = new Customer("Hasan","12345678910");
    }

    @Test
    public void testLoanApproved_WhenScoreIsHeigh() {
        when(mockCreditScoreService.getCreditScore(customer)).thenReturn(720.0);
        boolean result = loanApplicationService.isLoanApproved(customer);
        assertTrue(result);
        verify(mockCreditScoreService).getCreditScore(customer);
    }

    @Test
    public void testLoanApproved_WhenScoreIsLow() {
        when(mockCreditScoreService.getCreditScore(customer)).thenReturn(600.0);
        boolean result = loanApplicationService.isLoanApproved(customer);
        assertFalse(result);
        verify(mockCreditScoreService, times(1)).getCreditScore(customer);
    }

    @Test(expected = RuntimeException.class)
    public void testLoanApproved_WhenServiceFails(){
        when(mockCreditScoreService.getCreditScore(customer)).thenThrow(new RuntimeException());
        loanApplicationService.isLoanApproved(customer);//hata fırlatır
    }

    @Test
    public void testLoanRejected_WhenScoreIsExactlyLimit(){
        when(mockCreditScoreService.getCreditScore(customer)).thenReturn(699.0);
        boolean result = loanApplicationService.isLoanApproved(customer);
        assertFalse(result);
    }

    @Test
    public void testLoanApproved_WhenScoreIsHigh() throws InterruptedException {
        when(mockCreditScoreService.getCreditScore(customer)).thenReturn(720.0);
        String result = loanApplicationService.applyForLoan(customer);
        assertEquals("Loan Approved", result);
        verify(mockCreditScoreService).getCreditScore(customer);
    }

    @Test
    public void testLoanRejected_WhenScoreIsLow() throws InterruptedException {
        when(mockCreditScoreService.getCreditScore(customer)).thenReturn(650.0);
        String result = loanApplicationService.applyForLoan(customer);
        assertEquals("Loan Rejected", result);
        verify(mockCreditScoreService).getCreditScore(customer);
    }

    @Test(expected = RuntimeException.class)
    public void testLoanFails_WhenServiceFailsAfterRetries() throws InterruptedException {
        when(mockCreditScoreService.getCreditScore(customer)).thenThrow(new RuntimeException("Service unavailable"));
        loanApplicationService.applyForLoan(customer); // Exception is thrown after retries
    }

    @Test
    public void testLoanApproved_AfterRetry() throws InterruptedException {
        when(mockCreditScoreService.getCreditScore(customer)).thenThrow(new RuntimeException("Service unavailable"))
                .thenThrow(new RuntimeException("Service unavailable"))
                .thenReturn(710.0);
        String result = loanApplicationService.applyForLoan(customer);
        assertEquals("Loan Approved", result);
        verify(mockCreditScoreService, times(3)).getCreditScore(customer); // Verifying 3 calls (2 retries + 1 successful)
    }

    @Test
    public void testLoanRejected_AfterRetry() throws InterruptedException {
        when(mockCreditScoreService.getCreditScore(customer)).thenThrow(new RuntimeException("Service unavailable"))
                .thenThrow(new RuntimeException("Service unavailable"))
                .thenReturn(650.0);
        String result = loanApplicationService.applyForLoan(customer);
        assertEquals("Loan Rejected", result);
        verify(mockCreditScoreService, times(3)).getCreditScore(customer); // Verifying 3 calls
    }

    @After
    public void tearDown() {
        mockCreditScoreService = null;
        loanApplicationService = null;
    }
}