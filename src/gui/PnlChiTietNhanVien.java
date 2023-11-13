package gui;

public class PnlChiTietNhanVien extends javax.swing.JPanel {
    public PnlChiTietNhanVien() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrDanhSachHoaDonNhanVienTao = new javax.swing.JScrollPane();
        tblDanhSachDoaDonNhanVienTao = new javax.swing.JTable();
        pnlThongTinNhanVien = new javax.swing.JPanel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgayBatDauLam = new javax.swing.JLabel();
        lblNgayKetThucLam = new javax.swing.JLabel();
        lblLuong = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();
        txtNgayBatDauLam = new javax.swing.JTextField();
        txtNgayKetThucLam = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        btnXemChiTietHoaDon = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        scrDanhSachHoaDonNhanVienTao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hóa Đơn Nhân Viên Đã Lập", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        scrDanhSachHoaDonNhanVienTao.setMinimumSize(new java.awt.Dimension(816, 700));
        scrDanhSachHoaDonNhanVienTao.setPreferredSize(new java.awt.Dimension(816, 700));

        tblDanhSachDoaDonNhanVienTao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Ngày lập đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDanhSachHoaDonNhanVienTao.setViewportView(tblDanhSachDoaDonNhanVienTao);

        add(scrDanhSachHoaDonNhanVienTao, java.awt.BorderLayout.CENTER);

        pnlThongTinNhanVien.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongTinNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết nhân viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinNhanVien.setPreferredSize(new java.awt.Dimension(350, 700));

        lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblMaNhanVien.setText("Mã nhân viên");

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(255, 255, 255));
        lblHoTen.setText("Họ tên");

        lblSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setText("Số điện thoại");

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setText("Địa chỉ");

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(255, 255, 255));
        lblChucVu.setText("Chức vụ");

        lblNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        lblNgaySinh.setText("Ngày Sinh");

        lblCCCD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCCCD.setForeground(new java.awt.Color(255, 255, 255));
        lblCCCD.setText("CCCD");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setText("Giới tính");

        lblNgayBatDauLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayBatDauLam.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayBatDauLam.setText("Ngày bắt đầu làm");

        lblNgayKetThucLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayKetThucLam.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayKetThucLam.setText("Ngày kết thúc làm");

        lblLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblLuong.setText("Lương");

        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtChucVu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCCCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayBatDauLam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayKetThucLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnXemChiTietHoaDon.setBackground(new java.awt.Color(0, 255, 255));
        btnXemChiTietHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemChiTietHoaDon.setText("Xem chi tiết hóa đơn");
        btnXemChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietHoaDonActionPerformed(evt);
            }
        });

        btnQuayLai.setBackground(new java.awt.Color(255, 0, 51));
        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay lại");

        javax.swing.GroupLayout pnlThongTinNhanVienLayout = new javax.swing.GroupLayout(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(pnlThongTinNhanVienLayout);
        pnlThongTinNhanVienLayout.setHorizontalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtChucVu))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaNhanVien))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiaChi))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoDienThoai))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLuong))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNgayBatDauLam, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNgayKetThucLam, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayKetThucLam)
                                    .addComponent(txtNgaySinh)
                                    .addComponent(txtCCCD)
                                    .addComponent(txtNgayBatDauLam)
                                    .addComponent(txtGioiTinh))))
                        .addContainerGap())
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addComponent(btnXemChiTietHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))))
        );
        pnlThongTinNhanVienLayout.setVerticalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNhanVien)
                    .addComponent(lblMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoTen)
                    .addComponent(lblHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoDienThoai)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtDiaChi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtChucVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNgayBatDauLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNgayKetThucLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addComponent(txtNgaySinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCCCD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayBatDauLam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayKetThucLam)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtLuong))
                .addGap(72, 72, 72)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnlThongTinNhanVien, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXemChiTietHoaDonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnXemChiTietHoaDon;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgayBatDauLam;
    private javax.swing.JLabel lblNgayKetThucLam;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JPanel pnlThongTinNhanVien;
    private javax.swing.JScrollPane scrDanhSachHoaDonNhanVienTao;
    private javax.swing.JTable tblDanhSachDoaDonNhanVienTao;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgayBatDauLam;
    private javax.swing.JTextField txtNgayKetThucLam;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

}
