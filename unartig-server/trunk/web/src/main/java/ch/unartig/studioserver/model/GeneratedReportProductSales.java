package ch.unartig.studioserver.model;

import java.util.*;




/**
 * GeneratedReportProductSales generated by hbm2java
 */
public class GeneratedReportProductSales  implements java.io.Serializable {

    // Fields    

     private String eventName;
     private String albumName;
     private Long albumId;
     private String productName;
     private Long productId;
     private Integer count;
     private Integer quantity;


    // Constructors

    /** default constructor */
    public GeneratedReportProductSales() {
    }
    
   
    
    

    // Property accessors

    /**
     * 
     */
    public String getEventName() {
        return this.eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * 
     */
    public String getAlbumName() {
        return this.albumName;
    }
    
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * 
     */
    public Long getAlbumId() {
        return this.albumId;
    }
    
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * 
     */
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 
     */
    public Long getProductId() {
        return this.productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 
     */
    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     */
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}