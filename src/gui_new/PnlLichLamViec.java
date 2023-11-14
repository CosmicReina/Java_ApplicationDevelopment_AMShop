package gui_new;

import dao.DAO_CaLamViec;
import gui.*;
import dao.DAO_ChiTietPhanCong;
import dao.DAO_LichLamViec;
import dao.DAO_NhanVien;
import data.FormatDate;
import data.GenerateID;
import data.UtilityJTextField;
import data.UtilityLocalDateTime;
import entity.CaLamViec;
import entity.ChiTietPhanCong;
import entity.LichLamViec;
import entity.NhanVien;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
        initExtra();
    }
    
    private void initExtra(){
        showTableListLichLamViec(DAO_LichLamViec.getAllLichLamViec());
        
        ArrayList<CaLamViec> listCaLamViec = DAO_CaLamViec.getAllCaLamViec();
        for(CaLamViec thisCaLamViec : listCaLamViec){
            cmbCaLamViec.addItem(thisCaLamViec.getTenCaLamViec());
        }
    }

    private void showTableListLichLamViec(ArrayList<LichLamViec> list){
        DefaultTableModel model = (DefaultTableModel) tblDanhSachLichLamViec.getModel();
        model.getDataVector().removeAllElements();
        tblDanhSachLichLamViec.revalidate();
        tblDanhSachLichLamViec.repaint();
        for(LichLamViec thisLichLamViec : list){
            model.addRow(new Object[]{
                thisLichLamViec.getMaLichLamViec(),
                thisLichLamViec.getCaLamViec().getTenCaLamViec(),
                thisLichLamViec.getNgayLamViec()
            });
        }
    }    

    private void showTableListNhanVien(ArrayList<NhanVien> list){
        DefaultTableModel model = (DefaultTableModel) tblDanhSachNhanVien.getModel();
        model.getDataVector().removeAllElements();
        tblDanhSachNhanVien.revalidate();
        tblDanhSachNhanVien.repaint();
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
        model.getDataVector().removeAllElements();
        tblDanhSachNhanVienTrongCa.revalidate();
        tblDanhSachNhanVienTrongCa.repaint();
        for(ChiTietPhanCong thisChiTietPhanCong : list){
            model.addRow(new Object[]{
                thisChiTietPhanCong.getNhanVien().getMaNhanVien(),
                thisChiTietPhanCong.getNhanVien().getHoTen(),
                UtilityLocalDateTime.toFormattedLocalDateTime(thisChiTietPhanCong.getThoiGianVaoCa()),
                UtilityLocalDateTime.toFormattedLocalDateTime(thisChiTietPhanCong.getThoiGianRaCa())
            });
        }
    }    
    
    private void themLichLamViec(){
        String ngayLamViecString = txtNgayLamViec.getText();
        String caLamViecString = cmbCaLamViec.getSelectedItem().toString();
        
        String error = "";
        
        LocalDate ngayLamViec = null;
        if(ngayLamViecString.equals("")) // Kiểm tra rỗng
            error += "\n- Vui lòng nhập Ngày Làm Việc.";
        else
            try{
                ngayLamViec = FormatDate.toLocalDate(ngayLamViecString); // Kiểm tra chuyển đổi
                if(ngayLamViec.isBefore(LocalDate.now())){
                    error += "\n- Vui lòng nhập Ngày Làm Việc không bé hơn ngày hiện tại.";
                }
            }
            catch(Exception e){
                error += "\n- Vui lòng nhập Ngày Làm Việc hợp lệ (DD/MM/YYYY).";
            }
        
        if(caLamViecString.equals("Ca Làm Việc"))
            error += "\n- Vui lòng chọn Ca Làm Việc.";
        
        if(error.equals("")){
            int maCaLamViec;
            String charCaLamViec;
            if(caLamViecString.equals("Ca Sáng")){
                maCaLamViec = 1;
                charCaLamViec = "S";
            }
            else{
                maCaLamViec = 2;
                charCaLamViec = "C";
            }
            
            String maLichlamViec = GenerateID.generateMaLichLamViec(ngayLamViec, charCaLamViec);
            CaLamViec caLamViec = DAO_CaLamViec.getCaLamViecTheoMaCaLamViec(maCaLamViec);
            LichLamViec lichLamViec = new LichLamViec(maLichlamViec, ngayLamViec, caLamViec);
            
            ArrayList<LichLamViec> list = DAO_LichLamViec.getAllLichLamViec();
            if(list.contains(lichLamViec)){
                JOptionPane.showMessageDialog(null, "Lịch Làm Việc đã tồn tại.");
                return;
            }
            
            if(DAO_LichLamViec.createLichLamViec(lichLamViec)){
                JOptionPane.showMessageDialog(null, "Thêm Lịch Thành Công.");
                PnlMain.getInstance().showPanel(newInstance());
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm Lịch Thất Bại.");
            }
        }
        else{
            String throwMessage = "Lỗi nhập liệu: " + error;
            JOptionPane.showMessageDialog(null, throwMessage);
        }
    }

    private void updateDanhSachNhanVien(){
        int i = tblDanhSachLichLamViec.getSelectedRow();
        if(i < 0) return;
        String maLichLamViec = tblDanhSachLichLamViec.getValueAt(i, 0).toString();
        updateSauCapNhat(maLichLamViec);
        
        LichLamViec lichLamViec = DAO_LichLamViec.getLichLamViecTheoMaLichLamViec(maLichLamViec);
        if(lichLamViec.getNgayLamViec().isBefore(LocalDate.now())){
            btnThemNhanVien.setEnabled(false);
            btnXoaNhanVienKhoiCaLam.setEnabled(false);
            btnChamCongVao.setEnabled(false);
            btnChamCongRa.setEnabled(false);
        }
        else{
            btnThemNhanVien.setEnabled(true);
            btnXoaNhanVienKhoiCaLam.setEnabled(true);
            btnChamCongVao.setEnabled(true);
            btnChamCongRa.setEnabled(true);
        }
    }
    
    private void themNhanVienVaoLich(){
        int i = tblDanhSachNhanVien.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Nhân Viên để thêm.");
            return;
        }
        
        String maNhanVien = tblDanhSachNhanVien.getValueAt(i, 0).toString();
        NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
        
        int y = tblDanhSachLichLamViec.getSelectedRow();
        if(y < 0) return;
        
        String maLichLamViec = tblDanhSachLichLamViec.getValueAt(y, 0).toString();
        LichLamViec lichLamViec = DAO_LichLamViec.getLichLamViecTheoMaLichLamViec(maLichLamViec);
        
        ChiTietPhanCong chiTietPhanCong = new ChiTietPhanCong(lichLamViec, nhanVien, null, null);
        
        if(DAO_ChiTietPhanCong.createChiTietPhanCong(chiTietPhanCong) == true){
            updateSauCapNhat(maLichLamViec);
        }
        else{
            JOptionPane.showMessageDialog(null, "Thêm Nhân Viên vào Lịch thất bại.");
        }
    }
    
    private void xoaNhanVienKhoiLich(){
        int i = tblDanhSachNhanVienTrongCa.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Nhân Viên cần xóa.");
            return;
        }
        
        String maNhanVien = tblDanhSachNhanVienTrongCa.getValueAt(i, 0).toString();
        NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
        
        int y = tblDanhSachLichLamViec.getSelectedRow();
        if(y < 0) return;
        
        String maLichLamViec = tblDanhSachLichLamViec.getValueAt(y, 0).toString();
        LichLamViec lichLamViec = DAO_LichLamViec.getLichLamViecTheoMaLichLamViec(maLichLamViec);
        
        ChiTietPhanCong chiTietPhanCong = new ChiTietPhanCong(lichLamViec, nhanVien, null, null);
        
        if(DAO_ChiTietPhanCong.removeChiTietPhanCong(chiTietPhanCong)){
            updateSauCapNhat(maLichLamViec);
        }
        else{
            JOptionPane.showMessageDialog(null, "Xóa Nhân Viên khỏi Lịch thất bại.");
        }
        
    }
    
    private void updateSauCapNhat(String maLichLamViec){
        ArrayList<ChiTietPhanCong> listPC = DAO_ChiTietPhanCong.getAllChiTietPhanCongTheoMaLichLamViec(maLichLamViec);
        ArrayList<NhanVien> listNhanVienDaThem = new ArrayList<>();
        for(ChiTietPhanCong thisChiTietPhanCong : listPC){
            listNhanVienDaThem.add(thisChiTietPhanCong.getNhanVien());
        }
        ArrayList<NhanVien> listNhanVienChuaThem = DAO_NhanVien.getAllNhanVien();
        listNhanVienChuaThem.removeAll(listNhanVienDaThem);

        showTableListNhanVienTrongCa(maLichLamViec);
        showTableListNhanVien(listNhanVienChuaThem);
    }
    
    private void chamCongVaoCa(){
        int i = tblDanhSachNhanVienTrongCa.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Nhân Viên cần xóa.");
            return;
        }
        String maNhanVien = tblDanhSachNhanVienTrongCa.getValueAt(i, 0).toString();
        NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
        
        int y = tblDanhSachLichLamViec.getSelectedRow();
        if(y < 0) return;
        String maLichLamViec = tblDanhSachLichLamViec.getValueAt(y, 0).toString();
        LichLamViec lichLamViec = DAO_LichLamViec.getLichLamViecTheoMaLichLamViec(maLichLamViec);
        
        ChiTietPhanCong chiTietPhanCong = new ChiTietPhanCong(lichLamViec, nhanVien, null, null);
        ArrayList<ChiTietPhanCong> listPC = DAO_ChiTietPhanCong.getAllChiTietPhanCongTheoMaLichLamViec(maLichLamViec);
        
        ChiTietPhanCong chiTietPhanCongUpdate = null;
        if(listPC.contains(chiTietPhanCong))
            chiTietPhanCongUpdate = listPC.get(listPC.indexOf(chiTietPhanCong));
        if(chiTietPhanCongUpdate == null) return;
        
        LocalDateTime thoiGianVaoCa = LocalDateTime.now();
        chiTietPhanCongUpdate.setThoiGianVaoCa(thoiGianVaoCa);

        if(DAO_ChiTietPhanCong.updateChiTietPhanCong(chiTietPhanCongUpdate) == true){
            updateSauCapNhat(maLichLamViec);
        }
    }
    
    private void chamCongRaCa(){
        int i = tblDanhSachNhanVienTrongCa.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Nhân Viên cần xóa.");
            return;
        }
        String maNhanVien = tblDanhSachNhanVienTrongCa.getValueAt(i, 0).toString();
        NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
        
        int y = tblDanhSachLichLamViec.getSelectedRow();
        if(y < 0) return;
        String maLichLamViec = tblDanhSachLichLamViec.getValueAt(y, 0).toString();
        LichLamViec lichLamViec = DAO_LichLamViec.getLichLamViecTheoMaLichLamViec(maLichLamViec);
        
        ChiTietPhanCong chiTietPhanCong = new ChiTietPhanCong(lichLamViec, nhanVien, null, null);
        ArrayList<ChiTietPhanCong> listPC = DAO_ChiTietPhanCong.getAllChiTietPhanCongTheoMaLichLamViec(maLichLamViec);
        
        ChiTietPhanCong chiTietPhanCongUpdate = null;
        if(listPC.contains(chiTietPhanCong))
            chiTietPhanCongUpdate = listPC.get(listPC.indexOf(chiTietPhanCong));
        if(chiTietPhanCongUpdate == null) return;
        
        LocalDateTime thoiGianRaCa = LocalDateTime.now();
        chiTietPhanCongUpdate.setThoiGianRaCa(thoiGianRaCa);

        if(DAO_ChiTietPhanCong.updateChiTietPhanCong(chiTietPhanCongUpdate) == true){
            updateSauCapNhat(maLichLamViec);
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
        pnlTimKiemNhanVien = new javax.swing.JPanel();
        btnThemNhanVien = new javax.swing.JButton();
        btnXoaNhanVienKhoiCaLam = new javax.swing.JButton();

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
        tblDanhSachLichLamViec.setRowHeight(40);
        tblDanhSachLichLamViec.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDanhSachLichLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachLichLamViecMouseClicked(evt);
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
        tblDanhSachNhanVienTrongCa.setRowHeight(40);
        tblDanhSachNhanVienTrongCa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrDanhSachNhanVienTrongCa.setViewportView(tblDanhSachNhanVienTrongCa);

        pnlDanhSachNhanVienTrongCa.add(scrDanhSachNhanVienTrongCa, java.awt.BorderLayout.CENTER);

        pnlDanhSachLichVaNhanVien.add(pnlDanhSachNhanVienTrongCa, java.awt.BorderLayout.CENTER);

        pnlLichLamViec.add(pnlDanhSachLichVaNhanVien, java.awt.BorderLayout.NORTH);

        pnlCapNhatLichLamViec.setPreferredSize(new java.awt.Dimension(900, 350));
        pnlCapNhatLichLamViec.setLayout(new java.awt.BorderLayout());

        pnlCapNhatCaLam.setBackground(new java.awt.Color(79, 137, 255));
        pnlCapNhatCaLam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm Lịch Làm Việc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlCapNhatCaLam.setPreferredSize(new java.awt.Dimension(500, 350));

        lblNgayLamViec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNgayLamViec.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayLamViec.setText("Ngày Làm Việc");

        lblCaLamViec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCaLamViec.setForeground(new java.awt.Color(255, 255, 255));
        lblCaLamViec.setText("Ca Làm Việc");

        txtNgayLamViec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cmbCaLamViec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbCaLamViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca Làm Việc" }));

        btnThem.setBackground(new java.awt.Color(170, 238, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setText("Thêm Lịch");
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
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayLamViec)
                    .addComponent(cmbCaLamViec, 0, 200, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        pnlCapNhatCaLamLayout.setVerticalGroup(
            pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatCaLamLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblNgayLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlCapNhatCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCaLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(cmbCaLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pnlCapNhatLichLamViec.add(pnlCapNhatCaLam, java.awt.BorderLayout.WEST);

        pnlDanhSachNhanVien.setBackground(new java.awt.Color(79, 137, 255));
        pnlDanhSachNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Nhân Viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDanhSachNhanVien.setPreferredSize(new java.awt.Dimension(400, 350));
        pnlDanhSachNhanVien.setLayout(new java.awt.BorderLayout());

        tblDanhSachNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        tblDanhSachNhanVien.setRowHeight(40);
        tblDanhSachNhanVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrDanhSachNhanVien.setViewportView(tblDanhSachNhanVien);

        pnlDanhSachNhanVien.add(scrDanhSachNhanVien, java.awt.BorderLayout.CENTER);

        pnlCapNhatLichLamViec.add(pnlDanhSachNhanVien, java.awt.BorderLayout.CENTER);

        pnlLichLamViec.add(pnlCapNhatLichLamViec, java.awt.BorderLayout.CENTER);

        add(pnlLichLamViec, java.awt.BorderLayout.WEST);

        pnlChamCong.setLayout(new java.awt.BorderLayout());

        pnlChamCongCaLam.setBackground(new java.awt.Color(79, 137, 255));
        pnlChamCongCaLam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chấm Công Ca Làm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlChamCongCaLam.setPreferredSize(new java.awt.Dimension(266, 350));

        btnChamCongVao.setBackground(new java.awt.Color(170, 238, 255));
        btnChamCongVao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChamCongVao.setText("Chấm Công Vào Ca");
        btnChamCongVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongVaoActionPerformed(evt);
            }
        });

        btnChamCongRa.setBackground(new java.awt.Color(170, 238, 255));
        btnChamCongRa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChamCongRa.setText("Chấm Công Ra Ca");
        btnChamCongRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongRaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChamCongCaLamLayout = new javax.swing.GroupLayout(pnlChamCongCaLam);
        pnlChamCongCaLam.setLayout(pnlChamCongCaLamLayout);
        pnlChamCongCaLamLayout.setHorizontalGroup(
            pnlChamCongCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamCongCaLamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChamCongCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChamCongRa, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChamCongVao, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        pnlChamCongCaLamLayout.setVerticalGroup(
            pnlChamCongCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamCongCaLamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnChamCongVao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChamCongRa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        pnlChamCong.add(pnlChamCongCaLam, java.awt.BorderLayout.NORTH);

        pnlTimKiemNhanVien.setBackground(new java.awt.Color(79, 137, 255));
        pnlTimKiemNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chỉnh Sửa Ca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiemNhanVien.setPreferredSize(new java.awt.Dimension(266, 350));

        btnThemNhanVien.setBackground(new java.awt.Color(170, 238, 255));
        btnThemNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThemNhanVien.setText("Thêm Nhân Viên Vào Ca");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVienKhoiCaLam.setBackground(new java.awt.Color(170, 238, 255));
        btnXoaNhanVienKhoiCaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoaNhanVienKhoiCaLam.setText("Xóa Nhân Viên Khỏi Ca");
        btnXoaNhanVienKhoiCaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienKhoiCaLamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemNhanVienLayout = new javax.swing.GroupLayout(pnlTimKiemNhanVien);
        pnlTimKiemNhanVien.setLayout(pnlTimKiemNhanVienLayout);
        pnlTimKiemNhanVienLayout.setHorizontalGroup(
            pnlTimKiemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimKiemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(btnXoaNhanVienKhoiCaLam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTimKiemNhanVienLayout.setVerticalGroup(
            pnlTimKiemNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaNhanVienKhoiCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        pnlChamCong.add(pnlTimKiemNhanVien, java.awt.BorderLayout.CENTER);

        add(pnlChamCong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themLichLamViec();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaNhanVienKhoiCaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienKhoiCaLamActionPerformed
        // TODO add your handling code here:
        xoaNhanVienKhoiLich();
    }//GEN-LAST:event_btnXoaNhanVienKhoiCaLamActionPerformed

    private void tblDanhSachLichLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachLichLamViecMouseClicked
        // TODO add your handling code here:
        updateDanhSachNhanVien();
    }//GEN-LAST:event_tblDanhSachLichLamViecMouseClicked

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        themNhanVienVaoLich();
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnChamCongVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongVaoActionPerformed
        // TODO add your handling code here:
        chamCongVaoCa();
    }//GEN-LAST:event_btnChamCongVaoActionPerformed

    private void btnChamCongRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongRaActionPerformed
        // TODO add your handling code here:
        chamCongRaCa();
    }//GEN-LAST:event_btnChamCongRaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChamCongRa;
    private javax.swing.JButton btnChamCongVao;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemNhanVien;
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
    // End of variables declaration//GEN-END:variables

}
