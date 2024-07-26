/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import pro1041.com.entity.NhaSanXuat;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.util.List;

import pro1041.com.utils.DBConnect;

/**
 *
 * @author datsky
 */
public class NhaSanXuatService {

    public List<NhaSanXuat> getAll() {
        String sql = """
                SELECT id_nsx, tenNSX, maNSX
                FROM NhaSanXuat
                 """;
        ArrayList<NhaSanXuat> listNsx = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaSanXuat nsx = new NhaSanXuat(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
   
                );
                listNsx.add(nsx);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi để tiện kiểm tra
        }
        return listNsx; // Luôn trả về danh sách, có thể rỗng
    }

    public int add(NhaSanXuat nsx) {
        String sql = """
                     INSERT INTO NhaSanXuat (maNSX,tenNSX)
                          VALUES
                                (?,?)
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nsx.getMaNsx());
            ps.setObject(2, nsx.getTenNsx());
            
      

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Update(NhaSanXuat nsx, String ma) {
        String sql = "UPDATE dbo.NhaSanXuat SET tenNSX = ? WHERE maNSX = ? ";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            pst.setObject(1, nsx.getTenNsx());
            pst.setString(2, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean xoa(int id) {
        String sql = """
                     BEGIN TRANSACTION;
                           
                           -- Step 1: Update the SanPhamChiTiet table
                           UPDATE dbo.SanPhamChiTiet
                           SET id_NSX = NULL
                           WHERE id_NSX = ?;
                           
                           -- Step 2: Delete from the KichThuoc table
                           DELETE FROM dbo.NhaSanXuat
                           WHERE id_NSX = ?;
                           
                           COMMIT TRANSACTION;
                           	
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.setObject(2, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkIdTrung(String id) {
        String sql = "SELECT COUNT(*) AS count FROM dbo.NhaSanXuat WHERE maNSX = ?";
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
