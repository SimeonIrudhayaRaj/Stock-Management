package com.example.stock;

public class data_model {
    private String stock,col;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getStock() {
        return stock;
    }

    public String getCol() {
        return col;
    }

    public void setQuantity(int quantity_) {
        this.quantity = quantity_;
    }

    public void setStock(String stock_) {
        this.stock = stock_;
    }

    public void setCol(String col) {
        this.col = col;
    }
}
