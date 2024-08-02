/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tom
 */
public class UserDAO {
    public static String getUserFullName(String taiKhoan) {
        String tenNhanVien = null;
        Connection con = DBConnect.getConnection();
        if (con != null) {
            String sql = """
                       SELECT tenNhanVien FROM [dbo].[NhanVien] WHERE taiKhoan = ?
                     """;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, taiKhoan);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("tenNhanVien");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tenNhanVien; // Or return an empty string if you prefer
    }

}
