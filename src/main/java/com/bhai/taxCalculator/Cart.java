package com.bhai.taxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.lang.System.out;

public class Cart {
    private final List<LineItem> lineItems;

    public Cart(List<LineItem> items) {
        this.lineItems = items;
    }

    public double calculateCostIncludingTax(double basicTax, double importDutyPercent) {
        double totalCost = 0;
        for(var lineItem: lineItems) {
            double lineItemCost = lineItem.getItem().calculateCostIncludingTaxes(basicTax, importDutyPercent) * lineItem.getQuantity();
            totalCost += lineItemCost;
        }
        return totalCost;
    }

    public double calculateTax(double basicTax, double importDuty) {
        double totalBasicTax = 0;
        double totalImportDuty = 0;
        for(var lineItem: lineItems) {
            totalBasicTax += lineItem.getItem().calculateBasicTax(basicTax) * lineItem.quantity;
            totalImportDuty += lineItem.getItem().calculateImportDuty(importDuty) * lineItem.quantity;
        }

        double totalTaxes = new BigDecimal(totalBasicTax + totalImportDuty).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return Math.round((totalTaxes) * 20.0) / 20.0;
    }

    public void displayCart(double basicTax, double importDutyPercent) {
        for(LineItem lineItem: lineItems) {
            Item item = lineItem.getItem();
            double costIncludingTaxes = item.calculateCostIncludingTaxes(basicTax, importDutyPercent);
            out.println(lineItem.quantity + " " + item.getName() + " at " + costIncludingTaxes + "\n");
        }
        out.println("Tax: " + calculateTax(basicTax, importDutyPercent) + "\n");
        out.println("Total: " + calculateCostIncludingTax(basicTax, importDutyPercent));
    }

    static class LineItem {
        private final Item item;
        private final int quantity;

        public LineItem(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public Item getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
