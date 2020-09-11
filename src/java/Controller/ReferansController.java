/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ReferansDAO;
import Entity.Referans;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "referansController")
@SessionScoped
public class ReferansController implements Serializable {

    private List<Referans> referansList;
    private ReferansDAO referansDao;
    private Referans referans;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String searchField = "";

    public ReferansController() {
        this.referansList = new ArrayList<>();
        this.referansDao = new ReferansDAO();
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    
    public ArrayList<Referans> search() {
        ArrayList<Referans> resultList = new ArrayList<>();
        for (Referans referans : this.referansList) {
            if (referans.getAdi().toLowerCase().startsWith(searchField.toLowerCase())) {
                resultList.add(referans);
            }
        }

        return resultList;
    }

    public List<Referans> getaList() {
        this.referansList = getaDao().list(page, pageSize);
        if (searchField.compareTo("")==0 || searchField != null) {
            this.referansList = this.search();
        }
        System.out.println("|||"+this.referansList);
        return referansList;
    }

    public ReferansDAO getaDao() {
        if (this.referansDao == null) {
            this.referansDao = new ReferansDAO();
        }
        return referansDao;
    }

    public Referans getReferans() {
        if (this.referans == null) {
            this.referans = new Referans();
        }
        return referans;
    }

    public void setReferans(Referans referans) {
        this.referans = referans;
    }

    public void create() {

        this.getaDao().create(this.referans);
        clearForm();
    }

    public String updateForm(Referans referans) {
        this.referans = referans;
        return "referans";
    }

    public void clearForm() {
        this.referans = new Referans();

    }

    public String update() {
        this.referansDao.update(this.referans);
        this.clearForm();
        return "referans";
    }

    public String delete(Referans referans) {
        this.referans = referans;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(referans.getId());
        clearForm();
        return "referans";
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
