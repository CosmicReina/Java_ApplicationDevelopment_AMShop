package gui_new;

import dao.DAO_ChucVu;
import dao.DAO_NhanVien;
import data.FormatDate;
import data.FormatDouble;
import data.GenerateID;
import data.UtilityJTextField;
import entity.NhanVien;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PnlThemNhanVien extends javax.swing.JPanel {
    
    private static PnlThemNhanVien instance = new PnlThemNhanVien();

    public static PnlThemNhanVien getInstance() {
        return instance;
    }
    
    public static PnlThemNhanVien newInstance() {
        instance = new PnlThemNhanVien();
        return instance;
    }

    public PnlThemNhanVien() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTable();
        
        txtTenDangNhap.setEditable(false);
        txtMaNhanVien.setEditable(false);
        
        txtTenDangNhap.setText(GenerateID.generateMaNhanVien());
        txtMaNhanVien.setText(GenerateID.generateMaNhanVien());
        
        ArrayList<String> listChucVu = DAO_ChucVu.getAllChucVu();
        for(String thisChucVu : listChucVu)
            cmbChucVu.addItem(thisChucVu);
        
        tblTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        UtilityJTextField.addPlaceHolderStyle(txtHoTen);
        UtilityJTextField.addPlaceHolderStyle(txtCCCD);
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
        UtilityJTextField.addPlaceHolderStyle(txtNgaySinh);
        UtilityJTextField.addPlaceHolderStyle(txtLuong);
        UtilityJTextField.addPlaceHolderStyle(txtDiaChi);
    }
    
    private void updateTable(){
        ArrayList<NhanVien> list = DAO_NhanVien.getAllNhanVien();
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        for(NhanVien thisNhanVien : list){
            model.addRow(new Object[]{
                thisNhanVien.getMaNhanVien(),
                thisNhanVien.getHoTen(),
                thisNhanVien.getCanCuocCongDan(),
                thisNhanVien.getSoDienThoai(),
                FormatDate.fromLocalDate(thisNhanVien.getNgaySinh()),
                thisNhanVien.getGioiTinh(),
                thisNhanVien.getChucVu(),
                FormatDouble.toMoney(thisNhanVien.getLuong())
            });
        }
    }
    
    private void themNhanVien(){
        String error = "";
        
        String maNhanVien = txtMaNhanVien.getText();
        String hoTen = txtHoTen.getText();
        String canCuocCongDan = txtCCCD.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String ngaySinhString = txtNgaySinh.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
        String chucVu = cmbChucVu.getSelectedItem().toString();
        String luongString = txtLuong.getText();
        String diaChi = txtDiaChi.getText();
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = new String(txtMauKhau.getPassword());
        LocalDate ngayBatDauLam = LocalDate.now();
        LocalDate ngayKetThucLam = null;
        
        if(hoTen.equals("Họ Tên"))
            error += "\n- Vui lòng nhập Họ Tên.";
        else{
            if(!hoTen.matches("^[\\p{L}]+(\\s[\\p{L}]+)+$")){
                error += "\n- Vui lòng nhập Họ Tên hợp lệ.";
            }
        }
        
        if(canCuocCongDan.equals("Căn Cước Công Dân"))
            error += "\n- Vui lòng nhập Căn Cước Công Dân.";
        else{
            if(!canCuocCongDan.matches("[0-9]{12}"))
                error += "\n- Vui lòng nhập Căn Cước Công Dân hợp lệ.";
        }
            
        if(soDienThoai.equals("Số Điện Thoại"))
            error += "\n- Vui lòng nhập Số Điện Thoại.";
        else{
            if(!soDienThoai.matches("0{1}[0-9]{9}"))
                error += "\n- Vui lòng nhập Số Điện Thoại hợp lệ.";
        }
        
        LocalDate ngaySinh = null;
        if(ngaySinhString.equals("Ngày Sinh (DD/MM/YYYY)")){
            error += "\n- Vui lòng nhập Ngày Sinh.";
        }
        else{
            try{
                ngaySinh = FormatDate.toLocalDate(ngaySinhString);
            }
            catch(Exception e){
                error += "\n- Vui lòng nhập Ngày Sinh hợp lệ.";
            }
        }
        
        if(gioiTinh.equals("Giới Tính"))
            error += "\n- Vui lòng chọn Giới Tính.";
        
        if(chucVu.equals("Chức Vụ"))
            error += "\n- Vui lòng chọn Chức Vụ.";
        
        double luong = 0;
        if(luongString.equals("Mức Lương")){
            error += "\n- Vui lòng nhập Mức Lương";
        }
        else{
            try{
                luong = Double.parseDouble(luongString);
            }
            catch(NumberFormatException e){
                error += "\n- Vui lòng nhập Mức Lương hợp lệ.";
            }
        }
        
        if(diaChi.equals("Địa Chỉ"))
            error += "\n- Vui lòng nhập Địa Chỉ.";
        
        if(matKhau.equals(""))
            error += "\n- Vui lòng nhập Mật Khẩu.";
        
        if(error.equals("")){
            NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, soDienThoai, diaChi, chucVu, ngaySinh, canCuocCongDan, gioiTinh, ngayBatDauLam, ngayKetThucLam, luong, tenDangNhap, matKhau);
            if(DAO_NhanVien.createNhanVien(nhanVien) == true){
                JOptionPane.showMessageDialog(null, "Thêm Nhân Viên thành công.");
                PnlMain.getInstance().showPanel(newInstance());
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm Nhân Viên thất bại.");
            }
        }
        else{
            String throwMessage = "Lỗi: " + error;
            JOptionPane.showMessageDialog(null, throwMessage);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTable = new javax.swing.JPanel();
        scrTable = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        pnlCenter = new javax.swing.JPanel();
        pnlDangNhap = new javax.swing.JPanel();
        lblTenDangNhap = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        txtMauKhau = new javax.swing.JPasswordField();
        pnlNhanVien = new javax.swing.JPanel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        cmbChucVu = new javax.swing.JComboBox<>();
        txtLuong = new javax.swing.JTextField();
        lblLuong = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnlTable.setPreferredSize(new java.awt.Dimension(1066, 350));
        pnlTable.setLayout(new java.awt.BorderLayout());

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Họ Tên", "Căn Cước Công Dân", "Số Điện Thoại", "Ngày Sinh", "Giới Tính", "Chức Vụ", "Mức Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTable.setRowHeight(40);
        scrTable.setViewportView(tblTable);

        pnlTable.add(scrTable, java.awt.BorderLayout.CENTER);

        add(pnlTable, java.awt.BorderLayout.NORTH);

        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlDangNhap.setBackground(new java.awt.Color(68, 136, 255));
        pnlDangNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Đăng Nhập", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDangNhap.setPreferredSize(new java.awt.Dimension(400, 350));

        lblTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTenDangNhap.setText("Tên Đăng Nhập");

        txtTenDangNhap.setBackground(new java.awt.Color(204, 204, 204));
        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblMatKhau.setText("Mật Khẩu");

        txtMauKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlDangNhapLayout = new javax.swing.GroupLayout(pnlDangNhap);
        pnlDangNhap.setLayout(pnlDangNhapLayout);
        pnlDangNhapLayout.setHorizontalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMauKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        pnlDangNhapLayout.setVerticalGroup(
            pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangNhapLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMauKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(199, Short.MAX_VALUE))
        );

        pnlCenter.add(pnlDangNhap, java.awt.BorderLayout.WEST);

        pnlNhanVien.setBackground(new java.awt.Color(68, 136, 255));
        pnlNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Nhân Viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setText("Họ Tên");
        txtHoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoTenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoTenFocusLost(evt);
            }
        });

        txtCCCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCCCD.setText("Căn Cước Công Dân");
        txtCCCD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCCCDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCCCDFocusLost(evt);
            }
        });

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoai.setText("Số Điện Thoại");
        txtSoDienThoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiFocusLost(evt);
            }
        });

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgaySinh.setText("Ngày Sinh (DD/MM/YYYY)");
        txtNgaySinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgaySinhFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgaySinhFocusLost(evt);
            }
        });

        cmbGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giới Tính", "Nam", "Nữ" }));

        cmbChucVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức Vụ" }));

        txtLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLuong.setText("Mức Lương");
        txtLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLuongFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLuongFocusLost(evt);
            }
        });

        lblLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblLuong.setText("đ / giờ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setText("Địa Chỉ");
        txtDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusLost(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(170, 238, 255));
        btnThem.setText("Thêm Nhân Viên");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(170, 238, 255));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlNhanVienLayout.createSequentialGroup()
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlNhanVienLayout.createSequentialGroup()
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlNhanVienLayout.createSequentialGroup()
                            .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cmbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDiaChi))
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlCenter.add(pnlNhanVien, java.awt.BorderLayout.CENTER);

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themNhanVien();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        PnlMain.getInstance().showPanel(newInstance());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtHoTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtHoTen, "Họ Tên");
    }//GEN-LAST:event_txtHoTenFocusGained

    private void txtCCCDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCCDFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtCCCD, "Căn Cước Công Dân");
    }//GEN-LAST:event_txtCCCDFocusGained

    private void txtSoDienThoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusGained

    private void txtNgaySinhFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgaySinhFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtNgaySinh, "Ngày Sinh (DD/MM/YYYY)");
    }//GEN-LAST:event_txtNgaySinhFocusGained

    private void txtLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLuongFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtLuong, "Mức Lương");
    }//GEN-LAST:event_txtLuongFocusGained

    private void txtDiaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtDiaChi, "Địa Chỉ");
    }//GEN-LAST:event_txtDiaChiFocusGained

    private void txtHoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtHoTen, "Họ Tên");
    }//GEN-LAST:event_txtHoTenFocusLost

    private void txtCCCDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCCDFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtCCCD, "Căn Cước Công Dân");
    }//GEN-LAST:event_txtCCCDFocusLost

    private void txtSoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusLost

    private void txtNgaySinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgaySinhFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtNgaySinh, "Ngày Sinh (DD/MM/YYYY)");
    }//GEN-LAST:event_txtNgaySinhFocusLost

    private void txtLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLuongFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtLuong, "Mức Lương");
    }//GEN-LAST:event_txtLuongFocusLost

    private void txtDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtDiaChi, "Địa Chỉ");
    }//GEN-LAST:event_txtDiaChiFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cmbChucVu;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDangNhap;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JPasswordField txtMauKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables

}
