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

    public void setMaQuanAo(String maQuanAo) {
        this.maQuanAo = maQuanAo;
    }

    public String getTenQuanAo() {
        return tenQuanAo;
    }

    public void setTenQuanAo(String tenQuanAo) {
        this.tenQuanAo = tenQuanAo;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public double getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(double donGiaBan) {
        this.donGiaBan = donGiaBan;
    }

    public int getSoLuongTrongKho() {
        return soLuongTrongKho;
    }

    public void setSoLuongTrongKho(int soLuongTrongKho) {
        this.soLuongTrongKho = soLuongTrongKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public ImageIcon getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(ImageIcon hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isNgungNhap() {
        return ngungNhap;
    }

    public void setNgungNhap(boolean ngungNhap) {
        this.ngungNhap = ngungNhap;
    }

    public QuanAo() {
    }

    public QuanAo(String maQuanAo, String tenQuanAo, double donGiaNhap, double donGiaBan, int soLuongTrongKho, String nhaSanXuat, String danhMuc, String gioiTinh, String mauSac, String kichThuoc, String chatLieu, ImageIcon hinhAnh, boolean ngungNhap) {
        this.maQuanAo = maQuanAo;
        this.tenQuanAo = tenQuanAo;
        this.donGiaNhap = donGiaNhap;
        this.donGiaBan = donGiaBan;
        this.soLuongTrongKho = soLuongTrongKho;
        this.nhaSanXuat = nhaSanXuat;
        this.danhMuc = danhMuc;
        this.gioiTinh = gioiTinh;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.hinhAnh = hinhAnh;
        this.ngungNhap = ngungNhap;
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
