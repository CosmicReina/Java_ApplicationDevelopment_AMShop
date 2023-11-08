package gui;

import dao.DAO_ChiTietDonDatHang;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_CuaHang;
import dao.DAO_DonDatHang;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_NhanVien;
import data.PrintInvoice;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.CuaHang;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.QuanAo;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachDonDatHang extends javax.swing.JPanel {
    
    ArrayList<ChiTietHoaDon> listDonHang = new ArrayList<>();
    ArrayList<ChiTietDonDatHang> listDonDatHang = new ArrayList<>();
    DonDatHang donDatHangHienTai;
    double tongTienThanhToan = 0;
    
    public PnlDanhSachDonDatHang() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        showTableListDonDatHang();
    }
    
    private void showTableListDonDatHang (){
        
        ArrayList<DonDatHang> list = DAO_DonDatHang.getAllDonDatHang();
        DefaultTableModel model = (DefaultTableModel) tblDanhSachDonDatHang.getModel();
        for(DonDatHang thisDonDatHang : list){
            if(thisDonDatHang.isTrangThaiThanhToan() == true) break;
            ArrayList<ChiTietDonDatHang> listCTDDH = DAO_ChiTietDonDatHang.getAllChiTietDonDatHangTheoMaDonDatHang(thisDonDatHang.getMaDonDatHang());
            double tongTien = 0;
            for(ChiTietDonDatHang thisChiTietDonDatHang : listCTDDH){
                tongTien += (thisChiTietDonDatHang.getSoLuong() * thisChiTietDonDatHang.getQuanAo().getDonGiaBan());
            }
            model.addRow(new Object[]{
                thisDonDatHang.getMaDonDatHang(),
                thisDonDatHang.getNhanVien().getHoTen(),
                thisDonDatHang.getKhachHang().getHoTen(),
                thisDonDatHang.getThoiGianTao().toString(),
                tongTien
            });
        }
    }
    
    private String generateMaKhachHang(){
        String maKhachHang;
        int soKhachHang;
        
        LocalDate localDate = LocalDate.now();
        String year = Integer.toString(localDate.getYear()).substring(2);
        
        String prefix = "KH" + year;
        
        String maKhachHangCuoi = DAO_KhachHang.getMaKhachHangCuoi();
        if(maKhachHangCuoi == null){
            soKhachHang = 1;
            maKhachHang = prefix + String.format("%06d", soKhachHang);
        }
        else{
            soKhachHang = Integer.parseInt(maKhachHangCuoi.substring(4)) + 1;
            maKhachHang = prefix + String.format("%06d", soKhachHang);
        }
        return maKhachHang;
    }
    
    private String generateMaHoaDon(){
        String maHoaDon;
        int soHoaDon;
        
        LocalDate localDate = LocalDate.now();
        String year = Integer.toString(localDate.getYear()).substring(2);
        String month = String.format("%02d", localDate.getMonth().getValue());
        String day = String.format("%02d", localDate.getDayOfMonth());
        
        String prefix = "HD" + year + month + day;
        
        String maHoaDonCuoi = DAO_HoaDon.getMaHoaDonCuoi();
        if(maHoaDonCuoi == null){
            soHoaDon = 1;
            maHoaDon = prefix + String.format("%04d", soHoaDon);
        }
        else{
            soHoaDon = Integer.parseInt(maHoaDonCuoi.substring(8)) + 1;
            maHoaDon = prefix + String.format("%04d", soHoaDon);
        }
        return maHoaDon;
    }
    
    private void thanhToanChoDonHang(){
        
        
        String maHoaDon = generateMaHoaDon();
        
        String soDienThoai = txtSoDienThoai.getText();
        String hoTen = txtHoTen.getText();
        String diaChi = txtDiaChi.getText();
        KhachHang khachHang;
        if(DAO_KhachHang.checkKhachHangTheoSoDienThoai(soDienThoai)){
            khachHang = DAO_KhachHang.getKhachHangTheoSoDienThoai(soDienThoai);
        } 
        else{
            String maKhachHang = generateMaKhachHang();
            String nhomKhachHang = "Thường";
            khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, diaChi, nhomKhachHang);
            DAO_KhachHang.createKhachHang(khachHang);
        } 
        
        CuaHang cuaHang = DAO_CuaHang.getCuaHang();
        
        NhanVien nhanVien = DAO_NhanVien.nhanVienHienTai;

        LocalDateTime thoiGianTao = LocalDateTime.now();

        HoaDon hoaDon = new HoaDon(maHoaDon, cuaHang, nhanVien, khachHang, thoiGianTao);
        
        boolean themHoaDon = DAO_HoaDon.createHoaDon(hoaDon);
        
        for(ChiTietDonDatHang thisChiTietDonDatHang : listDonDatHang){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, thisChiTietDonDatHang.getQuanAo(), thisChiTietDonDatHang.getSoLuong(), thisChiTietDonDatHang.getQuanAo().getDonGiaBan());
            listDonHang.add(chiTietHoaDon);
        }
        
        if(themHoaDon == true){
            try {
                double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                PrintInvoice.createAMShopInvoice(hoaDon, listDonHang, tongTienThanhToan, tienKhachDua);
                donDatHangHienTai.setTrangThaiThanhToan(true);
                DAO_DonDatHang.updateDonDatHang(donDatHangHienTai);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        } 
        else{
            JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
        }
        PnlMain.getInstance().showPanel(new PnlDanhSachDonDatHang());
        listDonHang = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHoaDon = new javax.swing.JPanel();
        scrDanhSachQA = new javax.swing.JScrollPane();
        tblDanhSachDonDatHang = new javax.swing.JTable();
        pnlTaoHoaDon = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        scrDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        pnlDatDonHang = new javax.swing.JPanel();
        pnlThongTinKhachHang = new javax.swing.JPanel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        pnlThanhToan = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        lblTienKhachDua = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        lblTienThua = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        pnlHoaDon.setPreferredSize(new java.awt.Dimension(1166, 700));
        pnlHoaDon.setLayout(new java.awt.BorderLayout());

        scrDanhSachQA.setPreferredSize(new java.awt.Dimension(1166, 352));

        tblDanhSachDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn đặt trước", "Tên nhân viên lập đơn", "Tên khách hàng", "Ngày lập đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachDonDatHang.setPreferredSize(new java.awt.Dimension(1164, 375));
        tblDanhSachDonDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachDonDatHangMouseClicked(evt);
            }
        });
        scrDanhSachQA.setViewportView(tblDanhSachDonDatHang);

        pnlHoaDon.add(scrDanhSachQA, java.awt.BorderLayout.NORTH);

        pnlTaoHoaDon.setPreferredSize(new java.awt.Dimension(1164, 425));
        pnlTaoHoaDon.setLayout(new java.awt.BorderLayout());

        pnlDonHang.setBackground(new java.awt.Color(79, 137, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(434, 373));
        pnlDonHang.setLayout(new java.awt.BorderLayout());

        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã quần áo", "Tên quần áo", "Số lượng", "Đơn giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrDonHang.setViewportView(tblDonHang);

        pnlDonHang.add(scrDonHang, java.awt.BorderLayout.CENTER);

        pnlTaoHoaDon.add(pnlDonHang, java.awt.BorderLayout.EAST);

        pnlDatDonHang.setPreferredSize(new java.awt.Dimension(714, 373));
        pnlDatDonHang.setLayout(new java.awt.BorderLayout());

        pnlThongTinKhachHang.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinKhachHang.setPreferredSize(new java.awt.Dimension(730, 156));

        txtSoDienThoai.setBackground(new java.awt.Color(204, 204, 204));
        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHoTen.setBackground(new java.awt.Color(204, 204, 204));
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDiaChi.setBackground(new java.awt.Color(204, 204, 204));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlThongTinKhachHangLayout = new javax.swing.GroupLayout(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(pnlThongTinKhachHangLayout);
        pnlThongTinKhachHangLayout.setHorizontalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(372, Short.MAX_VALUE))
        );
        pnlThongTinKhachHangLayout.setVerticalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(txtSoDienThoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlDatDonHang.add(pnlThongTinKhachHang, java.awt.BorderLayout.NORTH);

        pnlThanhToan.setBackground(new java.awt.Color(79, 137, 255));
        pnlThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThanhToan.setPreferredSize(new java.awt.Dimension(730, 167));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTien.setText("Tổng tiền");
        lblTongTien.setPreferredSize(new java.awt.Dimension(200, 16));

        lblTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTienKhachDua.setForeground(new java.awt.Color(255, 255, 255));
        lblTienKhachDua.setText("Tiền khách đưa");

        txtTongTien.setBackground(new java.awt.Color(204, 204, 204));
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThanhToan.setBackground(new java.awt.Color(0, 255, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblTienThua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 255, 255));
        lblTienThua.setText("Tiền thừa");

        txtTienThua.setBackground(new java.awt.Color(204, 204, 204));
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTien)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 121, Short.MAX_VALUE))
        );

        pnlDatDonHang.add(pnlThanhToan, java.awt.BorderLayout.CENTER);

        pnlTaoHoaDon.add(pnlDatDonHang, java.awt.BorderLayout.CENTER);

        pnlHoaDon.add(pnlTaoHoaDon, java.awt.BorderLayout.CENTER);

        add(pnlHoaDon, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachDonDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachDonDatHangMouseClicked
        // TODO add your handling code here:
        int i = tblDanhSachDonDatHang.getSelectedRow();
        if(i < 0) return;
        NumberFormat nbf = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
        String maDonDatHang = tblDanhSachDonDatHang.getValueAt(i, 0).toString();
        DonDatHang donDatHang = DAO_DonDatHang.getDonDatHangTheoMaDonDatHang(maDonDatHang);
        ArrayList<ChiTietDonDatHang> listCTDDH = DAO_ChiTietDonDatHang.getAllChiTietDonDatHangTheoMaDonDatHang(maDonDatHang);
        donDatHangHienTai = donDatHang;
        listDonDatHang = listCTDDH;
        double tongTien = 0;
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        model.getDataVector().removeAllElements();
        tblDonHang.revalidate();
        tblDonHang.repaint();
        for(ChiTietDonDatHang thisChiTietDonDatHang : listCTDDH){
            tongTien += thisChiTietDonDatHang.getSoLuong() * thisChiTietDonDatHang.getQuanAo().getDonGiaBan();
            model.addRow(new Object[]{
                thisChiTietDonDatHang.getQuanAo().getMaQuanAo(),
                thisChiTietDonDatHang.getQuanAo().getTenQuanAo(),
                thisChiTietDonDatHang.getSoLuong(),
                thisChiTietDonDatHang.getQuanAo().getDonGiaBan(),
                thisChiTietDonDatHang.getSoLuong() * thisChiTietDonDatHang.getQuanAo().getDonGiaBan()
            });
        }
        txtSoDienThoai.setText(donDatHang.getKhachHang().getSoDienThoai());
        txtHoTen.setText(donDatHang.getKhachHang().getHoTen());
        txtDiaChi.setText(donDatHang.getKhachHang().getDiaChi());
        txtTongTien.setText(nbf.format(tongTien));
        tongTienThanhToan = tongTien;
    }//GEN-LAST:event_tblDanhSachDonDatHangMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        thanhToanChoDonHang();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlDatDonHang;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlTaoHoaDon;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JPanel pnlThongTinKhachHang;
    private javax.swing.JScrollPane scrDanhSachQA;
    private javax.swing.JScrollPane scrDonHang;
    private javax.swing.JTable tblDanhSachDonDatHang;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
