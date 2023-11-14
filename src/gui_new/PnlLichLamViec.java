package gui_new;

import gui.*;
import dao.DAO_ChiTietPhanCong;
import dao.DAO_LichLamViec;
import dao.DAO_NhanVien;
import entity.ChiTietPhanCong;
import entity.LichLamViec;
import entity.NhanVien;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PnlLichLamViec extends javax.swing.JPanel {
    
    private static PnlLichLamViec instance = new PnlLichLamViec();

    public static PnlLichLamViec getInstance() {
        return instance;
    }
    
    public static PnlLichLamViec newInstance() {
        instance = new PnlLichLamViec();
        return instance;
    }
    
    public PnlLichLamViec() {
        initComponents();
        showTableListLichLamViec();
        showTableListNhanVien();
    }

private void showTableListLichLamViec(){
    ArrayList<LichLamViec> list = DAO_LichLamViec.getAllLichLamViec();
    DefaultTableModel model = (DefaultTableModel) tblDanhSachLichLamViec.getModel();
    for(LichLamViec thisLichLamViec : list){
        model.addRow(new Object[]{
            thisLichLamViec.getMaLichLamViec(),
            thisLichLamViec.getCaLamViec().getTenCaLamViec(),
            thisLichLamViec.getNgayLamViec()
        });
    }
}    

private void showTableListNhanVien(){
    ArrayList<NhanVien> list = DAO_NhanVien.getAllNhanVien();
    DefaultTableModel model = (DefaultTableModel) tblDanhSachNhanVien.getModel();
    for(NhanVien thisNhanVien : list){
        model.addRow(new Object[]{
            thisNhanVien.getMaNhanVien(),
            thisNhanVien.getHoTen()
        });
    }
}    

private void showTableListNhanVienTrongCa(String maLichLamViec){
    ArrayList<ChiTietPhanCong> list = DAO_ChiTietPhanCong.getAllChiTietPhanCongTheoMaLichLamViec(maLichLamViec);
    DefaultTableModel model = (DefaultTableModel) tblDanhSachNhanVienTrongCa.getModel();
    for(ChiTietPhanCong thisChiTietPhanCong : list){
        model.addRow(new Object[]{
            thisChiTietPhanCong.getNhanVien().getMaNhanVien(),
            thisChiTietPhanCong.getNhanVien().getHoTen(),
            thisChiTietPhanCong.getThoiGianVaoCa(),
            thisChiTietPhanCong.getThoiGianRaCa()
        });
    }
}    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLichLamViec = new javax.swing.JPanel();
        pnlDanhSachLichVaNhanVien = new javax.swing.JPanel();
        pnlDanhSachLichLamViec = new javax.swing.JPanel();
        scrDanhSachLichLamViec = new javax.swing.JScrollPane();
        tblDanhSachLichLamViec = new javax.swing.JTable();
        pnlDanhSachNhanVienTrongCa = new javax.swing.JPanel();
        scrDanhSachNhanVienTrongCa = new javax.swing.JScrollPane();
        tblDanhSachNhanVienTrongCa = new javax.swing.JTable();
        pnlCapNhatLichLamViec = new javax.swing.JPanel();
        pnlCapNhatCaLam = new javax.swing.JPanel();
        lblNgayLamViec = new javax.swing.JLabel();
        lblCaLamViec = new javax.swing.JLabel();
        txtNgayLamViec = new javax.swing.JTextField();
        cmbCaLamViec = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        pnlDanhSachNhanVien = new javax.swing.JPanel();
        scrDanhSachNhanVien = new javax.swing.JScrollPane();
        tblDanhSachNhanVien = new javax.swing.JTable();
        pnlChamCong = new javax.swing.JPanel();
        pnlChamCongCaLam = new javax.swing.JPanel();
        btnChamCongVao = new javax.swing.JButton();
        btnChamCongRa = new javax.swing.JButton();
        btnXoaNhanVienKhoiCaLam = new javax.swing.JButton();
        pnlTimKiemNhanVien = new javax.swing.JPanel();
        txtTimKiemNhanVien = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnlLichLamViec.setPreferredSize(new java.awt.Dimension(900, 700));
        pnlLichLamViec.setLayout(new java.awt.BorderLayout());

        pnlDanhSachLichVaNhanVien.setPreferredSize(new java.awt.Dimension(900, 350));
        pnlDanhSachLichVaNhanVien.setLayout(new java.awt.BorderLayout());

        pnlDanhSachLichLamViec.setBackground(new java.awt.Color(79, 137, 255));
        pnlDanhSachLichLamViec.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Lịch Làm Việc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDanhSachLichLamViec.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlDanhSachLichLamViec.setLayout(new java.awt.BorderLayout());

        tblDanhSachLichLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã lịch làm việc", "Tên ca làm", "Ngày làm việc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDanhSachLichLamViec.setViewportView(tblDanhSachLichLamViec);

        pnlDanhSachLichLamViec.add(scrDanhSachLichLamViec, java.awt.BorderLayout.CENTER);

        pnlDanhSachLichVaNhanVien.add(pnlDanhSachLichLamViec, java.awt.BorderLayout.WEST);

        pnlDanhSachNhanVienTrongCa.setBackground(new java.awt.Color(79, 137, 255));
        pnlDanhSachNhanVienTrongCa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Nhân Viên Trong Ca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDanhSachNhanVienTrongCa.setPreferredSize(new java.awt.Dimension(400, 350));
        pnlDanhSachNhanVienTrongCa.setLayout(new java.awt.BorderLayout());

        tblDanhSachNhanVienTrongCa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Thời gian vào ca", "Thời gian ra ca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDanhSachNhanVienTrongCa.setViewportView(tblDanhSachNhanVienTrongCa);

        pnlDanhSachNhanVienTrongCa.add(scrDanhSachNhanVienTrongCa, java.awt.BorderLayout.CENTER);

        pnlDanhSachLichVaNhanVien.add(pnlDanhSachNhanVienTrongCa, java.awt.BorderLayout.CENTER);

        pnlLichLamViec.add(pnlDanhSachLichVaNhanVien, java.awt.BorderLayout.NORTH);

        pnlCapNhatLichLamViec.setPreferredSize(new java.awt.Dimension(900, 350));
        pnlCapNhatLichLamViec.setLayout(new java.awt.BorderLayout());

        pnlCapNhatCaLam.setBackground(new java.awt.Color(79, 137, 255));
        pnlCapNhatCaLam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật ca làm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlCapNhatCaLam.setPreferredSize(new java.awt.Dimension(500, 350));

        lblNgayLamViec.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayLamViec.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayLamViec.setText("Ngày Làm Việc");

        lblCaLamViec.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCaLamViec.setForeground(new java.awt.Color(255, 255, 255));
        lblCaLamViec.setText("Ca Làm Việc");

        btnThem.setBackground(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCapNhatCaLamLayout = new javax.swing.GroupLayout(pnlCapNhatCaLam);
        pnlCapNhatCaLam.setLayout(pnlCapNhatCaLamLayout);
        pnlCapNhatCaLamLayout.setHorizontalGroup(
            pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatCaLamLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNgayLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblCaLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNgayLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(cmbCaLamViec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        pnlCapNhatCaLamLayout.setVerticalGroup(
            pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatCaLamLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayLamViec)
                    .addComponent(lblNgayLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCaLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cmbCaLamViec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        pnlCapNhatLichLamViec.add(pnlCapNhatCaLam, java.awt.BorderLayout.WEST);

        pnlDanhSachNhanVien.setBackground(new java.awt.Color(79, 137, 255));
        pnlDanhSachNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Nhân Viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDanhSachNhanVien.setPreferredSize(new java.awt.Dimension(400, 350));
        pnlDanhSachNhanVien.setLayout(new java.awt.BorderLayout());

        tblDanhSachNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDanhSachNhanVien.setViewportView(tblDanhSachNhanVien);

        pnlDanhSachNhanVien.add(scrDanhSachNhanVien, java.awt.BorderLayout.CENTER);

        pnlCapNhatLichLamViec.add(pnlDanhSachNhanVien, java.awt.BorderLayout.CENTER);

        pnlLichLamViec.add(pnlCapNhatLichLamViec, java.awt.BorderLayout.CENTER);

        add(pnlLichLamViec, java.awt.BorderLayout.WEST);

        pnlChamCong.setLayout(new java.awt.BorderLayout());

        pnlChamCongCaLam.setBackground(new java.awt.Color(79, 137, 255));
        pnlChamCongCaLam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chấm công ca làm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlChamCongCaLam.setPreferredSize(new java.awt.Dimension(266, 350));

        btnChamCongVao.setBackground(new java.awt.Color(0, 255, 255));
        btnChamCongVao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChamCongVao.setText("Chấm công vào");
        btnChamCongVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongVaoActionPerformed(evt);
            }
        });

        btnChamCongRa.setBackground(new java.awt.Color(0, 255, 255));
        btnChamCongRa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChamCongRa.setText("Chấm công ra");

        btnXoaNhanVienKhoiCaLam.setBackground(new java.awt.Color(0, 255, 255));
        btnXoaNhanVienKhoiCaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoaNhanVienKhoiCaLam.setText("Xóa");

        javax.swing.GroupLayout pnlChamCongCaLamLayout = new javax.swing.GroupLayout(pnlChamCongCaLam);
        pnlChamCongCaLam.setLayout(pnlChamCongCaLamLayout);
        pnlChamCongCaLamLayout.setHorizontalGroup(
            pnlChamCongCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChamCongCaLamLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(pnlChamCongCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnChamCongRa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChamCongVao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaNhanVienKhoiCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(61, 61, 61))
        );
        pnlChamCongCaLamLayout.setVerticalGroup(
            pnlChamCongCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamCongCaLamLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnChamCongVao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnChamCongRa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnXoaNhanVienKhoiCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pnlChamCong.add(pnlChamCongCaLam, java.awt.BorderLayout.NORTH);

        pnlTimKiemNhanVien.setBackground(new java.awt.Color(79, 137, 255));
        pnlTimKiemNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiemNhanVien.setPreferredSize(new java.awt.Dimension(266, 350));

        btnTimKiem.setBackground(new java.awt.Color(0, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");

        javax.swing.GroupLayout pnlTimKiemNhanVienLayout = new javax.swing.GroupLayout(pnlTimKiemNhanVien);
        pnlTimKiemNhanVien.setLayout(pnlTimKiemNhanVienLayout);
        pnlTimKiemNhanVienLayout.setHorizontalGroup(
            pnlTimKiemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemNhanVienLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(pnlTimKiemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiemNhanVien)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(62, 62, 62))
        );
        pnlTimKiemNhanVienLayout.setVerticalGroup(
            pnlTimKiemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemNhanVienLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtTimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChamCong.add(pnlTimKiemNhanVien, java.awt.BorderLayout.CENTER);

        add(pnlChamCong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnChamCongVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongVaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChamCongVaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChamCongRa;
    private javax.swing.JButton btnChamCongVao;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaNhanVienKhoiCaLam;
    private javax.swing.JComboBox<String> cmbCaLamViec;
    private javax.swing.JLabel lblCaLamViec;
    private javax.swing.JLabel lblNgayLamViec;
    private javax.swing.JPanel pnlCapNhatCaLam;
    private javax.swing.JPanel pnlCapNhatLichLamViec;
    private javax.swing.JPanel pnlChamCong;
    private javax.swing.JPanel pnlChamCongCaLam;
    private javax.swing.JPanel pnlDanhSachLichLamViec;
    private javax.swing.JPanel pnlDanhSachLichVaNhanVien;
    private javax.swing.JPanel pnlDanhSachNhanVien;
    private javax.swing.JPanel pnlDanhSachNhanVienTrongCa;
    private javax.swing.JPanel pnlLichLamViec;
    private javax.swing.JPanel pnlTimKiemNhanVien;
    private javax.swing.JScrollPane scrDanhSachLichLamViec;
    private javax.swing.JScrollPane scrDanhSachNhanVien;
    private javax.swing.JScrollPane scrDanhSachNhanVienTrongCa;
    private javax.swing.JTable tblDanhSachLichLamViec;
    private javax.swing.JTable tblDanhSachNhanVien;
    private javax.swing.JTable tblDanhSachNhanVienTrongCa;
    private javax.swing.JTextField txtNgayLamViec;
    private javax.swing.JTextField txtTimKiemNhanVien;
    // End of variables declaration//GEN-END:variables

}
