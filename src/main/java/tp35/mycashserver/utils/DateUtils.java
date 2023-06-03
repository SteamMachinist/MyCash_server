package tp35.mycashserver.utils;

import org.apache.commons.math3.util.Pair;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {
    public static Pair<LocalDateTime, LocalDateTime> getDatesBorderMonthDays(int year1, int month1, int day1,
                                                                      int year2, int month2, int day2) {
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime from = base.toLocalDate()
                .withYear(year1)
                .withMonth(month1)
                .withDayOfMonth(day1)
                .atTime(LocalTime.MIN);

        LocalDateTime to = base.toLocalDate()
                .withYear(year2)
                .withMonth(month2)
                .withDayOfMonth(day2)
                .atTime(LocalTime.MAX).minus(1, ChronoUnit.SECONDS);
        return new Pair<>(from, to);
    }

    public static Pair<LocalDateTime, LocalDateTime> getDatesBorderMonth(int year1, int month1,
                                                                  int year2, int month2) {
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime from = base.toLocalDate()
                .withYear(year1)
                .withMonth(month1)
                .with(TemporalAdjusters.firstDayOfMonth())
                .atTime(LocalTime.MIN);

        LocalDateTime to = base.toLocalDate()
                .withYear(year2)
                .withMonth(month2)
                .with(TemporalAdjusters.lastDayOfMonth())
                .atTime(LocalTime.MAX).minus(1, ChronoUnit.SECONDS);
        return new Pair<>(from, to);
    }
}
