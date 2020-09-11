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
public class Cv {
    private int id;
    private String adi;
    private Dosya dosya;
    private String aciklama;

    public Cv() {
    }

    public Cv(String adi, Dosya dosya, String aciklama) {
        this.adi = adi;
        this.dosya = dosya;
        this.aciklama = aciklama;
    }

    public Cv(int id, String adi, Dosya dosya, String aciklama) {
        this.id = id;
        this.adi = adi;
        this.dosya = dosya;
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

    public Dosya getDosya() {
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Cv{" + "id=" + id + ", adi=" + adi + ", dosya=" + dosya + ", aciklama=" + aciklama + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final Cv other = (Cv) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
