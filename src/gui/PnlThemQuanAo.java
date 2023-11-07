package gui;

import dao.DAO_ChatLieu;
import dao.DAO_DanhMuc;
import dao.DAO_GioiTinh;
import dao.DAO_KichThuoc;
import dao.DAO_MauSac;
import dao.DAO_NhaSanXuat;
import dao.DAO_QuanAo;
import data.UtilityImageIcon;
import entity.QuanAo;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class PnlThemQuanAo extends javax.swing.JPanel {
    
    private String imagePath = "";
    private static PnlThemQuanAo instance = new PnlThemQuanAo();
    
    public static PnlThemQuanAo getInstance() {
        return instance;
    }
    
    public static PnlThemQuanAo newInstance() {
        instance = new PnlThemQuanAo();
        return instance;
    }
    
    public PnlThemQuanAo() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        String maQuanAo = generateMaQuanAo();
        txtMaQuanAo.setText(maQuanAo);
        txtMaQuanAo.setEditable(false);
        
        ArrayList<String> listNhaSanXuat = DAO_NhaSanXuat.getAllNhaSanXuat();
        for(String thisNhaSanXuat : listNhaSanXuat){
            cmbNhaSanXuat.addItem(thisNhaSanXuat);
        }
        cmbNhaSanXuat.setSelectedItem(null);
       
        ArrayList<String> listDanhMuc = DAO_DanhMuc.getAllDanhMuc();
        for(String thisDanhMuc : listDanhMuc){
            cmbDanhMuc.addItem(thisDanhMuc);
        }
        cmbDanhMuc.setSelectedItem(null);
        
        ArrayList<String> listGioiTinh = DAO_GioiTinh.getAllGioiTinh();
        for(String thisGioiTinh : listGioiTinh){
            cmbGioiTinh.addItem(thisGioiTinh);
        }
        cmbGioiTinh.setSelectedItem(null);
        
        ArrayList<String> listMauSac = DAO_MauSac.getAllMauSac();
        for(String thisMauSac : listMauSac){
            cmbMauSac.addItem(thisMauSac);
        }
        cmbMauSac.setSelectedItem(null);
        
        ArrayList<String> listKichThuoc = DAO_KichThuoc.getAllKichThuoc();
        for(String thisKichThuoc : listKichThuoc){
            cmbKichThuoc.addItem(thisKichThuoc);
        }
        cmbKichThuoc.setSelectedItem(null);
        
        ArrayList<String> listChatLieu = DAO_ChatLieu.getAllChatLieu();
        for(String thisChatLieu : listChatLieu){
            cmbChatLieu.addItem(thisChatLieu);
        }
        cmbChatLieu.setSelectedItem(null);
        
        updateTable(DAO_QuanAo.getAllQuanAo());
    }
    
    private String generateMaQuanAo(){
        String maQuanAo;
        int soSanPham;
        String prefix = "SP";
        String maQuanAoCuoi = DAO_QuanAo.getMaQuanAoCuoi();
        if(maQuanAoCuoi == null){
            soSanPham = 1;
            maQuanAo = prefix + String.format("%06d", soSanPham);
        }
        else{
            soSanPham = Integer.parseInt(maQuanAoCuoi.substring(2)) + 1;
            maQuanAo = prefix + String.format("%06d", soSanPham);
        }
        return maQuanAo;
    }
    
    private void themQuanAo(){
        String maQuanAo = txtMaQuanAo.getText();
        String tenQuanAo = txtTenQuanAo.getText();
        
        String donGiaNhapString = txtDonGiaNhap.getText();
        double donGiaNhap = Double.parseDouble(donGiaNhapString);
        
        String donGiaBanString = txtDonGiaBan.getText();
        double donGiaBan = Double.parseDouble(donGiaBanString);
        
        String soLuongString = txtSoLuong.getText();
        int soLuong = Integer.parseInt(soLuongString);
        
        String nhaSanXuat = cmbNhaSanXuat.getSelectedItem().toString();
        String danhMuc = cmbDanhMuc.getSelectedItem().toString();
        String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
        String mauSac = cmbMauSac.getSelectedItem().toString();
        String kichThuoc = cmbKichThuoc.getSelectedItem().toString();
        String chatLieu = cmbChatLieu.getSelectedItem().toString();
        ImageIcon hinhAnh = UtilityImageIcon.fromStringPath(imagePath, 194, 270);
        boolean ngungNhap = false;
        
        QuanAo quanAo = new QuanAo(maQuanAo, tenQuanAo, donGiaNhap, donGiaBan, soLuong, nhaSanXuat, danhMuc, gioiTinh, mauSac, kichThuoc, chatLieu, hinhAnh, ngungNhap);
        
        boolean themQuanAo = DAO_QuanAo.createQuanAo(quanAo);
        if(themQuanAo == true){
            JOptionPane.showMessageDialog(null, "Thêm Thành Công");
            PnlMain.getInstance().showPanel(newInstance());
        }
        else{
            JOptionPane.showMessageDialog(null, "Thêm Thất Bại");
        }
    }
    
    private void updateTable(ArrayList<QuanAo> list){
        DefaultTableModel model = (DefaultTableModel) tblDanhSachQuanAo.getModel();
        for(QuanAo thisQuanAo : list){
            model.addRow(new Object[]{
                thisQuanAo.getMaQuanAo(),
                thisQuanAo.getTenQuanAo(),
                thisQuanAo.getDonGiaNhap(),
                thisQuanAo.getDonGiaBan(),
                thisQuanAo.getSoLuongTrongKho(),
                thisQuanAo.getNhaSanXuat(),
                thisQuanAo.getDanhMuc(),
                thisQuanAo.getGioiTinh(),
                thisQuanAo.getMauSac(),
                thisQuanAo.getKichThuoc(),
                thisQuanAo.getChatLieu()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThemQuanAo = new javax.swing.JPanel();
        lblMaQuanAo = new javax.swing.JLabel();
        lblTenQuanAo = new javax.swing.JLabel();
        lblDonGiaNhap = new javax.swing.JLabel();
        lblDonGiaBan = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblNhaSanXuat = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblMauSac = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        lblChatLieu = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        txtMaQuanAo = new javax.swing.JTextField();
        txtTenQuanAo = new javax.swing.JTextField();
        txtDonGiaNhap = new javax.swing.JTextField();
        txtDonGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cmbNhaSanXuat = new javax.swing.JComboBox<>();
        cmbDanhMuc = new javax.swing.JComboBox<>();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        cmbMauSac = new javax.swing.JComboBox<>();
        cmbKichThuoc = new javax.swing.JComboBox<>();
        cmbChatLieu = new javax.swing.JComboBox<>();
        btnHinhAnh = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        btnTimKiemTheoMa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnhPNG = new javax.swing.JLabel();
        scrDanhSachQuanAo = new javax.swing.JScrollPane();
        tblDanhSachQuanAo = new javax.swing.JTable();

        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setLayout(new java.awt.BorderLayout());

        pnlThemQuanAo.setBackground(new java.awt.Color(79, 137, 255));
        pnlThemQuanAo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quần áo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThemQuanAo.setPreferredSize(new java.awt.Dimension(1166, 349));

        lblMaQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaQuanAo.setForeground(new java.awt.Color(255, 255, 255));
        lblMaQuanAo.setText("Mã quần áo");

        lblTenQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenQuanAo.setForeground(new java.awt.Color(255, 255, 255));
        lblTenQuanAo.setText("Tên quần áo");

        lblDonGiaNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDonGiaNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblDonGiaNhap.setText("Đơn giá nhập");

        lblDonGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDonGiaBan.setForeground(new java.awt.Color(255, 255, 255));
        lblDonGiaBan.setText("Đơn giá bán");

        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblSoLuong.setText("Số lượng");

        lblNhaSanXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNhaSanXuat.setForeground(new java.awt.Color(255, 255, 255));
        lblNhaSanXuat.setText("Nhà sản xuất");

        lblDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        lblDanhMuc.setText("Danh mục");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setText("Giới tính");

        lblMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMauSac.setForeground(new java.awt.Color(255, 255, 255));
        lblMauSac.setText("Màu sắc");

        lblKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKichThuoc.setForeground(new java.awt.Color(255, 255, 255));
        lblKichThuoc.setText("Kích thước");

        lblChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        lblChatLieu.setText("Chất liệu");

        lblHinhAnh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        lblHinhAnh.setText("Hình ảnh");

        txtMaQuanAo.setBackground(new java.awt.Color(204, 204, 204));
        txtMaQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTenQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDonGiaNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDonGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbNhaSanXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnHinhAnh.setBackground(new java.awt.Color(204, 255, 255));
        btnHinhAnh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHinhAnh.setText("Hình ảnh");
        btnHinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHinhAnhActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(0, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTimKiem.setText("Tìm kiếm ");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnTimKiemTheoMa.setBackground(new java.awt.Color(0, 255, 255));
        btnTimKiemTheoMa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTimKiemTheoMa.setText("Tìm kiếm theo mã");
        btnTimKiemTheoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoMaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 255, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        pnlHinhAnh.setBackground(new java.awt.Color(204, 204, 204));
        pnlHinhAnh.setLayout(new java.awt.GridBagLayout());

        lblHinhAnhPNG.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHinhAnhPNG.setText("IMG");
        pnlHinhAnh.add(lblHinhAnhPNG, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout pnlThemQuanAoLayout = new javax.swing.GroupLayout(pnlThemQuanAo);
        pnlThemQuanAo.setLayout(pnlThemQuanAoLayout);
        pnlThemQuanAoLayout.setHorizontalGroup(
            pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemQuanAoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThemQuanAoLayout.createSequentialGroup()
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDonGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(lblDonGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(txtTenQuanAo)
                            .addComponent(txtDonGiaNhap)
                            .addComponent(txtDonGiaBan))
                        .addGap(55, 55, 55)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(lblDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoLuong)
                            .addComponent(cmbNhaSanXuat, 0, 135, Short.MAX_VALUE)
                            .addComponent(cmbDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(lblChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                    .addGroup(pnlThemQuanAoLayout.createSequentialGroup()
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlThemQuanAoLayout.setVerticalGroup(
            pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemQuanAoLayout.createSequentialGroup()
                .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThemQuanAoLayout.createSequentialGroup()
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThemQuanAoLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                    .addComponent(lblMaQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemQuanAoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(lblKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNhaSanXuat)
                            .addComponent(lblNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenQuanAo))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(lblChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDanhMuc)
                            .addComponent(lblDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDonGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDonGiaNhap))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDonGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDonGiaBan)
                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbGioiTinh)
                            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(pnlThemQuanAoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnTimKiemTheoMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnlThemQuanAo, java.awt.BorderLayout.CENTER);

        scrDanhSachQuanAo.setPreferredSize(new java.awt.Dimension(1166, 351));

        tblDanhSachQuanAo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã quần áo", "Tên quần áo", "Đơn giá nhập", "Đơn giá bán", "Số lượng", "Nhà sản xuất", "Danh mục", "Giới tính", "Màu sắc", "Kích thước", "Chất liệu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachQuanAo.setPreferredSize(new java.awt.Dimension(1166, 351));
        scrDanhSachQuanAo.setViewportView(tblDanhSachQuanAo);
        if (tblDanhSachQuanAo.getColumnModel().getColumnCount() > 0) {
            tblDanhSachQuanAo.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblDanhSachQuanAo.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        add(scrDanhSachQuanAo, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemTheoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemTheoMaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themQuanAo();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        PnlMain.getInstance().showPanel(newInstance());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHinhAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter executeFilter = new FileNameExtensionFilter("Image", "jpg", "png");
        fileChooser.setFileFilter(executeFilter);
        fileChooser.setMultiSelectionEnabled(false);
        
        int prompt = fileChooser.showDialog(this, "Add");
        if(prompt == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            imagePath = file.getAbsolutePath();
            lblHinhAnhPNG.setText("");
            lblHinhAnhPNG.setIcon(UtilityImageIcon.fromStringPath(imagePath, 194, 270));
        }
    }//GEN-LAST:event_btnHinhAnhActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHinhAnh;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemTheoMa;
    private javax.swing.JComboBox<String> cmbChatLieu;
    private javax.swing.JComboBox<String> cmbDanhMuc;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JComboBox<String> cmbMauSac;
    private javax.swing.JComboBox<String> cmbNhaSanXuat;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblDonGiaBan;
    private javax.swing.JLabel lblDonGiaNhap;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblHinhAnhPNG;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblMaQuanAo;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblNhaSanXuat;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenQuanAo;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JPanel pnlThemQuanAo;
    private javax.swing.JScrollPane scrDanhSachQuanAo;
    private javax.swing.JTable tblDanhSachQuanAo;
    private javax.swing.JTextField txtDonGiaBan;
    private javax.swing.JTextField txtDonGiaNhap;
    private javax.swing.JTextField txtMaQuanAo;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenQuanAo;
    // End of variables declaration//GEN-END:variables

}
