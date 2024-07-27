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
public class SanPham {

    private int id_sanPham;
    private String tenSanPham;
    private int gia;
    private int soluongtonkho;
    private Date ngayTao;
    private Date ngaySua;
    private String tenNSX;
    private String tenThuongHieu;
    private String tenChatLieu;
    private String tenKichThuoc;
    private String tenMauSac;
    private String tenKhoa;
    private int id_SPCT;
    private String kieuDang;

    public SanPham() {
    }

    public SanPham(int id_sanPham, int soluongtonkho) {
        this.id_sanPham = id_sanPham;
        this.soluongtonkho = soluongtonkho;
    }

   

    public SanPham(int id_sanPham, String tenSanPham, Date ngayTao, Date ngaySua, String tenNSX, String tenThuongHieu) {
        this.id_sanPham = id_sanPham;
        this.tenSanPham = tenSanPham;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tenNSX = tenNSX;
        this.tenThuongHieu = tenThuongHieu;
    }

    public SanPham(int id_SPCT, String tenSanPham, int soluongtonkho, int gia, String tenThuongHieu, String tenNSX, String tenChatLieu, String tenKichThuoc, String tenKhoa, String kieuDang, String tenMauSac) {
        this.id_SPCT = id_SPCT;
        this.tenSanPham = tenSanPham;
        this.soluongtonkho = soluongtonkho;
        this.gia = gia;
        this.tenNSX = tenNSX;
        this.tenThuongHieu = tenThuongHieu;
        this.tenChatLieu = tenChatLieu;
        this.tenKichThuoc = tenKichThuoc;
        this.tenMauSac = tenMauSac;
        this.tenKhoa = tenKhoa;
        this.kieuDang = kieuDang;
    }
    
    
    public SanPham(int id_SPCT,int id_sanPham , String tenSanPham, int gia, int soluongtonkho, Date ngayTao, Date ngaySua, String tenNSX, String tenThuongHieu, String tenChatLieu, String kieuDang,String tenKichThuoc, String tenMauSac, String tenKhoa) {
        this.id_sanPham = id_sanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soluongtonkho = soluongtonkho;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tenNSX = tenNSX;
        this.tenThuongHieu = tenThuongHieu;
        this.tenChatLieu = tenChatLieu;
        this.tenKichThuoc = tenKichThuoc;
        this.tenMauSac = tenMauSac;
        this.tenKhoa = tenKhoa;
        this.id_SPCT = id_SPCT;
        this.kieuDang = kieuDang;
    }

    
    

    public int getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(int id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

    

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluongtonkho() {
        return soluongtonkho;
    }

    public void setSoluongtonkho(int soluongtonkho) {
        this.soluongtonkho = soluongtonkho;
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

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public int getId_SPCT() {
        return id_SPCT;
    }

    public void setId_SPCT(int id_SPCT) {
        this.id_SPCT = id_SPCT;
    }

    public String getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(String kieuDang) {
        this.kieuDang = kieuDang;
    }

    
    
    public Date getNgaySuaOrDefault() {
        return (ngaySua != null) ? ngaySua : new Date();
    }

}
