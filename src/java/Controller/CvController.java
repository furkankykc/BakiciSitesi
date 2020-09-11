/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CvDAO;
import Entity.Cv;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "cvController")
@SessionScoped
public class CvController implements Serializable {

    private List<Cv> cvList;
    private CvDAO cvDao;
    private Cv cv;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String searchField = "";

    public CvController() {
        this.cvList = new ArrayList<>();
        this.cvDao = new CvDAO();
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    
    public ArrayList<Cv> search() {
        ArrayList<Cv> resultList = new ArrayList<>();
        for (Cv cv : this.cvList) {
            if (cv.getAdi().toLowerCase().startsWith(searchField.toLowerCase())) {
                resultList.add(cv);
            }
        }

        return resultList;
    }

    public List<Cv> getaList() {
        this.cvList = getaDao().list(page, pageSize);
        if (searchField.compareTo("")==0 || searchField != null) {
            this.cvList = this.search();
        }
        System.out.println("|||"+this.cvList);
        return cvList;
    }

    public CvDAO getaDao() {
        if (this.cvDao == null) {
            this.cvDao = new CvDAO();
        }
        return cvDao;
    }

    public Cv getCv() {
        if (this.cv == null) {
            this.cv = new Cv();
        }
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public void create() {

        this.getaDao().create(this.cv);
        clearForm();
    }

    public String updateForm(Cv cv) {
        this.cv = cv;
        return "cv";
    }

    public void clearForm() {
        this.cv = new Cv();

    }

    public String update() {
        this.cvDao.update(this.cv);
        this.clearForm();
        return "cv";
    }

    public String delete(Cv cv) {
        this.cv = cv;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(cv.getId());
        clearForm();
        return "cv";
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
