/*
 * To change this license header, choose License HeaCv in Project Properties.
 * To change this template fdosyae, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Cv;
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
public class CvDAO {

    private Cv cv;
    private ArrayList cvList;
    private DosyaDAO dosyaDao;

    public DosyaDAO getDosyaDao() {
        if (dosyaDao == null) {
            dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

    public Cv get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from cv where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.cv = new Cv(rs.getInt("id"), rs.getString("adi"), getDosyaDao().get(rs.getInt("dosya_id")), rs.getString("aciklama"));
            } else {
                this.cv = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.cv;
    }

    public ArrayList<Cv> list() {
        this.cvList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cv");
            while (rs.next()) {
                this.cvList.add(new Cv(
                        rs.getInt("id"), rs.getString("adi"), getDosyaDao().get(rs.getInt("dosya_id")), rs.getString("aciklama")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.cvList;
    }

    public ArrayList<Cv> list(int page, int pageSize) {
        this.cvList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cv order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.cvList.add(new Cv(
                        rs.getInt("id"), rs.getString("adi"), getDosyaDao().get(rs.getInt("dosya_id")), rs.getString("aciklama")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.cvList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from cv where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Cv a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from cv where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from cv");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Cv a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update cv set adi=?,dosya_id=?,aciklama=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getDosya().getId());
            st.setString(3, a.getAciklama());
            st.setInt(4, a.getId());

            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Cv a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into cv (adi,dosya_id,aciklama) values (?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getDosya().getId());
            st.setString(3, a.getAciklama());
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
