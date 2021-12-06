package Education.utill;

import java.text.SimpleDateFormat;
import java.util.Date;


public interface MyDate {
    SimpleDateFormat spf = new SimpleDateFormat("yy/mm/dd hh/mm");


    static String printUtil(Date date){
        return spf.format(date);

    }
}
