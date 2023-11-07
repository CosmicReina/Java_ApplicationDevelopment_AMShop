package initiate;

import connect.ConnectDB;
import gui.PnlCapNhatQuanAo;
import gui.PnlDanhSachDonDatHang;
import gui.PnlDanhSachKhachHang;
import gui.PnlLapHoaDon;
import gui.PnlTaoNhanVien;
import gui.PnlThemQuanAo;

public class Initiate {
    public static void main(String[] args) {
        ConnectDB.getInstance().connectDatabase();
        
        ConnectDB.getInstance().disconnectDatabase();
    }
}
