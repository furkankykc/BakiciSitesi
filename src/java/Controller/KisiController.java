/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KisiDAO;
import Entity.Kisi;
import Utility.SessionUtils;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "kisiController")
@SessionScoped
public class KisiController implements Serializable {

    private List<Kisi> kisiList;
    private KisiDAO kisiDao;
    private Kisi kisi;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String searchField = "";
    private String passControll;

    public KisiController() {
        this.kisiList = new ArrayList<>();
        this.kisiDao = new KisiDAO();
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    
    public ArrayList<Kisi> search() {
        ArrayList<Kisi> resultList = new ArrayList<>();
        for (Kisi kisi : this.kisiList) {
            if (kisi.getKullaniciAdi().toLowerCase().startsWith(searchField.toLowerCase())) {
                resultList.add(kisi);
            }
        }

        return resultList;
    }

    public List<Kisi> getaList() {
        this.kisiList = getaDao().list(page, pageSize);
        if (searchField.compareTo("")==0 || searchField != null) {
            this.kisiList = this.search();
        }
        System.out.println("|||"+this.kisiList);
        return kisiList;
    }

    public KisiDAO getaDao() {
        if (this.kisiDao == null) {
            this.kisiDao = new KisiDAO();
        }
        return kisiDao;
    }

    public Kisi getKisi() {
        if (this.kisi == null) {
            this.kisi = new Kisi();
        }
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }

    public String getPassControll() {
        return passControll;
    }

    public void setPassControll(String passControll) {
        this.passControll = passControll;
    }
    

    public void create() {

        this.getaDao().create(this.kisi);
        clearForm();
    }

    public String updateForm(Kisi kisi) {
        this.kisi = kisi;
        return "kisi";
    }

    public void clearForm() {
        this.kisi = new Kisi();

    }

    public String update() {
        this.kisiDao.update(this.kisi);
        this.clearForm();
        return "kisi";
    }

    public String delete(Kisi kisi) {
        this.kisi = kisi;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(kisi.getId());
        clearForm();
        return "kisi";
    }
   public String panel() {
        return "admin/index.xhtml";
    }

    public String site() {
        return "/index.xhtml";
    }
 public Boolean isLoggedin() {

        return SessionUtils.isLoggedin();
    }
 public Boolean isAlan() {

        return SessionUtils.isAlan();
    }
 public Boolean isVeren() {

        return SessionUtils.isVeren();
    }
 public Kisi currentUser() {

        return SessionUtils.getUser();
    }
    public String login() {
        Kisi kisi = this.getaDao().get(this.kisi.getKullaniciAdi());
        if (kisi != null) {
            if (kisi.getSifre().compareTo(this.kisi.getSifre()) == 0) {
                Utility.SessionUtils.setUser(kisi);
                return "/index.xhtml";
            } else {

                return "";
            }
        } else {

        }

        return "";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        this.kisi = null;
        return "/index.xhtml";

    }

    public Boolean getType() {

        return SessionUtils.isAdmin();
    }


    public String register() {
        Kisi user = this.getaDao().get(this.kisi.getKullaniciAdi());
//        message = "";
        if (this.kisi.getKullaniciAdi().compareTo("") != 0) {

            if (this.kisi.getSifre().compareTo(this.passControll) == 0) {
                if (user == null) {
                    if (this.kisi.getSifre().compareTo("") != 0) {
                        this.getaDao().createEx(this.kisi);
                           
                        return "login";

                    } else {
                    }
                } else {

                }
            } else {
            }
        } else {
        }
        return "";
    }
    public void next() {
        if (page < pageCount) {
            this.page++;
        }
    }

    public void previous() {
        if (page > 1) {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        try {
            this.pageCount = (int) Math.ceil(this.getaDao().count() / (double) this.pageSize);
        } catch (Exception e) {
            return 1;
        }

        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
