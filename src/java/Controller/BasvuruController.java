/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BasvuruDAO;
import Entity.Basvuru;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "basvuruController")
@SessionScoped
public class BasvuruController implements Serializable {

    private List<Basvuru> basvuruList;
    private BasvuruDAO basvuruDao;
    private Basvuru basvuru;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String searchField = "";

    public BasvuruController() {
        this.basvuruList = new ArrayList<>();
        this.basvuruDao = new BasvuruDAO();
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    
    public ArrayList<Basvuru> search() {
        ArrayList<Basvuru> resultList = new ArrayList<>();
        for (Basvuru basvuru : this.basvuruList) {
            if (basvuru.getAciklama().toLowerCase().startsWith(searchField.toLowerCase())) {
                resultList.add(basvuru);
            }
        }

        return resultList;
    }

    public List<Basvuru> getaList() {
        this.basvuruList = getaDao().list(page, pageSize);
        if (searchField.compareTo("")==0 || searchField != null) {
            this.basvuruList = this.search();
        }
        System.out.println("|||"+this.basvuruList);
        return basvuruList;
    }

    public BasvuruDAO getaDao() {
        if (this.basvuruDao == null) {
            this.basvuruDao = new BasvuruDAO();
        }
        return basvuruDao;
    }

    public Basvuru getBasvuru() {
        if (this.basvuru == null) {
            this.basvuru = new Basvuru();
        }
        return basvuru;
    }

    public void setBasvuru(Basvuru basvuru) {
        this.basvuru = basvuru;
    }

    public void create() {

        this.getaDao().create(this.basvuru);
        clearForm();
    }

    public String updateForm(Basvuru basvuru) {
        this.basvuru = basvuru;
        return "basvuru";
    }

    public void clearForm() {
        this.basvuru = new Basvuru();

    }

    public String update() {
        this.basvuruDao.update(this.basvuru);
        this.clearForm();
        return "basvuru";
    }

    public String delete(Basvuru basvuru) {
        this.basvuru = basvuru;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(basvuru.getId());
        clearForm();
        return "basvuru";
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
