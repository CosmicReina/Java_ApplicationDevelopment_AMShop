package gui_new;

import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.NhanVien;
import javax.swing.JOptionPane;

public class FrmLogin extends javax.swing.JFrame {

    private static FrmLogin instance = new FrmLogin();

    public static FrmLogin getInstance() {
        return instance;
    }
    
    public static FrmLogin newInstance() {
        instance = new FrmLogin();
        return instance;
    }

    public FrmLogin() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        setLocationRelativeTo(null);
    }
    
    private void kiemTraDangNhap(){
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = new String(txtMatKhau.getPassword());
        if(tenDangNhap.isEmpty() || matKhau.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin đăng nhập.");
            return;
        }
        boolean kiemTraDangNhap = DAO_TaiKhoan.kiemTraDangNhap(tenDangNhap, matKhau);
        if(kiemTraDangNhap){
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công.");
            NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(matKhau);
            DAO_NhanVien.setNhanVienHienTai(nhanVien);
            FrmMain frmMain = FrmMain.getInstance();
            frmMain.setVisible(true);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Đăng nhập không thành công. Vui lòng kiểm tra lại thông tin tài khoản.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDangNhap = new javax.swing.JPanel();
        lblDangNhap = new javax.swing.JLabel();
        lblTenDangNhap = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        btnDangNhap = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlDangNhap.setBackground(new java.awt.Color(102, 102, 102));

        lblDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangNhap.setText("Đăng Nhập");

        lblTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenDangNhap.setText("Tên Đăng Nhập");

        lblMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMatKhau.setText("Mật Khẩu");

        txtTenDangNhap.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnDangNhap.setBackground(new java.awt.Color(0, 0, 255));
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 0, 51));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        txtMatKhau.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout pnlDangNhapLayout = new javax.swing.GroupLayout(pnlDangNhap);
        pnlDangNhap.setLayout(pnlDangNhapLayout);
        pnlDangNhapLayout.setHorizontalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDangNhapLayout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(lblTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDangNhapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(txtMatKhau))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDangNhapLayout.setVerticalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(lblDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(lblTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        getContentPane().add(pnlDangNhap, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        kiemTraDangNhap();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JPanel pnlDangNhap;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables

}
