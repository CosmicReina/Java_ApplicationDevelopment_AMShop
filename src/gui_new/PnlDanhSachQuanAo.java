package gui_new;

import dao.DAO_ChatLieu;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_CuaHang;
import dao.DAO_DanhMuc;
import dao.DAO_GioiTinh;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_KichThuoc;
import dao.DAO_MauSac;
import dao.DAO_NhaSanXuat;
import dao.DAO_NhanVien;
import dao.DAO_QuanAo;
import data.FormatDouble;
import data.GenerateID;
import data.GenerateInvoice;
import data.UtilityJTextField;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.CuaHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.QuanAo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachQuanAo extends javax.swing.JPanel implements MouseListener{
    
    private static PnlDanhSachQuanAo instance = new PnlDanhSachQuanAo();
    
    private double tongTien = 0;
    
    private ArrayList<ChiTietHoaDon> listDonHang = new ArrayList<>();

    public static PnlDanhSachQuanAo getInstance() {
        return instance;
    }
    
    public static PnlDanhSachQuanAo newInstance() {
        instance = new PnlDanhSachQuanAo();
        return instance;
    }

    public static void setInstance(PnlDanhSachQuanAo instance) {
        PnlDanhSachQuanAo.instance = instance;
    }

    public PnlDanhSachQuanAo() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTableQuanAo(DAO_QuanAo.getAllQuanAo());
        
        UtilityJTextField.addPlaceHolderStyle(txtMaQuanAo);
        UtilityJTextField.addPlaceHolderStyle(txtTenQuanAo);
        UtilityJTextField.addPlaceHolderStyle(txtSoLuong);
        UtilityJTextField.addPlaceHolderStyle(txtHoTen);
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
        UtilityJTextField.addPlaceHolderStyle(txtDiaChi);
        
        ArrayList<String> listNhaSanXuat = DAO_NhaSanXuat.getAllNhaSanXuat();
        ArrayList<String> listDanhMuc = DAO_DanhMuc.getAllDanhMuc();
        ArrayList<String> listGioiTinh = DAO_GioiTinh.getAllGioiTinh();
        ArrayList<String> listMauSac = DAO_MauSac.getAllMauSac();
        ArrayList<String> listKichThuoc = DAO_KichThuoc.getAllKichThuoc();
        ArrayList<String> listChatLieu = DAO_ChatLieu.getAllChatLieu();
        
        for(String thisNhaSanXuat : listNhaSanXuat){
            cmbNhaSanXuat.addItem(thisNhaSanXuat);
        }
        
        for(String thisDanhMuc : listDanhMuc){
            cmbDanhMuc.addItem(thisDanhMuc);
        }
        
        for(String thisGioiTinh : listGioiTinh){
            cmbGioiTinh.addItem(thisGioiTinh);
        }
        
        for(String thisMauSac : listMauSac){
            cmbMauSac.addItem(thisMauSac);
        }
        
        for(String thisKichThuoc : listKichThuoc){
            cmbKichThuoc.addItem(thisKichThuoc);
        }
        
        for(String thisChatLieu : listChatLieu){
            cmbChatLieu.addItem(thisChatLieu);
        }
    }
    
    private void updateTableQuanAo(ArrayList<QuanAo> list){
        DefaultTableModel model = (DefaultTableModel) tblQuanAo.getModel();
        model.getDataVector().removeAllElements();
        tblQuanAo.revalidate();
        tblQuanAo.repaint();
        for(QuanAo thisQuanAo : list){
            model.addRow(new Object[]{
                thisQuanAo.getMaQuanAo(),
                thisQuanAo.getTenQuanAo(),
                FormatDouble.toMoney(thisQuanAo.getDonGiaBan()),
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
    
    private void updateTableDonHang(ArrayList<ChiTietHoaDon> list){
        tongTien = 0;
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
                FormatDouble.toMoney(thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia())
            });
        }
        txtTongTien.setText(FormatDouble.toMoney(tongTien));
    }
    
    private void themQuanAoVaoDonHang(){
        int i = tblQuanAo.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm.");
            return;
        }
            
        String soLuongString = txtSoLuong.getText();
        int soLuong = 0;
        if(soLuongString.equals("Số Lượng"))
            soLuong = 1;
        else{
            try{
                soLuong = Integer.parseInt(soLuongString);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập Số Lượng hợp lệ.");
                return;
            }
        }
        
        String maQuanAo = tblQuanAo.getValueAt(i, 0).toString();
        QuanAo quanAo = DAO_QuanAo.getQuanAoTheoMaQuanAo(maQuanAo);
        
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(null, quanAo, soLuong, quanAo.getDonGiaBan());
        if(listDonHang.contains(chiTietHoaDon)){
            ChiTietHoaDon cthd = listDonHang.get(listDonHang.indexOf(chiTietHoaDon));
            cthd.setSoLuong(cthd.getSoLuong() + chiTietHoaDon.getSoLuong());
        }
        else{
            listDonHang.add(chiTietHoaDon);
        }
        tblQuanAo.clearSelection();
        updateTableDonHang(listDonHang);
    }
    
    private void thanhToanChoDonHang(){
        String error = "";
        
        String maHoaDon = GenerateID.generateMaHoaDon();
        
        String hoTen = txtHoTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = txtDiaChi.getText();
        
        String tienDuaString = txtTienDua.getText();
        double tienDua = 0;
        
        if(listDonHang.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng thêm ít nhất một Quần Áo vào đơn hàng.");
            return;
        }
        
        if(hoTen.equals("Họ Tên")) //Kiểm tra rỗng
            error += "\n- Vui lòng nhập Họ Tên.";
        else
            if(!hoTen.matches("^[\\p{L}]+(\\s[\\p{L}]+)+$")) //Kiểm tra Biểu thức chính quy
                error += "\n- Vui lòng nhập Họ Tên hợp lệ.";
        
        if(soDienThoai.equals("Số Điện Thoại")) // Kiểm tra rỗng
            error += "\n- Vui lòng nhập Số Điện Thoại.";
        else
            if(!soDienThoai.matches("0{1}[0-9]{9}")) // Kiểm tra biểu thức chính quy
                error += "\n- Vui lòng nhập Số Điện Thoại hợp lệ.";
        
        if(diaChi.equals("Địa Chỉ")) // Kiểm tra rỗng
            error += "\n- Vui lòng nhập Địa Chỉ.";
        
        if(tienDuaString.equals(""))
            error += "\n- Vui lòng nhập Tiền Khách Đưa.";
        else{
            try{
                tienDua = Double.parseDouble(tienDuaString);
                if(tienDua < tongTien){
                    error += "\n- Tiền Khách Đưa phải lớn hơn hoặc bằng Tổng Tiền.";
                }
            }
            catch(NumberFormatException e){
                error += "\n- Vui lòng nhập Tiền Khách Đưa hợp lệ.";
            }
        }
        
        if(!error.equals("")){
            String throwMessage = "Lỗi nhập liệu: " + error;
            JOptionPane.showMessageDialog(null, throwMessage);
            return;
        }
        KhachHang khachHang = DAO_KhachHang.getKhachHangTheoSoDienThoai(soDienThoai);
        if(khachHang == null){
            String maKhachHang = GenerateID.generateMaKhachHang();
            String nhomKhachHang = "Thường";
            khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, diaChi, nhomKhachHang);
            DAO_KhachHang.createKhachHang(khachHang);
        }

        CuaHang cuaHang = DAO_CuaHang.getCuaHang();
        NhanVien nhanVien = DAO_NhanVien.getNhanVienHienTai();
        LocalDateTime thoiGianTao = LocalDateTime.now();

        HoaDon hoaDon = new HoaDon(maHoaDon, cuaHang, nhanVien, khachHang, thoiGianTao);

        boolean themHoaDon = DAO_HoaDon.createHoaDon(hoaDon);

        for(ChiTietHoaDon thisChiTietHoaDon : listDonHang){
            thisChiTietHoaDon.setHoaDon(hoaDon);
            DAO_ChiTietHoaDon.createChiTietHoaDon(thisChiTietHoaDon);
        }
        
        if(themHoaDon){
            try {
                GenerateInvoice.createAMShopInvoice(hoaDon, listDonHang, tongTien, tienDua);
                JOptionPane.showMessageDialog(null, "Thanh toán thành công.");
                PnlMain.getInstance().showPanel(newInstance());
                //Show ChiTietHoaDon
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Gặp Lỗi Khi In Hóa Đơn.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlQuanAo = new javax.swing.JPanel();
        scrQuanAo = new javax.swing.JScrollPane();
        tblQuanAo = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        txtMaQuanAo = new javax.swing.JTextField();
        txtTenQuanAo = new javax.swing.JTextField();
        cmbNhaSanXuat = new javax.swing.JComboBox<>();
        cmbDanhMuc = new javax.swing.JComboBox<>();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        cmbMauSac = new javax.swing.JComboBox<>();
        cmbKichThuoc = new javax.swing.JComboBox<>();
        cmbChatLieu = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1166, 700));
        setLayout(new java.awt.BorderLayout());

        pnlQuanAo.setPreferredSize(new java.awt.Dimension(916, 700));
        pnlQuanAo.setLayout(new java.awt.BorderLayout());

        scrQuanAo.setPreferredSize(new java.awt.Dimension(916, 700));

        tblQuanAo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Quần Áo", "Tên Quần Áo", "Đơn Giá", "Số Lượng", "Nhà Sản Xuất", "Danh Mục", "Giới Tính", "Màu Sắc", "Kích Thước", "Chất Liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanAo.setPreferredSize(new java.awt.Dimension(916, 700));
        tblQuanAo.setRowHeight(40);
        tblQuanAo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrQuanAo.setViewportView(tblQuanAo);
        if (tblQuanAo.getColumnModel().getColumnCount() > 0) {
            tblQuanAo.getColumnModel().getColumn(1).setMinWidth(200);
            tblQuanAo.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        pnlQuanAo.add(scrQuanAo, java.awt.BorderLayout.WEST);

        add(pnlQuanAo, java.awt.BorderLayout.CENTER);

        pnlTimKiem.setBackground(new java.awt.Color(68, 136, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiem.setPreferredSize(new java.awt.Dimension(250, 700));

        txtMaQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaQuanAo.setText("Mã Quần Áo");
        txtMaQuanAo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaQuanAoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaQuanAoFocusLost(evt);
            }
        });

        txtTenQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenQuanAo.setText("Tên Quần Áo");
        txtTenQuanAo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenQuanAoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenQuanAoFocusLost(evt);
            }
        });

        cmbNhaSanXuat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà Sản Xuất" }));

        cmbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Danh Mục" }));

        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giới Tính" }));

        cmbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Màu Sắc" }));

        cmbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kích Thước" }));

        cmbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chất Liệu" }));

        btnTimKiem.setBackground(new java.awt.Color(170, 238, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(170, 238, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(170, 238, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.setText("Số Lượng");
        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusLost(evt);
            }
        });

        pnlHinhAnh.setBackground(new java.awt.Color(204, 204, 204));
        pnlHinhAnh.setPreferredSize(new java.awt.Dimension(196, 270));
        pnlHinhAnh.setLayout(new java.awt.GridBagLayout());

        lblHinhAnh.setText("IMG");
        pnlHinhAnh.add(lblHinhAnh, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaQuanAo)
                    .addComponent(txtTenQuanAo)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong)))
                .addContainerGap())
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtMaQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenQuanAo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaQuanAoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaQuanAoFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtMaQuanAo, "Mã Quần Áo");
    }//GEN-LAST:event_txtMaQuanAoFocusGained

    private void txtTenQuanAoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenQuanAoFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtTenQuanAo, "Tên Quần Áo");
    }//GEN-LAST:event_txtTenQuanAoFocusGained

    private void txtSoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoLuong, "Số Lượng");
    }//GEN-LAST:event_txtSoLuongFocusGained

    private void txtMaQuanAoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaQuanAoFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtMaQuanAo, "Mã Quần Áo");
    }//GEN-LAST:event_txtMaQuanAoFocusLost

    private void txtTenQuanAoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenQuanAoFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtTenQuanAo, "Tên Quần Áo");
    }//GEN-LAST:event_txtTenQuanAoFocusLost

    private void txtSoLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoLuong, "Số Lượng");
    }//GEN-LAST:event_txtSoLuongFocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themQuanAoVaoDonHang();
    }//GEN-LAST:event_btnThemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbChatLieu;
    private javax.swing.JComboBox<String> cmbDanhMuc;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JComboBox<String> cmbMauSac;
    private javax.swing.JComboBox<String> cmbNhaSanXuat;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JPanel pnlQuanAo;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrQuanAo;
    private javax.swing.JTable tblQuanAo;
    private javax.swing.JTextField txtMaQuanAo;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenQuanAo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
