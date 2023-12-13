package entity;

import java.util.Objects;
import javax.swing.ImageIcon;

public class QuanAo {
    private String maQuanAo;
    private String tenQuanAo;
    private double donGiaNhap;
    private double donGiaBan;
    private int soLuongTrongKho;
    private String nhaSanXuat;
    private String danhMuc;
    private String gioiTinh;
    private String mauSac;
    private String kichThuoc;
    private String chatLieu;
    private ImageIcon hinhAnh;
    private boolean ngungNhap;

    public String getMaQuanAo() {
        return maQuanAo;
    }

    public void setMaQuanAo(String maQuanAo) throws Exception {
        if(!maQuanAo.matches("^QA[0-9]{6}$"))
            throw new Exception("Mã Quần Áo không hợp lệ");
        this.maQuanAo = maQuanAo;
    }

    public String getTenQuanAo() {
        return tenQuanAo;
    }

    public void setTenQuanAo(String tenQuanAo) throws Exception {
        if(tenQuanAo.isBlank())
            throw new Exception("Tên Quần Áo không hợp lệ");
        this.tenQuanAo = tenQuanAo;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) throws Exception {
        if(donGiaNhap < 0)
            throw new Exception("Đơn Giá Nhập không hợp lệ");
        this.donGiaNhap = donGiaNhap;
    }

    public double getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(double donGiaBan) throws Exception {
        if(donGiaBan < donGiaNhap)
            throw new Exception("Đơn Giá Bán không hợp lệ");
        this.donGiaBan = donGiaBan;
    }

    public int getSoLuongTrongKho() {
        return soLuongTrongKho;
    }

    public void setSoLuongTrongKho(int soLuongTrongKho) throws Exception {
        if(soLuongTrongKho < 0)
            throw new Exception("Số Lượng Trong Kho không hợp lệ");
        this.soLuongTrongKho = soLuongTrongKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) throws Exception {
        if(nhaSanXuat.isBlank())
            throw new Exception("Nhà Sản Xuất không hợp lệ");
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) throws Exception {
        if(danhMuc.isBlank())
            throw new Exception("Danh Mục không hợp lệ");
        this.danhMuc = danhMuc;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) throws Exception {
        if(gioiTinh.isBlank())
            throw new Exception("Giới Tính không hợp lệ");
        this.gioiTinh = gioiTinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) throws Exception {
        if(mauSac.isBlank())
            throw new Exception("Màu Sắc không hợp lệ");
        this.mauSac = mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) throws Exception {
        if(kichThuoc.isBlank())
            throw new Exception("Kích Thước không hợp lệ");
        this.kichThuoc = kichThuoc;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) throws Exception {
        if(chatLieu.isBlank())
            throw new Exception("Chất Liệu không hợp lệ");
        this.chatLieu = chatLieu;
    }

    public ImageIcon getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(ImageIcon hinhAnh) throws Exception {
        if(hinhAnh == null)
            throw new Exception("Hình Ảnh không hợp lệ");
        this.hinhAnh = hinhAnh;
    }

    public boolean isNgungNhap() {
        return ngungNhap;
    }

    public void setNgungNhap(boolean ngungNhap) {
        this.ngungNhap = ngungNhap;
    }

    public QuanAo() {}

    public QuanAo(String maQuanAo, String tenQuanAo, double donGiaNhap, double donGiaBan, int soLuongTrongKho, String nhaSanXuat, String danhMuc, String gioiTinh, String mauSac, String kichThuoc, String chatLieu, ImageIcon hinhAnh, boolean ngungNhap) {
        try {
            setMaQuanAo(maQuanAo);
            setTenQuanAo(tenQuanAo);
            setDonGiaNhap(donGiaNhap);
            setDonGiaBan(donGiaBan);
            setSoLuongTrongKho(soLuongTrongKho);
            setNhaSanXuat(nhaSanXuat);
            setDanhMuc(danhMuc);
            setGioiTinh(gioiTinh);
            setMauSac(mauSac);
            setKichThuoc(kichThuoc);
            setChatLieu(chatLieu);
            setHinhAnh(hinhAnh);
            setNgungNhap(ngungNhap);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maQuanAo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuanAo other = (QuanAo) obj;
        return Objects.equals(this.maQuanAo, other.maQuanAo);
    }

    @Override
    public String toString() {
        return "QuanAo{" + "maQuanAo=" + maQuanAo + ", tenQuanAo=" + tenQuanAo + ", donGiaNhap=" + donGiaNhap + ", donGiaBan=" + donGiaBan + ", soLuongTrongKho=" + soLuongTrongKho + ", nhaSanXuat=" + nhaSanXuat + ", danhMuc=" + danhMuc + ", gioiTinh=" + gioiTinh + ", mauSac=" + mauSac + ", kichThuoc=" + kichThuoc + ", chatLieu=" + chatLieu + ", hinhAnh=" + hinhAnh + ", ngungNhap=" + ngungNhap + '}';
    }
}
