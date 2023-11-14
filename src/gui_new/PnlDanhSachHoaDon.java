package gui_new;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import data.FormatDouble;
import data.UtilityLocalDateTime;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.util.ArrayList;
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
        showTableListHoaDon();
    }

private void showTableListHoaDon(){
    ArrayList<HoaDon> list = DAO_HoaDon.getAllHoaDon();
    DefaultTableModel model = (DefaultTableModel) tblDanhSachHoaDon.getModel();
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
        btnXemChiTiet = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1166, 700));
        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        scrDanhSachHoaDon.setPreferredSize(new java.awt.Dimension(900, 700));

        tblDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Nhân Viên", "Khách Hàng", "Số điện thoại", "Ngày Lập Hóa Đơn", "Tổng tiền"
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

        btnTimKiem.setBackground(new java.awt.Color(0, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");

        btnXemChiTiet.setBackground(new java.awt.Color(0, 255, 255));
        btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemChiTiet.setText("Xem Chi Tiết Hóa Đơn");

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoDienThoai))
                    .addComponent(btnXemChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHoaDon)
                    .addComponent(lblMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoai))
                .addGap(87, 87, 87)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
