package com.bhai.taxCalculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bhai.taxCalculator.Item.ItemCategory.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;

    @Test
    void shouldCalculateCartCost() {
        given().cart(new Cart(lineItems())).verifyCartCost(3830);
    }

    @Test
    void shouldCalculateCartTotalTax() {
        given().cart(new Cart(lineItems())).verifyCartTotalTax(330, 10, 5);
    }

    @Test
    void verifyTaxIsRoundToTheNearest5Paisa() {
        given().cart(new Cart(lineItems())).verifyCartTotalTax(314.80, 9.54, 5);
    }

    private void verifyCartTotalTax(double basicTaxAmount, double basicTax, double importDuty) {
        assertEquals(basicTaxAmount, cart.calculateTax(basicTax, importDuty), 2);
    }

    private void verifyCartCost(double expected) {
        assertEquals(expected, cart.calculateCostIncludingTax(10, 5));
    }

    private CartTest cart(Cart cart) {
        this.cart = cart;
        return this;
    }

    private List<Cart.LineItem> lineItems() {
        Cart.LineItem lineItem1 = new Cart.LineItem(new Item(100, "Burger", Food, false), 2);
        Cart.LineItem lineItem2 = new Cart.LineItem(new Item(300, "Versace", Perfume, false), 1);
        Cart.LineItem lineItem3 = new Cart.LineItem(new Item(1000, "Zara", Clothing, false), 3);
        return  List.of(lineItem1, lineItem2, lineItem3);
    }

    private CartTest given() {
        return this;
    }
}