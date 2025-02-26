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
public class Referans {
    private int id;
    private String adi;
    private String aciklama;

    public Referans() {
    }

    public Referans(String adi, String aciklama) {
        this.adi = adi;
        this.aciklama = aciklama;
    }

    public Referans(int id, String adi, String aciklama) {
        this.id = id;
        this.adi = adi;
        this.aciklama = aciklama;
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

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Referans{" + "id=" + id + ", adi=" + adi + ", aciklama=" + aciklama + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
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
        final Referans other = (Referans) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
