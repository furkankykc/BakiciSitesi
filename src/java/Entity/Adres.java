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
public class Adres {
    private int id;
    private String adi;
    private String adres;
    private Il il;
    private String yolTarifi;

    public Adres() {
    }
    public Adres(String adi, String adres, Il il, String yolTarifi) {
        this.adi = adi;
        this.adres = adres;
        this.il = il;
        this.yolTarifi = yolTarifi;
    }
    public Adres(int id, String adi, String adres, Il il, String yolTarifi) {
        this.id = id;
        this.adi = adi;
        this.adres = adres;
        this.il = il;
        this.yolTarifi = yolTarifi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Il getIl() {
        return il;
    }

    public void setIl(Il il) {
        this.il = il;
    }

    public String getYolTarifi() {
        return yolTarifi;
    }

    public void setYolTarifi(String yolTarifi) {
        this.yolTarifi = yolTarifi;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }
    
    @Override
    public String toString() {
        return "Adres{" + "id=" + id + ", adi=" + adi + ", adres=" + adres + ", il=" + il + ", yolTarifi=" + yolTarifi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Adres other = (Adres) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
