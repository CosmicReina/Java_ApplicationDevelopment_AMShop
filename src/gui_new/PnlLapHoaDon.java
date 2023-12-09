package gui_new;

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

public class PnlLapHoaDon extends javax.swing.JPanel {
    
    private static PnlLapHoaDon instance = new PnlLapHoaDon();
    
    private double tongTien = 0;
    
    private final ArrayList<ChiTietHoaDon> listDonHang = new ArrayList<>();

    public static PnlLapHoaDon getInstance() {
        return instance;
    }
    
    public static PnlLapHoaDon newInstance() {
        instance = new PnlLapHoaDon();
        return instance;
    }

    public static void setInstance(PnlLapHoaDon instance) {
        PnlLapHoaDon.instance = instance;
    }

    public PnlLapHoaDon() {
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
                FormatDouble.toMoney(thisChiTietHoaDon.getDonGia()),
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
        int soLuong;
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
            int soLuongNew = cthd.getSoLuong() + chiTietHoaDon.getSoLuong();
            if(soLuongNew > quanAo.getSoLuongTrongKho()){
                JOptionPane.showMessageDialog(null, "Số Lượng Nhập vượt quá số lượng trong kho.");
                return;
            }
            else{
                cthd.setSoLuong(soLuongNew);
            }
        }
        else{
            if(soLuong > quanAo.getSoLuongTrongKho()){
                JOptionPane.showMessageDialog(null, "Số Lượng Nhập vượt quá số lượng trong kho.");
                return;
            }
            else{
                listDonHang.add(chiTietHoaDon);
            }
        }
        tblQuanAo.clearSelection();
        txtSoLuong.setText("");
        UtilityJTextField.focusLost(txtSoLuong, "Số Lượng");
        updateTableDonHang(listDonHang);
    }
    
    private void thanhToanChoDonHang(){
        String error = "";
        
        String maHoaDon = GenerateID.generateMaHoaDon();
        
        String hoTen = txtHoTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = txtDiaChi.getText();
        
        String tienKhachDuaString = txtTienDua.getText();
        double tienKhachDua = 0;
        
        if(listDonHang.isEmpty()){
            JOptionPane.showMessageDialog(null, "Không có Quần Áo trong đơn hàng.");
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
        NhanVien nhanVien = DAO_NhanVien.getNhanVienHienTai();
        LocalDateTime thoiGianTao = LocalDateTime.now();

        HoaDon hoaDon = new HoaDon(maHoaDon, cuaHang, nhanVien, khachHang, thoiGianTao, tienKhachDua);

        boolean themHoaDon = DAO_HoaDon.createHoaDon(hoaDon);

        for(ChiTietHoaDon thisChiTietHoaDon : listDonHang){
            thisChiTietHoaDon.setHoaDon(hoaDon);
            DAO_ChiTietHoaDon.createChiTietHoaDon(thisChiTietHoaDon);
            
            QuanAo quanAo = thisChiTietHoaDon.getQuanAo();
            quanAo.setSoLuongTrongKho(quanAo.getSoLuongTrongKho() - thisChiTietHoaDon.getSoLuong());
            DAO_QuanAo.updateQuanAo(quanAo);
        }
        
        if(themHoaDon){
            try {
                GenerateInvoice.createAMShopInvoice(hoaDon, listDonHang, tongTien, tienKhachDua);
                JOptionPane.showMessageDialog(null, "Thanh toán thành công.");
                PnlMain.getInstance().showPanel(newInstance());

                PnlMain.getInstance().showPanel(PnlChiTietHoaDon.newInstance());
                PnlChiTietHoaDon.getInstance().showThongTinHoaDon(maHoaDon);
                PnlChiTietHoaDon.getInstance().setPnlBefore(newInstance());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Gặp Lỗi Khi In Hóa Đơn.");
            }
        }
    }
    
    private void taoDonDatHang(){
        String error = "";
        
        String maDonDatHang = GenerateID.generateMaDonDatHang();
        
        String hoTen = txtHoTen.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String diaChi = txtDiaChi.getText();
        
        if(listDonHang.isEmpty()){
            JOptionPane.showMessageDialog(null, "Không có Quần Áo trong đơn hàng.");
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

        NhanVien nhanVien = DAO_NhanVien.getNhanVienHienTai();
        LocalDateTime thoiGianTao = LocalDateTime.now();

        DonDatHang donDatHang = new DonDatHang(maDonDatHang, nhanVien, khachHang, thoiGianTao, false);

        boolean themDonDatHang = DAO_DonDatHang.createDonDatHang(donDatHang);

        for(ChiTietHoaDon thisChiTietHoaDon : listDonHang){
            ChiTietDonDatHang chiTietDonDatHang = new ChiTietDonDatHang(donDatHang, thisChiTietHoaDon.getQuanAo(), thisChiTietHoaDon.getSoLuong());
            DAO_ChiTietDonDatHang.createChiTietDonDatHang(chiTietDonDatHang);
            
            QuanAo quanAo = thisChiTietHoaDon.getQuanAo();
            quanAo.setSoLuongTrongKho(quanAo.getSoLuongTrongKho() - thisChiTietHoaDon.getSoLuong());
            DAO_QuanAo.updateQuanAo(quanAo);
        }
        
        if(themDonDatHang == true){
            JOptionPane.showMessageDialog(null, "Tạo Đơn Đặt Hàng Thành Công");
            PnlMain.getInstance().showPanel(newInstance());
        }
        else{
            JOptionPane.showMessageDialog(null, "Tạo Đơn Đặt Hàng Thất Bại");
        }
    }
    
    private void xoaQuanAoKhoiDonHang(){
        int i = tblDonHang.getSelectedRow();
        if(i < 0){
            JOptionPane.showMessageDialog(null, "Vui lòng Chọn Quần Áo cần xóa.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        model.removeRow(i);
        tblDonHang.revalidate();
        tblDonHang.repaint();
    }
    
    private void timKiemQuanAo(){
        ArrayList<QuanAo> list = DAO_QuanAo.getAllQuanAo();
        ArrayList<QuanAo> listRemove = new ArrayList<>();
        
        if(!txtMaQuanAo.getText().equals("Mã Quần Áo")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getMaQuanAo().equals(txtMaQuanAo.getText()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!txtTenQuanAo.getText().equals("Tên Quần Áo")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getTenQuanAo().toLowerCase().contains(txtTenQuanAo.getText().toLowerCase()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!cmbNhaSanXuat.getSelectedItem().toString().equals("Nhà Sản Xuất")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getNhaSanXuat().equals(cmbNhaSanXuat.getSelectedItem().toString()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!cmbDanhMuc.getSelectedItem().toString().equals("Danh Mục")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getDanhMuc().equals(cmbDanhMuc.getSelectedItem().toString()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!cmbGioiTinh.getSelectedItem().toString().equals("Giới Tính")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getGioiTinh().equals(cmbGioiTinh.getSelectedItem().toString()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!cmbMauSac.getSelectedItem().toString().equals("Màu Sắc")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getMauSac().equals(cmbMauSac.getSelectedItem().toString()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!cmbKichThuoc.getSelectedItem().toString().equals("Kích Thước")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getKichThuoc().equals(cmbKichThuoc.getSelectedItem().toString()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        if(!cmbChatLieu.getSelectedItem().toString().equals("Chất Liệu")){
            for(int i = 0; i < list.size(); i ++){
                QuanAo thisQuanAo = list.get(i);
                if(!thisQuanAo.getChatLieu().equals(cmbChatLieu.getSelectedItem().toString()))
                    listRemove.add(thisQuanAo);
            }
        }
        
        list.removeAll(listRemove);
        updateTableQuanAo(list);
    }
    
    private void updateTienThua(){
        if(listDonHang.isEmpty()){
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
    
    private void timKiemSDT(){
        String soDienThoai = txtSoDienThoai.getText();
        KhachHang khachHang = DAO_KhachHang.getKhachHangTheoSoDienThoai(soDienThoai);
        if(khachHang == null){
            JOptionPane.showMessageDialog(null, "Không tồn tại Khách Hàng với Số Điện Thoại này.");
        }
        else{
            txtHoTen.setText(khachHang.getHoTen());
            txtDiaChi.setText(khachHang.getDiaChi());
            UtilityJTextField.removePlaceHolderStyle(txtHoTen);
            UtilityJTextField.removePlaceHolderStyle(txtDiaChi);
        }
    }
    
    private void lamMoiTimKiem(){
        txtMaQuanAo.setText("");
        txtTenQuanAo.setText("");
        cmbNhaSanXuat.setSelectedItem("Nhà Sản Xuất");
        cmbDanhMuc.setSelectedItem("Danh Mục");
        cmbGioiTinh.setSelectedItem("Giới Tính");
        cmbMauSac.setSelectedItem("Màu Sắc");
        cmbKichThuoc.setSelectedItem("Kích Thước");
        cmbChatLieu.setSelectedItem("Chất Liệu");
        
        UtilityJTextField.focusLost(txtMaQuanAo, "Mã Quần Áo");
        UtilityJTextField.focusLost(txtTenQuanAo, "Tên Quần Áo");
    }
    
    private void updateHinhAnh(){
        int i = tblQuanAo.getSelectedRow();
        if(i < 0) return;
        String maQuanAo = tblQuanAo.getValueAt(i, 0).toString();
        QuanAo quanAo = DAO_QuanAo.getQuanAoTheoMaQuanAo(maQuanAo);

        lblHinhAnh.setText("");
        lblHinhAnh.setIcon(quanAo.getHinhAnh());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        pnlLapHoaDon = new javax.swing.JPanel();
        pnlQuanAo = new javax.swing.JPanel();
        scrQuanAo = new javax.swing.JScrollPane();
        tblQuanAo = new javax.swing.JTable();
        pnlHoaDon = new javax.swing.JPanel();
        pnlDonHang = new javax.swing.JPanel();
        scrDonHang = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        pnlThongTinThanhToan = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnTimKiemSDT = new javax.swing.JButton();
        pnlThanhToan = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        lblTienDua = new javax.swing.JLabel();
        txtTienDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        btnTaoDonDatHang = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

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

        btnLamMoi.setBackground(new java.awt.Color(170, 238, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
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
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

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
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addComponent(cmbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTimKiemLayout.createSequentialGroup()
                        .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        add(pnlTimKiem, java.awt.BorderLayout.EAST);

        pnlLapHoaDon.setLayout(new java.awt.BorderLayout());

        pnlQuanAo.setPreferredSize(new java.awt.Dimension(916, 300));
        pnlQuanAo.setLayout(new java.awt.BorderLayout());

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
        tblQuanAo.setRowHeight(40);
        tblQuanAo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblQuanAo.getTableHeader().setReorderingAllowed(false);
        tblQuanAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanAoMouseClicked(evt);
            }
        });
        scrQuanAo.setViewportView(tblQuanAo);
        if (tblQuanAo.getColumnModel().getColumnCount() > 0) {
            tblQuanAo.getColumnModel().getColumn(0).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(1).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblQuanAo.getColumnModel().getColumn(2).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(3).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(4).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(5).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(6).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(7).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(8).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(9).setResizable(false);
        }

        pnlQuanAo.add(scrQuanAo, java.awt.BorderLayout.CENTER);

        pnlLapHoaDon.add(pnlQuanAo, java.awt.BorderLayout.NORTH);

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

        btnTimKiemSDT.setBackground(new java.awt.Color(170, 238, 255));
        btnTimKiemSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiemSDT.setText("Tìm Kiếm Theo SĐT");
        btnTimKiemSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSDTActionPerformed(evt);
            }
        });

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
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoDienThoai)
                    .addComponent(btnTimKiemSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btnTaoDonDatHang.setBackground(new java.awt.Color(170, 238, 255));
        btnTaoDonDatHang.setText("Tạo Đơn Đặt Hàng");
        btnTaoDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonDatHangActionPerformed(evt);
            }
        });

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
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaoDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void txtHoTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtHoTen, "Họ Tên");
    }//GEN-LAST:event_txtHoTenFocusGained

    private void txtSoDienThoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusGained

    private void txtDiaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusGained
        // TODO add your handling code here:
        UtilityJTextField.focusGained(txtDiaChi, "Địa Chỉ");
    }//GEN-LAST:event_txtDiaChiFocusGained

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

    private void txtHoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtHoTen, "Họ Tên");
    }//GEN-LAST:event_txtHoTenFocusLost

    private void txtSoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoDienThoaiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtSoDienThoai, "Số Điện Thoại");
    }//GEN-LAST:event_txtSoDienThoaiFocusLost

    private void txtDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusLost
        // TODO add your handling code here:
        UtilityJTextField.focusLost(txtDiaChi, "Địa Chỉ");
    }//GEN-LAST:event_txtDiaChiFocusLost

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemQuanAo();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themQuanAoVaoDonHang();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        thanhToanChoDonHang();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTimKiemSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSDTActionPerformed
        // TODO add your handling code here:
        timKiemSDT();
    }//GEN-LAST:event_btnTimKiemSDTActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaQuanAoKhoiDonHang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoiTimKiem();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblQuanAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanAoMouseClicked
        // TODO add your handling code here:
        updateHinhAnh();
    }//GEN-LAST:event_tblQuanAoMouseClicked

    private void btnTaoDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonDatHangActionPerformed
        // TODO add your handling code here:
        taoDonDatHang();
    }//GEN-LAST:event_btnTaoDonDatHangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTaoDonDatHang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemSDT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbChatLieu;
    private javax.swing.JComboBox<String> cmbDanhMuc;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JComboBox<String> cmbMauSac;
    private javax.swing.JComboBox<String> cmbNhaSanXuat;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblTienDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlDonHang;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlLapHoaDon;
    private javax.swing.JPanel pnlQuanAo;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JPanel pnlThongTinThanhToan;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JScrollPane scrDonHang;
    private javax.swing.JScrollPane scrQuanAo;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTable tblQuanAo;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaQuanAo;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenQuanAo;
    private javax.swing.JTextField txtTienDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
