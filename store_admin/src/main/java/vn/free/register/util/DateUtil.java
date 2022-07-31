package vn.free.register.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static Date convertStringToDate(String date, String format) {
        try {
            DateFormat parser = new SimpleDateFormat(format);
            Date dateResult = parser.parse(date);
            return dateResult;
        } catch (ParseException parseExp) {
            log.warn(" System error. Parse String to Date  {}  Exception", date, parseExp);
            return new Date();
        }catch (Exception exp){
            log.warn(" System error. Exception", exp);
            return new Date();
        }
    }
}
