/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pro1041.com.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import pro1041.com.entity.KhoaAo;
import pro1041.com.entity.KieuDang;
import pro1041.com.entity.MauSac;
import pro1041.com.entity.NhaSanXuat;
import pro1041.com.entity.SanPham;
import pro1041.com.entity.ThuongHieu;
import pro1041.com.entity.ChatLieu;
import pro1041.com.entity.KichThuoc;
import pro1041.com.service.KhoaAoService;
import pro1041.com.service.KieuDangService;
import pro1041.com.service.MauSacService;
import pro1041.com.service.NhaSanXuatService;
import pro1041.com.service.SanPhamService;
import pro1041.com.service.ThuongHieuService;
import pro1041.com.service.ChatLieuService;
import pro1041.com.service.KichThuocService;

/**
 *
 * @author Tom
 */
public class formSanPham extends javax.swing.JPanel {

    private List<SanPham> dssp = new ArrayList<>();
    private List<SanPham> dsspFull = new ArrayList<>();
    private ChatLieuService clDao = new ChatLieuService();
    private KichThuocService kichDao = new KichThuocService();
    private MauSacService mauS = new MauSacService();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private final SanPhamService sanPhamService = new SanPhamService();
    private DefaultTableModel tbm = new DefaultTableModel();
    private DefaultTableModel model = new DefaultTableModel();
    private KhoaAoService khoaAoService = new KhoaAoService();
    private NhaSanXuatService nhaSanXuatService = new NhaSanXuatService();
    private ThuongHieuService thuongHieuService = new ThuongHieuService();
    private KieuDangService kieuDangService = new KieuDangService();

    /**
     * Creates new form formSanPham
     */
    public formSanPham() {
        initComponents();
        setSize(2000, 1500);
        dssp = sanPhamService.getAll();
        dsspFull = sanPhamService.getAllSP();
        tableModel = (DefaultTableModel) tblSanPham.getModel();
        tbm = (DefaultTableModel) tblSanPhamChinh.getModel();
        showDuLieu();
        showData();
        fillChatLieuComboBox();
        fillKieuDangComboBox();
        fillSanPhamComboBox();
        fillKichThuocComboBox();
        fillMauSacComboBox();
        fillNSXComboBox();
        fillThuongHieuComboBox();
        fillKhoaAoComboBox();
        clearForm();
        clearFormSP();
    }

    public void showDuLieu() {
        tableModel.setRowCount(0);
        for (SanPham sp : dssp) {
            tableModel.addRow(new Object[]{
                sp.getId_SPCT(), sp.getTenSanPham(), sp.getGia(), sp.getSoluongtonkho(), sp.getNgayTao(), sp.getNgaySua(), sp.getTenNSX(), sp.getTenThuongHieu(), sp.getTenChatLieu(), sp.getKieuDang(), sp.getTenKichThuoc(), sp.getTenMauSac(), sp.getTenKhoa()
            });

        }
    }

    public void showData() {
        tbm.setRowCount(0);
        for (SanPham sp : dsspFull) {
            tbm.addRow(new Object[]{
                sp.getId_sanPham(), sp.getTenSanPham(), sp.getTenNSX(), sp.getTenThuongHieu(), sp.getNgayTao(), sp.getNgaySua()
            });

        }
    }

    private void fillKhoaAoComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllLoaiKhoaAo();
        cboKhoaAo.setModel(thModel);
    }

    private void fillNSXComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllNSX();
        cboNSX.setModel(thModel);
    }

    private void fillThuongHieuComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllThuongHieu();
        cboThuongHieu.setModel(thModel);
    }

    private void fillSanPhamComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllTenSp();
        cboTenSanPham.setModel(thModel);
    }

    private void fillChatLieuComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllChatLieu();
        cboChatLieu.setModel(thModel);
    }

    private void fillKichThuocComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllKichThuoc();
        cboKichThuoc.setModel(thModel);
    }

    private void fillMauSacComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllMauSac();
        cboMauSac.setModel(thModel);
    }

    private void fillKieuDangComboBox() {
        DefaultComboBoxModel<String> thModel = sanPhamService.getAllKieuDang();
        cboKieuDang.setModel(thModel);
    }

    public void fillKT() {
        rdoKichThuoc.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        ArrayList<KichThuoc> data = kichDao.getAll();
        for (int i = 0; i < data.size(); i++) {
            KichThuoc kicThuoc = data.get(i);
            model.addRow(new Object[]{i + 1, kicThuoc.getMaKichThuoc(), kicThuoc.getTenKichThuoc()});
        }
    }

    public void fillMS() {
        rdoMauSac.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        ArrayList<MauSac> data = mauS.getALL();
        for (int i = 0; i < data.size(); i++) {
            MauSac mauSac = data.get(i);
            model.addRow(new Object[]{i + 1, mauSac.getMaMauSac(), mauSac.getTenMauSac()});
        }
    }

    public void fillCL() {
        rdoChatLieu.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        ArrayList<ChatLieu> data = clDao.getAll();
        for (int i = 0; i < data.size(); i++) {
            ChatLieu chatLieu = data.get(i);
            model.addRow(new Object[]{i + 1, chatLieu.getMachatLieu(), chatLieu.getTenchatLieu()});
        }
    }

    public void fillKa() {
        rdoKhoaAo.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        List<KhoaAo> data = khoaAoService.getAll();
        for (int i = 0; i < data.size(); i++) {
            KhoaAo khoaAo = data.get(i);
            model.addRow(new Object[]{i + 1, khoaAo.getMaKhoa(), khoaAo.getTenKhoa()});
        }

    }

    public void fillNsx() {
        rdoNSX.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        List<NhaSanXuat> data = nhaSanXuatService.getAll();
        for (int i = 0; i < data.size(); i++) {
            NhaSanXuat nsx = data.get(i);
            model.addRow(new Object[]{i + 1, nsx.getMaNsx(), nsx.getTenNsx()});
        }

    }

    public void fillTh() {
        rdoThuongHieu.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        List<ThuongHieu> data = thuongHieuService.getAll();
        for (int i = 0; i < data.size(); i++) {
            ThuongHieu th = data.get(i);
            model.addRow(new Object[]{i + 1, th.getMaThuonghieu(), th.getTenThuongHieu()});
        }

    }

    public void fillKD() {
        rdoKieuDang.setSelected(true);
        model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        List<KieuDang> data = kieuDangService.getAll();
        for (int i = 0; i < data.size(); i++) {
            KieuDang kd = data.get(i);
            model.addRow(new Object[]{i + 1, kd.getMaKieuDang(), kd.getTenKieuDang()});
        }

    }

    void clearForm() {
        cboKieuDang.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
        cboTenSanPham.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        txtGia.setText("");
        txtSoLuong.setText("");
        Date now = new Date();
        jNgayTao.setDate(now);
        jNgaySua.setDate(now);
    }

    void clearFormSP() {
        txtTenSP.setText("");
        Date now = new Date();
        jNgayTaoSP.setDate(now);
        jNgaySuaSP.setDate(now);
        cboNSX.setSelectedIndex(0);
        cboThuongHieu.setSelectedIndex(0);

    }

    void clearformTT() {
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
        txtTimKiemThuocTinh.setText("");
    }

    private boolean checkForm() {
        String mathuocTinh = txtMaThuocTinh.getText().trim();
        String tenThuocTinh = txtTenThuocTinh.getText().trim();

        if (mathuocTinh.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã thuộc tính", "Warning", JOptionPane.WARNING_MESSAGE);
            txtMaThuocTinh.requestFocus();
            return false;
        }

        if (mathuocTinh.length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã thuộc tính không được vượt quá 100 ký tự", "Warning", JOptionPane.WARNING_MESSAGE);
            txtMaThuocTinh.requestFocus();
            return false;
        }
        if (tenThuocTinh.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên thuộc tính", "Warning", JOptionPane.WARNING_MESSAGE);
            txtTenThuocTinh.requestFocus();
            return false;
        }

        if (tenThuocTinh.length() > 100) {
            JOptionPane.showMessageDialog(null, "Tên thuộc tính không được vượt quá 100 ký tự", "Warning", JOptionPane.WARNING_MESSAGE);
            txtTenThuocTinh.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jNgayTaoSP = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jNgaySuaSP = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboNSX = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamChinh = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jNgayTao = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jNgaySua = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        cboKieuDang = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cboTenSanPham = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cboKhoaAo = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoNSX = new javax.swing.JRadioButton();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoKhoaAo = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        txtTimKiemThuocTinh = new javax.swing.JTextField();
        rdoKieuDang = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnAdd1 = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnLamMoiTT = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 982, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("QUẢN LÝ SẢN PHẨM");
        jLabel3.setPreferredSize(new java.awt.Dimension(263, 28));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("TÊN SẢN PHẨM");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("NGÀY TẠO");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("NGÀY SỬA");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("THƯƠNG HIỆU");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("NHÀ SẢN XUẤT");

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-add-16.png"))); // NOI18N
        btnThemSP.setText("THÊM");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnXoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/remove_7695824 (1).png"))); // NOI18N
        btnXoaSP.setText("XÓA");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/synchronize_2749336.png"))); // NOI18N
        btnLamMoiSP.setText("LÀM MỚI");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-update-16.png"))); // NOI18N
        btnSuaSP.setText("SỬA");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiSP)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSP)
                    .addComponent(btnLamMoiSP)
                    .addComponent(btnXoaSP)
                    .addComponent(btnSuaSP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSanPhamChinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TÊN SẢN PHẨM", "TÊN NHÀ SẢN XUẤT", "TÊN THƯƠNG HIỆU", "NGÀY TẠO", "NGÀY SỬA"
            }
        ));
        tblSanPhamChinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChinhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamChinh);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel24))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(29, 29, 29)
                                        .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel23))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jNgayTaoSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jNgaySuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNgayTaoSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNgaySuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SẢN PHẨM", jPanel2);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("QUẢN LÝ SẢN PHẨM CHI TIẾT");
        jLabel2.setPreferredSize(new java.awt.Dimension(263, 28));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("TÊN SẢN PHẨM");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("NGÀY TẠO");

        jNgayTao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jNgayTaoMouseEntered(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("NGÀY SỬA");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("CHẤT LIỆU");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("KÍCH THƯỚC");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("MÀU SẮC");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("KIỂU DÁNG");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("SỐ LƯỢNG");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("ĐƠN GIÁ");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        cboKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-add-16.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/remove_7695824 (1).png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-captcha-16.png"))); // NOI18N
        btnLamMoi.setText("LÀM MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-update-16.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLamMoi)
                    .addComponent(btnXoa)
                    .addComponent(btnSua))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID SPCT", "TÊN SP", "GIÁ", "SỐ LƯỢNG TỒN KHO", "NGÀY TẠO", "NGÀY SỬA", "NSX", "THƯƠNG HIỆU", "CHẤT LIỆU", "KIỂU DÁNG", "KÍCH THƯỚC", "MÀU SẮC", "LOẠI KHÓA ÁO"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        cboTenSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("KHÓA ÁO");

        cboKhoaAo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(34, 34, 34)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(48, 48, 48)
                                        .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cboKhoaAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(39, 39, 39)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(101, 101, 101)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addComponent(jNgaySua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 262, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(cboKhoaAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 168, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("SẢN PHẨM CHI TIẾT", jPanel1);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setText("QUẢN LÝ THUỘC TÍNH");
        jLabel7.setPreferredSize(new java.awt.Dimension(263, 28));

        buttonGroup1.add(rdoChatLieu);
        rdoChatLieu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoChatLieu.setText("Chất Liệu");
        rdoChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChatLieuMouseClicked(evt);
            }
        });
        rdoChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatLieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKichThuoc);
        rdoKichThuoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoKichThuoc.setText("Kích Thước");
        rdoKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKichThuocMouseClicked(evt);
            }
        });
        rdoKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichThuocActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMauSac);
        rdoMauSac.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoMauSac.setText("Màu Sắc");
        rdoMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoMauSacMouseClicked(evt);
            }
        });
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNSX);
        rdoNSX.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoNSX.setText("Nhà Sản Xuất");
        rdoNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNSXMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoThuongHieu);
        rdoThuongHieu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoThuongHieu.setText("Thương Hiệu");
        rdoThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoThuongHieuMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoKhoaAo);
        rdoKhoaAo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoKhoaAo.setText("Khóa Áo");
        rdoKhoaAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKhoaAoMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Tìm Kiếm");

        txtTimKiemThuocTinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemThuocTinhKeyPressed(evt);
            }
        });

        buttonGroup1.add(rdoKieuDang);
        rdoKieuDang.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoKieuDang.setText("Kiểu Dáng");
        rdoKieuDang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKieuDangMouseClicked(evt);
            }
        });
        rdoKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKieuDangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoMauSac)
                            .addComponent(rdoKichThuoc)
                            .addComponent(rdoChatLieu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoThuongHieu)
                                    .addComponent(rdoKhoaAo))
                                .addGap(4, 4, 4))
                            .addComponent(rdoNSX, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rdoKieuDang)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTimKiemThuocTinh))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoKhoaAo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoThuongHieu))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMauSac)
                    .addComponent(rdoNSX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoKieuDang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTimKiemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtMaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaThuocTinhActionPerformed(evt);
            }
        });

        txtTenThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenThuocTinhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Mã");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Tên ");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(btnAdd)
                    .addComponent(jButton4))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("THÔNG TIN ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel17))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenThuocTinh)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        btnAdd1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-add-16.png"))); // NOI18N
        btnAdd1.setText("Thêm");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSuaThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-update-16.png"))); // NOI18N
        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnLamMoiTT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLamMoiTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/com/icon/icons8-captcha-16.png"))); // NOI18N
        btnLamMoiTT.setText("Làm Mới");
        btnLamMoiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnAdd1)
                    .addComponent(btnLamMoiTT))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaThuocTinh)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiTT)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÃ", "TÊN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBang);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(276, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("THUỘC TÍNH", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        showDetail(row);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String soLuong = txtSoLuong.getText().trim();
        String donGia = txtGia.getText().trim();
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Check if the fields are empty
        if (soLuong.isEmpty() || donGia.isEmpty() || soLuong.isBlank() || donGia.isBlank()) {
            JOptionPane.showMessageDialog(this, "Số lượng và đơn giá không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int soLuongInt = Integer.parseInt(soLuong);
            double donGiaDouble = Double.parseDouble(donGia);
            if (soLuongInt < 0 || donGiaDouble < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số dương", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật dữ liệu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        int id = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 0).toString());
        if (confirm == JOptionPane.YES_OPTION) {
            if (sanPhamService.update(id, getForm()) != 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                clearForm();
                dssp = sanPhamService.getAll();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        } else {
            showDuLieu();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 0).toString());
        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dữ liệu này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            if (sanPhamService.xoa(id)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                clearForm();
                dssp = sanPhamService.getAll();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        } else {
            showDuLieu();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String soLuong = txtSoLuong.getText().trim();
        String donGia = txtGia.getText().trim();
        // Check if the fields are empty
        if (soLuong.isEmpty() && donGia.isEmpty() && soLuong.isBlank() && donGia.isBlank()) {
            JOptionPane.showMessageDialog(this, "Số lượng và đơn giá không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (soLuong.isBlank() || soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (donGia.isBlank() || donGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Đơn giá không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int soLuongInt = Integer.parseInt(soLuong);
            double donGiaDouble = Double.parseDouble(donGia);
            if (soLuongInt < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số dương", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (donGiaDouble < 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải là số dương", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm sản phẩm ?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sanPhamService.add(getForm()) != 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                clearForm();
                dssp = sanPhamService.getAll();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công");
            }
        } else {
            showDuLieu();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
    }//GEN-LAST:event_txtGiaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        String tenSP = txtTenSP.getText().trim();
        if (tenSP.isEmpty() || tenSP.isBlank()) {
            JOptionPane.showMessageDialog(this, " Tên sản phẩm không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm sản phẩm ?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sanPhamService.addSP(getFormSP()) != 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                clearFormSP();
                dsspFull = sanPhamService.getAllSP();
                dssp = sanPhamService.getAll();
                showData();
                fillSanPhamComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công");
            }
        } else {
            showData();
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblSanPhamChinh.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tblSanPhamChinh.getValueAt(selectedRow, 0).toString());
        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dữ liệu này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            if (sanPhamService.xoaSP(id)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                clearFormSP();
                dsspFull = sanPhamService.getAllSP();
                showData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        } else {
            showData();
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        clearFormSP();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here:

        int selectedRow = tblSanPhamChinh.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tenSP = txtTenSP.getText().trim();
        if (tenSP.isEmpty() || tenSP.isBlank()) {
            JOptionPane.showMessageDialog(this, " Tên sản phẩm không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = Integer.parseInt(tblSanPhamChinh.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật dữ liệu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sanPhamService.updateSP(id, getFormSP()) != 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                clearFormSP();
                dsspFull = sanPhamService.getAllSP();
                dssp = sanPhamService.getAll();
                showData();
                showDuLieu();
                fillSanPhamComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        } else {
            showData();
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblSanPhamChinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChinhMouseClicked
        // TODO add your handling code here:
        int row = tblSanPhamChinh.getSelectedRow();
        showDetailSP(row);
    }//GEN-LAST:event_tblSanPhamChinhMouseClicked

    private void jNgayTaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNgayTaoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jNgayTaoMouseEntered

    private void rdoChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChatLieuMouseClicked

    }//GEN-LAST:event_rdoChatLieuMouseClicked

    private void rdoChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatLieuActionPerformed
        fillCL();
    }//GEN-LAST:event_rdoChatLieuActionPerformed

    private void rdoKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKichThuocMouseClicked

    }//GEN-LAST:event_rdoKichThuocMouseClicked

    private void rdoKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichThuocActionPerformed
        fillKT();
    }//GEN-LAST:event_rdoKichThuocActionPerformed

    private void rdoMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoMauSacMouseClicked

    }//GEN-LAST:event_rdoMauSacMouseClicked

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        fillMS();
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNSXMouseClicked
        // TODO add your handling code here:
        fillNsx();
    }//GEN-LAST:event_rdoNSXMouseClicked

    private void rdoThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoThuongHieuMouseClicked
        // TODO add your handling code here:
        fillTh();
    }//GEN-LAST:event_rdoThuongHieuMouseClicked

    private void rdoKhoaAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKhoaAoMouseClicked
        // TODO add your handling code here:
        fillKa();
    }//GEN-LAST:event_rdoKhoaAoMouseClicked

    private void txtTimKiemThuocTinhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemThuocTinhKeyPressed
        String tim = txtTimKiemThuocTinh.getText();
        Search(tim);
    }//GEN-LAST:event_txtTimKiemThuocTinhKeyPressed

    private void txtTenThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenThuocTinhActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

    }//GEN-LAST:event_btnAddActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        String maThuocTinh = txtMaThuocTinh.getText();
        String tenThuocTinh = txtTenThuocTinh.getText();

        if (rdoChatLieu.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (clDao.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            ChatLieu chatLieu1 = new ChatLieu();
            chatLieu1.setMachatLieu(maThuocTinh);
            chatLieu1.setTenchatLieu(tenThuocTinh);
            if (clDao.addNew(chatLieu1) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillCL();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoKichThuoc.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (kichDao.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            KichThuoc kicThuoc = new KichThuoc();
            kicThuoc.setMaKichThuoc(maThuocTinh);
            kicThuoc.setTenKichThuoc(tenThuocTinh);
            if (kichDao.addNew(kicThuoc) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillKT();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoMauSac.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (mauS.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            MauSac mauSac = new MauSac();
            mauSac.setMaMauSac(maThuocTinh);
            mauSac.setTenMauSac(tenThuocTinh);
            if (mauS.addNew(mauSac) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillMS();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoThuongHieu.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (thuongHieuService.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            ThuongHieu th = new ThuongHieu();
            th.setMaThuonghieu(maThuocTinh);
            th.setTenThuongHieu(tenThuocTinh);
            if (thuongHieuService.add(th) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillTh();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoKhoaAo.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (khoaAoService.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            KhoaAo ka = new KhoaAo();
            ka.setMaKhoa(maThuocTinh);
            ka.setTenKhoa(tenThuocTinh);
            if (khoaAoService.add(ka) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillKa();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoNSX.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (nhaSanXuatService.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            NhaSanXuat nsx = new NhaSanXuat();
            nsx.setMaNsx(maThuocTinh);
            nsx.setTenNsx(tenThuocTinh);
            if (nhaSanXuatService.add(nsx) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillNsx();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoKieuDang.isSelected()) {
            if (!checkForm()) {
                return;
            }
            if (kieuDangService.checkIdTrung(maThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Không được để trùng mã", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMaThuocTinh.requestFocus();
                return;
            }
            KieuDang kd = new KieuDang();
            kd.setMaKieuDang(maThuocTinh);
            kd.setTenKieuDang(tenThuocTinh);
            if (kieuDangService.add(kd) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                fillKD();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        String maThuocTinh = txtMaThuocTinh.getText();
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (rdoChatLieu.isSelected()) {
            if (!checkForm()) {
                return;
            }
            ChatLieu chatLieu1 = new ChatLieu();
            chatLieu1.setMachatLieu(maThuocTinh);
            chatLieu1.setTenchatLieu(tenThuocTinh);

            if (clDao.Update(chatLieu1, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillCL();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoKichThuoc.isSelected()) {
            if (!checkForm()) {
                return;
            }
            KichThuoc kciThuoc = new KichThuoc();
            kciThuoc.setMaKichThuoc(maThuocTinh);
            kciThuoc.setTenKichThuoc(tenThuocTinh);
            if (kichDao.Update(kciThuoc, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillKT();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoMauSac.isSelected()) {
            if (!checkForm()) {
                return;
            }

            MauSac mauSac = new MauSac();
            mauSac.setMaMauSac(maThuocTinh);
            mauSac.setTenMauSac(tenThuocTinh);

            if (mauS.Update(mauSac, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillMS();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoKhoaAo.isSelected()) {
            if (!checkForm()) {
                return;
            }
            KhoaAo ka = new KhoaAo();
            ka.setMaKhoa(maThuocTinh);
            ka.setTenKhoa(tenThuocTinh);
            if (khoaAoService.Update(ka, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillKa();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoNSX.isSelected()) {
            if (!checkForm()) {
                return;
            }
            NhaSanXuat nsx = new NhaSanXuat();
            nsx.setMaNsx(maThuocTinh);
            nsx.setTenNsx(tenThuocTinh);
            if (nhaSanXuatService.Update(nsx, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillNsx();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoThuongHieu.isSelected()) {
            if (!checkForm()) {
                return;
            }
            ThuongHieu th = new ThuongHieu();
            th.setMaThuonghieu(maThuocTinh);
            th.setTenThuongHieu(tenThuocTinh);

            if (thuongHieuService.Update(th, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillTh();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (rdoKieuDang.isSelected()) {
            if (!checkForm()) {
                return;
            }
            KieuDang kd = new KieuDang();
            kd.setMaKieuDang(maThuocTinh);
            kd.setTenKieuDang(tenThuocTinh);

            if (kieuDangService.Update(kd, maThuocTinh) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillKD();
                clearformTT();
                dssp = sanPhamService.getAll();
                fillChatLieuComboBox();
                fillKhoaAoComboBox();
                fillKieuDangComboBox();
                fillKichThuocComboBox();
                fillMauSacComboBox();
                fillThuongHieuComboBox();
                fillNSXComboBox();
                showDuLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        if (rdoChatLieu.isSelected()) {
            int index = tblBang.getSelectedRow();
            ChatLieu l = clDao.getAll().get(index);
            txtMaThuocTinh.setText(l.getMachatLieu());
            txtTenThuocTinh.setText(l.getTenchatLieu());
            fillCL();
        }
        if (rdoKichThuoc.isSelected()) {
            int index = tblBang.getSelectedRow();
            KichThuoc kt = kichDao.getAll().get(index);
            txtMaThuocTinh.setText(kt.getMaKichThuoc());
            txtTenThuocTinh.setText(kt.getTenKichThuoc());
            fillKT();
        }
        if (rdoMauSac.isSelected()) {
            int index = tblBang.getSelectedRow();
            MauSac ms = mauS.getALL().get(index);
            txtMaThuocTinh.setText(ms.getMaMauSac());
            txtTenThuocTinh.setText(ms.getTenMauSac());
            fillMS();
        }
        if (rdoThuongHieu.isSelected()) {
            int index = tblBang.getSelectedRow();
            ThuongHieu th = thuongHieuService.getAll().get(index);
            txtMaThuocTinh.setText(th.getMaThuonghieu());
            txtTenThuocTinh.setText(th.getTenThuongHieu());
            fillTh();
        }
        if (rdoNSX.isSelected()) {
            int index = tblBang.getSelectedRow();
            NhaSanXuat nsx = nhaSanXuatService.getAll().get(index);
            txtMaThuocTinh.setText(nsx.getMaNsx());
            txtTenThuocTinh.setText(nsx.getTenNsx());
            fillNsx();
        }
        if (rdoKhoaAo.isSelected()) {
            int index = tblBang.getSelectedRow();
            KhoaAo ka = khoaAoService.getAll().get(index);
            txtMaThuocTinh.setText(ka.getMaKhoa());
            txtTenThuocTinh.setText(ka.getTenKhoa());
            fillKa();
        }
        if (rdoKieuDang.isSelected()) {
            int index = tblBang.getSelectedRow();
            KieuDang kd = kieuDangService.getAll().get(index);
            txtMaThuocTinh.setText(kd.getMaKieuDang());
            txtTenThuocTinh.setText(kd.getTenKieuDang());
            fillKD();
        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void txtMaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaThuocTinhActionPerformed

    private void btnLamMoiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTTActionPerformed
        clearformTT();
    }//GEN-LAST:event_btnLamMoiTTActionPerformed

    private void rdoKieuDangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKieuDangMouseClicked
        // TODO add your handling code here:
        fillKD();
    }//GEN-LAST:event_rdoKieuDangMouseClicked

    private void rdoKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKieuDangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKieuDangActionPerformed
    public void showDetail(int index) {
        SanPham sp = dssp.get(index);
        txtSoLuong.setText(String.valueOf(sp.getSoluongtonkho()));
        txtGia.setText(String.valueOf(sp.getGia()));
        jNgayTao.setDate(sp.getNgayTao());
        jNgaySua.setDate(sp.getNgaySuaOrDefault());

        cboChatLieu.setSelectedItem(sp.getTenChatLieu());
        cboTenSanPham.setSelectedItem(sp.getTenSanPham());
        cboKieuDang.setSelectedItem(sp.getKieuDang());
        cboKichThuoc.setSelectedItem(sp.getTenKichThuoc());
        cboMauSac.setSelectedItem(sp.getTenMauSac());
        cboKhoaAo.setSelectedItem(sp.getTenKhoa());
    }

    public void showDetailSP(int index) {
        SanPham sp = dsspFull.get(index);
        txtTenSP.setText(sp.getTenSanPham());
        jNgayTaoSP.setDate(sp.getNgayTao());
        jNgaySuaSP.setDate(sp.getNgaySuaOrDefault());

        cboNSX.setSelectedItem(sp.getTenNSX());
        cboThuongHieu.setSelectedItem(sp.getTenThuongHieu());
    }

    public SanPham getFormSP() {
        SanPham sp = new SanPham();
        String ten = txtTenSP.getText().trim();
        String nsx = cboNSX.getSelectedItem().toString();
        String thuongHieu = cboThuongHieu.getSelectedItem().toString();
        Date ngayTao = jNgayTaoSP.getDate();
        Date ngaySua = jNgaySuaSP.getDate();

        sp.setTenSanPham(ten);
        sp.setTenNSX(nsx);
        sp.setTenThuongHieu(thuongHieu);
        sp.setNgayTao(ngayTao);
        sp.setNgaySua(ngaySua);
        return sp;

    }

    public void Search(String tim) {
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(model);
        tblBang.setRowSorter(tableRowSorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        filters.add(RowFilter.regexFilter("(?i)" + tim));

        filters.add(RowFilter.regexFilter("(?i)" + tim, 1));

        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);

        tableRowSorter.setRowFilter(combinedFilter);
    }

    public SanPham getForm() {
        SanPham sp = new SanPham();
        String gia = txtGia.getText().trim();
        String soLuong = txtSoLuong.getText().trim();
        Date ngayTao = jNgayTao.getDate();
        Date ngaySua = jNgaySua.getDate();
        String chatLieu = cboChatLieu.getSelectedItem().toString();
        String kieuDang = cboKieuDang.getSelectedItem().toString();
        String kichThuoc = cboKichThuoc.getSelectedItem().toString();
        String mauSac = cboMauSac.getSelectedItem().toString();
        String tenSp = cboTenSanPham.getSelectedItem().toString();
        String khoaAo = cboKhoaAo.getSelectedItem().toString();

        sp.setGia(Integer.valueOf(gia));
        sp.setTenMauSac(mauSac);
        sp.setTenChatLieu(chatLieu);
        sp.setTenSanPham(tenSp);
        sp.setTenKichThuoc(kichThuoc);
        sp.setTenKhoa(khoaAo);
        sp.setKieuDang(kieuDang);
        sp.setNgayTao(ngayTao);
        sp.setNgaySua(ngaySua);
        sp.setSoluongtonkho(Integer.valueOf(soLuong));
        return sp;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiTT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKhoaAo;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboKieuDang;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNSX;
    private javax.swing.JComboBox<String> cboTenSanPham;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jNgaySua;
    private com.toedter.calendar.JDateChooser jNgaySuaSP;
    private com.toedter.calendar.JDateChooser jNgayTao;
    private com.toedter.calendar.JDateChooser jNgayTaoSP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoKhoaAo;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoKieuDang;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoNSX;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamChinh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiemThuocTinh;
    // End of variables declaration//GEN-END:variables
}
