package gui_new;

public class PnlChiTietHoaDon extends javax.swing.JPanel {
    public PnlChiTietHoaDon() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrDanhSachQuanAoTrongHoaDon = new javax.swing.JScrollPane();
        tblDanhSachQuanAoTrongHoaDon = new javax.swing.JTable();
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

        setMinimumSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        scrDanhSachQuanAoTrongHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Quần Áo Trong Hóa Đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        scrDanhSachQuanAoTrongHoaDon.setMinimumSize(new java.awt.Dimension(816, 700));
        scrDanhSachQuanAoTrongHoaDon.setPreferredSize(new java.awt.Dimension(816, 700));

        tblDanhSachQuanAoTrongHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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
        scrDanhSachQuanAoTrongHoaDon.setViewportView(tblDanhSachQuanAoTrongHoaDon);

        add(scrDanhSachQuanAoTrongHoaDon, java.awt.BorderLayout.CENTER);

        pnlThongTinHoaDon.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinHoaDon.setPreferredSize(new java.awt.Dimension(350, 700));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblMaHoaDon.setText("Mã hóa đơn");

        lblNhanVienLapDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNhanVienLapDon.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVienLapDon.setText("Nhân viên lập đơn");

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Khách hàng");

        lblThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblThoiGianTao.setForeground(new java.awt.Color(255, 255, 255));
        lblThoiGianTao.setText("Thời gian tạo");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTien.setText("Tổng tiền");

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNhanVienLapDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnXemFileHoaDon.setBackground(new java.awt.Color(0, 255, 255));
        btnXemFileHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemFileHoaDon.setText("Xem file hóa đơn");
        btnXemFileHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemFileHoaDonActionPerformed(evt);
            }
        });

        btnQuayLai.setBackground(new java.awt.Color(255, 0, 51));
        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay lại");

        javax.swing.GroupLayout pnlThongTinHoaDonLayout = new javax.swing.GroupLayout(pnlThongTinHoaDon);
        pnlThongTinHoaDon.setLayout(pnlThongTinHoaDonLayout);
        pnlThongTinHoaDonLayout.setHorizontalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTien))
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon))
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(lblNhanVienLapDon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNhanVienLapDon))
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(lblThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiGianTao))
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKhachHang))
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(btnXemFileHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(343, 343, 343)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemFileHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        add(pnlThongTinHoaDon, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemFileHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemFileHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXemFileHoaDonActionPerformed

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
