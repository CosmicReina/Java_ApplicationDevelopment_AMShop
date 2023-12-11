package initiate;

import connect.ConnectDB;
import gui_old.FrmLogin;

public class Initiate_Old {
    public static void main(String[] args) {
//        ConnectDB.getInstance().connectDatabase();
//        FrmMain frmMain = FrmMain.getInstance();
//        frmMain.setVisible(true);

        ConnectDB.getInstance().connectDatabase();
        FrmLogin frmLogin = FrmLogin.newInstance();
        frmLogin.setVisible(true);
    }
}
