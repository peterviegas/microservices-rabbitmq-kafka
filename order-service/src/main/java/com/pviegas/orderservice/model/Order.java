package com.pviegas.orderservice.model;
import java.io.Serializable;

public class Order implements Serializable {
	
	private String orderId;
    private String customerName;
    private String product;
    private int quantity;
    private String status;
    
    public Order() {}

    public Order(String orderId, String customerName, String product, int quantity, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }
    
    public String getOrderId() {                              
	        return orderId;  
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}     
    
	@Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
	        
 }                                       

                                                              