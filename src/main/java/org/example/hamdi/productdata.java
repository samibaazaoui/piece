package org.example.hamdi;

import java.util.Date;

public class productdata {
    private Integer id;
    private String productId ;
    private String productName ;
    private String productMarque;
    private Integer stock;
    private Double priceBuyU ;
    private String priceSellU ;
    private Double priceTotal;

    private Date date;
    private String Status ;
    private String type ;
    private Double priceBuyUrem ;
    private Double priceBuyUtva;
    private Double priceE ;
    private Double priceR ;
    private Integer quantity ;
    private Double price ;
    private Integer quantity1 ;
    private Integer number ;
    private String description;
    private Double priceCharge ;
    private String credit ;
    private Integer qt ;
    private Double recPrice ;
    private Date dateV ;
    private Date dateR ;


    public productdata( Integer id, String productId ,String productName , String productMarque,Integer stock,Double priceBuyU ,String priceSellU ,Date date , String Status, String type){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productMarque = productMarque;
        this.stock = stock;
        this.priceBuyU = priceBuyU;
        this.priceBuyUrem = priceBuyUrem;
        this.priceBuyUtva = priceBuyUtva;

        this.priceSellU = priceSellU;
        this.date = date;
        this.Status=Status;
        this.type = type;



    }
    public productdata( Integer id, String productId ,String productName , String productMarque,Integer stock,Double priceBuyU, Double priceBuyUrem,Double priceBuyUtva  ,String priceSellU ,Double priceTotal,String type,Date date ){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productMarque = productMarque;
        this.stock = stock;
        this.priceBuyU = priceBuyU;
        this.priceSellU = priceSellU;
        this.priceBuyUrem = priceBuyUrem;
        this.priceBuyUtva = priceBuyUtva;
        this.priceTotal = priceTotal;
        this.type = type;
        this.date = date;
    }
    public productdata( Integer id, String productId ,String productName , String productMarque,Integer quantity,Double priceE, Double priceR ,Date date ,String type,String credit ){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productMarque = productMarque;
        this.quantity = quantity;
        this.priceE = priceE;
        this.priceR = priceR;
        this.type = type;
        this.date = date;
        this.credit = credit;
    }
    public productdata( Integer id, String productId ,String productName , String productMarque,Integer quantity1,Double price ){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productMarque = productMarque;
        this.quantity1 = quantity1;
        this.price = price;

    }
    public productdata(Integer id ,Integer number ,String description ,Double priceCharge,Date date ){
        this.id = id ;
        this.number = number ;
        this.description = description;
        this.priceCharge = priceCharge;
        this.date = date;
    }
    public productdata(Integer id ,String productId,String productName,String productMarque,Integer qt ,Double recPrice ,Date dateV ,Date dateR ){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productMarque = productMarque;
        this.qt = qt;
        this.recPrice = recPrice;
        this.dateV = dateV;
        this.dateR = dateR;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductMarque() {
        return productMarque;
    }
    public void setProductMarque(String productMarque) {
        this.productMarque = productMarque;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Double getPriceBuyU() {
        return priceBuyU;
    }
    public void setPriceBuyU(Double priceBuyU) {
        this.priceBuyU = priceBuyU;
    }
    public String getPriceSellU() {
        return priceSellU;
    }
    public void setPriceSellU(String priceSellU) {
        this.priceSellU = priceSellU;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getPriceTotal() {
        return priceTotal;
    }
    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Double getPriceBuyUrem() {
        return priceBuyUrem;
    }

    public Double getPriceBuyUtva() {
        return priceBuyUtva;
    }
    public Double getPriceE() {
        return priceE;
    }
    public void setPriceE(Double priceE) {
        this.priceE = priceE;
    }
    public Double getPriceR() {
        return priceR;
    }
    public void setPriceR(Double priceR) {
        this.priceR = priceR;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity1() {
        return quantity1;
    }
    public void setQuantity1(Integer quantity1) {
        this.quantity1 = quantity1;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setPriceCharge(Double priceCharge) {
        this.priceCharge = priceCharge;
    }
    public Double getPriceCharge() {
        return priceCharge;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }
    public String getCredit() {
        return credit;
    }
    public void setQt(Integer qt) {
        this.qt = qt;
    }
    public Integer getQt() {
        return qt;
    }

    public Date getDateR() {
        return dateR;
    }
    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }
    public Date getDateV() {
        return dateV;
    }
    public void setDateV(Date dateV) {
        this.dateV = dateV;
    }
    public Double getRecPrice() {
        return recPrice;
    }

}

