package initiate;

import connect.ConnectDB;
import dao.DAO_CaLamViec;
import dao.DAO_ChatLieu;
import dao.DAO_DanhMuc;
import dao.DAO_DonDatHang;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_NhaSanXuat;
import dao.DAO_NhanVien;
import dao.DAO_QuanAo;

public class Initiate_Test {
    public static void main(String[] args) {
        ConnectDB.getInstance().connectDatabase();
//        System.out.println(DAO_CaLamViec.getCaLamViecTheoMaCaLamViec(1));
//        System.out.println(DAO_QuanAo.getQuanAoTheoMaQuanAo("SP000001"));
//        System.out.println(DAO_NhanVien.getNhanVienTheoMaNhanVien("NV12312312"));
//        System.out.println(DAO_NhanVien.getNhanVienTheoCanCuocCongDan("029374856172"));
//        System.out.println(DAO_NhanVien.getNhanVienTheoSoDienThoai("0332917628"));
//        System.out.println(DAO_NhaSanXuat.kiemTraTonTai("YAME"));
//        System.out.println(DAO_DanhMuc.kiemTraTonTai("Áo khoác"));
//        System.out.println(DAO_ChatLieu.kiemTraTonTai("Thun"));
//        System.out.println(DAO_HoaDon.getHoaDonTheoMaHoaDon("HD2312090001"));
//        System.out.println(DAO_KhachHang.getKhachHangTheoMaKhachHang("KH23000005"));
//        System.out.println(DAO_KhachHang.getKhachHangTheoSoDienThoai("0334999221"));
//        System.out.println(DAO_DonDatHang.getDonDatHangTheoMaDonDatHang("DD2311150002"));
        System.out.println(DAO_KhachHang.getKhachHangTheoSoDienThoai("0334445555"));
        ConnectDB.getInstance().disconnectDatabase();
    }
}
