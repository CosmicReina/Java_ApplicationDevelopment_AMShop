package initiate;

import connect.ConnectDB;

public class Initiate {
    public static void main(String[] args) {
        ConnectDB.getInstance().connectDatabase();
        
        
        
        ConnectDB.getInstance().disconnectDatabase();
    }
}
