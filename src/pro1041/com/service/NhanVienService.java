/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import java.util.ArrayList;
import java.util.List;
import pro1041.com.entity.NhanVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import pro1041.com.utils.DBConnect;

/**
 *
 * @author Tom
 */
public class NhanVienService {

    public List<NhanVien> getAll() {
        String sql = """
                     SELECT [id_nhanVien]
                           ,[maNhanVien]
                           ,[tenNhanVien]
                     ,[taiKhoan]
                           ,[matKhau]
                           ,[gioiTinh]
                           ,[trangThai]
                           ,[sdt]
                           ,[ngayTao]
                           ,[ngaySua]
                       FROM [dbo].[NhanVien]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<NhanVien> dsnv = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getString(8), rs.getDate(9), rs.getDate(10));
                dsnv.add(nv);
            }
            return dsnv;
        } catch (Exception e) {
        }
        return null;
    }

    public int add(NhanVien nv) {
         String checkSql = """
                          SELECT COUNT(*) FROM [dbo].[NhanVien] WHERE maNhanVien = ?
                          """;
        String sql = """
                     INSERT INTO [dbo].[NhanVien]
                                ([maNhanVien]
                                ,[tenNhanVien]
                     ,[taiKhoan]
                           ,[matKhau]
                                ,[gioiTinh]
                                ,[trangThai]
                                ,[sdt]
                                ,[ngayTao]
                                ,[ngaySua])
                          VALUES
                                (?,?,?,?,?,?,?,?,?)
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement pscheck = con.prepareStatement(checkSql)) {
            pscheck.setString(1, nv.getMaNhanVien());
            ResultSet rs = pscheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại " + nv.getMaNhanVien());
                return 0;
            }
            ps.setObject(1, nv.getMaNhanVien());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.getTaiKhoan());
            ps.setObject(4, nv.getMatKhau());
            ps.setObject(5, nv.getGioiTinh());
            ps.setObject(6, nv.isTrangThai());
            ps.setObject(7, nv.getSdt());
            ps.setObject(8, nv.getNgayTao());
            ps.setObject(9, nv.getNgaySua());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean xoa(int id) {
        String sql = """
                     DELETE FROM [dbo].[NhanVien]
                           WHERE id_nhanVien=?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int sua(int id, NhanVien nv) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [maNhanVien] = ?
                           ,[tenNhanVien] = ?
                     ,[taiKhoan] = ?
                           ,[matKhau] = ?
                           ,[gioiTinh] = ?
                           ,[trangThai] =?
                           ,[sdt] = ?
                           ,[ngayTao] = ?
                           ,[ngaySua] = ?
                      WHERE id_nhanVien=?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getMaNhanVien());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.getTaiKhoan());
            ps.setObject(4, nv.getMatKhau());
            ps.setObject(5, nv.getGioiTinh());
            ps.setObject(6, nv.isTrangThai());
            ps.setObject(7, nv.getSdt());
            ps.setObject(8, nv.getNgayTao());
            ps.setObject(9, nv.getNgaySua());
            ps.setObject(10, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public DefaultComboBoxModel<String> getAllTenNV() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = """
                           SELECT [tenNhanVien]
                             FROM [dbo].[NhanVien] WHERE trangThai = 1 
                           """;
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String nhanVien = rs.getString("tenNhanVien");
                model.addElement(nhanVien);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
}
