/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.entity;

import java.util.Date;

/**
 *
 * @author Tom
 */
public class HoaDon {
    private int idHoaDon;
    private int idHoaDonChiTiet;
    private int idSanPham;
    private String tenSanPham;
    private String maHoaDon;
    private String maHDCT;
    private String tenHoaDon;
    private String tenNhanVien;
    private String tenKhachHang;
    private String loaiTT;
    private boolean trangThai;
    private int soLuong;
    private int tongTien;
    private Date ngayTaoHD;
    private Date ngayTaoSP;
    private int gia;
    private int idSPCT;

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, String maHoaDon, String tenHoaDon, boolean trangThai, Date ngayTaoHD ,String tenNhanVien, String tenKhachHang, String loaiTT) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.tenHoaDon = tenHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.loaiTT = loaiTT;
        this.trangThai = trangThai;
        this.ngayTaoHD = ngayTaoHD;
    }

    public HoaDon(String tenSanPham,  int soLuong,int gia,int tongTien, Date ngayTaoHD, Date ngayTaoSP) {
        this.tenSanPham = tenSanPham;
        this.maHDCT = maHDCT;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tongTien = tongTien;
        this.ngayTaoHD = ngayTaoHD;
        this.ngayTaoSP = ngayTaoSP;
    }

    public HoaDon(int idHoaDon, String tenNhanVien, String tenKhachHang, String loaiTT) {
        this.idHoaDon = idHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.loaiTT = loaiTT;
    }

    public HoaDon(int idHoaDon, int idSanPham, String tenSanPham, int soLuong, int gia,int tongTien) {
        this.idHoaDon = idHoaDon;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tongTien = tongTien;
    }
    

    
    
    public HoaDon(int idHoaDon, int idHoaDonChiTiet, int idSanPham, String tenSanPham, String maHoaDon, String maHDCT, String tenHoaDon, String tenNhanVien, String tenKhachHang, String loaiTT, boolean trangThai, int soLuong, int tongTien, Date ngayTaoHD, Date ngayTaoSP) {
        this.idHoaDon = idHoaDon;
        this.idHoaDonChiTiet = idHoaDonChiTiet;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.maHoaDon = maHoaDon;
        this.maHDCT = maHDCT;
        this.tenHoaDon = tenHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.loaiTT = loaiTT;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngayTaoHD = ngayTaoHD;
        this.ngayTaoSP = ngayTaoSP;
    }

    public int getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(int idSPCT) {
        this.idSPCT = idSPCT;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdHoaDonChiTiet() {
        return idHoaDonChiTiet;
    }

    public void setIdHoaDonChiTiet(int idHoaDonChiTiet) {
        this.idHoaDonChiTiet = idHoaDonChiTiet;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getTenHoaDon() {
        return tenHoaDon;
    }

    public void setTenHoaDon(String tenHoaDon) {
        this.tenHoaDon = tenHoaDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getLoaiTT() {
        return loaiTT;
    }

    public void setLoaiTT(String loaiTT) {
        this.loaiTT = loaiTT;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTaoHD() {
        return ngayTaoHD;
    }

    public void setNgayTaoHD(Date ngayTaoHD) {
        this.ngayTaoHD = ngayTaoHD;
    }

    public Date getNgayTaoSP() {
        return ngayTaoSP;
    }

    public void setNgayTaoSP(Date ngayTaoSP) {
        this.ngayTaoSP = ngayTaoSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    
    
    
}
