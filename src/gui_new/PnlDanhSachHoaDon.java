package gui_new;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import data.FormatDouble;
import data.UtilityLocalDateTime;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachHoaDon extends javax.swing.JPanel {
    
    private static PnlDanhSachHoaDon instance = new PnlDanhSachHoaDon();

    public static PnlDanhSachHoaDon getInstance() {
        return instance;
    }
    
    public static PnlDanhSachHoaDon newInstance() {
        instance = new PnlDanhSachHoaDon();
        return instance;
    }
    
    public PnlDanhSachHoaDon() {
        initComponents();
        showTableListHoaDon(DAO_HoaDon.getAllHoaDon());
    }

    private void showTableListHoaDon(ArrayList<HoaDon> list){
        DefaultTableModel model = (DefaultTableModel) tblDanhSachHoaDon.getModel();
        model.getDataVector().removeAllElements();
        tblDanhSachHoaDon.revalidate();
        tblDanhSachHoaDon.repaint();
        for(HoaDon thisHoaDon : list){
            ArrayList<ChiTietHoaDon> listCTHD = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(thisHoaDon.getMaHoaDon());
            double tongTienThanhPhan = 0;
            for(ChiTietHoaDon thisChiTietHoaDon : listCTHD){
                tongTienThanhPhan += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            }
            model.addRow(new Object[]{
                thisHoaDon.getMaHoaDon(),
                thisHoaDon.getNhanVien().getHoTen(),
                thisHoaDon.getKhachHang().getHoTen(),
                thisHoaDon.getKhachHang().getSoDienThoai(),
                UtilityLocalDateTime.toFormattedLocalDateTime(thisHoaDon.getThoiGianTao()),
                FormatDouble.toMoney(tongTienThanhPhan)
            });
        }
    }
    
    private void xemChiTietHoaDon(){
        int i = tblDanhSachHoaDon.getSelectedRow();
        if(i < 0){
            JOptionPane.showConfirmDialog(null, "Vui lòng chọn một Hóa Đơn");
            return;
        }
        String maHoaDon = tblDanhSachHoaDon.getValueAt(i, 0).toString();
        
        PnlMain.getInstance().showPanel(PnlChiTietHoaDon.newInstance());
        PnlChiTietHoaDon.getInstance().showThongTinHoaDon(maHoaDon);
        PnlChiTietHoaDon.getInstance().setPnlBefore(this);
    }
    
    private void timKiemHoaDon(){
        String maHoaDon = txtMaHoaDon.getText();
        String soDienThoai = txtSoDienThoai.getText();
        
        if(maHoaDon.equals("") && soDienThoai.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm.");
        }
        
        ArrayList<HoaDon> list = DAO_HoaDon.getAllHoaDon();
        ArrayList<HoaDon> listRemove = new ArrayList<>();
        
        if(!maHoaDon.equals("")){
            for(int i = 0; i < list.size(); i++){
                HoaDon thisHoaDon = list.get(i);
                if(!thisHoaDon.getMaHoaDon().equals(maHoaDon))
                    listRemove.add(thisHoaDon);
            }
            list.removeAll(listRemove);
            showTableListHoaDon(list);
            return;
        }
        
        if(!soDienThoai.equals("")){
            for(int i = 0; i < list.size(); i++){
                HoaDon thisHoaDon = list.get(i);
                if(!thisHoaDon.getKhachHang().getSoDienThoai().equals(soDienThoai))
                    listRemove.add(thisHoaDon);
            }
            list.removeAll(listRemove);
            showTableListHoaDon(list);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrDanhSachHoaDon = new javax.swing.JScrollPane();
        tblDanhSachHoaDon = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        lblMaHoaDon = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnXemChiTiet = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1166, 700));
        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        scrDanhSachHoaDon.setPreferredSize(new java.awt.Dimension(900, 700));

        tblDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Nhân Viên", "Khách Hàng", "Số Điện Thoại", "Ngày Lập Hóa Đơn", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachHoaDon.setRowHeight(40);
        scrDanhSachHoaDon.setViewportView(tblDanhSachHoaDon);

        add(scrDanhSachHoaDon, java.awt.BorderLayout.WEST);

        pnlTimKiem.setBackground(new java.awt.Color(79, 137, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm Hóa Đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblMaHoaDon.setText("Mã Hóa Đơn");

        lblSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setText("Số Điện Thoại");

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimKiem.setBackground(new java.awt.Color(170, 238, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(170, 238, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnXemChiTiet.setBackground(new java.awt.Color(170, 238, 255));
        btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemChiTiet.setText("Xem Chi Tiết Hóa Đơn");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXemChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoDienThoai))
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHoaDon)
                    .addComponent(lblMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoai))
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemHoaDon();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        // TODO add your handling code here:
        xemChiTietHoaDon();
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        PnlMain.getInstance().showPanel(newInstance());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrDanhSachHoaDon;
    private javax.swing.JTable tblDanhSachHoaDon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

}
