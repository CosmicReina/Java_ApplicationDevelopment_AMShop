package gui_new;

import gui.*;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import data.FormatDate;
import data.FormatDouble;
import data.GenerateBaoCaoDoanhThu;
import data.UtilityLocalDateTime;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlThongKeDoanhThu extends javax.swing.JPanel {
    
    private static PnlThongKeDoanhThu instance = new PnlThongKeDoanhThu();
    
    private ArrayList<HoaDon> listHoaDon = new ArrayList<>();
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    public static PnlThongKeDoanhThu getInstance() {
        return instance;
    }
    
    public static PnlThongKeDoanhThu newInstance() {
        instance = new PnlThongKeDoanhThu();
        return instance;
    }
    
    public PnlThongKeDoanhThu() {
        initComponents();
        
    }
    
    private void updateTable(){
        String ngayBatDauString = txtNgayBatDau.getText();
        String ngayKetThucString = txtNgayKetThuc.getText();
        
        LocalDate ngayBatDau = LocalDate.now();
        LocalDate ngayKetThuc = LocalDate.now();
        
        String error = "";
        
        if(ngayBatDauString.isBlank())
            error += "\n- Vui lòng nhập Ngày Bắt Đầu thống kê.";
        else{
            try{
                ngayBatDau = FormatDate.toLocalDate(ngayBatDauString); // Kiểm tra chuyển đổi
            }
            catch(Exception e){
                error += "\n- Vui lòng nhập Ngày Bắt Đầu hợp lệ (DD/MM/YYYY).";
            }
        }
        
        if(ngayKetThucString.isBlank())
            error += "\n- Vui lòng nhập Ngày Kết Thúc thống kê.";
        else{
            try{
                ngayKetThuc = FormatDate.toLocalDate(ngayKetThucString); // Kiểm tra chuyển đổi
                if(ngayBatDau.isAfter(ngayKetThuc))
                    error += "\n- Ngày Kết Thúc Thống kê phải lớn hơn Ngày Bắt Đầu.";
            }
            catch(Exception e){
                error += "\n- Vui lòng nhập Ngày Kết Thúc hợp lệ (DD/MM/YYYY).";
            }
        }
        
        if(error.equals("")){
            DefaultTableModel model = (DefaultTableModel) tblDanhSachHoaDon.getModel();
            model.getDataVector().removeAllElements();
            tblDanhSachHoaDon.revalidate();
            tblDanhSachHoaDon.repaint();
            
            ArrayList<HoaDon> listHD = DAO_HoaDon.getAllHoaDonTrongKhoangNgay(ngayBatDau, ngayKetThuc);
            double tongDoanhThu = 0;
            for(HoaDon thisHoaDon : listHD){
                ArrayList<ChiTietHoaDon> listCTHD = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(thisHoaDon.getMaHoaDon());
                double doanhThuThanhPhan = 0;
                for(ChiTietHoaDon thisChiTietHoaDon : listCTHD){
                    doanhThuThanhPhan += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
                }
                model.addRow(new Object[]{
                    thisHoaDon.getMaHoaDon(),
                    thisHoaDon.getNhanVien().getHoTen(),
                    thisHoaDon.getKhachHang().getHoTen(),
                    UtilityLocalDateTime.toFormattedLocalDateTime(thisHoaDon.getThoiGianTao()),
                    FormatDouble.toMoney(doanhThuThanhPhan)
                });
                tongDoanhThu += doanhThuThanhPhan;
            }
            txtTongSoHoaDon.setText(Integer.toString(listHD.size()));
            txtTongSoDoanhThu.setText(FormatDouble.toMoney(tongDoanhThu));
            listHoaDon = listHD;
            this.ngayBatDau = ngayBatDau;
            this.ngayKetThuc = ngayKetThuc;
        }
        else{
            String throwMessage = "Lỗi nhập liệu: " + error;
            JOptionPane.showMessageDialog(null, throwMessage);
        }
    }
    
    private void inBaoCaoThongKe(){
        try {
            if(listHoaDon.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng tạo thống kê trước.");
                return;
            }
            if(GenerateBaoCaoDoanhThu.createBaoCaoDoanhThu(listHoaDon, ngayBatDau, ngayKetThuc) == true){
                JOptionPane.showMessageDialog(null, "Tạo Báo Cáo Doanh Thu thành công.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Tạo Báo Cáo Doanh Thu thất bại.");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrDanhSachHoaDon = new javax.swing.JScrollPane();
        tblDanhSachHoaDon = new javax.swing.JTable();
        pnlThongKe = new javax.swing.JPanel();
        lblNgayBatDau = new javax.swing.JLabel();
        lblNgayKetThuc = new javax.swing.JLabel();
        lblTongSoHoaDon = new javax.swing.JLabel();
        lblTongSoDoanhThu = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        txtNgayKetThuc = new javax.swing.JTextField();
        txtTongSoHoaDon = new javax.swing.JTextField();
        txtTongSoDoanhThu = new javax.swing.JTextField();
        btnThongKe = new javax.swing.JButton();
        btnInBaoCao = new javax.swing.JButton();

        setBackground(new java.awt.Color(68, 136, 255));
        setLayout(new java.awt.BorderLayout());

        scrDanhSachHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn bán hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        scrDanhSachHoaDon.setPreferredSize(new java.awt.Dimension(800, 800));

        tblDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Khách hàng", "Ngày lập hóa đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachHoaDon.setRowHeight(40);
        scrDanhSachHoaDon.setViewportView(tblDanhSachHoaDon);

        add(scrDanhSachHoaDon, java.awt.BorderLayout.WEST);

        pnlThongKe.setBackground(new java.awt.Color(68, 136, 255));
        pnlThongKe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        lblNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNgayBatDau.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayBatDau.setText("Ngày bắt đầu");

        lblNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNgayKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayKetThuc.setText("Ngày kết thúc");

        lblTongSoHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTongSoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSoHoaDon.setText("Tổng số hóa đơn");

        lblTongSoDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTongSoDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSoDoanhThu.setText("Tổng số doanh thu");

        txtNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtTongSoHoaDon.setEditable(false);
        txtTongSoHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        txtTongSoHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtTongSoDoanhThu.setEditable(false);
        txtTongSoDoanhThu.setBackground(new java.awt.Color(204, 204, 204));
        txtTongSoDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

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
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongKeLayout.createSequentialGroup()
                        .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTongSoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTongSoDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayBatDau)
                            .addComponent(txtNgayKetThuc)
                            .addComponent(txtTongSoHoaDon)
                            .addComponent(txtTongSoDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
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
                    .addComponent(txtTongSoHoaDon)
                    .addComponent(lblTongSoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongSoDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongSoDoanhThu))
                .addGap(45, 45, 45)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(324, Short.MAX_VALUE))
        );

        add(pnlThongKe, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        updateTable();
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        // TODO add your handling code here:
        inBaoCaoThongKe();
    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInBaoCao;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblTongSoDoanhThu;
    private javax.swing.JLabel lblTongSoHoaDon;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JScrollPane scrDanhSachHoaDon;
    private javax.swing.JTable tblDanhSachHoaDon;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtTongSoDoanhThu;
    private javax.swing.JTextField txtTongSoHoaDon;
    // End of variables declaration//GEN-END:variables

}
