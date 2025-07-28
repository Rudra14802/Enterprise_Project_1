//package com.example.demo;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import lombok.*;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Document
//public class Market {
//    @Id
//    private String marketID;
//    private String stockSymbol;
//    private double price;
//    private int availableQty;
//    private String listedExchangeName;
//}


package com.example.demo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Market {
    @Id
    private String marketID;
    private String stockSymbol;
    private double price;
    private int availableQty;
    private String listedExchangeName;

    public Market() {}

    public Market(String marketID, String stockSymbol, double price, int availableQty, String listedExchangeName) {
        this.marketID = marketID;
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.availableQty = availableQty;
        this.listedExchangeName = listedExchangeName;
    }

    public String getMarketID() {
        return marketID;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public String getListedExchangeName() {
        return listedExchangeName;
    }

    public void setListedExchangeName(String listedExchangeName) {
        this.listedExchangeName = listedExchangeName;
    }
}
