/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pro1041.com.entity.KieuDang;
import pro1041.com.utils.DBConnect;

/**
 *
 * @author Tom
 */
public class KieuDangService {
     public List<KieuDang> getAll() {
        String sql = """
                SELECT id_kieuDang, maKieuDang, tenKieuDang
                FROM KieuDang
                 """;
        ArrayList<KieuDang> listKD = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KieuDang kd = new KieuDang(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                listKD.add(kd);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi để tiện kiểm tra
        }
        return listKD; // Luôn trả về danh sách, có thể rỗng
    }

    public int add(KieuDang kd) {
        String sql = """
                     INSERT INTO KieuDang (maKieuDang,tenKieuDang)
                          VALUES
                                (?,?)
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kd.getMaKieuDang());
            ps.setObject(2, kd.getTenKieuDang());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Update(KieuDang kieuDang, String ma) {
        String sql = "UPDATE dbo.KieuDang SET tenKieuDang = ? WHERE maKieuDang = ? ";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            pst.setObject(1, kieuDang.getTenKieuDang());
            pst.setString(2, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkIdTrung(String id) {
        String sql = "SELECT COUNT(*) AS count FROM dbo.KieuDang WHERE maKieuDang = ?";
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
