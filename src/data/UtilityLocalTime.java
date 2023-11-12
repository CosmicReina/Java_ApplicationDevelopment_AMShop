package data;

import java.time.LocalTime;
import java.sql.Time;

public class UtilityLocalTime {
    public static java.sql.Time fromLocalTime(LocalTime localTime){
        Time time = Time.valueOf(localTime);
        return time;
    }
    
    public static LocalTime toLocalTime(Time time){
        LocalTime localTime = time.toLocalTime();
        return localTime;
    }
}
