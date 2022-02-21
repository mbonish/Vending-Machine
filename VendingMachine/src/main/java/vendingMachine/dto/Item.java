/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author mariana.bonish
 */
public class Item {
    
    int itemId;
    String name;
    BigDecimal cost;
    int quantity;

    public Item(int itemId){
    this.itemId = itemId;
}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.itemId;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.cost);
        hash = 41 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
    
    
    public int getItemId() {
        return itemId;
    }

    public void setItemid(int itemid) {
        this.itemId = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" + "itemid=" + itemId + ", name=" + name + ", cost=" + cost + ", quantity=" + quantity + '}';
    }

}
