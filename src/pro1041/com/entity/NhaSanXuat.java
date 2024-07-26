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
public class NhaSanXuat {
    private int idNsx;
    private String tenNsx;
    private String maNsx;


    public NhaSanXuat() {
    }

    public NhaSanXuat(int idNsx, String tenNsx, String maNsx) {
        this.idNsx = idNsx;
        this.tenNsx = tenNsx;
        this.maNsx = maNsx;
    }

    public int getIdNsx() {
        return idNsx;
    }

    public void setIdNsx(int idNsx) {
        this.idNsx = idNsx;
    }

    public String getTenNsx() {
        return tenNsx;
    }

    public void setTenNsx(String tenNsx) {
        this.tenNsx = tenNsx;
    }

    public String getMaNsx() {
        return maNsx;
    }

    public void setMaNsx(String maNsx) {
        this.maNsx = maNsx;
    }

       
    
    
    
}