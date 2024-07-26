/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.entity;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Van Can
 */
public class KichThuoc {
    private Integer id;
    private String maKichThuoc;
    private String tenKichThuoc;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public KichThuoc() {
    }

    public KichThuoc(Integer id, String maKichThuoc, String tenKichThuoc, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    // Getters
    public String getMaKichThuoc() { return maKichThuoc; }
    public String getTenKichThuoc() { return tenKichThuoc; }
   
    public boolean getTrangThai() { return trangThai; } // Không cần tham số

    // Setters
    public void setMaKichThuoc(String maKichThuoc) { this.maKichThuoc = maKichThuoc; }
    public void setTenKichThuoc(String tenKichThuoc) { this.tenKichThuoc = tenKichThuoc; }

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
    
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }

    public boolean isTrangThai() {
        return trangThai;
    }

    

}
