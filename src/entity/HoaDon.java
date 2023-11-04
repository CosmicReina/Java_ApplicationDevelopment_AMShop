package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDon {
    private String maHoaDon;
    private CuaHang cuaHang;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private LocalDateTime thoiGianTao;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public CuaHang getCuaHang() {
        return cuaHang;
    }

    public void setCuaHang(CuaHang cuaHang) {
        this.cuaHang = cuaHang;
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

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, CuaHang cuaHang, NhanVien nhanVien, KhachHang khachHang, LocalDateTime thoiGianTao) {
        this.maHoaDon = maHoaDon;
        this.cuaHang = cuaHang;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.thoiGianTao = thoiGianTao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.maHoaDon);
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
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHoaDon, other.maHoaDon);
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", cuaHang=" + cuaHang + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", thoiGianTao=" + thoiGianTao + '}';
    }
}
