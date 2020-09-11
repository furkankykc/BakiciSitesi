/*
 * To change this license header, choose License HeaKisi in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Kisi;
import Entity.Yorum_;
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
public class KisiDAO {

    private Kisi kisi;
    private ArrayList kisiList;
    private GrupDAO grupDao;
    private AdresDAO adresDao;
    private ReferansDAO referansDao;
    private YorumDAO yorumDao;

    public GrupDAO getGrupDao() {
        if (grupDao == null) {
            grupDao = new GrupDAO();
        }
        return grupDao;
    }

    public AdresDAO getAdresDao() {
        if (adresDao == null) {
            adresDao = new AdresDAO();
        }
        return adresDao;
    }

    public ReferansDAO getReferansDao() {
        if (referansDao == null) {
            referansDao = new ReferansDAO();
        }
        return referansDao;
    }

    public YorumDAO getYorumDao() {
        if (yorumDao == null) {
            yorumDao = new YorumDAO();
        }
        return yorumDao;
    }

    public Kisi get(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from kisi where id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.kisi = new Kisi(rs.getInt("id"), rs.getString("adi"), rs.getString("soyadi"), rs.getInt("yas"), rs.getString("tel_no"), rs.getString("sifre"), rs.getString("kullanici_adi"), rs.getString("biyografi"), getGrupDao().get(rs.getInt("grup_id")), getReferansDao().get(rs.getInt("referans_id")), getAdresDao().get(rs.getInt("adres_id")), getYorumDao().getKisiYorum(rs.getInt("id")));
                //, getYorumDao().getKisiYorum(rs.getInt("id"))
            } else {
                this.kisi = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisi;
    }

    public Kisi get(String name) {
        Connection con = ConnectionManager.getConnection();
        String sql = "select * from kisi where kullanici_adi=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.kisi = new Kisi(rs.getInt("id"), rs.getString("adi"), rs.getString("soyadi"), rs.getInt("yas"), rs.getString("tel_no"), rs.getString("sifre"), rs.getString("kullanici_adi"), rs.getString("biyografi"), getGrupDao().get(rs.getInt("grup_id")), getReferansDao().get(rs.getInt("referans_id")), getAdresDao().get(rs.getInt("adres_id")), getYorumDao().getKisiYorum(rs.getInt("id")));
            } else {
                this.kisi = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisi;
    }

    public ArrayList<Kisi> list() {
        this.kisiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kisi");
            while (rs.next()) {
                this.kisiList.add(new Kisi(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("soyadi"), rs.getInt("yas"), rs.getString("tel_no"), rs.getString("sifre"), rs.getString("kullanici_adi"), rs.getString("biyografi"), getGrupDao().get(rs.getInt("grup_id")), getReferansDao().get(rs.getInt("referans_id")), getAdresDao().get(rs.getInt("adres_id")), getYorumDao().getKisiYorum(rs.getInt("id"))
                //                , getYorumDao().getKisiYorum(rs.getInt("id"))
                ));
                System.out.println("-----------------");
            }
            //con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisiList;
    }

    public ArrayList<Kisi> list(int page, int pageSize) {
        this.kisiList = new ArrayList();
        Connection con = ConnectionManager.getConnection();
        int start = (page - 1) * pageSize;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from kisi order by id asc limit " + start + "," + pageSize);
            while (rs.next()) {
                this.kisiList.add(new Kisi(
                        rs.getInt("id"), rs.getString("adi"), rs.getString("soyadi"), rs.getInt("yas"), rs.getString("tel_no"), rs.getString("sifre"), rs.getString("kullanici_adi"), rs.getString("biyografi"), getGrupDao().get(rs.getInt("grup_id")), getReferansDao().get(rs.getInt("referans_id")), getAdresDao().get(rs.getInt("adres_id")), getYorumDao().getKisiYorum(rs.getInt("id"))
                ));
                System.out.println("-----------------");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.kisiList;
    }

    public void delete(int id) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from kisi where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Kisi a) {
        Connection con = ConnectionManager.getConnection();
        String sql = "delete from kisi where id=?";
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
            ResultSet rs = st.executeQuery("select count(id) as a_count from kisi");
            rs.next();
            count = rs.getInt("a_count");
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void update(Kisi a) {
        Connection con = ConnectionManager.getConnection();
//rs.getInt("id"), rs.getString("adi"), rs.getString("soyadi"),rs.getInt("yas"), rs.getString("tel_no"), rs.getString("sifre"), rs.getString("kullanici_adi"), rs.getString("biyografi"), getGrupDao().get(rs.getInt("grup_id")), getReferansDao().get(rs.getInt("referans_id")), getAdresDao().get(rs.getInt("adres_id"))

        String sql = "update kisi set adi=?,soyadi=?,yas=?,tel_no=?,sifre=?,kullanici_adi=?,biyografi=?,grup_id=?,referans_id=?,adres_id=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getAdi());
            st.setString(2, a.getSoyadi());
            st.setInt(3, a.getYas());
            st.setString(4, a.getTelNo());
            st.setString(5, a.getSifre());
            st.setString(6, a.getKullaniciAdi());
            st.setString(7, a.getBiyografi());
            st.setInt(8, a.getGrup().getId());
            st.setInt(9, a.getReferans().getId());
            st.setInt(10, a.getAdres().getId());
            st.setInt(11, a.getId());

            st.executeUpdate();
            st.executeUpdate("delete from kisi_yorum where kisi_id=" + a.getId());
            for (Yorum_ g : a.getYorum()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into kisi_yorum(kisi_id,yorum_id) values(" + a.getId() + ",'" + g.getId() + "')");

            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int createEx(Kisi a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into kisi (adi,soyadi,yas,tel_no,sifre,kullanici_adi,biyografi,grup_id) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setString(2, a.getSoyadi());
            st.setInt(3, a.getYas());
            st.setString(4, a.getTelNo());
            st.setString(5, a.getSifre());
            st.setString(6, a.getKullaniciAdi());
            st.setString(7, a.getBiyografi());
            st.setInt(8, a.getGrup().getId());

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

    public int create(Kisi a) {
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into kisi (adi,soyadi,yas,tel_no,sifre,kullanici_adi,biyografi,grup_id,referans_id,adres_id) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getAdi());
            st.setString(2, a.getSoyadi());
            st.setInt(3, a.getYas());
            st.setString(4, a.getTelNo());
            st.setString(5, a.getSifre());
            st.setString(6, a.getKullaniciAdi());
            st.setString(7, a.getBiyografi());
            st.setInt(8, a.getGrup().getId());
            st.setInt(9, a.getReferans().getId());
            st.setInt(10, a.getAdres().getId());

            st.executeUpdate();
            int kid = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                kid = rs.getInt(1);

            }
            for (Yorum_ g : a.getYorum()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate("insert into kisi_yorum(kisi_id,yorum_id) values(+" + kid + ",'" + g.getId() + "')");

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
