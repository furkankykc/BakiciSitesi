/*
 * To change this license header, choose License HeaIl in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Il;
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
public class IlDAO {

    private Il il;
    private ArrayList ilList;


    
    
    public Il get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from il where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.il = new Il(rs.getInt("id"), rs.getString("adi"));
            } else {
                this.il = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.il;
    }

    public ArrayList<Il> list() {
        this.ilList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from il");
            while (rs.next()) {
                this.ilList.add(new Il(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.ilList;
    }

    public ArrayList<Il> list(int page, int pageSize) {
        this.ilList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from il order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.ilList.add(new Il(
                        rs.getInt("id"), rs.getString("adi")
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.ilList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from il where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Il a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from il where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from il");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Il a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "update il set adi=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setInt(2, a.getId());
            
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(Il a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into il (adi) values (?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
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
