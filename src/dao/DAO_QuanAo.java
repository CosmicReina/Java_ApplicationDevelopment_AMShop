package dao;

import data.UtilityImageIcon;
import entity.QuanAo;
import java.util.ArrayList;
import java.sql.*;

public class DAO_QuanAo extends DAO {
    public static boolean createQuanAo(QuanAo quanAo){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO QuanAo "
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
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, quanAo.getMaQuanAo());
            prs.setString(2, quanAo.getTenQuanAo());
            prs.setDouble(3, quanAo.getDonGiaNhap());
            prs.setDouble(4, quanAo.getDonGiaBan());
            prs.setInt(5, quanAo.getSoLuongTrongKho());
            prs.setString(6, quanAo.getNhaSanXuat());
            prs.setString(7, quanAo.getDanhMuc());
            prs.setString(8, quanAo.getGioiTinh());
            prs.setString(9, quanAo.getMauSac());
            prs.setString(10, quanAo.getKichThuoc());
            prs.setString(11, quanAo.getChatLieu());
            prs.setBytes(12, UtilityImageIcon.toBytes(quanAo.getHinhAnh()));
            prs.setBoolean(13, quanAo.isNgungNhap());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static boolean updateQuanAo(QuanAo quanAo){
        int n = 0;
        try {
            String sql = ""
                    + "UPDATE QuanAO "
                    + "SET "
                    + "TenQuanAo = ?, "
                    + "DonGiaNhap = ?, "
                    + "DonGiaBan = ?, "
                    + "SoLuongTrongKho = ?, "
                    + "NhaSanXuat = ?, "
                    + "DanhMuc = ?, "
                    + "GioiTinh = ?, "
                    + "MauSac = ?, "
                    + "KichThuoc = ?, "
                    + "ChatLieu = ?, "
                    + "HinhAnh = ?, "
                    + "NgungNhap = ?"
                    + "WHERE MaQuanAo = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(13, quanAo.getMaQuanAo());
            prs.setString(1, quanAo.getTenQuanAo());
            prs.setDouble(2, quanAo.getDonGiaNhap());
            prs.setDouble(3, quanAo.getDonGiaBan());
            prs.setInt(4, quanAo.getSoLuongTrongKho());
            prs.setString(5, quanAo.getNhaSanXuat());
            prs.setString(6, quanAo.getDanhMuc());
            prs.setString(7, quanAo.getGioiTinh());
            prs.setString(8, quanAo.getMauSac());
            prs.setString(9, quanAo.getKichThuoc());
            prs.setString(10, quanAo.getChatLieu());
            prs.setBytes(11, UtilityImageIcon.toBytes(quanAo.getHinhAnh()));
            prs.setBoolean(12, quanAo.isNgungNhap());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<QuanAo> getAllQuanAo(){
        ArrayList<QuanAo> list = new ArrayList<>();
        
        return list;
    }
    
    public static QuanAo getQuanAoTheoMaQuanAo(String maQuanAo){
        ArrayList<QuanAo> list = getAllQuanAo();
        for(QuanAo thisQuanAo : list){
            if(thisQuanAo.getMaQuanAo().equals(maQuanAo))
                return thisQuanAo;
        }
        return null;
    }
}
