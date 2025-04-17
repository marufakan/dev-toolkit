package com.marufakan;

public class CreditAllocation {

    private CreditScoreService creditScoreService;

    // Constructor
    public CreditAllocation(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    // Kredi tahsisi yapacak metod
    public boolean allocateCredit(Customer customer, double requestedCredit) {
        // Kredi puanını al
        double creditScore = creditScoreService.getCreditScore(customer);

        // Kredi puanı yüksek olmalı
        if (creditScore < 600) {
            System.out.println("Kredi puanı yetersiz.");
            return false;
        }

        // Mevcut borç toplam gelirin %50'sinden fazla olmamalı
        if (customer.getExistingDebt() > customer.getIncome() * 0.5) {
            System.out.println("Mevcut borç çok fazla.");
            return false;
        }

        // İstenilen kredi miktarı, gelirle orantılı olmalı (Gelirin 3 katından fazla olmamalı)
        if (requestedCredit > customer.getIncome() * 3) {
            System.out.println("İstenilen kredi miktarı fazla.");
            return false;
        }

        System.out.println("Kredi tahsisi başarılı: " + requestedCredit + " TL");
        return true;
    }
}
