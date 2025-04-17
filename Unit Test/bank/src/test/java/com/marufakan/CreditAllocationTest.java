package com.marufakan;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class CreditAllocationTest {

    @Test
    public void testSuccessfulCreditAllocation() {
        // Mock CreditScoreService
        CreditScoreService mockCreditScoreService = mock(CreditScoreService.class);
        when(mockCreditScoreService.getCreditScore(any(Customer.class))).thenReturn(650.0);
        //bunu hangi müşteri çağırırsa çağırsın 650.0 yi dondur

        // Test için müşteri oluşturuluyor
        Customer customer = new Customer("Ali Veli", 5000, 20000);
        CreditAllocation creditAllocation = new CreditAllocation(mockCreditScoreService);

        // Kredi tahsisi başarılı olmalı
        boolean result = creditAllocation.allocateCredit(customer, 15000);

        assertTrue(result); // Sonuç başarılı olmalı
    }

    @Test
    public void testUnsuccessfulCreditDueToLowCreditScore() {
        // Mock CreditScoreService
        CreditScoreService mockCreditScoreService = mock(CreditScoreService.class);
        when(mockCreditScoreService.getCreditScore(any(Customer.class))).thenReturn(550.0);

        // Düşük kredi puanlı bir müşteri oluşturuluyor
        Customer customer = new Customer("Mehmet Can", 3000, 12000);
        CreditAllocation creditAllocation = new CreditAllocation(mockCreditScoreService);

        // Kredi puanı düşük olduğu için kredi tahsisi başarısız olmalı
        boolean result = creditAllocation.allocateCredit(customer, 5000);

        assertFalse(result); // Sonuç başarısız olmalı
    }

    @Test
    public void testUnsuccessfulCreditDueToExcessiveDebt() {
        // Mock CreditScoreService
        CreditScoreService mockCreditScoreService = mock(CreditScoreService.class);
        when(mockCreditScoreService.getCreditScore(any(Customer.class))).thenReturn(700.0);

        // Mevcut borcu yüksek bir müşteri oluşturuluyor
        Customer customer = new Customer("Zeynep Kaya", 15000, 12000);
        CreditAllocation creditAllocation = new CreditAllocation(mockCreditScoreService);

        // Borç, gelirin %50'sinden fazla olduğu için kredi tahsisi başarısız olmalı
        boolean result = creditAllocation.allocateCredit(customer, 5000);

        assertFalse(result); // Sonuç başarısız olmalı
    }

    @Test
    public void testUnsuccessfulCreditDueToExcessiveRequestedAmount() {
        // Mock CreditScoreService
        CreditScoreService mockCreditScoreService = mock(CreditScoreService.class);
        when(mockCreditScoreService.getCreditScore(any(Customer.class))).thenReturn(700.0);

        // Müşteri geliri düşük olduğu için yüksek kredi isteniyor
        Customer customer = new Customer("Ayşe Yılmaz", 2000, 6000);
        CreditAllocation creditAllocation = new CreditAllocation(mockCreditScoreService);

        // İstenilen kredi, gelirinin 3 katından fazla olduğu için kredi tahsisi başarısız olmalı
        boolean result = creditAllocation.allocateCredit(customer, 25000);

        assertFalse(result); // Sonuç başarısız olmalı
    }
}
