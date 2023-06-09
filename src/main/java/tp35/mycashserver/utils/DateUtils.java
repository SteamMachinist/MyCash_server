package tp35.mycashserver.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {
    public static Pair<LocalDateTime, LocalDateTime> getBorderingDatesBetween(int year1, int month1, int day1,
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

    public static Pair<LocalDateTime, LocalDateTime> getBorderingDatesBetween(int year1, int month1,
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

    public static Pair<LocalDateTime, LocalDateTime> getBorderingDatesBetween(int year1, int year2) {
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime from = base.toLocalDate()
                .withYear(year1)
                .withMonth(1)
                .with(TemporalAdjusters.firstDayOfMonth())
                .atTime(LocalTime.MIN);

        LocalDateTime to = base.toLocalDate()
                .withYear(year2)
                .withMonth(12)
                .with(TemporalAdjusters.lastDayOfMonth())
                .atTime(LocalTime.MAX).minus(1, ChronoUnit.SECONDS);
        return new Pair<>(from, to);
    }
}
