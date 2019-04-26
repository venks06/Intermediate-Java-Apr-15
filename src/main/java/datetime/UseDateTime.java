package datetime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class UseDateTime {

    private static final int P1_SLA = 7 * 24 * 60 * 60;

    public static Date convertToDate(String date) {
        if (null == date || "".equals(date) || "null".equals(date))
            return null;
        try {
            int tIndex = date.indexOf('T');
            if (tIndex == -1)
                tIndex = 10;
            String strDate = date.substring(0, tIndex) + " " + date.substring(tIndex + 1, tIndex + 9);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    public static long getTimeDiff(Date d1, Date d2) {
        return (d1.getTime() - d2.getTime()) / 1000;
    }

    public static void main1(String[] args) {
        Set<String> timezones = ZoneId.getAvailableZoneIds();
        timezones.stream().filter(t -> t.contains("awaii")).forEachOrdered(System.out::println);
//        System.exit(0);
        ZoneId hawaii = ZoneId.of("US/Hawaii");

        LocalDateTime now = LocalDateTime.now();

        System.out.println("now is " + now);

        LocalDateTime then = now.plusDays(10);
        System.out.println("now is " + then);

        ZonedDateTime zNow = ZonedDateTime.now();
        System.out.println(zNow);

        zNow = zNow.plus(10, ChronoUnit.HOURS);
        System.out.println(zNow);
        ZonedDateTime zNowInHawaii = zNow.withZoneSameInstant(hawaii);
        System.out.println(zNowInHawaii);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYY / MMMM / dd 'at' HH : mm : ss 'TZ ' zzzz");
        System.out.println(dtf.format(zNowInHawaii));

        String inputVal = "2019-04-17T23:53:22.754912-10:00[US/Hawaii]";
        DateTimeFormatter dtf2 = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        System.out.println(dtf2.format(zNowInHawaii));
        ZonedDateTime readIn = ZonedDateTime.parse(inputVal, dtf2);
        System.out.println("readIn is " + readIn.getClass().getName());
        System.out.println("value is " + readIn);
    }

    public static void main(String[] args) {
        Date start = convertToDate("2019-04-15T13:51:00");
        Date end = getCurrentTime();
        long diff = getTimeDiff(end, start);
        System.out.println(diff > P1_SLA);
    }
}
