/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Oleg_Tymchak
 */
public class UserAccount {
    
    private long id;
    private double        value;
    @JsonIgnore
    private User user;
    
    public UserAccount() {
    }

    public UserAccount(double value) {
        this.value = value;
    }
       
    public UserAccount(long id, double value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final UserAccount other = (UserAccount) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
            
    
}
