package gui_old;

import dao.DAO_KhachHang;
import dao.DAO_NhanVien;
import data.UtilityJTextField;
import entity.KhachHang;
import entity.NhanVien;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachNhanVien extends javax.swing.JPanel {
    
    private static PnlDanhSachNhanVien instance = new PnlDanhSachNhanVien();

    public static PnlDanhSachNhanVien getInstance() {
        return instance;
    }
    
    public static PnlDanhSachNhanVien newInstance() {
        instance = new PnlDanhSachNhanVien();
        return instance;
    }

    public PnlDanhSachNhanVien() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTable(DAO_NhanVien.getAllNhanVien());
        
        UtilityJTextField.addPlaceHolderStyle(txtMaNhanVien);
    }
    
    private void updateTable(ArrayList<NhanVien> list){
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.getDataVector().removeAllElements();
        tblTable.revalidate();
        tblTable.repaint();
        for(NhanVien thisNhanVien : list){
            model.addRow(new Object[]{
                thisNhanVien.getMaNhanVien(),
                thisNhanVien.getHoTen(),
                thisNhanVien.getSoDienThoai(),
                thisNhanVien.getCanCuocCongDan(),
                thisNhanVien.getChucVu()
            });
        }
    }
    
    private void timKiemTheoMaNhanVien(){
        String maNhanVien = txtMaNhanVien.getText();
        
        ArrayList<NhanVien> list = DAO_NhanVien.getAllNhanVien();
        ArrayList<NhanVien> listRemove = new ArrayList<>();
        
        if(!maNhanVien.equals("")){
            for(NhanVien thisNhanVien : list){
                if(!thisNhanVien.getMaNhanVien().equals(maNhanVien))
                    listRemove.add(thisNhanVien);
            }
        }
        
        list.removeAll(listRemove);
        updateTable(list);
    }

    private void xemChiTietNhanVien(){
        int i = tblTable.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Nhân Viên.");
            return;
        }
        String maNhanVien = tblTable.getValueAt(i, 0).toString();
        
        PnlMain.getInstance().showPanel(PnlChiTietNhanVien.newInstance());
        PnlChiTietNhanVien.getInstance().setPnlBefore(this);
        PnlChiTietNhanVien.getInstance().showChiTietNhanVien(maNhanVien);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimKiem = new javax.swing.JPanel();
        txtMaNhanVien = new javax.swing.JTextField();
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

        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNhanVien.setText("Mã Nhân Viên");
        txtMaNhanVien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaNhanVienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaNhanVienFocusLost(evt);
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
        btnChiTiet.setText("Xem Chi Tiết Nhân Viên");
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
                    .addComponent(txtMaNhanVien)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "Mã Nhân Viên", "Họ Tên", "Số Điện Thoại", "Căn Cước Công Dân", "Chức Vụ"
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

    private void txtMaNhanVienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaNhanVienFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtMaNhanVien, "Mã Nhân Viên");
    }//GEN-LAST:event_txtMaNhanVienFocusGained

    private void txtMaNhanVienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaNhanVienFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtMaNhanVien, "Mã Nhân Viên");
    }//GEN-LAST:event_txtMaNhanVienFocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemTheoMaNhanVien();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        PnlMain.getInstance().showPanel(newInstance());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        xemChiTietNhanVien();
    }//GEN-LAST:event_btnChiTietActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtMaNhanVien;
    // End of variables declaration//GEN-END:variables

}
