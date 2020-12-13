/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DB1407
 */
public class Cart {

    private Item item;
    private int qty;

    public Cart(Item item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Cart{" + "item=" + item.toString() + ", qty=" + qty + '}';
    }

    

}
