package initiate;

import connect.ConnectDB;
import gui.FrmMain;

public class Initiate {
    public static void main(String[] args) {
        ConnectDB.getInstance().connectDatabase();
        
        FrmMain frmMain = FrmMain.getInstance();
        frmMain.setVisible(true);
        
        ConnectDB.getInstance().disconnectDatabase();
    }
}
