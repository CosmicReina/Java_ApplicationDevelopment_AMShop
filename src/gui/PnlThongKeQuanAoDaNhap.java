package gui;

public class PnlThongKeQuanAoDaNhap extends javax.swing.JPanel {
    public PnlThongKeQuanAoDaNhap() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrDanhSachQuanAoDaNhapHang = new javax.swing.JScrollPane();
        tblDanhSachQuanAoDaNhapHang = new javax.swing.JTable();
        pnlDanhSachQuanAoDaNhapHang = new javax.swing.JPanel();
        lblNgayBatDau = new javax.swing.JLabel();
        lblNgayKetThuc = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        txtNgayKetThuc = new javax.swing.JTextField();
        btnThongKe = new javax.swing.JButton();
        btnInBaoCao = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        scrDanhSachQuanAoDaNhapHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Danh Sách Quần Áo Đã Nhập Hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        scrDanhSachQuanAoDaNhapHang.setPreferredSize(new java.awt.Dimension(800, 800));

        tblDanhSachQuanAoDaNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã quần áo", "Tên quần áo", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDanhSachQuanAoDaNhapHang.setViewportView(tblDanhSachQuanAoDaNhapHang);

        add(scrDanhSachQuanAoDaNhapHang, java.awt.BorderLayout.WEST);

        pnlDanhSachQuanAoDaNhapHang.setBackground(new java.awt.Color(79, 137, 255));
        pnlDanhSachQuanAoDaNhapHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        lblNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayBatDau.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayBatDau.setText("Ngày bắt đầu");

        lblNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayKetThuc.setText("Ngày kết thúc");

        txtNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThongKe.setBackground(new java.awt.Color(0, 255, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnInBaoCao.setBackground(new java.awt.Color(0, 255, 255));
        btnInBaoCao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnInBaoCao.setText("In Báo Cáo");

        javax.swing.GroupLayout pnlDanhSachQuanAoDaNhapHangLayout = new javax.swing.GroupLayout(pnlDanhSachQuanAoDaNhapHang);
        pnlDanhSachQuanAoDaNhapHang.setLayout(pnlDanhSachQuanAoDaNhapHangLayout);
        pnlDanhSachQuanAoDaNhapHangLayout.setHorizontalGroup(
            pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createSequentialGroup()
                            .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(lblNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(txtNgayKetThuc)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pnlDanhSachQuanAoDaNhapHangLayout.setVerticalGroup(
            pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDanhSachQuanAoDaNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(320, Short.MAX_VALUE))
        );

        add(pnlDanhSachQuanAoDaNhapHang, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThongKeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInBaoCao;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JPanel pnlDanhSachQuanAoDaNhapHang;
    private javax.swing.JScrollPane scrDanhSachQuanAoDaNhapHang;
    private javax.swing.JTable tblDanhSachQuanAoDaNhapHang;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    // End of variables declaration//GEN-END:variables

}
