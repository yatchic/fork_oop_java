package ru.gb.vending_machine.products;
public class HotDrink extends Product {
    private int temperature;

    public HotDrink(String name, double price, int temperature) {
        super(name, price);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return super.toString() + ", temperature: " + temperature + "°Tree";
    }
}
