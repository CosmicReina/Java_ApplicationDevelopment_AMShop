package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_NhaSanXuat {
    public static ArrayList<String> getAllNhaSanXuat(){
        ArrayList<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM NhaSanXuat";
            ResultSet rs = DAO.getResultSet(sql);
            while(rs.next()){
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
}
