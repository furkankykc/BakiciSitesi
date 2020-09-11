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
public class Kisi {
    private int id;
    private String adi;
    private String soyadi;
    private int yas;
    private String telNo;
    private String sifre;
    private String kullaniciAdi;
    private String biyografi;
    private Grup grup;
    private Referans referans;
    private Adres adres;
    private ArrayList<Yorum_> yorum;

    public Kisi() {
            this.yorum = new ArrayList<>();
    }

    public Kisi(String kullaniciAdi, String sifre) {
        this.sifre = sifre;
        this.kullaniciAdi = kullaniciAdi;
        this.grup = new Grup();
        this.referans = new Referans();
        this.adres = new Adres();
        this.yorum = new ArrayList<>();
    }

public Kisi(String adi, String soyadi, int yas, String telNo, String sifre, String kullaniciAdi, String biyografi, Grup grup, Referans referans, Adres adres, ArrayList<Yorum_> yorum) {
        this.adi = adi;
        this.soyadi = soyadi;
        this.yas = yas;
        this.telNo = telNo;
        this.sifre = sifre;
        this.kullaniciAdi = kullaniciAdi;
        this.biyografi = biyografi;
        this.grup = grup;
        this.referans = referans;
        this.adres = adres;
        this.yorum = yorum;
    }

    public Kisi(int id, String adi, String soyadi, int yas, String telNo, String sifre, String kullaniciAdi, String biyografi, Grup grup, Referans referans, Adres adres, ArrayList<Yorum_> yorum) {
        this.id = id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.yas = yas;
        this.telNo = telNo;
        this.sifre = sifre;
        this.kullaniciAdi = kullaniciAdi;
        this.biyografi = biyografi;
        this.grup = grup;
        this.referans = referans;
        this.adres = adres;
        this.yorum = yorum;

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

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getBiyografi() {
        return biyografi;
    }

    public void setBiyografi(String biyografi) {
        this.biyografi = biyografi;
    }

    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    public Referans getReferans() {
        return referans;
    }

    public void setReferans(Referans referans) {
        this.referans = referans;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public ArrayList<Yorum_> getYorum() {
        return yorum;
    }

    public void setYorum(ArrayList<Yorum_> yorum) {
        this.yorum = yorum;
    }

    @Override
    public String toString() {
        return "Kisi{" + "id=" + id + ", adi=" + adi + ", soyadi=" + soyadi + ", yas=" + yas + ", telNo=" + telNo + ", sifre=" + sifre + ", kullaniciAdi=" + kullaniciAdi + ", biyografi=" + biyografi + ", grup=" + grup + ", referans=" + referans + ", adres=" + adres + ", yorum=" + yorum + '}';
        
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Kisi other = (Kisi) obj;
        return this.id == other.id;
    }
    

    
    
    
    
}
