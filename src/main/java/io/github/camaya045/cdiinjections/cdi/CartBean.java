package io.github.camaya045.cdiinjections.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class CartBean implements Serializable {
    private int orderList = 0;

    public void addItem() {orderList++;}
    public void removeItem() {orderList--;}

    public int getOrderList() {return orderList;}
    public void setOrderList(int orderList) {this.orderList = orderList;}
}