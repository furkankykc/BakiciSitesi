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
public class Yorum_ {
    private int id;
    private String aciklama;

    public Yorum_() {
    }

    public Yorum_(String aciklama) {
        this.aciklama = aciklama;
    }

    public Yorum_(int id, String aciklama) {
        this.id = id;
        this.aciklama = aciklama;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Yorum{" + "id=" + id + ", aciklama=" + aciklama + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Yorum_ other = (Yorum_) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
