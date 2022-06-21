package com.example.product_list;

public class MenuItem {
    private String name;
    private int price;
    private String iconResource;
    private int count;

    public MenuItem(String name, int price, String iconResource, int count) {
        this.name = name;
        this.price = price;
        this.iconResource = iconResource;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIconResource() {
        return iconResource;
    }

    public void setIconResource(String iconResource) {
        this.iconResource = iconResource;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
