package entity_enum;

public enum Enum_GioiTinh {
    NAM("Nam"),
    NU("Nữ");
    
    public final String gioiTinh;

    private Enum_GioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public static Enum_GioiTinh getEnum(String gioiTinh){
        if(gioiTinh.equals("Nam")) return NAM;
        if(gioiTinh.equals("Nữ")) return NU;
        return null;
    }
}
