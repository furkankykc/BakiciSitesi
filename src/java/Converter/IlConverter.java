/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.IlDAO;
import Entity.Il;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "ilConverter")
public class IlConverter implements Converter{

    private IlDAO ilDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getIlDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Il il = (Il) value;
        return Integer.toString(il.getId());
    }

    public IlDAO getIlDao() {
        if(ilDao==null)
            ilDao =new IlDAO();
        return ilDao;
    }
    
    
}
