package gui_old;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import dao.DAO_NhanVien;
import data.FormatDate;
import data.FormatDouble;
import data.UtilityLocalDate;
import data.UtilityLocalDateTime;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class PnlChiTietNhanVien extends javax.swing.JPanel {
    
    private static PnlChiTietNhanVien instance = new PnlChiTietNhanVien();
    
    private JPanel pnlBefore = null;

    public JPanel getPnlBefore() {
        return pnlBefore;
    }

    public void setPnlBefore(JPanel pnlBefore) {
        this.pnlBefore = pnlBefore;
    }
    
    public static PnlChiTietNhanVien getInstance() {
        return instance;
    }
    
    public static PnlChiTietNhanVien newInstance() {
        instance = new PnlChiTietNhanVien();
        return instance;
    }
    
    public PnlChiTietNhanVien() {
        initComponents();
    }
    
    public void showChiTietNhanVien(String maNhanVien){
        NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
        txtMaNhanVien.setText(nhanVien.getMaNhanVien());
        txtHoTen.setText(nhanVien.getHoTen());
        txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        txtDiaChi.setText(nhanVien.getDiaChi());
        txtChucVu.setText(nhanVien.getChucVu());
        txtNgaySinh.setText(FormatDate.fromLocalDate(nhanVien.getNgaySinh()));
        txtCCCD.setText(nhanVien.getCanCuocCongDan());
        txtGioiTinh.setText(nhanVien.getGioiTinh());
        txtNgayBatDauLam.setText(FormatDate.fromLocalDate(nhanVien.getNgayBatDauLam()));
        if(nhanVien.getNgayKetThucLam() != null)
            txtNgayKetThucLam.setText(FormatDate.fromLocalDate(nhanVien.getNgayKetThucLam()));
        else
            txtNgayKetThucLam.setText("");
        txtLuong.setText(FormatDouble.toMoney(nhanVien.getLuong()));
        
        ArrayList<HoaDon> listHD = DAO_HoaDon.getAllHoaDon();
        ArrayList<HoaDon> listRemove = new ArrayList<>();
        for(int i = 0; i < listHD.size(); i++){
            HoaDon thisHoaDon = listHD.get(i);
            if(!thisHoaDon.getNhanVien().getMaNhanVien().equals(nhanVien.getMaNhanVien()))
                listRemove.add(thisHoaDon);
        }
        listHD.removeAll(listRemove);
        DefaultTableModel model = (DefaultTableModel) tblDanhSachDoaDonNhanVienTao.getModel();
        for(HoaDon thisHoaDon : listHD){
            ArrayList<ChiTietHoaDon> listCTHD = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(thisHoaDon.getMaHoaDon());
            double tongTien = 0;
            for(ChiTietHoaDon thisChiTietHoaDon : listCTHD)
                tongTien += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            model.addRow(new Object[]{
                thisHoaDon.getMaHoaDon(),
                thisHoaDon.getKhachHang().getHoTen(),
                UtilityLocalDateTime.toFormattedLocalDateTime(thisHoaDon.getThoiGianTao()),
                FormatDouble.toMoney(tongTien)
            });
        }
    }
    
    private void xemChiTietHoaDon(){
        int i = tblDanhSachDoaDonNhanVienTao.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Hóa Đơn");
            return;
        }
        String maHoaDon = tblDanhSachDoaDonNhanVienTao.getValueAt(i, 0).toString();
        
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

        setBackground(new java.awt.Color(68, 136, 255));
        setMinimumSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        scrDanhSachHoaDonNhanVienTao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hóa Đơn Nhân Viên Đã Lập", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        scrDanhSachHoaDonNhanVienTao.setMinimumSize(new java.awt.Dimension(816, 700));
        scrDanhSachHoaDonNhanVienTao.setPreferredSize(new java.awt.Dimension(816, 700));

        tblDanhSachDoaDonNhanVienTao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        tblDanhSachDoaDonNhanVienTao.setRowHeight(40);
        scrDanhSachHoaDonNhanVienTao.setViewportView(tblDanhSachDoaDonNhanVienTao);

        add(scrDanhSachHoaDonNhanVienTao, java.awt.BorderLayout.CENTER);

        pnlThongTinNhanVien.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongTinNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Nhân Viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinNhanVien.setPreferredSize(new java.awt.Dimension(350, 700));

        lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblMaNhanVien.setText("Mã Nhân Viên");

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(255, 255, 255));
        lblHoTen.setText("Họ Tên");

        lblSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setText("Số Điện Thoại");

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setText("Địa Chỉ");

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(255, 255, 255));
        lblChucVu.setText("Chức Vụ");

        lblNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        lblNgaySinh.setText("Ngày Sinh");

        lblCCCD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCCCD.setForeground(new java.awt.Color(255, 255, 255));
        lblCCCD.setText("CCCD");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setText("Giới Tính");

        lblNgayBatDauLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayBatDauLam.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayBatDauLam.setText("Ngày Bắt Đầu Làm");

        lblNgayKetThucLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayKetThucLam.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayKetThucLam.setText("Ngày Kết Thúc Làm");

        lblLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblLuong.setText("Lương");

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHoTen.setEditable(false);
        txtHoTen.setBackground(new java.awt.Color(204, 204, 204));
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoDienThoai.setEditable(false);
        txtSoDienThoai.setBackground(new java.awt.Color(204, 204, 204));
        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDiaChi.setEditable(false);
        txtDiaChi.setBackground(new java.awt.Color(204, 204, 204));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtChucVu.setEditable(false);
        txtChucVu.setBackground(new java.awt.Color(204, 204, 204));
        txtChucVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCCCD.setEditable(false);
        txtCCCD.setBackground(new java.awt.Color(204, 204, 204));
        txtCCCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtGioiTinh.setEditable(false);
        txtGioiTinh.setBackground(new java.awt.Color(204, 204, 204));
        txtGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayBatDauLam.setEditable(false);
        txtNgayBatDauLam.setBackground(new java.awt.Color(204, 204, 204));
        txtNgayBatDauLam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNgayKetThucLam.setEditable(false);
        txtNgayKetThucLam.setBackground(new java.awt.Color(204, 204, 204));
        txtNgayKetThucLam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtLuong.setEditable(false);
        txtLuong.setBackground(new java.awt.Color(204, 204, 204));
        txtLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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
        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinNhanVienLayout = new javax.swing.GroupLayout(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(pnlThongTinNhanVienLayout);
        pnlThongTinNhanVienLayout.setHorizontalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXemChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblNgayKetThucLam, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(lblNgayBatDauLam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCCCD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayKetThucLam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtNgayBatDauLam, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNgaySinh)
                                    .addComponent(txtLuong)))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtChucVu)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
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
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayBatDauLam, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblNgayBatDauLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayKetThucLam, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblNgayKetThucLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtLuong))
                .addGap(18, 18, 18)
                .addComponent(btnXemChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(pnlThongTinNhanVien, java.awt.BorderLayout.WEST);
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
