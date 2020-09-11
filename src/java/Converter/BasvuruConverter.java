/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.BasvuruDAO;
import Entity.Basvuru;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "basvuruConverter")
public class BasvuruConverter implements Converter{

    private BasvuruDAO basvuruDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBasvuruDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Basvuru il = (Basvuru) value;
        return Integer.toString(il.getId());
    }

    public BasvuruDAO getBasvuruDao() {
        if(basvuruDao==null)
            basvuruDao =new BasvuruDAO();
        return basvuruDao;
    }
    
    
}
