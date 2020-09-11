/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.IlDAO;
import Entity.Il;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "ilController")
@SessionScoped
public class IlController implements Serializable {

    private List<Il> ilList;
    private IlDAO ilDao;
    private Il il;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String searchField = "";

    public IlController() {
        this.ilList = new ArrayList<>();
        this.ilDao = new IlDAO();
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    
    public ArrayList<Il> search() {
        ArrayList<Il> resultList = new ArrayList<>();
        for (Il il : this.ilList) {
            if (il.getAdi().toLowerCase().startsWith(searchField.toLowerCase())) {
                resultList.add(il);
            }
        }

        return resultList;
    }

    public List<Il> getaList() {
        this.ilList = getaDao().list(page, pageSize);
        if (searchField.compareTo("")==0 || searchField != null) {
            this.ilList = this.search();
        }
        System.out.println("|||"+this.ilList);
        return ilList;
    }

    public IlDAO getaDao() {
        if (this.ilDao == null) {
            this.ilDao = new IlDAO();
        }
        return ilDao;
    }

    public Il getIl() {
        if (this.il == null) {
            this.il = new Il();
        }
        return il;
    }

    public void setIl(Il il) {
        this.il = il;
    }

    public void create() {

        this.getaDao().create(this.il);
        clearForm();
    }

    public String updateForm(Il il) {
        this.il = il;
        return "il";
    }

    public void clearForm() {
        this.il = new Il();

    }

    public String update() {
        this.ilDao.update(this.il);
        this.clearForm();
        return "il";
    }

    public String delete(Il il) {
        this.il = il;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(il.getId());
        clearForm();
        return "il";
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
