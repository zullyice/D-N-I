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
public class ThuongHieu {
    private int idThuongHieu;
    private String tenThuongHieu;
    private String maThuonghieu;
    private Date ngayTao;
    private Date ngaySua;

    public ThuongHieu(int idThuongHieu, String maThuongHieu, String tenThuongHieu, Date ngayTao, Date ngaySua) {
        this.idThuongHieu = idThuongHieu;
        this.tenThuongHieu = maThuongHieu;
        this.maThuonghieu = tenThuongHieu;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public ThuongHieu() {
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getMaThuonghieu() {
        return maThuonghieu;
    }

    public void setMaThuonghieu(String maThuonghieu) {
        this.maThuonghieu = maThuonghieu;
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
