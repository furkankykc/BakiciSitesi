/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.IsDAO;
import Entity.Is;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "isConverter")
public class IsConverter implements Converter{

    private IsDAO isDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getIsDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Is il = (Is) value;
        return Integer.toString(il.getId());
    }

    public IsDAO getIsDao() {
        if(isDao==null)
            isDao =new IsDAO();
        return isDao;
    }
    
    
}
