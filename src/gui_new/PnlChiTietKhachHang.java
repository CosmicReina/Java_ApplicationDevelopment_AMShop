package gui_new;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import data.FormatDouble;
import data.UtilityLocalDateTime;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import gui.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class PnlChiTietKhachHang extends javax.swing.JPanel {
    
    private static PnlChiTietKhachHang instance = new PnlChiTietKhachHang();
    
    private JPanel pnlBefore = null;

    public JPanel getPnlBefore() {
        return pnlBefore;
    }

    public void setPnlBefore(JPanel pnlBefore) {
        this.pnlBefore = pnlBefore;
    }

    public static PnlChiTietKhachHang getInstance() {
        return instance;
    }
    
    public static PnlChiTietKhachHang newInstance() {
        instance = new PnlChiTietKhachHang();
        return instance;
    }
    
    public PnlChiTietKhachHang() {
        initComponents();
    }
    
    public void showThongTinKhachHang(String maKhachHang){
        KhachHang khachHang = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHang);
        if(khachHang == null) return;
        ArrayList<HoaDon> listHD = DAO_HoaDon.getAllHoaDon();
        ArrayList<HoaDon> listRemove = new ArrayList<>();
        for(int i = 0; i < listHD.size(); i++){
            HoaDon thisHoaDon = listHD.get(i);
            if(!thisHoaDon.getKhachHang().getMaKhachHang().equals(maKhachHang))
                listRemove.add(thisHoaDon);
        }
        listHD.removeAll(listRemove);
        
        DefaultTableModel model = (DefaultTableModel) tblDanhSachHoaDonKhachHangThanhToan.getModel();
        for(HoaDon thisHoaDon : listHD){
            ArrayList<ChiTietHoaDon> listCTHD = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(thisHoaDon.getMaHoaDon());
            double tongTien = 0;
            for(ChiTietHoaDon thisChiTietHoaDon : listCTHD)
                tongTien += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            model.addRow(new Object[]{
                thisHoaDon.getMaHoaDon(),
                thisHoaDon.getNhanVien().getHoTen(),
                UtilityLocalDateTime.toFormattedLocalDateTime(thisHoaDon.getThoiGianTao()),
                FormatDouble.toMoney(tongTien)
            });
        }
        
        txtMaKhachHang.setText(khachHang.getMaKhachHang());
        txtHoTen.setText(khachHang.getHoTen());
        txtSoDienThoai.setText(khachHang.getSoDienThoai());
        txtDiaChi.setText(khachHang.getDiaChi());
        txtNhomKhachHang.setText(khachHang.getNhomKhachHang());
    }
    
    private void xemChiTietHoaDon(){
        int i = tblDanhSachHoaDonKhachHangThanhToan.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Hóa Đơn");
            return;
        }
        String maHoaDon = tblDanhSachHoaDonKhachHangThanhToan.getValueAt(i, 0).toString();
        
        PnlMain.getInstance().showPanel(PnlChiTietHoaDon.newInstance());
        PnlChiTietHoaDon.getInstance().showThongTinHoaDon(maHoaDon);
        PnlChiTietHoaDon.getInstance().setPnlBefore(this);
    }
    
    private void quayLai(){
        if(pnlBefore != null) PnlMain.getInstance().showPanel(pnlBefore);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinKhachHang = new javax.swing.JPanel();
        lblMaKhachHang = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblNhomKhachHang = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtNhomKhachHang = new javax.swing.JTextField();
        btnXemChiTietHoaDon = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        scrDanhSachHoaDonKhachHangThanhToan = new javax.swing.JScrollPane();
        tblDanhSachHoaDonKhachHangThanhToan = new javax.swing.JTable();

        setBackground(new java.awt.Color(68, 136, 255));
        setMinimumSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        pnlThongTinKhachHang.setBackground(new java.awt.Color(68, 136, 255));
        pnlThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinKhachHang.setMinimumSize(new java.awt.Dimension(350, 700));
        pnlThongTinKhachHang.setPreferredSize(new java.awt.Dimension(350, 700));

        lblMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblMaKhachHang.setText("Mã Khách Hàng");

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(255, 255, 255));
        lblHoTen.setText("Họ Tên");

        lblSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setText("Số Điện Thoại");

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setText("Địa Chỉ");

        lblNhomKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNhomKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblNhomKhachHang.setText("Nhóm Khách");

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHoTen.setEditable(false);
        txtHoTen.setBackground(new java.awt.Color(204, 204, 204));
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoDienThoai.setEditable(false);
        txtSoDienThoai.setBackground(new java.awt.Color(204, 204, 204));
        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDiaChi.setEditable(false);
        txtDiaChi.setBackground(new java.awt.Color(204, 204, 204));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNhomKhachHang.setEditable(false);
        txtNhomKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtNhomKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnXemChiTietHoaDon.setBackground(new java.awt.Color(0, 255, 255));
        btnXemChiTietHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemChiTietHoaDon.setText("Xem Chi Tiết Hóa Đơn");
        btnXemChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietHoaDonActionPerformed(evt);
            }
        });

        btnQuayLai.setBackground(new java.awt.Color(255, 0, 51));
        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinKhachHangLayout = new javax.swing.GroupLayout(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(pnlThongTinKhachHangLayout);
        pnlThongTinKhachHangLayout.setHorizontalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(lblNhomKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNhomKhachHang))
                            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi))
                            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSoDienThoai))
                            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHoTen))
                            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(btnXemChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlThongTinKhachHangLayout.setVerticalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNhomKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNhomKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                .addComponent(btnXemChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlThongTinKhachHang, java.awt.BorderLayout.WEST);

        scrDanhSachHoaDonKhachHangThanhToan.setBackground(new java.awt.Color(68, 136, 255));
        scrDanhSachHoaDonKhachHangThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hóa Đơn Khách Hàng Đã Thanh Toán", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        scrDanhSachHoaDonKhachHangThanhToan.setMinimumSize(new java.awt.Dimension(816, 700));
        scrDanhSachHoaDonKhachHangThanhToan.setPreferredSize(new java.awt.Dimension(816, 700));

        tblDanhSachHoaDonKhachHangThanhToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Nhân viên lập đơn", "Ngày lập đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachHoaDonKhachHangThanhToan.setPreferredSize(new java.awt.Dimension(816, 700));
        tblDanhSachHoaDonKhachHangThanhToan.setRowHeight(40);
        scrDanhSachHoaDonKhachHangThanhToan.setViewportView(tblDanhSachHoaDonKhachHangThanhToan);

        add(scrDanhSachHoaDonKhachHangThanhToan, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        xemChiTietHoaDon();
    }//GEN-LAST:event_btnXemChiTietHoaDonActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        quayLai();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnXemChiTietHoaDon;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaKhachHang;
    private javax.swing.JLabel lblNhomKhachHang;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JPanel pnlThongTinKhachHang;
    private javax.swing.JScrollPane scrDanhSachHoaDonKhachHangThanhToan;
    private javax.swing.JTable tblDanhSachHoaDonKhachHangThanhToan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNhomKhachHang;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

}
