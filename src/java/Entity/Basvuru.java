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
public class Basvuru {
    private int id;
    private String aciklama;
    private Kisi kisi;

    public Basvuru() {
    }

    public Basvuru(String aciklama, Kisi kisi) {
        this.aciklama = aciklama;
        this.kisi = kisi;
    }

    public Basvuru(int id, String aciklama, Kisi kisi) {
        this.id = id;
        this.aciklama = aciklama;
        this.kisi = kisi;
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

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }

    @Override
    public String toString() {
        return "Basvuru{" + "id=" + id + ", aciklama=" + aciklama + ", kisi=" + kisi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
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
        final Basvuru other = (Basvuru) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
