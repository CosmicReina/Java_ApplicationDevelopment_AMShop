package dao;

import data.UtilityLocalDate;
import entity.NhanVien;
import entity_enum.Enum_GioiTinh;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;

public class DAO_NhanVien extends DAO {
    
    public static NhanVien nhanVienHienTai = getNhanVienTheoMaNhanVien("NV23110801");
    
    public static boolean createNhanVien(NhanVien nhanVien){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO NhanVien "
                    + "VALUES ("
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, nhanVien.getMaNhanVien());
            prs.setString(2, nhanVien.getHoTen());
            prs.setString(3, nhanVien.getSoDienThoai());
            prs.setString(4, nhanVien.getDiaChi());
            prs.setString(5, nhanVien.getChucVu());
            prs.setDate(6, UtilityLocalDate.fromLocalDate(nhanVien.getNgaySinh()));
            prs.setString(7, nhanVien.getCanCuocCongDan());
            prs.setString(8, nhanVien.getGioiTinh().gioiTinh);
            prs.setDate(9, UtilityLocalDate.fromLocalDate(nhanVien.getNgaySinh()));
            prs.setDate(10, UtilityLocalDate.fromLocalDate(nhanVien.getNgaySinh()));
            prs.setDouble(11, nhanVien.getLuong());
            
            n = prs.executeUpdate();
            DAO_TaiKhoan.createTaiKhoan(nhanVien.getTenDangNhap(), nhanVien.getMatKhau());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static boolean updateNhanVien(NhanVien nhanVien){
        int n = 0;
        try {
            String sql = ""
                    + "UPDATE NhanVien "
                    + "SET "
                    + "HoTen = ?, "
                    + "SoDienThoai = ?, "
                    + "DiaChi = ?, "
                    + "ChucVu = ?, "
                    + "NgaySinh = ?, "
                    + "CanCuocCongDan = ?, "
                    + "GioiTinh = ?, "
                    + "NgayBatDauLam = ?, "
                    + "NgayKetThucLam = ?, "
                    + "Luong = ? "
                    + "WHERE MaNhanVien = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(11, nhanVien.getMaNhanVien());
            prs.setString(1, nhanVien.getHoTen());
            prs.setString(2, nhanVien.getSoDienThoai());
            prs.setString(3, nhanVien.getDiaChi());
            prs.setString(4, nhanVien.getChucVu());
            prs.setDate(5, UtilityLocalDate.fromLocalDate(nhanVien.getNgaySinh()));
            prs.setString(6, nhanVien.getCanCuocCongDan());
            prs.setString(7, nhanVien.getGioiTinh().gioiTinh);
            prs.setDate(8, UtilityLocalDate.fromLocalDate(nhanVien.getNgaySinh()));
            prs.setDate(9, UtilityLocalDate.fromLocalDate(nhanVien.getNgaySinh()));
            prs.setDouble(10, nhanVien.getLuong());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<NhanVien> getAllNhanVien(){
        ArrayList<NhanVien> list = new ArrayList();
        try {
            String sql = "SELECT * FROM NhanVien";
            ResultSet rs = getResultSet(sql);
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String hoTen = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String diaChi = rs.getString(4);
                String chucVu = rs.getString(5);
                LocalDate ngaySinh = UtilityLocalDate.toLocalDate(rs.getDate(6));
                String canCuocCongDan = rs.getString(7);
                Enum_GioiTinh gioiTinh = Enum_GioiTinh.getEnum(rs.getString(8));
                LocalDate ngayBatDauLam = UtilityLocalDate.toLocalDate(rs.getDate(9));
                LocalDate ngayKetThucLam = UtilityLocalDate.toLocalDate(rs.getDate(10));
                double luong = rs.getDouble(11);
                String tenDangNhap = null;
                String matKhau = null;
                
                ResultSet rs_2 = DAO_TaiKhoan.getTaiKhoanTheoMaNhanVien(maNhanVien);
                if(rs_2 != null && rs_2.next()){
                    tenDangNhap = rs_2.getString(1);
                    matKhau = rs_2.getString(2);
                }
                NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, soDienThoai, diaChi, chucVu, ngaySinh, canCuocCongDan, gioiTinh, ngayBatDauLam, ngayKetThucLam, luong, tenDangNhap, matKhau);
                
                list.add(nhanVien);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static NhanVien getNhanVienTheoMaNhanVien(String maNhanVien){
        ArrayList<NhanVien> list = getAllNhanVien();
        for(NhanVien thisNhanVien : list){
            if(thisNhanVien.getMaNhanVien().equals(maNhanVien))
                return thisNhanVien;
        }
        return null;
    }

    public static String getMaNhanVienCuoi(){
        String maNhanVienCuoi = null;
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM NhanVien "
                    + "ORDER BY MaNhanVien DESC";
            ResultSet rs = getResultSet(sql);
            if(rs.next()){
                maNhanVienCuoi = rs.getString(1);
                return maNhanVienCuoi;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return maNhanVienCuoi;
    }
}
