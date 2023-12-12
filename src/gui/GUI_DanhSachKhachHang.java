package gui;

import gui_old.*;
import dao.DAO_KhachHang;
import data.UtilityJTextField;
import entity.KhachHang;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GUI_DanhSachKhachHang extends javax.swing.JPanel {
    
    private static GUI_DanhSachKhachHang instance = new GUI_DanhSachKhachHang();

    public static GUI_DanhSachKhachHang getInstance() {
        return instance;
    }
    
    public static GUI_DanhSachKhachHang newInstance() {
        instance = new GUI_DanhSachKhachHang();
        return instance;
    }

    public GUI_DanhSachKhachHang() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTable(DAO_KhachHang.getAllKhachHang());
        
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
    }
    
    private void updateTable(ArrayList<KhachHang> list){
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.getDataVector().removeAllElements();
        tblTable.revalidate();
        tblTable.repaint();
        for(KhachHang thisKhachHang : list){
            model.addRow(new Object[]{
                thisKhachHang.getMaKhachHang(),
                thisKhachHang.getHoTen(),
                thisKhachHang.getSoDienThoai(),
                thisKhachHang.getDiaChi(),
                thisKhachHang.getNhomKhachHang()
            });
        }
    }
    
    private void timKiemTheoSoDienThoai(){
        String soDienThoai = txtSoDienThoai.getText();
        
        ArrayList<KhachHang> list = DAO_KhachHang.getAllKhachHang();
        ArrayList<KhachHang> listRemove = new ArrayList<>();
        
        if(!soDienThoai.equals("")){
            for(KhachHang thisKhachHang : list){
                if(!thisKhachHang.getSoDienThoai().equals(soDienThoai))
                    listRemove.add(thisKhachHang);
            }
        }
        
        list.removeAll(listRemove);
        updateTable(list);
    }

    private void xemChiTietKhachHang(){
        int i = tblTable.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Khách Hàng.");
            return;
        }
        String maKhachHang = tblTable.getValueAt(i, 0).toString();
        
        PnlMain.getInstance().showPanel(PnlChiTietKhachHang.newInstance());
        PnlChiTietKhachHang.getInstance().setPnlBefore(this);
        PnlChiTietKhachHang.getInstance().showThongTinKhachHang(maKhachHang);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimKiem = new javax.swing.JPanel();
        txtSoDienThoai = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        pnlTable = new javax.swing.JPanel();
        scrTable = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlTimKiem.setBackground(new java.awt.Color(68, 136, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiem.setPreferredSize(new java.awt.Dimension(300, 700));

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoai.setText("Số Điện Thoại");
        txtSoDienThoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiFocusLost(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(170, 238, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(170, 238, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnChiTiet.setBackground(new java.awt.Color(170, 238, 255));
        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChiTiet.setText("Xem Chi Tiết Khách Hàng");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoDienThoai)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 465, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.EAST);

        pnlTable.setLayout(new java.awt.BorderLayout());

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khách Hàng", "Họ Tên", "Số Điện Thoại", "Địa Chỉ", "Nhóm Khách Hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTable.setRowHeight(40);
        tblTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrTable.setViewportView(tblTable);

        pnlTable.add(scrTable, java.awt.BorderLayout.CENTER);

        add(pnlTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusGained

    private void txtSoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemTheoSoDienThoai();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        PnlMain.getInstance().showPanel(newInstance());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        xemChiTietKhachHang();
    }//GEN-LAST:event_btnChiTietActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

}
