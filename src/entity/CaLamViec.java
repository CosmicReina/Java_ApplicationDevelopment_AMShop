package entity;

import java.time.LocalTime;

public class CaLamViec {
    private int maCaLamViec;
    private String tenCaLamViec;
    private LocalTime thoiGianBatDau;
    private LocalTime thoiGianKetThuc;

    public int getMaCaLamViec() {
        return maCaLamViec;
    }

    public void setMaCaLamViec(int maCaLamViec) {
        this.maCaLamViec = maCaLamViec;
    }

    public String getTenCaLamViec() {
        return tenCaLamViec;
    }

    public void setTenCaLamViec(String tenCaLamViec) {
        this.tenCaLamViec = tenCaLamViec;
    }

    public LocalTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public CaLamViec() {
    }

    public CaLamViec(int maCaLamViec, String tenCaLamViec, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc) {
        this.maCaLamViec = maCaLamViec;
        this.tenCaLamViec = tenCaLamViec;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.maCaLamViec;
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
        final CaLamViec other = (CaLamViec) obj;
        return this.maCaLamViec == other.maCaLamViec;
    }

    @Override
    public String toString() {
        return "CaLamViec{" + "maCaLamViec=" + maCaLamViec + ", tenCaLamViec=" + tenCaLamViec + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + '}';
    }
}
