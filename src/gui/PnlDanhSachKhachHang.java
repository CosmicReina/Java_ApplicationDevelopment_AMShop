package gui;

import dao.DAO_KhachHang;
import entity.KhachHang;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachKhachHang extends javax.swing.JPanel {
    public PnlDanhSachKhachHang() {
        initComponents();
        showTableListKhachHang();
    }

private void showTableListKhachHang(){
    ArrayList<KhachHang> list = DAO_KhachHang.getAllKhachHang();
    DefaultTableModel model = (DefaultTableModel) tblDanhSachKH.getModel();
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        srcDanhSachKH = new javax.swing.JScrollPane();
        tblDanhSachKH = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        pnlTimKiemKH = new javax.swing.JPanel();
        txtTimKiemSoDienThoai = new javax.swing.JTextField();
        txtTimKiemHoTen = new javax.swing.JTextField();
        btnTimKiemKH = new javax.swing.JButton();
        pnlThongTinKH = new javax.swing.JPanel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        srcDanhSachKH.setPreferredSize(new java.awt.Dimension(500, 700));

        tblDanhSachKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Số điện thoại", "Địa chỉ", "Nhóm khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachKH.setPreferredSize(new java.awt.Dimension(525, 700));
        srcDanhSachKH.setViewportView(tblDanhSachKH);

        add(srcDanhSachKH, java.awt.BorderLayout.CENTER);

        pnlTimKiem.setPreferredSize(new java.awt.Dimension(431, 700));
        pnlTimKiem.setLayout(new java.awt.BorderLayout());

        pnlTimKiemKH.setBackground(new java.awt.Color(79, 137, 255));
        pnlTimKiemKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm Khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        txtTimKiemSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTimKiemHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimKiemKH.setBackground(new java.awt.Color(51, 255, 255));
        btnTimKiemKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiemKH.setText("Tìm kiếm");

        javax.swing.GroupLayout pnlTimKiemKHLayout = new javax.swing.GroupLayout(pnlTimKiemKH);
        pnlTimKiemKH.setLayout(pnlTimKiemKHLayout);
        pnlTimKiemKHLayout.setHorizontalGroup(
            pnlTimKiemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemKHLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlTimKiemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiemKH, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(txtTimKiemSoDienThoai))
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlTimKiemKHLayout.setVerticalGroup(
            pnlTimKiemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemKHLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlTimKiemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        pnlTimKiem.add(pnlTimKiemKH, java.awt.BorderLayout.CENTER);

        pnlThongTinKH.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongTinKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinKH.setPreferredSize(new java.awt.Dimension(431, 490));

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemKH.setBackground(new java.awt.Color(51, 255, 255));
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemKH.setText("Thêm khách hàng");

        btnSuaKH.setBackground(new java.awt.Color(0, 255, 255));
        btnSuaKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaKH.setText("Sửa khách hàng");

        javax.swing.GroupLayout pnlThongTinKHLayout = new javax.swing.GroupLayout(pnlThongTinKH);
        pnlThongTinKH.setLayout(pnlThongTinKHLayout);
        pnlThongTinKHLayout.setHorizontalGroup(
            pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChi)
                    .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongTinKHLayout.setVerticalGroup(
            pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(txtHoTen))
                .addGap(18, 18, 18)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnSuaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(523, 523, 523))
        );

        pnlTimKiem.add(pnlThongTinKH, java.awt.BorderLayout.SOUTH);

        add(pnlTimKiem, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnTimKiemKH;
    private javax.swing.JPanel pnlThongTinKH;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JPanel pnlTimKiemKH;
    private javax.swing.JScrollPane srcDanhSachKH;
    private javax.swing.JTable tblDanhSachKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTimKiemHoTen;
    private javax.swing.JTextField txtTimKiemSoDienThoai;
    // End of variables declaration//GEN-END:variables

}
