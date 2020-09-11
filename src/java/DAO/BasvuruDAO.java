/*
 * To change this license header, choose License HeaBasvuru in Project Properties.
 * To change this template fkisie, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Basvuru;
import Entity.Basvuru;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author furkankykc
 */
public class BasvuruDAO {

    private Basvuru basvuru;
    private ArrayList basvuruList;
    private KisiDAO kisiDao;

    public KisiDAO getKisiDao() {
        if (kisiDao == null) {
            kisiDao = new KisiDAO();
        }
        return kisiDao;
    }

    public Basvuru get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from basvuru where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.basvuru = new Basvuru(rs.getInt("id"), rs.getString("aciklama"), getKisiDao().get(rs.getInt("kisi_id")));
            } else {
                this.basvuru = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.basvuru;
    }
    public ArrayList<Basvuru> getIsBasvuru(int is_id) {
        this.basvuruList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from is_basvuru where is_id=" + is_id);
            while (rs.next()) {
                this.basvuruList.add(this.get(rs.getInt("basvuru_id")));
                System.out.println("-----------------");
            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.basvuruList;
    }
  

    public ArrayList<Basvuru> list() {
        this.basvuruList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from basvuru");
            while (rs.next()) {
                this.basvuruList.add(new Basvuru(
                        rs.getInt("id"), rs.getString("aciklama"), getKisiDao().get(rs.getInt("kisi_id"))
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.basvuruList;
    }

    public ArrayList<Basvuru> list(int page, int pageSize) {
        this.basvuruList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from basvuru order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.basvuruList.add(new Basvuru(
                        rs.getInt("id"), rs.getString("aciklama"), getKisiDao().get(rs.getInt("kisi_id"))
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.basvuruList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from basvuru where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Basvuru a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from basvuru where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from basvuru");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Basvuru a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update basvuru set kisi_id=?,aciklama=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a.getKisi().getId());
            st.setString(2, a.getAciklama());
            st.setInt(3, a.getId());

            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Basvuru a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into basvuru (kisi_id,aciklama) values (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, a.getKisi().getId());
            st.setString(2, a.getAciklama());
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
