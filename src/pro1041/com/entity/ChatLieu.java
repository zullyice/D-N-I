/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.entity;

import java.time.LocalDate;
import java.util.Date;




public class ChatLieu {
    private Integer id ;
    private String machatLieu;
    private String tenchatLieu;
    private Date ngayTao;
    private Date ngaySua;
    private String moTa;
    private boolean trangThai;

    public ChatLieu() {
    }

    public ChatLieu(Integer id, String machatLieu, String tenchatLieu, Date ngayTao, Date ngaySua, String moTa, boolean trangThai) {
        this.id = id;
        this.machatLieu = machatLieu;
        this.tenchatLieu = tenchatLieu;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public ChatLieu(String maChatLieu, String tenChatLieu) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public String getMachatLieu() {
        return machatLieu;
    }

    public void setMachatLieu(String machatLieu) {
        this.machatLieu = machatLieu;
    }

    public String getTenchatLieu() {
        return tenchatLieu;
    }

    public void setTenchatLieu(String tenchatLieu) {
        this.tenchatLieu = tenchatLieu;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   
}
