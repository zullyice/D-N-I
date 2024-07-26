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
public class KhuyenMai {

    private int id_khuyenMai;
    private String tenKM;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Double giaTri;
    private String donVi;
    private boolean trangThai;
    private Date ngayTao;
    private Date ngaySua;

    public KhuyenMai() {
    }

    public KhuyenMai(int id_khuyenMai, String tenKM, Date ngayBatDau, Date ngayKetThuc, Double giaTri, String donVi, boolean trangThai, Date ngayTao, Date ngaySua) {
        this.id_khuyenMai = id_khuyenMai;
        this.tenKM = tenKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
        this.donVi = donVi;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public int getId_khuyenMai() {
        return id_khuyenMai;
    }

    public void setId_khuyenMai(int id_khuyenMai) {
        this.id_khuyenMai = id_khuyenMai;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Double giaTri) {
        this.giaTri = giaTri;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public boolean getTrangThai() {
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

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "id_khuyenMai=" + id_khuyenMai + ", tenKM=" + tenKM + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", giaTri=" + giaTri + ", donVi=" + donVi + ", trangThai=" + trangThai + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + '}';
    }
    public Date getNgaySuaOrDefault() {
        return (ngaySua != null) ? ngaySua : new Date();
    }
}
