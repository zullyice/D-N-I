/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import pro1041.com.utils.DBConnect;

import pro1041.com.entity.KichThuoc;
import java.util.ArrayList;
import java.sql.*;
import pro1041.com.utils.DBConnect;



public class KichThuocService {
    DBConnect dbconnect = new DBConnect();

    public ArrayList<KichThuoc> getAll() {
        String sql = "SELECT * FROM dbo.KichThuoc";
        ArrayList<KichThuoc> list = new ArrayList<>();
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                KichThuoc t = new KichThuoc();
                t.setMaKichThuoc(rs.getString("maKichThuoc"));
                t.setTenKichThuoc(rs.getString("tenKichThuoc"));
                t.setNgayTao(rs.getDate("ngayTao"));
                t.setNgaySua(rs.getDate("ngaySua"));
                t.setTrangThai(rs.getBoolean("trangThai"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addNew(KichThuoc kichthuoc) {
        String sql = "INSERT INTO KichThuoc (maKichThuoc, tenKichThuoc) "
                +  "VALUES"
                +  "(?,?)";
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)){
            pst.setObject(1,kichthuoc.getMaKichThuoc());
            pst.setObject(2,kichthuoc.getTenKichThuoc());
            
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<KichThuoc> SearchByMa(String tenKichThuoc) {
        String sql = "SELECT * FROM dbo.KichThuoc WHERE tenKichThuoc LIKE '%" + tenKichThuoc + "%'";
        ArrayList<KichThuoc> list = new ArrayList<>();
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                KichThuoc t = new KichThuoc();
//                t.setId(rs.getInt("id_kichThuoc"));
                t.setMaKichThuoc(rs.getString("maKichThuoc"));
                t.setTenKichThuoc(rs.getString("tenKichThuoc"));
                t.setNgayTao(rs.getDate("ngayTao"));
                t.setNgaySua(rs.getDate("ngaySua"));
                t.setTrangThai(rs.getBoolean("trangThai"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
        
    }

    public int Update(KichThuoc kichthuoc,String ma) {
        String sql = "UPDATE dbo.KichThuoc SET tenKichThuoc = ? WHERE maKichThuoc = ? ";
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)){
            pst.setObject(1,kichthuoc.getTenKichThuoc());
            pst.setString(2,ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean checkIdTrung(String id) {
        String sql = "SELECT COUNT(*) AS count FROM dbo.KichThuoc WHERE maKichThuoc = ?";
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {

            pst.setObject(1, id);
            ResultSet rs = pst.executeQuery();  
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
