/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.entity;

/**
 *
 * @author Tom
 */
public class KieuDang {
    private int idKieuDang;
    private String maKieuDang;
    private String tenKieuDang;

    public KieuDang() {
    }

    public KieuDang(int idKieuDang, String maKieuDang, String tenKieuDang) {
        this.idKieuDang = idKieuDang;
        this.maKieuDang = maKieuDang;
        this.tenKieuDang = tenKieuDang;
    }

    public int getIdKieuDang() {
        return idKieuDang;
    }

    public void setIdKieuDang(int idKieuDang) {
        this.idKieuDang = idKieuDang;
    }

    public String getMaKieuDang() {
        return maKieuDang;
    }

    public void setMaKieuDang(String maKieuDang) {
        this.maKieuDang = maKieuDang;
    }

    public String getTenKieuDang() {
        return tenKieuDang;
    }

    public void setTenKieuDang(String tenKieuDang) {
        this.tenKieuDang = tenKieuDang;
    }
    
}
