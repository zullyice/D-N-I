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
public class KhachHang {
    private int id_khachHang;
    private String maKh;
    private String hoTenKh;
    private String diaChi;
    private String gioiTinh;
    private String email;
    private String sdt;
    private Date ngayTao;
    private Date ngaySua;

    public KhachHang() {
    }

    public KhachHang(int id_khachHang, String maKh, String hoTenKh, String diaChi, String gioiTinh, String email, String sdt, Date ngayTao, Date ngaySua) {
        this.id_khachHang = id_khachHang;
        this.maKh = maKh;
        this.hoTenKh = hoTenKh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getHoTenKh() {
        return hoTenKh;
    }

    public void setHoTenKh(String hoTenKh) {
        this.hoTenKh = hoTenKh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "id_khachHang=" + id_khachHang + ", maKh=" + maKh + ", hoTenKh=" + hoTenKh + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", email=" + email + ", sdt=" + sdt + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + '}';
    }
    public Date getNgaySuaOrDefault() {
        return (ngaySua != null) ? ngaySua : new Date();
    }
    
}
