package hu.callcenter.domain.model;

public class LogTime implements Comparable<LogTime> {

    private static final LogTime WORK_BEGIN = new LogTime(8, 0, 0);
    private static final LogTime WORK_END = new LogTime(11, 59, 59);

    private final int hour;
    private final int minute;
    private final int second;

    public LogTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public Integer toSecond() {
        return mpbe();
    }

    public static Integer diff(LogTime t1, LogTime t2) {
        return Math.abs(t1.toSecond() - t2.toSecond());
    }

    public static boolean isBefore(LogTime t1, LogTime t2) {
        return t1.toSecond() < t2.toSecond();
    }

    public static boolean isAfter(LogTime t1, LogTime t2) {
        return !isBefore(t1, t2);
    }

    public boolean between(LogTime t1, LogTime t2) {
        return isAfter(this, t1) && isBefore(this, t2);
    }

    public boolean isAvailable(LogTime t1, LogTime t2) {
        return isOfficeHours(t1, t2) && isBefore(this, t2);
    }

    private static boolean isOfficeHours(LogTime t1, LogTime t2) {
        return isAfter(t2, WORK_BEGIN) && isBefore(t1, WORK_END);
    }

    private int mpbe() {
        return hour * 60 * 60 + minute * 60 + second;
    }

    @Override
    public String toString() {
        return hour + " " + minute + " " + second;
    }

    @Override
    public int compareTo(LogTime time) {
        return toSecond().compareTo(time.toSecond());
    }
}
