package initiate;

import connect.ConnectDB;
import dao.DAO_NhanVien;
import gui.GUI_DangNhap;
import gui.GUI_MainFrame;

public class Initiate {
    public static void main(String[] args) {
//        ConnectDB.getInstance().connectDatabase();
//        GUI_DangNhap gui = GUI_DangNhap.getInstance();
//        gui.setVisible(true);
        
        ConnectDB.getInstance().connectDatabase();
        DAO_NhanVien.nhanVienHienTai = DAO_NhanVien.getNhanVienTheoMaNhanVien("NV12312312");
        GUI_MainFrame gui = GUI_MainFrame.getInstance();
        gui.setVisible(true);
    }
}
