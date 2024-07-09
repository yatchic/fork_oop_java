package ru.gb.vending_machine.vending;

import ru.gb.vending_machine.products.Product;
import ru.gb.vending_machine.products.HotDrink;

import java.util.ArrayList;
import java.util.List;

public class HotDrinkVendingMachine implements VendingMachineInterface {
    private int id;
    private int productId;
    private List<Product> productList;

    public HotDrinkVendingMachine(int id) {
        this.id = id;
        productList = new ArrayList<>();
    }
    public void addProduct(HotDrink hotDrink) {
        hotDrink.setId(productId++);
        productList.add(hotDrink);
    }


    @Override
    public String getProductsInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список продуктов:\n");
        for (Product product : productList) {
            stringBuilder.append(product);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}