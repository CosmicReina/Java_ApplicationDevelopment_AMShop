package dao;

import data.UtilityLocalDateTime;
import entity.CuaHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAO_HoaDon extends DAO {
    public static boolean createHoaDon(HoaDon hoaDon){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO HoaDon "
                    + "VALUES ("
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, hoaDon.getMaHoaDon());
            prs.setString(2, hoaDon.getCuaHang().getMaCuaHang());
            prs.setString(3, hoaDon.getNhanVien().getMaNhanVien());
            prs.setString(4, hoaDon.getKhachHang().getMaKhachHang());
            prs.setTimestamp(5, UtilityLocalDateTime.fromLocalDate(hoaDon.getThoiGianTao()));
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<HoaDon> getAllHoaDon(){
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HoaDon";
            ResultSet rs = getResultSet(sql);
            while(rs.next()) {
                String maHoaDon = rs.getString(1);
                String maNhanVien = rs.getString(3);
                String maKhachHang = rs.getString(4);
                LocalDateTime thoiGianTao = UtilityLocalDateTime.toLocalDate(rs.getTimestamp(5));
                
                CuaHang cuaHang = DAO_CuaHang.getCuaHang();
                NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
                KhachHang khachHang = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHang);
                
                HoaDon hoaDon = new HoaDon(maHoaDon, cuaHang, nhanVien, khachHang, thoiGianTao);
                
                list.add(hoaDon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static HoaDon getHoaDonTheoMaHoaDon(String maHoaDon){
        ArrayList<HoaDon> list = getAllHoaDon();
        for(HoaDon thisHoaDon : list){
            if(thisHoaDon.getMaHoaDon().equals(maHoaDon))
                return thisHoaDon;
        }
        return null;
    }
    
    public static String getMaHoaDonCuoi(String prefix){
        String searchPrefix = prefix + "%";
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM HoaDon "
                    + "WHERE MaHoaDon LIKE ? "
                    + "ORDER BY MaHoaDon DESC";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, searchPrefix);
            ResultSet rs = prs.executeQuery();
            if(rs.next()){
                String maHoaDonCuoi = rs.getString(1);
                return maHoaDonCuoi;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
