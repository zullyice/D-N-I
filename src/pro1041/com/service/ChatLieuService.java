/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.com.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pro1041.com.entity.ChatLieu;
import pro1041.com.utils.DBConnect;

/**
 *
 * @author Van Can
 */
public class ChatLieuService {

    DBConnect dbconnect = new DBConnect();

    public ArrayList<ChatLieu> getAll() {
        String sql = "SELECT id_chatLieu,tenChatLieu,maChatLieu FROM ChatLieu";
        ArrayList<ChatLieu> list = new ArrayList<>();
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChatLieu t = new ChatLieu();
                t.setMachatLieu(rs.getString("maChatLieu"));
                t.setTenchatLieu(rs.getString("tenChatLieu"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addNew(ChatLieu chLieu) {

        String sql = "INSERT INTO dbo.ChatLieu(maChatLieu,tenChatLieu) VALUES (?,?)";
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            pst.setObject(1, chLieu.getMachatLieu());
            pst.setObject(2, chLieu.getTenchatLieu());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkIdTrung(String id) {
        String sql = "SELECT COUNT(*) AS count FROM dbo.ChatLieu WHERE maChatLieu = ?";
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {

            pst.setObject(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ChatLieu> SearchByma(String maChatLieu) {
        String sql = "SELECT * FROM dbo.ChatLieu WHERE maChatLieu LIKE '%" + maChatLieu + "%'";
        ArrayList<ChatLieu> list = new ArrayList<>();
        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChatLieu t = new ChatLieu();
                t.setId(rs.getInt("id_chatLieu"));
                t.setMachatLieu(rs.getString("maChatLieu"));
                t.setTenchatLieu(rs.getString("tenChatLieu"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int Update(ChatLieu chatLieu1, String ma) {
        String sql = "UPDATE dbo.ChatLieu SET tenChatLieu = ? WHERE maChatLieu = ?";

        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, chatLieu1.getTenchatLieu());
            pst.setString(2, ma);

            return pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean Delete(String ma ){

        String sql = """
                     DELETE FROM dbo.ChatLieu WHERE maChatLieu = ?
                     """;

        try (Connection conn = dbconnect.getConnection(); PreparedStatement pst = conn.prepareCall(sql)) {
            pst.setObject(1, ma);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
