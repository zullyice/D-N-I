/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import java.util.ArrayList;
import pro1041.com.entity.KhachHang;
import pro1041.com.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tom
 */
public class KhachHangService {

    public List<KhachHang> getAll() {
        String sql = """
                     SELECT [id_khachHang],[maKh],[hoTenKh],[diaChi],[gioiTinh],[email],[sdt],[ngayTao],[ngaySua]
                                              FROM [dbo].[KhachHang]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<KhachHang> dskh = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9));
                dskh.add(kh);
            }
            return dskh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHang> selectkh(String makh) {
        List<KhachHang> data = new ArrayList<>();
        String sql = """
                     SELECT * FROM KhachHang WHERE maKh LIKE ?
                     """;
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang h = new KhachHang();
                h.setMaKh(rs.getString("maKh"));
                h.setHoTenKh(rs.getString("hoTenKh"));
                h.setDiaChi(rs.getString("diaChi"));
                h.setSdt(rs.getString("sdt"));
                h.setEmail(rs.getString("email"));
                h.setGioiTinh(rs.getString("gioiTinh"));
                data.add(h);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return data;
    }

    public List<KhachHang> timKiem(String makh) {
        List<KhachHang> data = new ArrayList<>();
        String sql = """
                     SELECT * FROM KhachHang WHERE maKh LIKE ? or hoTenKh LIKE ? OR sdt = ? 
                     """;
        Connection cn = DBConnect.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, makh);
            ps.setString(2, makh);
            ps.setString(3, makh);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang h = new KhachHang();
                h.setMaKh(rs.getString("maKh"));
                h.setHoTenKh(rs.getString("hoTenKh"));
                h.setDiaChi(rs.getString("diaChi"));
                h.setSdt(rs.getString("sdt"));
                h.setEmail(rs.getString("email"));
                h.setGioiTinh(rs.getString("gioiTinh"));
                data.add(h);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return data;
    }

    public int add(KhachHang khachHang) {
        String checkSql = """
                          SELECT COUNT(*) FROM [dbo].[KhachHang] WHERE maKh = ?
                          """;
        String sql = """
                    INSERT INTO [dbo].[KhachHang]
                               ([maKh]
                               ,[hoTenKh]
                               ,[diaChi]
                               ,[gioiTinh]
                               ,[email]
                               ,[sdt]
                               ,[ngayTao]
                               ,[ngaySua])
                         VALUES
                               (?,?,?,?,?,?,?,?)
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); PreparedStatement pscheck = con.prepareStatement(checkSql)) {
            pscheck.setString(1, khachHang.getMaKh());
            ResultSet rs = pscheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại " + khachHang.getMaKh());
                return 0;
            }
            ps.setObject(1, khachHang.getMaKh());
            ps.setObject(2, khachHang.getHoTenKh());
            ps.setObject(3, khachHang.getDiaChi());
            ps.setObject(4, khachHang.getGioiTinh());
            ps.setObject(5, khachHang.getEmail());
            ps.setObject(6, khachHang.getSdt());
            ps.setObject(7, khachHang.getNgayTao());
            ps.setObject(8, khachHang.getNgaySua());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean xoa(int id) {
        String sql = """
          DELETE FROM [dbo].[KhachHang]
                WHERE id_khachHang=?
          """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int sua(int id, KhachHang kh) {
        String sql = """
              UPDATE [dbo].[KhachHang]
                 SET [maKh] = ?
                    ,[hoTenKh] =?
                    ,[diaChi] = ?
                    ,[gioiTinh] = ?
                    ,[email] = ?
                    ,[sdt] = ?
                    ,[ngayTao] = ?
                    ,[ngaySua] = ?
               WHERE id_khachHang=?
              """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, kh.getMaKh());
            ps.setObject(2, kh.getHoTenKh());
            ps.setObject(3, kh.getDiaChi());
            ps.setObject(4, kh.getGioiTinh());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getSdt());
            ps.setObject(7, kh.getNgayTao());
            ps.setObject(8, kh.getNgaySua());
            ps.setObject(9, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void ThemKhandSDT(String tenKhachHang, String sdt) {
        String sqlInsertKhachHang = "INSERT INTO KHACHHANG (hoTenKh, sdt) VALUES (?, ?)";

        if (tenKhachHang == null || tenKhachHang.trim().isEmpty() || tenKhachHang.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sdt == null || !sdt.matches("\\d{1,11}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sqlInsertKhachHang, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tenKhachHang);
            statement.setString(2, sdt);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm khách hàng. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
