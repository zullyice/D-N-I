/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import pro1041.com.entity.HoaDon;
import pro1041.com.entity.SanPham;
import pro1041.com.utils.DBConnect;

/**
 *
 * @author Tom
 */
public class HoaDonService {

    private ResultSet rs = null;

    public List<HoaDon> getAll() {
        String sql = """
                     SELECT [id_hoaDon]
                           ,[maHoaDon]
                           ,[tenHoaDon]
                            ,hd.trangThai
                            ,hd.ngayTao
                           ,nv.tenNhanVien
                           ,kh.hoTenKh
                           ,tt.loai
                       FROM [PRO1041_SD19308].[dbo].[HoaDon] hd
                       LEFT JOIN NhanVien nv ON hd.id_nhanVien = nv.id_nhanVien
                       LEFT JOIN KhachHang kh	ON hd.id_KhachHang = kh.id_khachHang
                       LEFT JOIN ThanhToan tt ON hd.id_HTTT = tt.id_HTTT
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDon> dshd = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8));
                dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getAllSP() {
        String sql = """
                     SELECT
                     sp.tenSanPham
                     ,hdct.soLuong
                     ,spct.gia
                     ,[tongTien]
                     ,hd.ngayTao 
                     ,sp.ngayTao 
                     FROM [dbo].[HoaDonChiTiet] hdct
                     JOIN SanPham sp ON sp.id_sanPham = hdct.id_SPCT
                     LEFT JOIN HoaDon hd on hdct.id_hoaDon = hd.id_hoaDon
                     INNER JOIN SanPhamChiTiet spct ON hdct.id_SPCT = spct.id_SPCT
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDon> dshdct = new ArrayList<>();
            while (rs.next()) {
                HoaDon hdct = new HoaDon(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getDate(6));
                dshdct.add(hdct);
            }
            return dshdct;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getById(int idhd) {
        List<HoaDon> searchID = new ArrayList<>();
        String sql = """
                 	 SELECT 
                     sp.tenSanPham,
                     hdct.soLuong,
                     hdct.donGia,
                     hdct.tongTien,
                     hd.ngayTao ,
                     sp.ngayTao 
                 FROM 
                     dbo.HoaDonChiTiet hdct
                 INNER JOIN 
                     HoaDon hd ON hdct.id_hoaDon = hd.id_hoaDon
                 LEFT JOIN 
                     SanPhamChiTiet spct ON hdct.id_SPCT = spct.id_SPCT
                 LEFT JOIN 
                     SanPham sp ON spct.id_sanPham = sp.id_sanPham
                 WHERE 
                     hd.id_hoaDon = ?;
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idhd);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDon hdct = new HoaDon(
                            rs.getString("tenSanPham"),
                            rs.getInt("soLuong"),
                            rs.getInt("donGia"),
                            rs.getInt("tongTien"),
                            rs.getDate("ngayTao"),
                            rs.getDate("ngayTao")
                    );
                    searchID.add(hdct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchID;
    }

    public List<HoaDon> locTheoNgay(Date ngayBD, Date ngayKT) {
        List<HoaDon> searchID = new ArrayList<>();
        String sql = """
                 	SELECT [id_hoaDon]
                                                   ,[maHoaDon]
                                                   ,[tenHoaDon]
                                                    ,hd.trangThai
                                                    ,hd.ngayTao
                                                   ,nv.tenNhanVien
                                                   ,kh.hoTenKh
                                                   ,tt.loai
                                               FROM [PRO1041_SD19308].[dbo].[HoaDon] hd
                                               LEFT JOIN NhanVien nv ON hd.id_nhanVien = nv.id_nhanVien
                                               LEFT JOIN KhachHang kh	ON hd.id_KhachHang = kh.id_khachHang
                                               LEFT JOIN ThanhToan tt ON hd.id_HTTT = tt.id_HTTT
                        WHERE hd.ngayTao BETWEEN ? AND ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(ngayBD.getTime()));
            ps.setDate(2, new java.sql.Date(ngayKT.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8));
                    searchID.add(hd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchID;
    }

    public int updateTrangThaiHoaDon(int idHoaDon, boolean trangThai, HoaDon hoaDon) {
        String sql = "UPDATE HoaDon SET TrangThai = ?, id_nhanVien = ?, id_HTTT = ?, id_khachHang = ? WHERE id_hoaDon = ?";
        int rowsAffected = 0;

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            String getIDNhanVien = "SELECT id_nhanVien FROM [NhanVien] WHERE tenNhanVien LIKE ?";
            PreparedStatement getIDNV = con.prepareStatement(getIDNhanVien);
            getIDNV.setString(1, "%" + hoaDon.getTenNhanVien() + "%");
            ResultSet idnv = getIDNV.executeQuery();
            if (!idnv.next()) {
                throw new SQLException("Cannot find Nhà Sản Xuất for TÊN NHÂN VIÊN: " + hoaDon.getTenNhanVien());
            }
            int idNV = idnv.getInt("id_nhanVien");

            String getIDHTT = "SELECT id_HTTT FROM [ThanhToan] WHERE loai LIKE ?";
            PreparedStatement getIDHTTT = con.prepareStatement(getIDHTT);
            getIDHTTT.setString(1, "%" + hoaDon.getLoaiTT() + "%");
            ResultSet idtt = getIDHTTT.executeQuery();
            if (!idtt.next()) {
                throw new SQLException("Cannot find ThanhToan for LOẠI TT: " + hoaDon.getLoaiTT());
            }
            int idTT = idtt.getInt("id_HTTT");

            String getIDKHang = "SELECT id_khachHang FROM [KhachHang] WHERE hoTenKh LIKE ?";
            PreparedStatement getIDKH = con.prepareStatement(getIDKHang);
            getIDKH.setString(1, "%" + hoaDon.getTenKhachHang() + "%");
            ResultSet idkh = getIDKH.executeQuery();
            if (!idkh.next()) {
                throw new SQLException("Cannot find Khách Hàng for Tên KH: " + hoaDon.getTenKhachHang());
            }
            int idKH = idkh.getInt("id_khachHang");

            statement.setBoolean(1, trangThai);
            statement.setInt(2, idNV);
            statement.setInt(3, idTT);
            statement.setInt(4, idKH);
            statement.setInt(5, idHoaDon);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }

    public static int themHoaDonVaoDatabase(String tenHD, boolean trangThai) {
        String sql = "INSERT INTO HoaDon (tenHoaDon,TrangThai) VALUES (?,0)";
        int generatedId = -1;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tenHD);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return generatedId;
    }

    public DefaultComboBoxModel<String> getAllHTTT() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = """
                           SELECT [loai]
                             FROM [dbo].[ThanhToan]
                           """;
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String loaiTT = rs.getString("loai");
                model.addElement(loaiTT);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    public DefaultComboBoxModel<String> getAllKM() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try {
            String query = """
                           SELECT [tenKM]
                             FROM [dbo].[KhuyenMai] WHERE trangThai = 1
                           """;
            Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String tenKM = rs.getString("tenKM");
                model.addElement(tenKM);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    public boolean checkIdTrung(String tenHD) {
        String sql = "SELECT COUNT(*) AS count FROM dbo.HoaDon WHERE tenHoaDon = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {

            pst.setObject(1, tenHD);
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

    public void addHDCT(int idHD, SanPham sp) {
        String sql = """
                     INSERT INTO [dbo].[HoaDonChiTiet]
                                ([id_hoaDon]
                                ,[id_SPCT]
                                ,[soLuong]
                                ,[donGia]
                                ,[tongTien],
                                [trangThai]
                                )
                          VALUES
                                (?,?,?,?,?,?)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            ps.setInt(2, sp.getId_SPCT());
            ps.setInt(3, sp.getSoluongtonkho());
            ps.setInt(4, sp.getGia());
            ps.setInt(5, sp.getSoluongtonkho() * sp.getGia());
            ps.setBoolean(6, true);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
