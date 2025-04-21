package com.marufakan;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreditScoreServiceTest {

    private CreditScoreService mockService;
    private Customer testCustomer;

    @Before
    public void setUp() {
        System.out.println("🔄 Her testten önce çalışır.");
        mockService = mock(CreditScoreService.class);
        testCustomer = new Customer("Ahmet", "1234567890");
    }

    @After
    public void tearDown() {
        System.out.println("🧹 Her testten sonra çalışır.");
        mockService = null;
    }

    @Test
    public void testGetCreditScore_Success() {
        when(mockService.getCreditScore(testCustomer)).thenReturn(650.0);
        double score = mockService.getCreditScore(testCustomer);
        assertEquals(650.0, score, 0.01);
        verify(mockService).getCreditScore(testCustomer);
    }

    @Test(expected = RuntimeException.class)
    public void testCreditScore_Exception() {
        when(mockService.getCreditScore(testCustomer)).thenThrow( new RuntimeException("Fail"));
        mockService.getCreditScore(testCustomer);
    }

}