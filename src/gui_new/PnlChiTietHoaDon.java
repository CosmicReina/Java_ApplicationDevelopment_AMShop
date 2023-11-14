package gui_new;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import data.FormatDouble;
import data.UtilityLocalDateTime;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.QuanAo;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class PnlChiTietHoaDon extends javax.swing.JPanel {
    
    private static PnlChiTietHoaDon instance = new PnlChiTietHoaDon();
    
    private String maHoaDon;
    
    private JPanel pnlBefore = null;

    public JPanel getPnlBefore() {
        return pnlBefore;
    }

    public void setPnlBefore(JPanel pnlBefore) {
        this.pnlBefore = pnlBefore;
    }

    public static PnlChiTietHoaDon getInstance() {
        return instance;
    }
    
    public static PnlChiTietHoaDon newInstance() {
        instance = new PnlChiTietHoaDon();
        return instance;
    }
    
    public PnlChiTietHoaDon() {
        initComponents();
    }
    
    public void showThongTinHoaDon(String maHoaDon){
        HoaDon hoaDon = DAO_HoaDon.getHoaDonTheoMaHoaDon(maHoaDon);
        if(hoaDon == null) return;
        ArrayList<ChiTietHoaDon> list = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(maHoaDon);
        double tongTien = 0;
        
        DefaultTableModel model = (DefaultTableModel) tblDanhSachQuanAoTrongHoaDon.getModel();
        model.getDataVector().removeAllElements();
        tblDanhSachQuanAoTrongHoaDon.revalidate();
        tblDanhSachQuanAoTrongHoaDon.repaint();
        for(ChiTietHoaDon thisChiTietHoaDon : list){
            double tongTienThanhPhan = thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            tongTien += tongTienThanhPhan;
            QuanAo quanAo = thisChiTietHoaDon.getQuanAo();
            model.addRow(new Object[]{
                quanAo.getTenQuanAo(),
                thisChiTietHoaDon.getSoLuong(),
                thisChiTietHoaDon.getDonGia(),
                FormatDouble.toMoney(tongTienThanhPhan)
            });
        }
        
        txtMaHoaDon.setText(hoaDon.getMaHoaDon());
        txtNhanVienLapDon.setText(hoaDon.getNhanVien().getHoTen());
        txtKhachHang.setText(hoaDon.getKhachHang().getHoTen());
        txtThoiGianTao.setText(UtilityLocalDateTime.toFormattedLocalDateTime(hoaDon.getThoiGianTao()));
        txtTongTien.setText(FormatDouble.toMoney(tongTien));
        
        this.maHoaDon = maHoaDon;
    }
    
    private void quayLai(){
        if(pnlBefore != null){PnlMain.getInstance().showPanel(pnlBefore);}
    }
    
    private void xemFileHoaDon(){
        String path = "files//hoaDon//" + maHoaDon + ".pdf";
        File file = new File(path);
        if(file.exists()) {
            Desktop desktop = Desktop.getDesktop();
            try {desktop.open(file);} 
            catch (IOException ex) {ex.printStackTrace(System.out);}
        }
        else{
            JOptionPane.showMessageDialog(null, "File Hóa Đơn không tồn tại. Vui lòng kiểm tra lại.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinHoaDon = new javax.swing.JPanel();
        lblMaHoaDon = new javax.swing.JLabel();
        lblNhanVienLapDon = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblThoiGianTao = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtNhanVienLapDon = new javax.swing.JTextField();
        txtKhachHang = new javax.swing.JTextField();
        txtThoiGianTao = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        btnXemFileHoaDon = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        scrDanhSachQuanAoTrongHoaDon = new javax.swing.JScrollPane();
        tblDanhSachQuanAoTrongHoaDon = new javax.swing.JTable();

        setBackground(new java.awt.Color(68, 136, 255));
        setMinimumSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        pnlThongTinHoaDon.setBackground(new java.awt.Color(68, 136, 255));
        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinHoaDon.setPreferredSize(new java.awt.Dimension(350, 700));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblMaHoaDon.setText("Mã Hóa Đơn");

        lblNhanVienLapDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNhanVienLapDon.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVienLapDon.setText("Nhân Viên Lập Đơn");

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Khách Hàng");

        lblThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblThoiGianTao.setForeground(new java.awt.Color(255, 255, 255));
        lblThoiGianTao.setText("Thời Gian Tạo");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTien.setText("Tổng Tiền");

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNhanVienLapDon.setEditable(false);
        txtNhanVienLapDon.setBackground(new java.awt.Color(204, 204, 204));
        txtNhanVienLapDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtKhachHang.setEditable(false);
        txtKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtThoiGianTao.setEditable(false);
        txtThoiGianTao.setBackground(new java.awt.Color(204, 204, 204));
        txtThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTongTien.setEditable(false);
        txtTongTien.setBackground(new java.awt.Color(204, 204, 204));
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnXemFileHoaDon.setBackground(new java.awt.Color(0, 255, 255));
        btnXemFileHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemFileHoaDon.setText("Xem File Hóa Đơn");
        btnXemFileHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemFileHoaDonActionPerformed(evt);
            }
        });

        btnQuayLai.setBackground(new java.awt.Color(255, 0, 51));
        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinHoaDonLayout = new javax.swing.GroupLayout(pnlThongTinHoaDon);
        pnlThongTinHoaDon.setLayout(pnlThongTinHoaDonLayout);
        pnlThongTinHoaDonLayout.setHorizontalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXemFileHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMaHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNhanVienLapDon)
                                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtThoiGianTao)
                            .addComponent(txtMaHoaDon)
                            .addComponent(txtKhachHang)
                            .addComponent(txtNhanVienLapDon))))
                .addContainerGap())
        );
        pnlThongTinHoaDonLayout.setVerticalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHoaDon)
                    .addComponent(lblMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNhanVienLapDon)
                    .addComponent(lblNhanVienLapDon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKhachHang)
                    .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblThoiGianTao, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtThoiGianTao))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtTongTien))
                .addGap(286, 286, 286)
                .addComponent(btnXemFileHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        add(pnlThongTinHoaDon, java.awt.BorderLayout.WEST);

        scrDanhSachQuanAoTrongHoaDon.setBackground(new java.awt.Color(68, 136, 255));
        scrDanhSachQuanAoTrongHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Quần Áo Trong Hóa Đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        scrDanhSachQuanAoTrongHoaDon.setMinimumSize(new java.awt.Dimension(816, 700));
        scrDanhSachQuanAoTrongHoaDon.setPreferredSize(new java.awt.Dimension(816, 700));

        tblDanhSachQuanAoTrongHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quần áo", "Số lượng", "Đơn giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachQuanAoTrongHoaDon.setRowHeight(40);
        scrDanhSachQuanAoTrongHoaDon.setViewportView(tblDanhSachQuanAoTrongHoaDon);
        if (tblDanhSachQuanAoTrongHoaDon.getColumnModel().getColumnCount() > 0) {
            tblDanhSachQuanAoTrongHoaDon.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        add(scrDanhSachQuanAoTrongHoaDon, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemFileHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemFileHoaDonActionPerformed
        // TODO add your handling code here:
        xemFileHoaDon();
    }//GEN-LAST:event_btnXemFileHoaDonActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        quayLai();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnXemFileHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVienLapDon;
    private javax.swing.JLabel lblThoiGianTao;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JScrollPane scrDanhSachQuanAoTrongHoaDon;
    private javax.swing.JTable tblDanhSachQuanAoTrongHoaDon;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNhanVienLapDon;
    private javax.swing.JTextField txtThoiGianTao;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
