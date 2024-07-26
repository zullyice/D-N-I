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
public class HoaDonCho {
    private int id_HoaDon;
    private boolean trangThai;
    private String tenHoaDon;
    private Date ngayTao;
    private int id_nhanVien;
    private int id_KhachHang;
    private int id_HTTT;

    public HoaDonCho() {
    }

    public HoaDonCho(int id_HoaDon, boolean trangThai, Date ngayTao, int id_nhanVien, int id_KhachHang, int id_HTTT) {
        this.id_HoaDon = id_HoaDon;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.id_nhanVien = id_nhanVien;
        this.id_KhachHang = id_KhachHang;
        this.id_HTTT = id_HTTT;
    }

    public HoaDonCho(int id_HoaDon, boolean trangThai, String tenHoaDon, Date ngayTao, int id_nhanVien, int id_KhachHang, int id_HTTT) {
        this.id_HoaDon = id_HoaDon;
        this.trangThai = trangThai;
        this.tenHoaDon = tenHoaDon;
        this.ngayTao = ngayTao;
        this.id_nhanVien = id_nhanVien;
        this.id_KhachHang = id_KhachHang;
        this.id_HTTT = id_HTTT;
    }
    

    public HoaDonCho(int id_HoaDon, String trangThai, Date ngayTao) {
        
    }

    public int getId_HoaDon() {
        return id_HoaDon;
    }

    public void setId_HoaDon(int id_HoaDon) {
        this.id_HoaDon = id_HoaDon;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getId_nhanVien() {
        return id_nhanVien;
    }

    public void setId_nhanVien(int id_nhanVien) {
        this.id_nhanVien = id_nhanVien;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public int getId_HTTT() {
        return id_HTTT;
    }

    public void setId_HTTT(int id_HTTT) {
        this.id_HTTT = id_HTTT;
    }

    public String getTenHoaDon() {
        return tenHoaDon;
    }

    public void setTenHoaDon(String tenHoaDon) {
        this.tenHoaDon = tenHoaDon;
    }
    
    
    
}
