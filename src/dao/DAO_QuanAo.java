package dao;

import entity.QuanAo;
import java.util.ArrayList;

public class DAO_QuanAo {
    public static boolean createQuanAo(){
        int n = 0;
        
        return n > 0;
    }
    
    public static boolean updateQuanAo(){
        int n = 0;
        
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
