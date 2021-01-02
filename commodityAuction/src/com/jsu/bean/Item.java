package com.jsu.bean;

import java.util.Date;

public class Item {
    private Integer itemId;
    private String itemName;
    private Double initPrice;
    private Double maxPrice;
    private String info;
    private Date addTime;
    private Date endTime;
    private String type;
    private String img;
    private Integer buyerId;
    private Integer sellerId;
    private Integer state;

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", initPrice=" + initPrice +
                ", maxPrice=" + maxPrice +
                ", info='" + info + '\'' +
                ", addTime=" + addTime +
                ", endTime=" + endTime +
                ", type='" + type + '\'' +
                ", img='" + img + '\'' +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", state=" + state +
                '}';
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Item(Integer itemId, String itemName, Double initPrice, Double maxPrice, String info, Date addTime, Date endTime, String type, String img, Integer buyerId, Integer sellerId, Integer state) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.initPrice = initPrice;
        this.maxPrice = maxPrice;
        this.info = info;
        this.addTime = addTime;
        this.endTime = endTime;
        this.type = type;
        this.img = img;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.state = state;
    }

    public void setInitPrice(Double initPrice) {
        this.initPrice = initPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Item(Integer itemId, String itemName, Double initPrice, Double maxPrice, String info, Date addTime, Date endTime, String type, String img, Integer buyerId, Integer state) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.initPrice = initPrice;
        this.maxPrice = maxPrice;
        this.info = info;
        this.addTime = addTime;
        this.endTime = endTime;
        this.type = type;
        this.img = img;
        this.buyerId = buyerId;
        this.state = state;
    }

    public Item(Integer itemId, String itemName, double initPrice, String info, Date addTime, Date endTime, String type, String img, Integer buyerId, Integer state) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.initPrice = initPrice;
        this.info = info;
        this.addTime = addTime;
        this.endTime = endTime;
        this.type = type;
        this.img = img;
        this.buyerId = buyerId;
        this.state = state;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Item() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(double initPrice) {
        this.initPrice = initPrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

}
