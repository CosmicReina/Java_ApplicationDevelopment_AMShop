package gui_new;

import dao.DAO_KhachHang;
import data.UtilityJTextField;
import entity.KhachHang;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlCapNhatKhachHang extends javax.swing.JPanel {
    
    private static PnlCapNhatKhachHang instance = new PnlCapNhatKhachHang();

    public static PnlCapNhatKhachHang getInstance() {
        return instance;
    }
    
    public static PnlCapNhatKhachHang newInstance() {
        instance = new PnlCapNhatKhachHang();
        return instance;
    }

    public PnlCapNhatKhachHang() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTable(DAO_KhachHang.getAllKhachHang());
        
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoaiTimKiem);
        UtilityJTextField.addPlaceHolderStyle(txtMaKhachHang);
        UtilityJTextField.addPlaceHolderStyle(txtHoTen);
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
        UtilityJTextField.addPlaceHolderStyle(txtDiaChi);
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
        String soDienThoai = txtSoDienThoaiTimKiem.getText();
        
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
    
    private void capNhatKhachHang(){
        String maKhachHang = txtMaKhachHang.getText();
        if(maKhachHang.equals("Mã Khách Hàng")){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Khách Hàng để cập nhật.");
            return;
        }
        String error = "";
        
        KhachHang khachHangCapNhat = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHang);
        
        String hoTen = txtHoTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = txtDiaChi.getText();
        
        if(hoTen.equals("Họ Tên")) //Kiểm tra rỗng
            error += "\n- Vui lòng nhập Họ Tên.";
        else
            if(!hoTen.matches("^[\\p{L}]+(\\s[\\p{L}]+)+$")) //Kiểm tra Biểu thức chính quy
                error += "\n- Vui lòng nhập Họ Tên hợp lệ.";
        
        if(soDienThoai.equals("Số Điện Thoại")) // Kiểm tra rỗng
            error += "\n- Vui lòng nhập Số Điện Thoại.";
        else
            if(!soDienThoai.matches("0{1}[0-9]{9}")) // Kiểm tra biểu thức chính quy
                error += "\n- Vui lòng nhập Số Điện Thoại hợp lệ.";
            else
                if(!khachHangCapNhat.getSoDienThoai().equals(soDienThoai))
                    if(DAO_KhachHang.getKhachHangTheoSoDienThoai(soDienThoai) != null) // Kiểm tra đã tồn tại
                        error += "\n- Số Điện Thoại đã tồn tại";
        
        if(diaChi.equals("Địa Chỉ") || diaChi.isBlank())
            error += "\n- Vui lòng nhập Địa Chỉ";
        
        if(error.equals("")){
            khachHangCapNhat.setHoTen(hoTen);
            khachHangCapNhat.setSoDienThoai(soDienThoai);
            khachHangCapNhat.setDiaChi(diaChi);
            if(DAO_KhachHang.updateKhachHang(khachHangCapNhat) == true){
                JOptionPane.showMessageDialog(null, "Cập Nhật Khách Hàng thành công.");
                PnlMain.getInstance().showPanel(newInstance());
            }
            else{
                JOptionPane.showMessageDialog(null, "Cập Nhật Khách Hàng thất bại.");
            }
        }
        else{
            String throwMessage = "Lỗi nhập liệu: " + error;
            JOptionPane.showMessageDialog(null, throwMessage);
        }
    }
    
    private void hienThiThongTinKhachHang(){
        int i = tblTable.getSelectedRow();
        if(i < 0) return;
        String maKhachHangHienThi = tblTable.getValueAt(i, 0).toString();
        KhachHang khachHang = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHangHienThi);
        
        txtMaKhachHang.setText(khachHang.getMaKhachHang());
        txtHoTen.setText(khachHang.getHoTen());
        txtSoDienThoai.setText(khachHang.getSoDienThoai());
        txtDiaChi.setText(khachHang.getDiaChi());
        
        UtilityJTextField.addPlaceHolderStyle(txtMaKhachHang);
        UtilityJTextField.addPlaceHolderStyle(txtHoTen);
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
        UtilityJTextField.addPlaceHolderStyle(txtDiaChi);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimKiem = new javax.swing.JPanel();
        txtSoDienThoaiTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        txtMaKhachHang = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnCapNhat = new javax.swing.JButton();
        pnlTable = new javax.swing.JPanel();
        scrTable = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlTimKiem.setBackground(new java.awt.Color(68, 136, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiem.setPreferredSize(new java.awt.Dimension(300, 700));

        txtSoDienThoaiTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoaiTimKiem.setText("Số Điện Thoại");
        txtSoDienThoaiTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiTimKiemFocusLost(evt);
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

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaKhachHang.setText("Mã Khách Hàng");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setText("Họ Tên");
        txtHoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoTenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoTenFocusLost(evt);
            }
        });

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

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setText("Địa Chỉ");
        txtDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusLost(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(170, 238, 255));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoDienThoaiTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaKhachHang)
                    .addComponent(txtHoTen)
                    .addComponent(txtSoDienThoai)
                    .addComponent(txtDiaChi)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtSoDienThoaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 275, Short.MAX_VALUE))
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
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        scrTable.setViewportView(tblTable);

        pnlTable.add(scrTable, java.awt.BorderLayout.CENTER);

        add(pnlTable, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiTimKiemFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoaiTimKiem, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiTimKiemFocusGained

    private void txtSoDienThoaiTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiTimKiemFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoDienThoaiTimKiem, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiTimKiemFocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemTheoSoDienThoai();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        PnlMain.getInstance().showPanel(newInstance());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        capNhatKhachHang();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtHoTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtHoTen, "Họ Tên");
    }//GEN-LAST:event_txtHoTenFocusGained

    private void txtHoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtHoTen, "Họ Tên");
    }//GEN-LAST:event_txtHoTenFocusLost

    private void txtSoDienThoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusGained

    private void txtSoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusLost

    private void txtDiaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtDiaChi, "Địa Chỉ");
    }//GEN-LAST:event_txtDiaChiFocusGained

    private void txtDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtDiaChi, "Địa Chỉ");
    }//GEN-LAST:event_txtDiaChiFocusLost

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        // TODO add your handling code here:
        hienThiThongTinKhachHang();
    }//GEN-LAST:event_tblTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoDienThoaiTimKiem;
    // End of variables declaration//GEN-END:variables

}
