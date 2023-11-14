package gui_new;

import dao.DAO_ChatLieu;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_CuaHang;
import dao.DAO_DanhMuc;
import dao.DAO_GioiTinh;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_KichThuoc;
import dao.DAO_MauSac;
import dao.DAO_NhaSanXuat;
import dao.DAO_NhanVien;
import dao.DAO_QuanAo;
import data.FormatDouble;
import data.GenerateID;
import data.GenerateInvoice;
import data.UtilityJTextField;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.CuaHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.QuanAo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachQuanAo extends javax.swing.JPanel implements MouseListener{
    
    private static PnlDanhSachQuanAo instance = new PnlDanhSachQuanAo();
    
    private double tongTien = 0;
    
    private ArrayList<ChiTietHoaDon> listDonHang = new ArrayList<>();

    public static PnlDanhSachQuanAo getInstance() {
        return instance;
    }
    
    public static PnlDanhSachQuanAo newInstance() {
        instance = new PnlDanhSachQuanAo();
        return instance;
    }

    public static void setInstance(PnlDanhSachQuanAo instance) {
        PnlDanhSachQuanAo.instance = instance;
    }

    public PnlDanhSachQuanAo() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTableQuanAo(DAO_QuanAo.getAllQuanAo());
        
        UtilityJTextField.addPlaceHolderStyle(txtMaQuanAo);
        UtilityJTextField.addPlaceHolderStyle(txtTenQuanAo);
        
        ArrayList<String> listNhaSanXuat = DAO_NhaSanXuat.getAllNhaSanXuat();
        ArrayList<String> listDanhMuc = DAO_DanhMuc.getAllDanhMuc();
        ArrayList<String> listGioiTinh = DAO_GioiTinh.getAllGioiTinh();
        ArrayList<String> listMauSac = DAO_MauSac.getAllMauSac();
        ArrayList<String> listKichThuoc = DAO_KichThuoc.getAllKichThuoc();
        ArrayList<String> listChatLieu = DAO_ChatLieu.getAllChatLieu();
        
        for(String thisNhaSanXuat : listNhaSanXuat){
            cmbNhaSanXuat.addItem(thisNhaSanXuat);
        }
        
        for(String thisDanhMuc : listDanhMuc){
            cmbDanhMuc.addItem(thisDanhMuc);
        }
        
        for(String thisGioiTinh : listGioiTinh){
            cmbGioiTinh.addItem(thisGioiTinh);
        }
        
        for(String thisMauSac : listMauSac){
            cmbMauSac.addItem(thisMauSac);
        }
        
        for(String thisKichThuoc : listKichThuoc){
            cmbKichThuoc.addItem(thisKichThuoc);
        }
        
        for(String thisChatLieu : listChatLieu){
            cmbChatLieu.addItem(thisChatLieu);
        }
    }
    
    private void updateTableQuanAo(ArrayList<QuanAo> list){
        DefaultTableModel model = (DefaultTableModel) tblQuanAo.getModel();
        model.getDataVector().removeAllElements();
        tblQuanAo.revalidate();
        tblQuanAo.repaint();
        for(QuanAo thisQuanAo : list){
            model.addRow(new Object[]{
                thisQuanAo.getMaQuanAo(),
                thisQuanAo.getTenQuanAo(),
                FormatDouble.toMoney(thisQuanAo.getDonGiaBan()),
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

        pnlQuanAo = new javax.swing.JPanel();
        scrQuanAo = new javax.swing.JScrollPane();
        tblQuanAo = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        txtMaQuanAo = new javax.swing.JTextField();
        txtTenQuanAo = new javax.swing.JTextField();
        cmbNhaSanXuat = new javax.swing.JComboBox<>();
        cmbDanhMuc = new javax.swing.JComboBox<>();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        cmbMauSac = new javax.swing.JComboBox<>();
        cmbKichThuoc = new javax.swing.JComboBox<>();
        cmbChatLieu = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        pnlQuanAo.setPreferredSize(new java.awt.Dimension(916, 700));
        pnlQuanAo.setLayout(new java.awt.BorderLayout());

        scrQuanAo.setPreferredSize(new java.awt.Dimension(916, 700));

        tblQuanAo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Quần Áo", "Tên Quần Áo", "Đơn Giá", "Số Lượng", "Nhà Sản Xuất", "Danh Mục", "Giới Tính", "Màu Sắc", "Kích Thước", "Chất Liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanAo.setPreferredSize(new java.awt.Dimension(916, 700));
        tblQuanAo.setRowHeight(40);
        tblQuanAo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrQuanAo.setViewportView(tblQuanAo);
        if (tblQuanAo.getColumnModel().getColumnCount() > 0) {
            tblQuanAo.getColumnModel().getColumn(1).setMinWidth(200);
            tblQuanAo.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        pnlQuanAo.add(scrQuanAo, java.awt.BorderLayout.WEST);

        add(pnlQuanAo, java.awt.BorderLayout.CENTER);

        pnlTimKiem.setBackground(new java.awt.Color(68, 136, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiem.setPreferredSize(new java.awt.Dimension(250, 700));

        txtMaQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaQuanAo.setText("Mã Quần Áo");
        txtMaQuanAo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaQuanAoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaQuanAoFocusLost(evt);
            }
        });

        txtTenQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenQuanAo.setText("Tên Quần Áo");
        txtTenQuanAo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenQuanAoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenQuanAoFocusLost(evt);
            }
        });

        cmbNhaSanXuat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà Sản Xuất" }));

        cmbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Danh Mục" }));

        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giới Tính" }));

        cmbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Màu Sắc" }));

        cmbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kích Thước" }));

        cmbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chất Liệu" }));

        btnTimKiem.setBackground(new java.awt.Color(170, 238, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        pnlHinhAnh.setBackground(new java.awt.Color(204, 204, 204));
        pnlHinhAnh.setPreferredSize(new java.awt.Dimension(196, 270));
        pnlHinhAnh.setLayout(new java.awt.GridBagLayout());

        lblHinhAnh.setText("IMG");
        pnlHinhAnh.add(lblHinhAnh, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaQuanAo)
                    .addComponent(txtTenQuanAo)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtMaQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaQuanAoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaQuanAoFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtMaQuanAo, "Mã Quần Áo");
    }//GEN-LAST:event_txtMaQuanAoFocusGained

    private void txtTenQuanAoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenQuanAoFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtTenQuanAo, "Tên Quần Áo");
    }//GEN-LAST:event_txtTenQuanAoFocusGained

    private void txtMaQuanAoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaQuanAoFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtMaQuanAo, "Mã Quần Áo");
    }//GEN-LAST:event_txtMaQuanAoFocusLost

    private void txtTenQuanAoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenQuanAoFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtTenQuanAo, "Tên Quần Áo");
    }//GEN-LAST:event_txtTenQuanAoFocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cmbChatLieu;
    private javax.swing.JComboBox<String> cmbDanhMuc;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JComboBox<String> cmbMauSac;
    private javax.swing.JComboBox<String> cmbNhaSanXuat;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JPanel pnlQuanAo;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrQuanAo;
    private javax.swing.JTable tblQuanAo;
    private javax.swing.JTextField txtMaQuanAo;
    private javax.swing.JTextField txtTenQuanAo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
