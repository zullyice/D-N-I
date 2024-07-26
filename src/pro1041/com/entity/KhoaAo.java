/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.entity;

import java.util.Date;

/**
 *
 * @author datsky
 */
public class KhoaAo {
    private int id_khoaAo;
    private String maKhoa;
    private String tenKhoa;
    private Date ngayTao;
    private Date ngaySua;

    public KhoaAo() {
    }

    public KhoaAo(int id_khoaAo, String maKhoa, String tenKhoa, Date ngayTao, Date ngaySua) {
        this.id_khoaAo = id_khoaAo;
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public int getId_khoaAo() {
        return id_khoaAo;
    }

    public void setId_khoaAo(int id_khoaAo) {
        this.id_khoaAo = id_khoaAo;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
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
    
    
}
