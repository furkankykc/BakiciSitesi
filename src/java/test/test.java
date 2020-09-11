/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.AdresDAO;
import DAO.GrupDAO;
import DAO.IlDAO;
import DAO.KisiDAO;
import DAO.ReferansDAO;
import Entity.Adres;
import Entity.Grup;
import Entity.Il;
import Entity.Kisi;
import Entity.Referans;

/**
 *
 * @author furkankykc
 */
public class test {
    public static void main(String[] args) {
        Kisi kisi = new Kisi("furkankykc@mail.com","1234");
        KisiDAO kisiDao = new KisiDAO();
        GrupDAO grupDao = new GrupDAO();
        AdresDAO adresDao = new AdresDAO();
        IlDAO ilDao = new IlDAO();
        ReferansDAO referansDao = new ReferansDAO();
        Il il = ilDao.get(ilDao.create(new Il("Malatya")));
        Referans referans =referansDao.get(referansDao.create(new Referans("millet vekili", "Vekilden torpilim var")));
        Adres adres = adresDao.get(adresDao.create(new Adres("adresim","Carsi",il,"Çarşının karşısındaki fırının karşısındaki çesmenin diğer tarafında kalan apartman")));
        Grup grup = grupDao.get(grupDao.create(new Grup("admin",true,true,true)));
        kisi.setGrup(grup);
        kisi.setReferans(referans);
        kisi.setAdres(adres);
        kisiDao.create(kisi);


        
    }
            
}
