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
    public static void main(String[] args) {
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
}
