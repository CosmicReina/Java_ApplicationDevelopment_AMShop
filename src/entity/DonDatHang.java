package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class DonDatHang {
    private String maDonDatHang;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private LocalDateTime thoiGianTao;
    private boolean trangThaiThanhToan;

    public String getMaDonDatHang() {
        return maDonDatHang;
    }

    public void setMaDonDatHang(String maDonDatHang) {
        this.maDonDatHang = maDonDatHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public boolean isTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public DonDatHang() {
    }

    public DonDatHang(String maDonDatHang, NhanVien nhanVien, KhachHang khachHang, LocalDateTime thoiGianTao, boolean trangThaiThanhToan) {
        this.maDonDatHang = maDonDatHang;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.thoiGianTao = thoiGianTao;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maDonDatHang);
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
        final DonDatHang other = (DonDatHang) obj;
        return Objects.equals(this.maDonDatHang, other.maDonDatHang);
    }

    @Override
    public String toString() {
        return "DonDatHang{" + "maDonDatHang=" + maDonDatHang + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", thoiGianTao=" + thoiGianTao + ", trangThaiThanhToan=" + trangThaiThanhToan + '}';
    }
}
