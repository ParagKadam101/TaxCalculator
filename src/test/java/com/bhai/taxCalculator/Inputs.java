package com.bhai.taxCalculator;

import com.bhai.taxCalculator.Cart.LineItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bhai.taxCalculator.Item.ItemCategory.*;

public class Inputs {

    private final Item book = new Item(124.99, "book", Book, false);
    private final Item musicCD = new Item(149.99, "music CD", Music, false);
    private final Item chocolateBar = new Item(40.85, "chocolate bar", Food, false);
    private final Item importedChocolateBox1 = new Item(100.00, "imported box of chocolates", Food, true);
    private final Item importedPerfumeBottle1 = new Item(470.50, "imported bottle of perfume", Perfume, true);
    private final Item importedPerfumeBottle2 = new Item(270.99, "imported bottle of perfume", Perfume, true);
    private final Item perfumeBottle = new Item(180.99, "bottle of perfume", Perfume, false);
    private final Item headachePill = new Item(19.75, "headache pills", Medicine, false);
    private final Item importedChocolateBox2 = new Item(210.25, "box of imported chocolates", Food, true);

    private Cart cart;

    private final double basicTax = 10;
    private final double importDutyPercent = 5;


    @Test
    void input1() {
        List<LineItem> lineItems = List.of(new LineItem(book, 1), new LineItem(musicCD, 1), new LineItem(chocolateBar, 1));
        cart = new Cart(lineItems);
        cart.displayCart(basicTax, importDutyPercent);
    }

    @Test
    void input2() {
        List<LineItem> lineItems = List.of(new LineItem(importedChocolateBox1, 1), new LineItem(importedPerfumeBottle1, 1));
        cart = new Cart(lineItems);
        cart.displayCart(basicTax, importDutyPercent);
    }

    @Test
    void input3() {
        List<LineItem> lineItems = List.of(new LineItem(importedPerfumeBottle2, 1), new LineItem(perfumeBottle, 1),
                new LineItem(headachePill, 1), new LineItem(importedChocolateBox2, 1));
        cart = new Cart(lineItems);
        cart.displayCart(basicTax, importDutyPercent);
    }
}
