package com.bhai.taxCalculator;

import com.bhai.taxCalculator.Item.ItemCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.bhai.taxCalculator.Item.ItemCategory.Perfume;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ItemTest {
    private Item item;

    @Test
    void shouldCalculateBasicTax() {
        given()
                .item(new Item(300, "Not exempted item", Perfume, false))
                .verifyBasicTax(10, 30);
    }

    @ParameterizedTest
    @EnumSource(value = ItemCategory.class)
    void shouldExemptGivenItemsFromBasicTax(ItemCategory category) {
        assumeTrue(category.isExemptedFromBasicTax());

        given()
                .item(new Item(500, "Any exempted item", category, false))
                .verifyBasicTax(12, 0);
    }

    @ParameterizedTest
    @EnumSource(ItemCategory.class)
    void shouldCalculateImportDutyOnAllItems(ItemCategory category) {
        given()
                .item(new Item(1000, "Any Item", category, true))
                .verifyImportDuty(5, 50);
    }

    @ParameterizedTest
    @EnumSource(ItemCategory.class)
    void shouldCalculateItemCostAllInclusiveForBasicTaxNonExempted(ItemCategory category) {
        assumeTrue(!category.isExemptedFromBasicTax());

        given()
                .item(new Item(1000, "Item Name", category, false))
                .verifyTotalItemCost(10, 5, 1100);
    }

    @ParameterizedTest
    @EnumSource(ItemCategory.class)
    void shouldCalculateItemCostAllInclusiveForBasicTaxExempted(ItemCategory category) {
        assumeTrue(category.isExemptedFromBasicTax());

        given()
                .item(new Item(1000, "Item Name", category, true))
                .verifyTotalItemCost(10, 5, 1050);
    }

    private void verifyTotalItemCost(double basicTax, double importDuty, int expected) {
        assertEquals(expected, item.calculateCostIncludingTaxes(basicTax, importDuty));
    }

    private void verifyImportDuty(double importDutyTax, int importDutyAmount) {
        assertEquals(importDutyAmount, item.calculateImportDuty(importDutyTax));
    }

    private void verifyBasicTax(double basicTaxPercent, double basicTaxAmount) {
        assertEquals(basicTaxAmount, item.calculateBasicTax(basicTaxPercent));
    }

    private ItemTest item(Item item) {
        this.item = item;
        return this;
    }

    private ItemTest given() {
        return this;
    }
}
