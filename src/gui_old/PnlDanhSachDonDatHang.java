package gui_old;

import dao.DAO_ChatLieu;
import dao.DAO_ChiTietDonDatHang;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_CuaHang;
import dao.DAO_DanhMuc;
import dao.DAO_DonDatHang;
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
import data.UtilityLocalDateTime;
import entity.ChiTietDonDatHang;
import entity.ChiTietHoaDon;
import entity.CuaHang;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.QuanAo;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class PnlDanhSachDonDatHang extends javax.swing.JPanel {
    
    private static PnlDanhSachDonDatHang instance = new PnlDanhSachDonDatHang();
    
    private final ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
    
    private ArrayList<ChiTietDonDatHang> listChiTietDonDatHang = new ArrayList<>();
    
    private double tongTien = 0;
    
    private DonDatHang donDatHangHienTai = null;

    public static PnlDanhSachDonDatHang getInstance() {
        return instance;
    }
    
    public static PnlDanhSachDonDatHang newInstance() {
        instance = new PnlDanhSachDonDatHang();
        return instance;
    }

    public static void setInstance(PnlDanhSachDonDatHang instance) {
        PnlDanhSachDonDatHang.instance = instance;
    }

    public PnlDanhSachDonDatHang() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        updateTableDanhSachDonDatHang(DAO_DonDatHang.getAllDonDatHang());
        
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoaiTimKiem);
        UtilityJTextField.addPlaceHolderStyle(txtHoTen);
        UtilityJTextField.addPlaceHolderStyle(txtSoDienThoai);
        UtilityJTextField.addPlaceHolderStyle(txtDiaChi);

        txtTienDua.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTienThua();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTienThua();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTienThua();
            }
        });
        
    }
    
    private void thanhToanChoDonHang(){
        String error = "";
        
        if(donDatHangHienTai == null){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một Đơn Đặt Hàng để thanh toán.");
            return;
        }
        
        String maHoaDon = GenerateID.generateMaHoaDon();
        
        String hoTen = txtHoTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = txtDiaChi.getText();
        
        String tienKhachDuaString = txtTienDua.getText();
        double tienKhachDua = 0;
        
        if(tienKhachDuaString.equals(""))
            error += "\n- Vui lòng nhập Tiền Khách Đưa.";
        else{
            try{
                tienKhachDua = Double.parseDouble(tienKhachDuaString);
                if(tienKhachDua < tongTien){
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
        NhanVien nhanVien = DAO_NhanVien.nhanVienHienTai;
        LocalDateTime thoiGianTao = LocalDateTime.now();
        

        HoaDon hoaDon = new HoaDon(maHoaDon, cuaHang, nhanVien, khachHang, thoiGianTao, tienKhachDua);

        boolean themHoaDon = DAO_HoaDon.createHoaDon(hoaDon);

        for(ChiTietDonDatHang thisChiTietDonDatHang : listChiTietDonDatHang){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(
                    hoaDon, 
                    thisChiTietDonDatHang.getQuanAo(), 
                    thisChiTietDonDatHang.getSoLuong(), 
                    thisChiTietDonDatHang.getQuanAo().getDonGiaBan());
            listChiTietHoaDon.add(chiTietHoaDon);
            DAO_ChiTietHoaDon.createChiTietHoaDon(chiTietHoaDon);
        }
        
        if(themHoaDon){
            try {
                GenerateInvoice.createAMShopInvoice(hoaDon, listChiTietHoaDon, tongTien, tienKhachDua);
                donDatHangHienTai.setTrangThaiThanhToan(true);
                DAO_DonDatHang.updateDonDatHang(donDatHangHienTai);
                JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công.");
                PnlMain.getInstance().showPanel(newInstance());
                
                PnlMain.getInstance().showPanel(PnlChiTietHoaDon.newInstance());
                PnlChiTietHoaDon.getInstance().showThongTinHoaDon(maHoaDon);
                PnlChiTietHoaDon.getInstance().setPnlBefore(newInstance());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Gặp Lỗi Khi In Hóa Đơn.");
            }
        }
    }
    
    private void updateTienThua(){
        if(listChiTietDonDatHang.isEmpty()){
            txtTienThua.setText("Đơn hàng trống");
            txtTienThua.setForeground(Color.RED);
            return;
        }
        String tienDuaString = txtTienDua.getText();
        double tienDua;
        try{
            tienDua = Double.parseDouble(tienDuaString);
            double tienThua = tienDua - tongTien;
            txtTienThua.setText(FormatDouble.toMoney(tienThua));
            txtTienThua.setForeground(Color.BLACK);
        }
        catch(NumberFormatException e){
            txtTienThua.setText("Lỗi nhập tiền");
            txtTienThua.setForeground(Color.RED);
        }
    }
    
    private void updateTableDanhSachDonDatHang(ArrayList<DonDatHang> list){
        DefaultTableModel model = (DefaultTableModel) tblDonDatHang.getModel();
        model.getDataVector().removeAllElements();
        tblDonDatHang.revalidate();
        tblDonDatHang.repaint();
        for(DonDatHang thisDonDatHang : list){
            if(thisDonDatHang.isTrangThaiThanhToan() == false){
                ArrayList<ChiTietDonDatHang> listCTDDH = DAO_ChiTietDonDatHang.getAllChiTietDonDatHangTheoMaDonDatHang(thisDonDatHang.getMaDonDatHang());
                double tongTienThanhPhan = 0;
                for(ChiTietDonDatHang thisChiTietDonDatHang : listCTDDH)
                    tongTienThanhPhan += thisChiTietDonDatHang.getSoLuong() * thisChiTietDonDatHang.getQuanAo().getDonGiaBan();
                model.addRow(new Object[]{
                    thisDonDatHang.getMaDonDatHang(),
                    thisDonDatHang.getNhanVien().getHoTen(),
                    thisDonDatHang.getKhachHang().getHoTen(),
                    thisDonDatHang.getKhachHang().getSoDienThoai(),
                    UtilityLocalDateTime.toFormattedLocalDateTime(thisDonDatHang.getThoiGianTao()),
                    FormatDouble.toMoney(tongTienThanhPhan)
                });
            }
        }
    }
    
    private void updateTableChiTietDonHang(){
        int i = tblDonDatHang.getSelectedRow();
        if(i < 0) return;
        String maDonDatHang = tblDonDatHang.getValueAt(i, 0).toString();
        DonDatHang donDatHang = DAO_DonDatHang.getDonDatHangTheoMaDonDatHang(maDonDatHang);
        donDatHangHienTai = donDatHang;
        ArrayList<ChiTietDonDatHang> list = DAO_ChiTietDonDatHang.getAllChiTietDonDatHangTheoMaDonDatHang(maDonDatHang);
        listChiTietDonDatHang = list;
        
        txtHoTen.setText(donDatHang.getKhachHang().getHoTen());
        txtSoDienThoai.setText(donDatHang.getKhachHang().getSoDienThoai());
        txtDiaChi.setText(donDatHang.getKhachHang().getDiaChi());
        
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        model.getDataVector().removeAllElements();
        tblDonHang.revalidate();
        tblDonHang.repaint();
        tongTien = 0;
        for(ChiTietDonDatHang thisChiTietDonDatHang : list){
            QuanAo quanAo = thisChiTietDonDatHang.getQuanAo();
            double giaThanhPhan = thisChiTietDonDatHang.getSoLuong() * quanAo.getDonGiaBan();
            tongTien += giaThanhPhan;
            model.addRow(new Object[]{
                quanAo.getMaQuanAo(),
                quanAo.getTenQuanAo(),
                thisChiTietDonDatHang.getSoLuong(),
                FormatDouble.toMoney(quanAo.getDonGiaBan()),
                FormatDouble.toMoney(giaThanhPhan)
            });
        }
        
        txtTongTien.setText(FormatDouble.toMoney(tongTien));
    }
    
    private void timKiemDonDatHang(){
        String soDienThoai = txtSoDienThoaiTimKiem.getText();
        if(soDienThoai.equals("Số Điện Thoại Khách Hàng")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Số Điện Thoại khách hàng.");
        }
        
        ArrayList<DonDatHang> list = DAO_DonDatHang.getAllDonDatHang();
        ArrayList<DonDatHang> listRemove = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            DonDatHang thisDonDatHang = list.get(i);
            String soDienThoaiKhachHang = thisDonDatHang.getKhachHang().getSoDienThoai();
            if(!soDienThoaiKhachHang.equals(soDienThoai))
                listRemove.add(thisDonDatHang);
        }
        list.removeAll(listRemove);
        updateTableDanhSachDonDatHang(list);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimKiem = new javax.swing.JPanel();
        txtSoDienThoaiTimKiem = new javax.swing.JTextField();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JButton();
        pnlLapHoaDon = new javax.swing.JPanel();
        pnlDonDatHang = new javax.swing.JPanel();
        scrDonDatHang = new javax.swing.JScrollPane();
        tblDonDatHang = new javax.swing.JTable();
        pnlHoaDon = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        scrDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        pnlThongTinThanhToan = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        pnlThanhToan = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        lblTienDua = new javax.swing.JLabel();
        txtTienDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnlTimKiem.setBackground(new java.awt.Color(68, 136, 255));
        pnlTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTimKiem.setPreferredSize(new java.awt.Dimension(250, 700));

        txtSoDienThoaiTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoaiTimKiem.setText("Số Điện Thoại Khách Hàng");
        txtSoDienThoaiTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoDienThoaiTimKiemFocusLost(evt);
            }
        });

        pnlHinhAnh.setBackground(new java.awt.Color(204, 204, 204));
        pnlHinhAnh.setPreferredSize(new java.awt.Dimension(196, 270));
        pnlHinhAnh.setLayout(new java.awt.GridBagLayout());

        lblHinhAnh.setText("IMG");
        pnlHinhAnh.add(lblHinhAnh, new java.awt.GridBagConstraints());

        txtTimKiem.setBackground(new java.awt.Color(170, 238, 255));
        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setText("Tìm Kiếm");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemLayout = new javax.swing.GroupLayout(pnlTimKiem);
        pnlTimKiem.setLayout(pnlTimKiemLayout);
        pnlTimKiemLayout.setHorizontalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoDienThoaiTimKiem))))
                .addContainerGap())
        );
        pnlTimKiemLayout.setVerticalGroup(
            pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemLayout.createSequentialGroup()
                .addComponent(txtSoDienThoaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.EAST);

        pnlLapHoaDon.setLayout(new java.awt.BorderLayout());

        pnlDonDatHang.setPreferredSize(new java.awt.Dimension(916, 300));
        pnlDonDatHang.setLayout(new java.awt.BorderLayout());

        tblDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Đơn Đặt Hàng", "Nhân Viên Lập Đơn", "Tên Khách Hàng", "Số Điện Thoại Khách Hàng", "Thời Gian Lập Đơn", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonDatHang.setRowHeight(40);
        tblDonDatHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDonDatHang.getTableHeader().setReorderingAllowed(false);
        tblDonDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonDatHangMouseClicked(evt);
            }
        });
        scrDonDatHang.setViewportView(tblDonDatHang);
        if (tblDonDatHang.getColumnModel().getColumnCount() > 0) {
            tblDonDatHang.getColumnModel().getColumn(0).setResizable(false);
            tblDonDatHang.getColumnModel().getColumn(1).setResizable(false);
            tblDonDatHang.getColumnModel().getColumn(2).setResizable(false);
            tblDonDatHang.getColumnModel().getColumn(3).setResizable(false);
            tblDonDatHang.getColumnModel().getColumn(4).setResizable(false);
            tblDonDatHang.getColumnModel().getColumn(5).setResizable(false);
        }

        pnlDonDatHang.add(scrDonDatHang, java.awt.BorderLayout.CENTER);

        pnlLapHoaDon.add(pnlDonDatHang, java.awt.BorderLayout.NORTH);

        pnlHoaDon.setLayout(new java.awt.BorderLayout());

        pnlDonHang.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlDonHang.setLayout(new java.awt.BorderLayout());

        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Quần Áo", "Tên Quần Áo", "Số Lượng", "Đơn Giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonHang.setRowHeight(40);
        tblDonHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDonHang.getTableHeader().setReorderingAllowed(false);
        scrDonHang.setViewportView(tblDonHang);
        if (tblDonHang.getColumnModel().getColumnCount() > 0) {
            tblDonHang.getColumnModel().getColumn(0).setResizable(false);
            tblDonHang.getColumnModel().getColumn(1).setResizable(false);
            tblDonHang.getColumnModel().getColumn(2).setResizable(false);
            tblDonHang.getColumnModel().getColumn(3).setResizable(false);
            tblDonHang.getColumnModel().getColumn(4).setResizable(false);
        }

        pnlDonHang.add(scrDonHang, java.awt.BorderLayout.CENTER);

        pnlHoaDon.add(pnlDonHang, java.awt.BorderLayout.EAST);

        pnlThongTinThanhToan.setLayout(new java.awt.BorderLayout());

        pnlKhachHang.setBackground(new java.awt.Color(68, 136, 255));
        pnlKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách Hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlKhachHang.setPreferredSize(new java.awt.Dimension(516, 150));

        txtHoTen.setEditable(false);
        txtHoTen.setBackground(new java.awt.Color(204, 204, 204));
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setText("Họ Tên");

        txtSoDienThoai.setEditable(false);
        txtSoDienThoai.setBackground(new java.awt.Color(204, 204, 204));
        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoai.setText("Số Điện Thoại");

        txtDiaChi.setEditable(false);
        txtDiaChi.setBackground(new java.awt.Color(204, 204, 204));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setText("Địa Chỉ");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(txtHoTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pnlThongTinThanhToan.add(pnlKhachHang, java.awt.BorderLayout.PAGE_START);

        pnlThanhToan.setBackground(new java.awt.Color(68, 136, 255));
        pnlThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh Toán", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTien.setText("Tổng Tiền");

        txtTongTien.setBackground(new java.awt.Color(204, 204, 204));
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTienDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTienDua.setForeground(new java.awt.Color(255, 255, 255));
        lblTienDua.setText("Tiền Khách Đưa");

        txtTienDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 255, 255));
        lblTienThua.setText("Tiền Thừa");

        txtTienThua.setBackground(new java.awt.Color(204, 204, 204));
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThanhToan.setBackground(new java.awt.Color(170, 238, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 65, Short.MAX_VALUE))
        );

        pnlThongTinThanhToan.add(pnlThanhToan, java.awt.BorderLayout.CENTER);

        pnlHoaDon.add(pnlThongTinThanhToan, java.awt.BorderLayout.CENTER);

        pnlLapHoaDon.add(pnlHoaDon, java.awt.BorderLayout.CENTER);

        add(pnlLapHoaDon, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiTimKiemFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoaiTimKiem, "Số Điện Thoại Khách Hàng");
    }//GEN-LAST:event_txtSoDienThoaiTimKiemFocusGained

    private void txtSoDienThoaiTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiTimKiemFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoDienThoaiTimKiem, "Số Điện Thoại Khách Hàng");
    }//GEN-LAST:event_txtSoDienThoaiTimKiemFocusLost

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        thanhToanChoDonHang();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblDonDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonDatHangMouseClicked
        // TODO add your handling code here:
        updateTableChiTietDonHang();
    }//GEN-LAST:event_tblDonDatHangMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemDonDatHang();
    }//GEN-LAST:event_txtTimKiemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblTienDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlDonDatHang;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlLapHoaDon;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JPanel pnlThongTinThanhToan;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrDonDatHang;
    private javax.swing.JScrollPane scrDonHang;
    private javax.swing.JTable tblDonDatHang;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoDienThoaiTimKiem;
    private javax.swing.JTextField txtTienDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JButton txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
