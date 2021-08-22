package com.bhai.taxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    private final double cost;
    private final String name;
    private final ItemCategory category;
    private final boolean isImported;

    public Item(double cost, String name, ItemCategory category, boolean isImported) {
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.isImported = isImported;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public boolean isImported() {
        return isImported;
    }

    public double calculateBasicTax(double basicTaxPercent) {
        if(category.isExemptedFromBasicTax)
            return 0;
        return cost * basicTaxPercent/100 ;
    }

    public double calculateImportDuty(double importDutyPercent) {
        if(!isImported)
            return 0;
        return cost * importDutyPercent/100;
    }

    public double calculateCostIncludingTaxes(double basicTax, double importDuty) {
        double costAfterTaxes = cost + calculateBasicTax(basicTax) + calculateImportDuty(importDuty);
        return new BigDecimal(costAfterTaxes).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public enum ItemCategory {
        Food(true), Book(true), Medicine(true),
        Perfume(false), Clothing(false), Music(false);

        public boolean isExemptedFromBasicTax() {
            return isExemptedFromBasicTax;
        }

        private final boolean isExemptedFromBasicTax;

        ItemCategory(boolean isExemptedFromBasicTax) {
            this.isExemptedFromBasicTax = isExemptedFromBasicTax;
        }
    }
}
