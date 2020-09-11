/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.YorumDAO;
import Entity.Yorum_;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "yorumConverter")
public class YorumConverter implements Converter{

    private YorumDAO yorumDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getYorumDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Yorum_ yorum = (Yorum_) value;
        return Integer.toString(yorum.getId());
    }

    public YorumDAO getYorumDao() {
        if(yorumDao==null)
            yorumDao =new YorumDAO();
        return yorumDao;
    }
    
    
}
