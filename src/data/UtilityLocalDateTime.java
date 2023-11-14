package data;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilityLocalDateTime {
    public static Timestamp fromLocalDateTime(LocalDateTime localDateTime){
        if(localDateTime == null) return null;
        return Timestamp.valueOf(localDateTime);
    }
    
    public static LocalDateTime toLocalDateTime(Timestamp timestamp){
        if(timestamp == null) return null;
        return timestamp.toLocalDateTime();
    }
    
    public static String toFormattedLocalDateTime(LocalDateTime localDateTime){
        if(localDateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }
}