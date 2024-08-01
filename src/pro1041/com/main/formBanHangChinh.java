/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pro1041.com.main;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import pro1041.com.entity.HoaDon;
import pro1041.com.entity.HoaDonCho;
import pro1041.com.entity.KhachHang;
import pro1041.com.entity.KhuyenMai;
import pro1041.com.entity.SanPham;
import static pro1041.com.main.formThemKH.maKH;
import pro1041.com.service.HoaDonService;
import pro1041.com.service.KhachHangService;
import pro1041.com.service.KhuyenMaiService;
import pro1041.com.service.NhanVienService;
import pro1041.com.service.SanPhamService;
import pro1041.com.utils.DBConnect;

/**
 *
 * @author Tom
 */
public class formBanHangChinh extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel model = new DefaultTableModel();
    private SanPhamService sanPhamService = new SanPhamService();
    private List<HoaDonCho> hoaDonTest = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private KhachHangService khachHangService = new KhachHangService();
    private NhanVienService nhanVienService = new NhanVienService();
    private Map<Integer, List<SanPham>> gioHangMap = new HashMap<>();
    private List<SanPham> sanPhamRepo = sanPhamService.getAllSPBH();
    private List<KhachHang> dskh = new ArrayList<>();
    String maKH;
    private String taiKhoan;
    private String tenHoaDon;

    /**
     * Creates new form formBanHangChinh
     */
    public formBanHangChinh() {
        this.taiKhoan = taiKhoan;
        initComponents();
        setSize(2500, 2000);
        loadSanPham();
        loadHoadon();
        fillNhanVienComboBox();
        fillHTTTComboBox();
        fillTenKM();
        createNameHD();
        txtTenHD.setText(tenHoaDon);
        txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateTienThua();
            }

            public void removeUpdate(DocumentEvent e) {
                updateTienThua();
            }

            public void insertUpdate(DocumentEvent e) {
                updateTienThua();
            }
        });
    }

    private void createNameHD() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("Hóa Đơn ");
        for (int i = 0; i < 4; i++) {
            int x = random.nextInt(10);
            sb.append(x);
        }
        tenHoaDon = sb.toString();
    }

    private void xoaHoaDonDaThanhToan(int id) {
        DefaultTableModel dtmHoaDon = (DefaultTableModel) tblHoaDonCho.getModel();
        int rowCount = dtmHoaDon.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            int id_hoaDon = (int) dtmHoaDon.getValueAt(i, 0);
            if (id_hoaDon == id) {
                dtmHoaDon.removeRow(i);
                break;
            }
        }
    }

    private double tinhTongTienGioHang() {
        double tongTien = 0;
        for (int i = 0; i < tblHDCT.getRowCount(); i++) {
            Object value = tblHDCT.getValueAt(i, 5);
            if (value != null) {
                try {
                    tongTien += Double.parseDouble(value.toString());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ ở hàng " + (i + 1) + ", cột 6", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return tongTien;
    }

    public void hienThiThongTinKhuyenMai(KhuyenMai km) {
        if (km != null) {
            txtGiaTriGiam.setText(String.valueOf(km.getGiaTri()));
            apMaKhuyenMai(km);
        }
    }

    private void apMaKhuyenMai(KhuyenMai km) {
        double tongTien = tinhTongTienGioHang();
        double giaTriGiam = km.getGiaTri();
        boolean isPercent = true;
        double tienGiam = isPercent ? (tongTien * giaTriGiam / 100) : giaTriGiam;
        double tongTienSauGiam = tongTien - tienGiam;
        txtSoTienGiam.setText(String.format("%.2f", tienGiam));
        txtTongTien.setText(String.format("%.2f", tongTienSauGiam));
        updateTienThua(giaTriGiam, isPercent);
    }

    private void updateTienThua() {
        String ten = String.valueOf(cboKhuyenMai.getSelectedItem());
        if (ten == null || ten.trim().isEmpty()) {
            updateTienThua(0.0, false);
        } else {
            try {
                double giaTriGiam = Double.parseDouble(txtGiaTriGiam.getText());
                boolean isPercent = true;
                updateTienThua(giaTriGiam, isPercent);
            } catch (NumberFormatException e) {
                //JOptionPane.showMessageDialog(null, "Giá trị giảm giá không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                updateTienThua(0.0, false);
            }
        }
    }

    private void updateTienThua(double giaTriGiam, boolean isPercent) {
        String tienKhachDuaStr = txtTienKhachDua.getText();
        if (tienKhachDuaStr.trim().isEmpty() || tienKhachDuaStr == null) {
            txtTienThua.setText("");
            return;
        }
        try {
            double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
            double tongTien = tinhTongTienGioHang();
            double tienGiam = isPercent ? (tongTien * giaTriGiam / 100) : giaTriGiam;
            double tongTienSauGiam = tongTien - tienGiam;
            double tienThua = tienKhachDua - tongTienSauGiam;
            txtTienThua.setText(String.format("%.2f", tienThua));
        } catch (NumberFormatException e) {
            txtTienThua.setText("");
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double tinhTongTienSauGiam(double tongTien, double giaTriGiam, boolean isPercent) {
        if (isPercent) {
            return tongTien - (tongTien * giaTriGiam / 100);
        } else {
            return tongTien - giaTriGiam;
        }
    }

    private void capNhatTongTien() {
        DefaultTableModel dtm = (DefaultTableModel) tblHDCT.getModel();
        double tongTien = 0;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            double giaBan = Double.parseDouble(tblHDCT.getValueAt(i, 4).toString());
            int soLuong = Integer.parseInt(tblHDCT.getValueAt(i, 3).toString());
            tongTien += giaBan * soLuong;
        }
        txtTongTien.setText(String.format("%.2f", tongTien));
    }

    public void loadSanPham() {
        model = (DefaultTableModel) tblSanPham.getModel();
        List<SanPham> list = sanPhamService.getAllSPBH();
        model.setRowCount(0);
        for (SanPham sp : list) {
            model.addRow(new Object[]{sp.getId_SPCT(), sp.getTenSanPham(), sp.getSoluongtonkho(), sp.getGia(), sp.getTenThuongHieu(), sp.getTenNSX(), sp.getTenChatLieu(), sp.getTenKichThuoc(), sp.getTenKhoa(), sp.getKieuDang(), sp.getTenMauSac()});
        }
    }

    public void loadHoadon() {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDonCho.getModel();
        dtm.setRowCount(0);
        hoaDonTest.clear();
        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement("SELECT id_hoaDon,tenHoaDon,trangThai,ngayTao FROM HoaDon WHERE TrangThai = ?")) {
            statement.setInt(1, 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_hoaDon");
                String ten = resultSet.getString("tenHoaDon");
                String trangThai = "Chưa Thanh Toán";
                String ngayTao = resultSet.getString("ngayTao");
                Object[] row = new Object[]{id, ten, trangThai, ngayTao};
                dtm.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void traLaiSanPham(int idHD) {
        List<HoaDon> hdctList = hoaDonService.getById(idHD);
        System.out.println(hdctList);
        if (hdctList != null) {
            for (HoaDon hoaDon : hdctList) {
                int rowCountHDCT = tblHDCT.getRowCount();
                for (int row = 0; row < rowCountHDCT; row++) {
                    int idSPCT = hoaDon.getIdSPCT();
                    int soLuongTraLai = hoaDon.getSoLuong();
                    int rowCount = tblSanPham.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        int idSP = Integer.parseInt(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString());
                        if (idSP == idSPCT) {
                            System.out.println(idSP);
                            int soLuongHienCoSP = Integer.parseInt(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 2).toString());;
                            int soLuongMoi = soLuongHienCoSP + soLuongTraLai;
                            tblSanPham.setValueAt(soLuongMoi, i, 2);
                            sanPhamService.updateSanPhamSoLuong(idSPCT, soLuongMoi);
                            break;
                        }
                    }
                }
                DefaultTableModel modelHDCT = (DefaultTableModel) tblHDCT.getModel();
                modelHDCT.setRowCount(0);
                gioHangMap.remove(idHD);
            }
            ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();
            capNhatTongTien();
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn để trả hàng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void huyHoaDon(int idHoaDon) {
        String sqlDeleteHoaDonCT = "DELETE FROM HoaDonChiTiet WHERE id_hoaDon = ?";

        String sqlDeleteHoaDon = "DELETE FROM HoaDon WHERE id_hoaDon = ?";

        try (Connection con = DBConnect.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement psHDCT = con.prepareStatement(sqlDeleteHoaDonCT); PreparedStatement psHD = con.prepareStatement(sqlDeleteHoaDon)) {
                psHDCT.setInt(1, idHoaDon);
                psHDCT.executeUpdate();
                psHD.setInt(1, idHoaDon);
                psHD.executeUpdate();
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi hủy hóa đơn: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage());
        }

        DefaultTableModel dtmHoaDon = (DefaultTableModel) tblHoaDonCho.getModel();
        int selectedRow = tblHoaDonCho.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                traLaiSanPham(idHoaDon);
                dtmHoaDon.removeRow(selectedRow);
                DefaultTableModel dtmGioHang = (DefaultTableModel) tblHDCT.getModel();
                int rowCount = dtmGioHang.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    dtmGioHang.removeRow(i);
                }
                clearFiel();
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để hủy.");
        }
    }

    private void fillNhanVienComboBox() {
        DefaultComboBoxModel<String> thModel = nhanVienService.getAllTenNV();
        cboTenNV.setModel(thModel);
    }

    private void fillHTTTComboBox() {
        DefaultComboBoxModel<String> thModel = hoaDonService.getAllHTTT();
        cboHTTT.setModel(thModel);
    }

    private void fillTenKM() {
        DefaultComboBoxModel<String> thModel = hoaDonService.getAllKM();
        cboKhuyenMai.setModel(thModel);
    }

    private double tongTienSauKhiGiam(double tongTien, double phanTramGiam) {
        double discountAmount = tongTien * (phanTramGiam / 100);
        return tongTien - discountAmount;
    }

    public void thanhToanHoaDon() {
        int selectedRow = tblHoaDonCho.getSelectedRow();
        if (selectedRow >= 0) {
            int idHD = Integer.parseInt(tblHoaDonCho.getValueAt(selectedRow, 0).toString());

            String tenNV = String.valueOf(cboTenNV.getSelectedItem());
            String loaiTT = String.valueOf(cboHTTT.getSelectedItem());
            String tenKH = txtTenKhachHang.getText();
            try {
                HoaDon don = new HoaDon(idHD, tenNV, tenKH, loaiTT);
                hoaDonService.updateTrangThaiHoaDon(idHD, true, don);

                List<SanPham> gioHang = gioHangMap.get(idHD);
                if (gioHang != null) {
                    int soLuongThem = Integer.valueOf(tblHDCT.getValueAt(tblHDCT.getSelectedRow(), 3).toString());
                    for (SanPham sp : gioHang) {
                        hoaDonService.addHDCT(idHD, soLuongThem, sp);
                    }
                }
                DefaultTableModel dtmHoaDon = (DefaultTableModel) tblHoaDonCho.getModel();
                dtmHoaDon.removeRow(selectedRow);
                DefaultTableModel tbm = (DefaultTableModel) tblHDCT.getModel();
                tbm.setRowCount(0);
                xoaHoaDonDaThanhToan(idHD);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi thanh toán hóa đơn: ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để thanh toán.", "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
    }

    void clearFiel() {
        txtTenKhachHang.setText("");
        txtSdt.setText("");
        txtSoTienGiam.setText("");
        txtGiaTriGiam.setText("");
        txtTongTien.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
        txtTenHD.setText("");
    }

    private int findRowInTable(JTable table, int idSPCT) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (Integer.valueOf(table.getValueAt(i, 0).toString()) == idSPCT) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        btnKhachHang = new java.awt.Button();
        jLabel8 = new javax.swing.JLabel();
        txtTenHD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboTenNV = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSoTienGiam = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cboHTTT = new javax.swing.JComboBox<>();
        btnThanhToan = new javax.swing.JButton();
        btnApDung = new javax.swing.JButton();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        btnTaoMoiHoaDonCho = new javax.swing.JButton();
        btnXoaHoaDonCho = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();

        jPanel1.setToolTipText("Hóa Đơn Chờ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN CHI TIẾT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 19))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel6.setToolTipText("");
        jPanel6.setName(""); // NOI18N

        txtTenKhachHang.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("TÊN KHÁCH HÀNG");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("SỐ ĐIỆN THOẠI");

        txtSdt.setEnabled(false);

        btnKhachHang.setBackground(new java.awt.Color(0, 153, 153));
        btnKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhachHang.setForeground(new java.awt.Color(51, 255, 255));
        btnKhachHang.setLabel("CHỌN");
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btnKhachHang.getAccessibleContext().setAccessibleDescription("");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("TÊN HÓA ĐƠN");

        txtTenHD.setEnabled(false);
        txtTenHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHDActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("TÊN NHÂN VIÊN");

        cboTenNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("TỔNG TIỀN");

        txtTongTien.setEnabled(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setText("TIỀN KHÁCH ĐƯA");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setText("TIỀN THỪA");

        txtTienThua.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("TÊN MÃ GIẢM GIÁ");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setText("GIÁ TRỊ GIẢM");

        txtGiaTriGiam.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setText("SỐ TIỀN GIẢM");

        txtSoTienGiam.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setText("HÌNH THỨC THANH TOÁN");

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/expense_4025718.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnApDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-tick-20.png"))); // NOI18N
        btnApDung.setText("ÁP DỤNG");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        cboKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel1.setText("%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongTien))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenHD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(40, 40, 40)
                                .addComponent(txtTienThua)))
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnApDung))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(btnThanhToan)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTenHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(btnApDung)
                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA ĐƠN CHỜ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID HÓA ĐƠN ", "TÊN HÓA ĐƠN", "TRẠNG THÁI", "NGÀY TẠO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonCho);
        if (tblHoaDonCho.getColumnModel().getColumnCount() > 0) {
            tblHoaDonCho.getColumnModel().getColumn(0).setResizable(false);
        }

        btnTaoMoiHoaDonCho.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTaoMoiHoaDonCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/expense_4025718.png"))); // NOI18N
        btnTaoMoiHoaDonCho.setText("TẠO HÓA ĐƠN MỚI");
        btnTaoMoiHoaDonCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiHoaDonChoActionPerformed(evt);
            }
        });

        btnXoaHoaDonCho.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnXoaHoaDonCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/trash-bin_5055247.png"))); // NOI18N
        btnXoaHoaDonCho.setText("HỦY HÓA ĐƠN CHỜ");
        btnXoaHoaDonCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHoaDonChoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaHoaDonCho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoMoiHoaDonCho)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaHoaDonCho)
                    .addComponent(btnTaoMoiHoaDonCho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID SPCT", "TÊN SẢN PHẨM ", "SỐ LƯỢNG", "ĐƠN GIÁ ", "THƯƠNG HIỆU ", "NHÀ SẢN XUẤT", "CHẤT LIỆU", "KÍCH THƯỚC", "LOẠI KHÓA", "KIỂU DÁNG", "MÀU SẮC"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GIỎ HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID HÓA ĐƠN ", "ID SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "TỔNG TIỀN"
            }
        ));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoMoiHoaDonChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiHoaDonChoActionPerformed
        // TODO add your handling code here:
        boolean trangThai = true;
        String tenHD = txtTenHD.getText().trim();
        if (hoaDonService.checkIdTrung(tenHD)) {
            JOptionPane.showMessageDialog(this, "Không để trùng tên hóa đơn", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtTenHD.requestFocus();
            return;
        }
        int id = hoaDonService.themHoaDonVaoDatabase(tenHD, trangThai);
        clearFiel();
        createNameHD();
        txtTenHD.setText(tenHoaDon);

        if (id != -1) {
            xoaHoaDonDaThanhToan(id);
            loadHoadon();
        }
    }//GEN-LAST:event_btnTaoMoiHoaDonChoActionPerformed

    private void btnXoaHoaDonChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHoaDonChoActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblHoaDonCho.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) tblHoaDonCho.getValueAt(selectedRow, 0);
                huyHoaDon(id);
                clearFiel();
                createNameHD();
                txtTenHD.setText(tenHoaDon);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để hủy.");
        }
    }//GEN-LAST:event_btnXoaHoaDonChoActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        // TODO add your handling code here:
        String ten = String.valueOf(cboKhuyenMai.getSelectedItem()).trim();
        String tienKhachDuaStr = txtTienKhachDua.getText().trim();
        KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
        KhuyenMai km = khuyenMaiService.getKhuyenMaiByID(ten);
        if (km != null) {
            hienThiThongTinKhuyenMai(km);
            double giaTriGiam = km.getGiaTri();
            double tongTien = tinhTongTienGioHang();
            double tienGiam = (tongTien * giaTriGiam / 100);
            double tongTienSauGiam = tongTien - tienGiam;
            txtSoTienGiam.setText(String.format("%.2f", tienGiam));
            txtTongTien.setText(String.format("%.2f", tongTienSauGiam));
            try {
                double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
                double tienThua = tienKhachDua - tongTienSauGiam;
                txtTienThua.setText(String.format("%.2f", tienThua));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Số tiền khách đưa không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE); // Handle invalid input
                txtTienThua.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mã khuyến mãi không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE); // Handle invalid promotion code
        }
    }//GEN-LAST:event_btnApDungActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        // TODO add your handling code here:
        int index = tblHoaDonCho.getSelectedRow();
        if (index != -1) {
            int id = Integer.parseInt(tblHoaDonCho.getValueAt(index, 0).toString());
            List<HoaDon> dsct = hoaDonService.getById(id);
            capNhatTongTien();
            showTableHDCT(dsct);
        }
        String tenHD = tblHoaDonCho.getValueAt(index, 1).toString();
        txtTenHD.setText(tenHD);
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        if (index >= 0) {
            int idSPCT = Integer.valueOf(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString());
            SanPham sp = findSanPhamByIdSPCT(idSPCT);
            if (sp == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int soLuongHienCo = getSoLuongHienCo(index);
            if (soLuongHienCo <= 0) {
                JOptionPane.showMessageDialog(null, "Sản phẩm này đã hết hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int soLuongThem = 0;
            try {
                String input = JOptionPane.showInputDialog(null, "Nhập số lượng cần thêm vào giỏ hàng:");
                soLuongThem = Integer.parseInt(input);
                if (soLuongThem <= 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng lớn hơn 0 để thêm vào giỏ hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (soLuongThem > soLuongHienCo) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng thêm vào ít hơn số lượng hiện có!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int selectedHoaDon = tblHoaDonCho.getSelectedRow();
            if (selectedHoaDon == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn trước khi chọn sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idHD = Integer.valueOf(tblHoaDonCho.getValueAt(selectedHoaDon, 0).toString());
            HoaDon check = hoaDonService.findHDCTByIdHDAndIdSPCT(idHD, idSPCT);
            if (check != null) {
                int newSoLuong = check.getSoLuong() + soLuongThem;
                sanPhamService.updateHDCTSoLuong(idSPCT, newSoLuong);
                int rowIndex = findRowInTable(tblHDCT, idSPCT);
                if (rowIndex != -1) {
                    tblHDCT.setValueAt(newSoLuong, rowIndex, 3);
                    double donGia = Double.parseDouble(tblSanPham.getValueAt(index, 3).toString());
                    double tongTien = newSoLuong * donGia;
                    tblHDCT.setValueAt(tongTien, rowIndex, 5);
                }
            } else {
                hoaDonService.addHDCT(idHD, soLuongThem, sp);
            }
            int newSoLuongTonKho = soLuongHienCo - soLuongThem;
            tblSanPham.setValueAt(newSoLuongTonKho, index, 2);
            sanPhamService.updateSanPhamSoLuong(idSPCT, newSoLuongTonKho);
            loadHDCTTable(idHD);
            capNhatTongTien();
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm thêm vào giỏ hàng", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        int index = tblHDCT.getSelectedRow();
        if (index >= 0) {
            int idSPCT = Integer.parseInt(tblHDCT.getValueAt(index, 1).toString());
            SanPham sp = findSanPhamByIdSPCT(idSPCT);
            if (sp == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int selectedHoaDon = tblHoaDonCho.getSelectedRow();
            if (selectedHoaDon == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn trước khi chọn sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int soLuongHienCo = getSoLuongHienCoHDCT(index);
            try {
                String soLuongTra = JOptionPane.showInputDialog(null, "Nhập số lượng trả lại:");
                int soLuongTraLai = Integer.parseInt(soLuongTra);

                if (soLuongTraLai <= 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (soLuongTraLai > soLuongHienCo) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng trả lại ít hơn số lượng trong giỏ hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int newSoLuong = soLuongHienCo - soLuongTraLai;
                double donGia = Double.parseDouble(tblHDCT.getValueAt(index, 4).toString()); // Giả sử đơn giá ở cột thứ 5 (index 4)
                if (newSoLuong == 0) {
                    ((DefaultTableModel) tblHDCT.getModel()).removeRow(index);
                    hoaDonService.deleteSanPhamFromHDCT(idSPCT);
                } else {
                    sanPhamService.updateHDCTSoLuong(idSPCT, newSoLuong);
                    tblHDCT.setValueAt(newSoLuong, index, 3);
                    double tongTien = newSoLuong * donGia;
                    tblHDCT.setValueAt(tongTien, index, 5);
                }
                int soLuongHienCoTblSanPham = getSoLuongTonKhoByIdSPCT(idSPCT);
                int soLuongMoi = soLuongHienCoTblSanPham + soLuongTraLai;
                tblSanPham.setValueAt(soLuongMoi, getRowInTblSanPhamByIdSPCT(idSPCT), 2);
                sanPhamService.updateSanPhamSoLuong(idSPCT, soLuongMoi);
                capNhatTongTien();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm trong bảng HDCT.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblHDCTMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int index = tblHoaDonCho.getSelectedRow();
        if (index >= 0) {
            DefaultTableModel dtm = (DefaultTableModel) tblHDCT.getModel();
            if (dtm.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(null, "Giỏ hàng không có sản phẩm để thanh toán", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String ma = txtTenKhachHang.getText().trim();
            String sdt = txtSdt.getText().trim();
            if (ma.isEmpty() && sdt.isEmpty() && ma.isBlank() && sdt.isBlank()) {
                JOptionPane.showMessageDialog(this, "Tên và mã khách hàng không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double tongTien = tinhTongTienGioHang();
            double giamGia = 0;
            String giaTri = txtGiaTriGiam.getText().trim();
            if (!giaTri.isEmpty()) {
                try {
                    giamGia = Double.parseDouble(giaTri);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Phần trăm giảm giá không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            double tongTienSauGiam = tongTienSauKhiGiam(tongTien, giamGia);
            txtTongTien.setText(String.format("%.2f", tongTienSauGiam));
            double tienKhachDua = 0;
            try {
                tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tiền khách đưa không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double tienThua = tienKhachDua - tongTienSauGiam;
            if (tienThua < 0) {
                JOptionPane.showMessageDialog(this, "Khách chưa đưa đủ tiền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                txtTienThua.setText(String.format("%.2f", tienThua));
            }
            SwingUtilities.invokeLater(() -> {
                DefaultTableModel model = (DefaultTableModel) tblHoaDonCho.getModel();
                if (index < model.getRowCount()) {
                    try {
                        //khachHangService.ThemKhandSDT(ma, sdt);
                        thanhToanHoaDon();
                        JOptionPane.showMessageDialog(null, "Hóa đơn đã được thanh toán.");
                        clearFiel();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý hóa đơn. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Dữ liệu hóa đơn đã thay đổi, vui lòng thử lại.");
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để thanh toán.");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        Frame parent = null;
        formThemKH fh = new formThemKH(parent, true);
        fh.setVisible(true);
        loadKH();
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void txtTenHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHDActionPerformed
    void loadKH() {
        dskh = khachHangService.selectkh(formThemKH.maKH);
        for (KhachHang kh : dskh) {
            txtSdt.setText(kh.getSdt());
            txtTenKhachHang.setText(kh.getHoTenKh());
            maKH = kh.getMaKh();
        }

    }

    void showTableHDCT(List<HoaDon> list) {
        dtm = (DefaultTableModel) tblHDCT.getModel();
        dtm.setRowCount(0);
        for (HoaDon hdct : list) {
            dtm.addRow(new Object[]{
                hdct.getIdHoaDon(),
                hdct.getIdSPCT(),
                hdct.getTenSanPham(),
                hdct.getSoLuong(),
                hdct.getGia(),
                hdct.getSoLuong() * hdct.getGia(),
                hdct.getNgayTaoHD(),});
        }
    }

    private void loadHDCTTable(int idHD) {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        model.setRowCount(0);
        List<HoaDon> hdctList = hoaDonService.getById(idHD);
        for (HoaDon hdct : hdctList) {
            Object[] rowData = {
                hdct.getIdHoaDon(),
                hdct.getIdSPCT(),
                hdct.getTenSanPham(),
                hdct.getSoLuong(),
                hdct.getGia(),
                hdct.getSoLuong() * hdct.getGia(),
                hdct.getNgayTaoHD()
            };
            model.addRow(rowData);
        }
    }

    private SanPham findSanPhamByIdSPCT(Integer idSPCT) {
        return sanPhamRepo.stream()
                .filter(sp -> sp.getId_SPCT() == idSPCT)
                .findFirst()
                .orElse(null);
    }

    private int getRowInTblSanPhamByIdSPCT(int idSPCT) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            int idSPCTInTable = Integer.parseInt(model.getValueAt(i, 0).toString()); // Giả sử idSPCT ở cột đầu tiên
            if (idSPCTInTable == idSPCT) {
                return i;
            }
        }

        return -1; // Trả về -1 nếu không tìm thấy
    }

    private int getSoLuongHienCo(int selectedRow) {
        try {
            return Integer.parseInt(tblSanPham.getValueAt(selectedRow, 2).toString());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ.", "Lỗi định dạng số", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private int getSoLuongTonKhoByIdSPCT(int idSPCT) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            int idSPCTInTable = Integer.parseInt(model.getValueAt(i, 0).toString()); // Giả sử idSPCT ở cột đầu tiên
            if (idSPCTInTable == idSPCT) {
                return Integer.parseInt(model.getValueAt(i, 2).toString()); // Giả sử số lượng tồn kho ở cột thứ ba
            }
        }

        return -1; // Trả về -1 nếu không tìm thấy sản phẩm
    }

    private int getSoLuongHienCoHDCT(int selectedRow) {
        try {
            return Integer.parseInt(tblHDCT.getValueAt(selectedRow, 3).toString());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ.", "Lỗi định dạng số", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDung;
    private java.awt.Button btnKhachHang;
    private javax.swing.JButton btnTaoMoiHoaDonCho;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaHoaDonCho;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSoTienGiam;
    private javax.swing.JTextField txtTenHD;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
