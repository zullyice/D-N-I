/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import java.util.ArrayList;
import pro1041.com.entity.MauSac;
import pro1041.com.utils.DBConnect;
import java.sql.*;

/**
 *
 * @author Van Can
 */
public class MauSacService {

    DBConnect dBConnect = new DBConnect();

    public ArrayList<MauSac> getALL() {
        ArrayList<MauSac> list = new ArrayList<>();
        String sql = "SELECT  id_mauSac,maMauSac,tenMauSac,ngayTao,ngaySua  FROM dbo.MauSac";
        try (Connection conn = dBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                MauSac k = new MauSac();
                k.setId(rs.getInt("id_mauSac"));
                k.setMaMauSac(rs.getString("maMauSac"));
                k.setTenMauSac(rs.getString("tenMauSac"));
                k.setNgayTao(rs.getDate("ngayTao"));
                k.setNgaySua(rs.getDate("ngaySua"));
                list.add(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addNew(MauSac mauSac) {
        String sql = "INSERT INTO dbo.MauSac(maMauSac,tenMauSac) VALUES (?,?)";
        try (Connection conn = dBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            pst.setObject(1, mauSac.getMaMauSac());
            pst.setObject(2, mauSac.getTenMauSac());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int Update(MauSac mauSac, String ma) {
        String sql = "UPDATE dbo.MauSac SET tenMauSac = ? WHERE maMauSac = ? ";
        try (Connection conn = dBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            pst.setObject(1, mauSac.getTenMauSac());
            pst.setString(2, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkIdTrung(String id) {
        String sql = "SELECT COUNT(*) AS count FROM dbo.MauSac WHERE maMauSac = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {

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
