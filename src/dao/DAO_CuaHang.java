package dao;

import entity.CuaHang;
import java.sql.*;

public class DAO_CuaHang extends DAO {
    public static CuaHang getCuaHang(){
        try {
            String sql = "SELECT * FROM CuaHang";
            ResultSet rs = DAO.getResultSet(sql);
            if(rs.next()){
                String maCuaHang = rs.getString(1);
                String tenCuaHang = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String diaChi = rs.getString(4);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
