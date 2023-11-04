package dao;

import java.sql.*;

public class DAO_TaiKhoan extends DAO {
    public static boolean createTaiKhoan(String tenDangNhap, String matKhau){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO TaiKhoan "
                    + "VALUES ("
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, tenDangNhap);
            prs.setString(2, matKhau);
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ResultSet getTaiKhoanTheoMaNhanVien(String maNhanVien){
        try {
            String sql = ""
                    + "SELECT * "
                    + "FROM TaiKhoan "
                    + "WHERE TenDangNhap = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, maNhanVien);
            
            ResultSet rs = prs.executeQuery();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
