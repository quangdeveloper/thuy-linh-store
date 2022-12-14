package vn.free.register.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static String DATE_TIME_FORMAT_V1 = "yyyy-MM-dd HH:mm:ss";

    public static Date convertStringToDate(String date, String format) {
        try {
            DateFormat parser = new SimpleDateFormat(format);
            return  parser.parse(date);
        } catch (ParseException parseExp) {
            log.warn(" System error. Parse String to Date  {}  Exception. ", date, parseExp);
            return new Date();
        }catch (Exception exp){
            log.warn(" System error. Exception", exp);
            return new Date();
        }
    }

    public static String convertDateToString(Date request, String format) {
        try{
            if (request == null){
                return StringUtils.EMPTY;
            }
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(request);
        }catch (Exception ex){
            log.error("Convert date to string ....fail. ", ex);
            return StringUtils.EMPTY;
        }

    }
}
