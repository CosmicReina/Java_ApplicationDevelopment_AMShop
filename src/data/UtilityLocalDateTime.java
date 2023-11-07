package data;

import java.sql.*;
import java.time.LocalDateTime;

public class UtilityLocalDateTime {
    public static Timestamp fromLocalDate(LocalDateTime localDateTime){
        return Timestamp.valueOf(localDateTime);
    }
    
    public static LocalDateTime toLocalDate(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }
}