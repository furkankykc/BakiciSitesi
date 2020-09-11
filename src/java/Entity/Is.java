/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class Is {
    private int id;
    private String baslik;
    private String aciklama;
    private Kisi kisi;
    private ArrayList<Basvuru> basvuru;

    public Is() {
        this.basvuru = new ArrayList<>();
    }

    public Is(String baslik, String aciklama, Kisi kisi, ArrayList<Basvuru> basvuru) {
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.kisi = kisi;
        this.basvuru = basvuru;
    }

    public Is(int id, String baslik, String aciklama, Kisi kisi, ArrayList<Basvuru> basvuru) {
        this.id = id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.kisi = kisi;
        this.basvuru = basvuru;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
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

    public ArrayList<Basvuru> getBasvuru() {
        return basvuru;
    }

    public void setBasvuru(ArrayList<Basvuru> basvuru) {
        this.basvuru = basvuru;
    }

    @Override
    public String toString() {
        return "Is{" + "id=" + id + ", baslik=" + baslik + ", aciklama=" + aciklama + ", kisi=" + kisi + ", basvuru=" + basvuru + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Is other = (Is) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
