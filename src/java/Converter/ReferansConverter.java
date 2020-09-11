/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.ReferansDAO;
import Entity.Referans;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "referansConverter")
public class ReferansConverter implements Converter{

    private ReferansDAO referansDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getReferansDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Referans il = (Referans) value;
        return Integer.toString(il.getId());
    }

    public ReferansDAO getReferansDao() {
        if(referansDao==null)
            referansDao =new ReferansDAO();
        return referansDao;
    }
    
    
}
