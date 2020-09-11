/*
 * To change this license header, choose License HeaIs in Project Properties.
 * To change this template fkisie, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Is;
import Entity.Basvuru;
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
public class IsDAO {

    private Is is;
    private ArrayList isList;
    private KisiDAO kisiDao;
    private BasvuruDAO basvuruDao;

    public KisiDAO getKisiDao() {
        if (kisiDao == null) {
            kisiDao = new KisiDAO();
        }
        return kisiDao;
    }

    public BasvuruDAO getBasvuruDao() {
        if (basvuruDao == null) {
            basvuruDao = new BasvuruDAO();
        }
        return basvuruDao;
    }

    public Is get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from `is` where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.is = new Is(rs.getInt("id"), rs.getString("baslik"), rs.getString("aciklama"), getKisiDao().get(rs.getInt("kisi_id")), getBasvuruDao().getIsBasvuru(rs.getInt("id")));
            } else {
                this.is = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.is;
    }

    public ArrayList<Is> list() {
        this.isList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `is`");
            while (rs.next()) {
                this.isList.add(new Is(
                        rs.getInt("id"), rs.getString("baslik"), rs.getString("aciklama"), getKisiDao().get(rs.getInt("kisi_id")), getBasvuruDao().getIsBasvuru(rs.getInt("id"))
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.isList;
    }

    public ArrayList<Is> list(int page, int pageSize) {
        this.isList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from `is` order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.isList.add(new Is(
                        rs.getInt("id"), rs.getString("baslik"), rs.getString("aciklama"), getKisiDao().get(rs.getInt("kisi_id")), getBasvuruDao().getIsBasvuru(rs.getInt("id"))
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.isList;
    }
      public void basvur(int is_id) {
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            st.executeQuery("insert into is_basvuru (is_id,basvuru_id) values()");
           
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from `is` where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Is a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from `is` where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from `is`");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Is a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update `is` set baslik=?,kisi_id=?,aciklama=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getBaslik());
            st.setInt(2, a.getKisi().getId());
            st.setString(3, a.getAciklama());
            st.setInt(4, a.getId());

            st.executeUpdate();
            st.executeUpdate("delete from is_basvuru where is_id=" + a.getId());
            for (Basvuru g : a.getBasvuru()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into is_basvuru(is_id,basvuru_id) values(" + a.getId() + ",'" + g.getId() + "')");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Is a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into `is` (baslik,kisi_id,aciklama) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getBaslik());
            st.setInt(2, a.getKisi().getId());
            st.setString(3, a.getAciklama());
            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Basvuru g : a.getBasvuru()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into is_basvuru(is_id,basvuru_id) values(+" + kid + ",'" + g.getId() + "')");

            }
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
