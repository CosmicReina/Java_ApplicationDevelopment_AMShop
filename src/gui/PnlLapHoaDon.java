package gui;

import dao.DAO_ChiTietDonDatHang;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_CuaHang;
import dao.DAO_DonDatHang;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_NhanVien;
import dao.DAO_QuanAo;
import data.PrintInvoice;
import data.UtilityJTextField;
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

public class PnlLapHoaDon extends javax.swing.JPanel {
    
    private ArrayList<ChiTietHoaDon> listDonHang = new ArrayList<>();
    private double tongTien;
    
    public PnlLapHoaDon() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        UtilityJTextField.addPlaceHolderStyle(txtMaQuanAo);
        UtilityJTextField.addPlaceHolderStyle(txtTenQuanAo);
        UtilityJTextField.addPlaceHolderStyle(txtSoLuong);
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
        UtilityJTextField.addPlaceHolderStyle(txtHoTen);
        UtilityJTextField.addPlaceHolderStyle(txtDiaChi);
        
        showTableListQuanAo();
    }
    
    private void showTableListQuanAo(){
        ArrayList<QuanAo> list = DAO_QuanAo.getAllQuanAo();
        DefaultTableModel model = (DefaultTableModel) tblDanhSachQuanAo.getModel();
        for(QuanAo thisQuanAo : list){
            model.addRow(new Object[]{
                thisQuanAo.getMaQuanAo(),
                thisQuanAo.getTenQuanAo(),
                thisQuanAo.getDonGiaBan(),
                thisQuanAo.getSoLuongTrongKho(),
                thisQuanAo.getNhaSanXuat(),
                thisQuanAo.getDanhMuc(),
                thisQuanAo.getGioiTinh(),
                thisQuanAo.getMauSac(),
                thisQuanAo.getKichThuoc(),
                thisQuanAo.getChatLieu()
            });
        }
    }
    
    private void updateTable(ArrayList<ChiTietHoaDon> list){
        tongTien = 0;
        NumberFormat nbf = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        model.getDataVector().removeAllElements();
        tblDonHang.revalidate();
        tblDonHang.repaint();
        for(ChiTietHoaDon thisChiTietHoaDon : list){
            tongTien += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            model.addRow(new Object[]{
                thisChiTietHoaDon.getQuanAo().getMaQuanAo(),
                thisChiTietHoaDon.getQuanAo().getTenQuanAo(),
                thisChiTietHoaDon.getSoLuong(),
                thisChiTietHoaDon.getDonGia(),
                nbf.format(tongTien)
            });
            
        }
        txtTongTien.setText(nbf.format(tongTien));
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
    
    private String generateMaDonDatHang(){
        String maDonDatHang;
        int soDonDatHang;
        
        LocalDate localDate = LocalDate.now();
        String year = Integer.toString(localDate.getYear()).substring(2);
        String month = String.format("%02d", localDate.getMonth().getValue());
        String day = String.format("%02d", localDate.getDayOfMonth());
        
        String prefix = "DD" + year + month + day;
        
        String maDonDatHangCuoi = DAO_DonDatHang.getMaDonDatHangCuoi();
        if(maDonDatHangCuoi == null){
            soDonDatHang = 1;
            maDonDatHang = prefix + String.format("%04d", soDonDatHang);
        }
        else{
            soDonDatHang = Integer.parseInt(maDonDatHangCuoi.substring(8)) + 1;
            maDonDatHang = prefix + String.format("%04d", soDonDatHang);
        }
        return maDonDatHang;
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
    
    private void themQuanAoVaoDonHang(){
        int selectedRow = tblDanhSachQuanAo.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm");
        }
        
        String amount = txtSoLuong.getText();
        int soLuong;
        
        if(amount.equals("")){
            soLuong = 1;
        }
        else{
            soLuong = Integer.parseInt(amount);
        }
        
        String maQuanAo = tblDanhSachQuanAo.getValueAt(selectedRow, 0).toString();
        QuanAo quanAo = DAO_QuanAo.getQuanAoTheoMaQuanAo(maQuanAo);
        
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(null, quanAo, soLuong, quanAo.getDonGiaBan());
        if(listDonHang.contains(chiTietHoaDon)){
            ChiTietHoaDon cthd = listDonHang.get(listDonHang.indexOf(chiTietHoaDon));
            cthd.setSoLuong(cthd.getSoLuong() + chiTietHoaDon.getSoLuong());
        }
        else{
            listDonHang.add(chiTietHoaDon);
        }
        tblDanhSachQuanAo.clearSelection();
        updateTable(listDonHang);
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
        
        for(ChiTietHoaDon thisChiTietHoaDon : listDonHang){
            thisChiTietHoaDon.setHoaDon(hoaDon);
            DAO_ChiTietHoaDon.createChiTietHoaDon(thisChiTietHoaDon);
        }
        
        if(themHoaDon == true){
            try {
                double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                PrintInvoice.createAMShopInvoice(hoaDon, listDonHang, tongTien, tienKhachDua);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        } 
        else{
            JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
        }
        
        listDonHang = new ArrayList<>();
    }
    
    private void taoDonDatHang(){
        String maDonDatHang = generateMaDonDatHang();
        
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
        
        NhanVien nhanVien = DAO_NhanVien.nhanVienHienTai;

        LocalDateTime thoiGianTao = LocalDateTime.now();

        DonDatHang donDatHang = new DonDatHang(maDonDatHang, nhanVien, khachHang, thoiGianTao, false);
        
        boolean themDonDatHang = DAO_DonDatHang.createDonDatHang(donDatHang);
        
        for(ChiTietHoaDon thisChiTietHoaDon : listDonHang){
            ChiTietDonDatHang chiTietDonDatHang = new ChiTietDonDatHang(donDatHang, thisChiTietHoaDon.getQuanAo(), thisChiTietHoaDon.getSoLuong());
            DAO_ChiTietDonDatHang.createChiTietDonDatHang(chiTietDonDatHang);
        }
        
        if(themDonDatHang == true){
            JOptionPane.showMessageDialog(null, "Tạo Đơn Đặt Hàng Thành Công");
        }
        else{
            JOptionPane.showMessageDialog(null, "Tạo Đơn Đặt Hàng Thất Bại");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimKiem = new javax.swing.JPanel();
        txtMaQuanAo = new javax.swing.JTextField();
        txtTenQuanAo = new javax.swing.JTextField();
        cmbNhaSanXuat = new javax.swing.JComboBox<>();
        cmbDanhMuc = new javax.swing.JComboBox<>();
        cmbMauSac = new javax.swing.JComboBox<>();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        cmbChatLieu = new javax.swing.JComboBox<>();
        cmbKichThuoc = new javax.swing.JComboBox<>();
        btnTimKiemQA = new javax.swing.JButton();
        btnThemQA = new javax.swing.JButton();
        btnXoaQA = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        scrDanhSachQuanAo = new javax.swing.JScrollPane();
        tblDanhSachQuanAo = new javax.swing.JTable();
        pnlTaoHoaDon = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        scrDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        pnlDatDonHang = new javax.swing.JPanel();
        pnlThongTinKhachHang = new javax.swing.JPanel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnTimKiemKH = new javax.swing.JButton();
        pnlThanhToan = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        lblTienKhachDua = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        btnDatHang = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        lblTienThua = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        pnlTimKiem.setBackground(new java.awt.Color(79, 137, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiem.setPreferredSize(new java.awt.Dimension(236, 700));

        txtMaQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaQuanAo.setText("Mã Quần Áo");
        txtMaQuanAo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaQuanAoFocusGained(evt);
            }
        });

        txtTenQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenQuanAo.setText("Tên Quần Áo");
        txtTenQuanAo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenQuanAoFocusGained(evt);
            }
        });

        cmbNhaSanXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbNhaSanXuat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà Sản Xuất" }));

        cmbDanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Danh Mục" }));

        cmbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Màu Sắc" }));

        cmbGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giới Tính" }));

        cmbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chất Liệu" }));

        cmbKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kích Thước" }));

        btnTimKiemQA.setBackground(new java.awt.Color(0, 255, 255));
        btnTimKiemQA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTimKiemQA.setText("Tìm kiếm");

        btnThemQA.setBackground(new java.awt.Color(0, 255, 255));
        btnThemQA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemQA.setText("Thêm");
        btnThemQA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemQAActionPerformed(evt);
            }
        });

        btnXoaQA.setBackground(new java.awt.Color(0, 255, 255));
        btnXoaQA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoaQA.setText("Xóa");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusGained(evt);
            }
        });

        pnlHinhAnh.setBackground(new java.awt.Color(204, 204, 204));
        pnlHinhAnh.setLayout(new java.awt.GridBagLayout());

        lblHinhAnh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHinhAnh.setText("IMG");
        pnlHinhAnh.add(lblHinhAnh, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaQA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(btnThemQA, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiemQA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbNhaSanXuat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenQuanAo)
                    .addComponent(txtMaQuanAo)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtMaQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiemQA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemQA, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtSoLuong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaQA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlTimKiem, java.awt.BorderLayout.EAST);

        pnlHoaDon.setPreferredSize(new java.awt.Dimension(930, 700));
        pnlHoaDon.setLayout(new java.awt.BorderLayout());

        scrDanhSachQuanAo.setPreferredSize(new java.awt.Dimension(452, 302));

        tblDanhSachQuanAo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã quần áo", "Tên quần áo ", "Đơn giá", "Số lượng", "Nhà sản xuất", "Danh mục", "Giới tính", "Màu sắc", "Kích thước", "Chất liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachQuanAo.setPreferredSize(new java.awt.Dimension(1164, 375));
        scrDanhSachQuanAo.setViewportView(tblDanhSachQuanAo);
        if (tblDanhSachQuanAo.getColumnModel().getColumnCount() > 0) {
            tblDanhSachQuanAo.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblDanhSachQuanAo.getColumnModel().getColumn(1).setMaxWidth(250);
        }

        pnlHoaDon.add(scrDanhSachQuanAo, java.awt.BorderLayout.PAGE_START);

        pnlTaoHoaDon.setPreferredSize(new java.awt.Dimension(930, 325));
        pnlTaoHoaDon.setLayout(new java.awt.BorderLayout());

        pnlDonHang.setBackground(new java.awt.Color(79, 137, 255));
        pnlDonHang.setPreferredSize(new java.awt.Dimension(374, 373));
        pnlDonHang.setLayout(new java.awt.BorderLayout());

        scrDonHang.setPreferredSize(new java.awt.Dimension(352, 202));

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

        pnlDatDonHang.setPreferredSize(new java.awt.Dimension(730, 373));
        pnlDatDonHang.setLayout(new java.awt.BorderLayout());

        pnlThongTinKhachHang.setBackground(new java.awt.Color(79, 137, 255));
        pnlThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThongTinKhachHang.setPreferredSize(new java.awt.Dimension(730, 206));

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoai.setText("Số Điện Thoại");
        txtSoDienThoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiFocusGained(evt);
            }
        });

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setText("Họ Tên");
        txtHoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoTenFocusGained(evt);
            }
        });

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setText("Địa Chỉ");
        txtDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusGained(evt);
            }
        });

        btnTimKiemKH.setBackground(new java.awt.Color(0, 255, 255));
        btnTimKiemKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTimKiemKH.setText("Tìm kiếm");

        javax.swing.GroupLayout pnlThongTinKhachHangLayout = new javax.swing.GroupLayout(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(pnlThongTinKhachHangLayout);
        pnlThongTinKhachHangLayout.setHorizontalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi))
                .addContainerGap(22, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlDatDonHang.add(pnlThongTinKhachHang, java.awt.BorderLayout.NORTH);

        pnlThanhToan.setBackground(new java.awt.Color(79, 137, 255));
        pnlThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThanhToan.setPreferredSize(new java.awt.Dimension(430, 167));

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

        btnDatHang.setBackground(new java.awt.Color(0, 255, 255));
        btnDatHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDatHang.setText("Đặt hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

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
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienThua)))
                .addGap(47, 47, 47)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDatHang))
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        pnlDatDonHang.add(pnlThanhToan, java.awt.BorderLayout.CENTER);

        pnlTaoHoaDon.add(pnlDatDonHang, java.awt.BorderLayout.CENTER);

        pnlHoaDon.add(pnlTaoHoaDon, java.awt.BorderLayout.CENTER);

        add(pnlHoaDon, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaQuanAoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaQuanAoFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtMaQuanAo);
    }//GEN-LAST:event_txtMaQuanAoFocusGained

    private void txtTenQuanAoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenQuanAoFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtTenQuanAo);
    }//GEN-LAST:event_txtTenQuanAoFocusGained

    private void txtSoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoLuong);
    }//GEN-LAST:event_txtSoLuongFocusGained

    private void txtSoDienThoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoai);
    }//GEN-LAST:event_txtSoDienThoaiFocusGained

    private void txtHoTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtHoTen);
    }//GEN-LAST:event_txtHoTenFocusGained

    private void txtDiaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtDiaChi);
    }//GEN-LAST:event_txtDiaChiFocusGained

    private void btnThemQAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemQAActionPerformed
        // TODO add your handling code here:
        themQuanAoVaoDonHang();
    }//GEN-LAST:event_btnThemQAActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        thanhToanChoDonHang();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        // TODO add your handling code here:
        taoDonDatHang();
    }//GEN-LAST:event_btnDatHangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemQA;
    private javax.swing.JButton btnTimKiemKH;
    private javax.swing.JButton btnTimKiemQA;
    private javax.swing.JButton btnXoaQA;
    private javax.swing.JComboBox<String> cmbChatLieu;
    private javax.swing.JComboBox<String> cmbDanhMuc;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JComboBox<String> cmbMauSac;
    private javax.swing.JComboBox<String> cmbNhaSanXuat;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlDatDonHang;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlTaoHoaDon;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JPanel pnlThongTinKhachHang;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrDanhSachQuanAo;
    private javax.swing.JScrollPane scrDonHang;
    private javax.swing.JTable tblDanhSachQuanAo;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaQuanAo;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenQuanAo;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
