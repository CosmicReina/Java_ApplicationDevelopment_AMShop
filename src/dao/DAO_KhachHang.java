package dao;

import entity.KhachHang;
import java.util.ArrayList;
import java.sql.*;

public class DAO_KhachHang extends DAO {
    public static boolean createKhachHang(KhachHang khachHang){
        return false;
    }
    
    public static boolean updateKhachHang(KhachHang khachHang){
        return false;
    }
    
    public static ArrayList<KhachHang> getAllKhachHang(){
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KhachHang";
            ResultSet rs = getResultSet(sql);
            while(rs.next()){
                String maKhachHang = rs.getString(1);
                String hoTen = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String diaChi = rs.getString(4);
                String nhomKhachHang = rs.getString(5);
                
                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, diaChi, nhomKhachHang);
                
                list.add(khachHang);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static KhachHang getKhachHangTheoMaKhachHang(String maKhachHang){
        ArrayList<KhachHang> list = new ArrayList<>();
        for(KhachHang thisKhachHang : list){
            if(thisKhachHang.getMaKhachHang().equals(maKhachHang))
                return thisKhachHang;
        }
        return null;
    }
}
