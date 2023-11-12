package dao;

import data.UtilityLocalDate;
import entity.CaLamViec;
import entity.LichLamViec;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;

public class DAO_LichLamViec extends DAO {
    public static boolean createLichLamViec(LichLamViec lichLamViec){
        int n = 0;
        try {
            String sql = ""
                    + "INSERT INTO LichLamViec "
                    + "VALUES ("
                    + "?, "
                    + "?, "
                    + "?"
                    + ")";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(1, lichLamViec.getMaLichLamViec());
            prs.setDate(2, UtilityLocalDate.fromLocalDate(lichLamViec.getNgayLamViec()));
            prs.setInt(3, lichLamViec.getCaLamViec().getMaCaLamViec());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static boolean updateLichLamViec(LichLamViec lichLamViec){
        int n = 0;
        try {
            String sql = ""
                    + "UPDATE LichLamViec "
                    + "SET "
                    + "NgayLamViec = ?, "
                    + "CaLamViec = ? "
                    + "WHERE MaCaLamViec = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setString(3, lichLamViec.getMaLichLamViec());
            prs.setDate(1, UtilityLocalDate.fromLocalDate(lichLamViec.getNgayLamViec()));
            prs.setInt(2, lichLamViec.getCaLamViec().getMaCaLamViec());
            
            n = prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return n > 0;
    }
    
    public static ArrayList<LichLamViec> getAllLichLamViec(){
        ArrayList<LichLamViec> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM LichLamViec";
            ResultSet rs = DAO.getResultSet(sql);
            while(rs.next()){
                String maLichLamViec = rs.getString(1);
                LocalDate ngayLamViec = UtilityLocalDate.toLocalDate(rs.getDate(2));
                int maCaLamViec = rs.getInt(3);
                
                CaLamViec caLamViec = DAO_CaLamViec.getCaLamViecTheoMaCaLamViec(maCaLamViec);
                
                LichLamViec lichLamViec = new LichLamViec(maLichLamViec, ngayLamViec, caLamViec);
                
                list.add(lichLamViec);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static LichLamViec getLichLamViecTheoMaLichLamViec(String maLichLamViec){
        ArrayList<LichLamViec> list = getAllLichLamViec();
        for(LichLamViec thisLichLamViec : list){
            if(thisLichLamViec.getMaLichLamViec().equals(maLichLamViec))
                return thisLichLamViec;
        }
        return null;
    }
}
