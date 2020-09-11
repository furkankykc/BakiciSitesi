/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author furkankykc
 */
public class Grup {
    private int id;
    private String adi;
    private boolean admin;
    private boolean alan;
    private boolean veren;

    public Grup() {
    }

    public Grup(String adi, boolean admin, boolean alan, boolean veren) {
        this.adi = adi;
        this.admin = admin;
        this.alan = alan;
        this.veren = veren;
    }

    public Grup(int id, String adi, boolean admin, boolean alan, boolean veren) {
        this.id = id;
        this.adi = adi;
        this.admin = admin;
        this.alan = alan;
        this.veren = veren;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAlan() {
        return alan;
    }

    public void setAlan(boolean alan) {
        this.alan = alan;
    }

    public boolean isVeren() {
        return veren;
    }

    public void setVeren(boolean veren) {
        this.veren = veren;
    }

    @Override
    public String toString() {
        return "Grup{" + "id=" + id + ", adi=" + adi + ", admin=" + admin + ", alan=" + alan + ", veren=" + veren + '}';
    }




    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final Grup other = (Grup) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
