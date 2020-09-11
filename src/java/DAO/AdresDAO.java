/*
 * To change this license header, choose License HeaAdres in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Adres;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class AdresDAO {

    private Adres adres;
    private ArrayList adresList;
    private IlDAO ilDao;

    public IlDAO getIlDao() {
        if (ilDao==null)
            ilDao = new IlDAO();
        return ilDao;
    }

    
    
    public Adres get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from adres where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.adres = new Adres(rs.getInt("id"), rs.getString("adi"), rs.getString("adres"), getIlDao().get(rs.getInt("il_id")), rs.getString("yol_tarifi"));
            } else {
                this.adres = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.adres;
    }

    public ArrayList<Adres> list() {
        this.adresList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from adres");
            while (rs.next()) {
                this.adresList.add(new Adres(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("adres"), getIlDao().get(rs.getInt("il_id")), rs.getString("yol_tarifi")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.adresList;
    }

    public ArrayList<Adres> list(int page, int pageSize) {
        this.adresList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from adres order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.adresList.add(new Adres(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("adres"), getIlDao().get(rs.getInt("il_id")), rs.getString("yol_tarifi")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.adresList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from adres where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Adres a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from adres where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int count() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(id) as a_count from adres");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Adres a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update adres set adi=?,il_id=?,adres=?,yol_tarifi=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getIl().getId());
            st.setString(3, a.getAdres());
            st.setString(4, a.getYolTarifi());
            st.setInt(5, a.getId());
            
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Adres a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into adres (adi,il_id,adres,yol_tarifi) values (?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getIl().getId());
            st.setString(3, a.getAdres());
            st.setString(4, a.getYolTarifi());
            st.executeUpdate();

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    return (generatedKeys.getInt(1));
                }

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
