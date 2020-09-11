/*
 * To change this license header, choose License HeaGrup in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Grup;
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
public class GrupDAO {

    private Grup grup;
    private ArrayList grupList;

    
    public Grup get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from grup where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.grup = new Grup(rs.getInt("id"), rs.getString("adi"), rs.getBoolean("admin"), rs.getBoolean("alan"), rs.getBoolean("veren"));
            } else {
                this.grup = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grup;
    }

    public ArrayList<Grup> list() {
        this.grupList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from grup");
            while (rs.next()) {
                this.grupList.add(new Grup(
                        rs.getInt("id"), rs.getString("adi"), rs.getBoolean("admin"), rs.getBoolean("alan"), rs.getBoolean("veren")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grupList;
    }

    public ArrayList<Grup> list(int page, int pageSize) {
        this.grupList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from grup order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.grupList.add(new Grup(
                        rs.getInt("id"), rs.getString("adi"), rs.getBoolean("admin"), rs.getBoolean("alan"), rs.getBoolean("veren")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.grupList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from grup where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Grup a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from grup where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from grup");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Grup a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update grup set adi=?,admin=?,alan=?,veren=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setBoolean(2, a.isAdmin());
            st.setBoolean(3, a.isAlan());
            st.setBoolean(4, a.isVeren());
            st.setInt(5, a.getId());
            
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Grup a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into grup (adi,admin,alan,veren) values (?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setBoolean(2, a.isAdmin());
            st.setBoolean(3, a.isAlan());
            st.setBoolean(4, a.isVeren());
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
