package entity;

import entity_enum.Enum_GioiTinh;
import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNhanVien;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String chucVu;
    private LocalDate ngaySinh;
    private String canCuocCongDan;
    private Enum_GioiTinh gioiTinh;
    private LocalDate ngayBatDauLam;
    private LocalDate ngayKetThucLam;
    private double luong;
    private String tenDangNhap;
    private String matKhau;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCanCuocCongDan() {
        return canCuocCongDan;
    }

    public void setCanCuocCongDan(String canCuocCongDan) {
        this.canCuocCongDan = canCuocCongDan;
    }

    public Enum_GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Enum_GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgayBatDauLam() {
        return ngayBatDauLam;
    }

    public void setNgayBatDauLam(LocalDate ngayBatDauLam) {
        this.ngayBatDauLam = ngayBatDauLam;
    }

    public LocalDate getNgayKetThucLam() {
        return ngayKetThucLam;
    }

    public void setNgayKetThucLam(LocalDate ngayKetThucLam) {
        this.ngayKetThucLam = ngayKetThucLam;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String hoTen, String soDienThoai, String diaChi, String chucVu, LocalDate ngaySinh, String canCuocCongDan, Enum_GioiTinh gioiTinh, LocalDate ngayBatDauLam, LocalDate ngayKetThucLam, double luong, String tenDangNhap, String matKhau) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.ngaySinh = ngaySinh;
        this.canCuocCongDan = canCuocCongDan;
        this.gioiTinh = gioiTinh;
        this.ngayBatDauLam = ngayBatDauLam;
        this.ngayKetThucLam = ngayKetThucLam;
        this.luong = luong;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.maNhanVien);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNhanVien, other.maNhanVien);
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", chucVu=" + chucVu + ", ngaySinh=" + ngaySinh + ", canCuocCongDan=" + canCuocCongDan + ", gioiTinh=" + gioiTinh + ", ngayBatDauLam=" + ngayBatDauLam + ", ngayKetThucLam=" + ngayKetThucLam + ", luong=" + luong + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + '}';
    }
}
