package gui_new;

import gui.*;

public class PnlThongKeKhachHang extends javax.swing.JPanel {
    public PnlThongKeKhachHang() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrDanhSachKhachHang = new javax.swing.JScrollPane();
        tblDanhSachKhachHang = new javax.swing.JTable();
        pnlThongKe = new javax.swing.JPanel();
        lblNgayBatDau = new javax.swing.JLabel();
        lblNgayKetThuc = new javax.swing.JLabel();
        lblTongSoDoanhThu = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        txtNgayKetThuc = new javax.swing.JTextField();
        txtTongSoDoanhThu = new javax.swing.JTextField();
        btnThongKe = new javax.swing.JButton();
        btnInBaoCao = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        scrDanhSachKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        scrDanhSachKhachHang.setPreferredSize(new java.awt.Dimension(800, 800));

        tblDanhSachKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Khách hàng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDanhSachKhachHang.setViewportView(tblDanhSachKhachHang);

        add(scrDanhSachKhachHang, java.awt.BorderLayout.WEST);

        pnlThongKe.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongKe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        lblNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayBatDau.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayBatDau.setText("Ngày bắt đầu");

        lblNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayKetThuc.setText("Ngày kết thúc");

        lblTongSoDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongSoDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSoDoanhThu.setText("Tổng số doanh thu");

        txtNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTongSoDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongSoDoanhThu.setEnabled(false);

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
        btnInBaoCao.setText("In báo cáo");
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThongKeLayout.createSequentialGroup()
                        .addComponent(lblTongSoDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongSoDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongKeLayout.createSequentialGroup()
                        .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(lblNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txtNgayKetThuc)))
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongSoDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtTongSoDoanhThu))
                .addGap(96, 96, 96)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(317, Short.MAX_VALUE))
        );

        add(pnlThongKe, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInBaoCao;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblTongSoDoanhThu;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JScrollPane scrDanhSachKhachHang;
    private javax.swing.JTable tblDanhSachKhachHang;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtTongSoDoanhThu;
    // End of variables declaration//GEN-END:variables

}
