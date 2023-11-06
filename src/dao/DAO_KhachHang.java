package dao;

import entity.KhachHang;
import java.util.ArrayList;

public class DAO_KhachHang extends DAO {
    public static boolean createKhachHang(KhachHang khachHang){
        return false;
    }
    
    public static boolean updateKhachHang(KhachHang khachHang){
        return false;
    }
    
    public static ArrayList<KhachHang> getAllKhachHang(){
        return null;
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
