/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BasvuruDAO;
import DAO.IsDAO;
import Entity.Basvuru;
import Entity.Is;
import Utility.SessionUtils;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "isController")
@SessionScoped
public class IsController implements Serializable {

    private List<Is> isList;
    private IsDAO isDao;
    private Is is;
    private Basvuru basvuru;
    private String aciklama;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private String searchField = "";

    public IsController() {
        this.isList = new ArrayList<>();
        this.isDao = new IsDAO();
    }

    public String getSearchField() {
        return searchField;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String basvur(Is is) {
        BasvuruDAO bc = new BasvuruDAO();
        ArrayList<Basvuru> basList = is.getBasvuru();
        basvuru = getBasvuru();
        basvuru.setAciklama(aciklama);
        basvuru.setKisi(Utility.SessionUtils.getUser());
        int id = bc.create(basvuru);
        Basvuru basTemp = bc.get(id);
        basList.add(basTemp);
//        basList.add(bc.get(1));
        is.setBasvuru(basList);
        this.getaDao().update(is);
        aciklama ="";
        return "index";
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

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public ArrayList<Is> search() {
        ArrayList<Is> resultList = new ArrayList<>();
        for (Is is : this.isList) {
            if (is.getBaslik().toLowerCase().startsWith(searchField.toLowerCase())) {
                resultList.add(is);
            }
        }

        return resultList;
    }

    public List<Is> getaList() {
        this.isList = getaDao().list(page, pageSize);
        if (searchField.compareTo("") == 0 || searchField != null) {
            this.isList = this.search();
        }
        System.out.println("|||" + this.isList);
        return isList;
    }

    public IsDAO getaDao() {
        if (this.isDao == null) {
            this.isDao = new IsDAO();
        }
        return isDao;
    }

    public Is getIs() {
        if (this.is == null) {
            this.is = new Is();
        }
        return is;
    }

    public void setIs(Is is) {
        this.is = is;
    }

    public void create() {

        this.getaDao().create(this.is);
        clearForm();
    }
    public void isCreate() {
        this.is.setKisi(SessionUtils.getUser());
        this.getaDao().create(this.is);
        clearForm();
    }

    public String updateForm(Is is) {
        this.is = is;
        return "is";
    }

    public void clearForm() {
        this.is = new Is();

    }

    public String update() {
        this.isDao.update(this.is);
        this.clearForm();
        return "is";
    }

    public String delete(Is is) {
        this.is = is;
        return "confirm_delete";

    }
    public String deleteX(Is is) {
        this.is = is;
        delete();
        return "index";

    }

    public String delete() {
        this.getaDao().delete(is.getId());
        clearForm();
        return "is";
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
