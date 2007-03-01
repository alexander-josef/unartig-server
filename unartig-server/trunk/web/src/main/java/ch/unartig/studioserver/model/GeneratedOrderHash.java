package ch.unartig.studioserver.model;

import java.util.*;




/**
 * GeneratedOrderHash generated by hbm2java
 */
public class GeneratedOrderHash  implements java.io.Serializable {

    // Fields    

     private Long orderHashId;
     private String hash;
     private Date expiryDate;
     private Order order;


    // Constructors

    /** default constructor */
    public GeneratedOrderHash() {
    }
    
    /** constructor with id */
    public GeneratedOrderHash(Long orderHashId) {
        this.orderHashId = orderHashId;
    }
   
    
    

    // Property accessors

    /**
     * 
     */
    public Long getOrderHashId() {
        return this.orderHashId;
    }
    
    public void setOrderHashId(Long orderHashId) {
        this.orderHashId = orderHashId;
    }

    /**
     * 
     */
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * 
     */
    public Date getExpiryDate() {
        return this.expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 
     */
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

  /**
	 * toString
	 * @return String
	 */
  public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("orderHashId").append("='").append(getOrderHashId()).append("' ");			
      buffer.append("hash").append("='").append(getHash()).append("' ");			
      buffer.append("expiryDate").append("='").append(getExpiryDate()).append("' ");			
      buffer.append("order").append("='").append(getOrder()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
	}



}