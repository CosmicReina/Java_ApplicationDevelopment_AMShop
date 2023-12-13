package entity;

import java.util.Objects;

public class KhachHang {
    private String maKhachHang;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String nhomKhachHang;

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) throws Exception {
        if(!maKhachHang.matches("^KH[0-9]{8}$"))
            throw new Exception("Mã Khách Hàng không hợp lệ");
        this.maKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) throws Exception {
        if(!hoTen.matches("^[\\p{L}]+(\\s[\\p{L}]+)+$"))
            throw new Exception("Họ Tên không hợp lệ");
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) throws Exception {
        if(!soDienThoai.matches("^[0-9]{10}$"))
            throw new Exception("Số điện thoại không hợp lệ");
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) throws Exception {
        if(diaChi.isBlank())
            throw new Exception("Địa chỉ không hợp lệ");
        this.diaChi = diaChi;
    }

    public String getNhomKhachHang() {
        return nhomKhachHang;
    }

    public void setNhomKhachHang(String nhomKhachHang) throws Exception {
        if(!nhomKhachHang.equals("Thường") && !nhomKhachHang.equals("Thân Thiết"))
            throw new Exception("Nhóm Khách Hàng không hợp lệ");
        this.nhomKhachHang = nhomKhachHang;
    }

    public KhachHang() {}

    public KhachHang(String maKhachHang, String hoTen, String soDienThoai, String diaChi, String nhomKhachHang) {
        try {
            setMaKhachHang(maKhachHang);
            setHoTen(hoTen);
            setSoDienThoai(soDienThoai);
            setDiaChi(diaChi);
            setNhomKhachHang(nhomKhachHang);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.maKhachHang);
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
        final KhachHang other = (KhachHang) obj;
        return Objects.equals(this.maKhachHang, other.maKhachHang);
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", nhomKhachHang=" + nhomKhachHang + '}';
    }
}
