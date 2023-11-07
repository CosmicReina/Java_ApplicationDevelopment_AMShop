package dao;

import static dao.DAO.connection;
import data.UtilityLocalDateTime;
import entity.DonDatHang;
import entity.KhachHang;
import entity.NhanVien;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;

public class DAO_DonDatHang extends DAO {
    public static boolean createDonDatHang(DonDatHang donDatHang){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO DonDatHang "
                    + "VALUES ("
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, donDatHang.getMaDonDatHang());
            prs.setString(2, donDatHang.getNhanVien().getMaNhanVien());
            prs.setString(3, donDatHang.getKhachHang().getMaKhachHang());
            prs.setTimestamp(4, UtilityLocalDateTime.fromLocalDate(donDatHang.getThoiGianTao()));
            prs.setBoolean(5, donDatHang.isTrangThaiThanhToan());
                    
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<DonDatHang> getAllDonDatHang(){
        ArrayList<DonDatHang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DonDatHang";
            ResultSet rs = getResultSet(sql);
            while(rs.next()){
                String maDonDatHang = rs.getString(1);
                String maNhanVien = rs.getString(2);
                String maKhachHang = rs.getString(3);
                LocalDateTime thoiGianTao = UtilityLocalDateTime.toLocalDate(rs.getTimestamp(4));
                boolean trangThaiThanhToan = rs.getBoolean(5);
                
                NhanVien nhanVien = DAO_NhanVien.getNhanVienTheoMaNhanVien(maNhanVien);
                KhachHang khachHang = DAO_KhachHang.getKhachHangTheoMaKhachHang(maKhachHang);
                
                DonDatHang donDatHang = new DonDatHang(maDonDatHang, nhanVien, khachHang, thoiGianTao, trangThaiThanhToan);
                
                list.add(donDatHang);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static DonDatHang getDonDatHangTheoMaDonDatHang(String maDonDatHang){
        ArrayList<DonDatHang> list = getAllDonDatHang();
        for(DonDatHang thisDonDatHang : list){
            if(thisDonDatHang.getMaDonDatHang().equals(maDonDatHang))
                return thisDonDatHang;
        }
        return null;
    }
}
