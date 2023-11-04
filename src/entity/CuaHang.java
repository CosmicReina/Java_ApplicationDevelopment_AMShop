package entity;

public class CuaHang {
    private int maCuaHang;
    private String tenCuaHang;
    private String diaChi;
    private String soDienThoai;

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public CuaHang() {
    }

    public CuaHang(int maCuaHang, String tenCuaHang, String diaChi, String soDienThoai) {
        this.maCuaHang = maCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    @Override
    public String toString() {
        return "CuaHang{" + "maCuaHang=" + maCuaHang + ", tenCuaHang=" + tenCuaHang + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + '}';
    }
}
