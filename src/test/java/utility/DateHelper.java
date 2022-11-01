package utility;

import java.util.HashMap;
import java.util.Map;

public class DateHelper {
    public static String  getOrdinal(int num) {
        switch (num) {
            case 1:
            case 21:
            case 31:
                return "st";
            case 2:
            case 22:
                return "nd";
            case 3:
            case 23:
                return "rd";
            default:
                return "th";
        }
    }
}