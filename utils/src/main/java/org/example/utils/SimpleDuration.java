package org.example.utils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDuration {
    private static final String REGEX =
            "(?:([-+]?[0-9]+)D)?" +
            "(?:([-+]?[0-9]+)H)?" +
            "(?:([-+]?[0-9]+)M)?" +
            "(?:([-+]?)([0-9]+)(?:[.]([0-9]{0,3}))?S)?" +
            "(?:([-+]?[0-9]+)MS)?";
    private static final Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);

    private final String duration;
    private final long millis;

    /**
     * @param duration Second is the default unit
     */
    public SimpleDuration(String duration) {
        this.duration = duration;
        if (duration == null || duration.length() == 0) {
            this.millis = 0;
            return;
        }

        char c = duration.charAt(duration.length() - 1);
        if ((c >= '0' && c <= '9') || c == '.') {
            duration += 'S';
        }

        Matcher matcher = pattern.matcher(duration);
        if (!matcher.matches()) {
            throw new RuntimeException(String.format("时间:%s错误！", this.duration));
        }
        long millis = 0;
        for (Unit unit : Unit.values()) {
            millis += unit.toMillis(matcher);
        }
        this.millis = millis;
    }

    public String getDuration() {
        return duration;
    }

    public long getMillis() {
        return millis;
    }

    private enum Unit {
        DAY(1, TimeUnit.DAYS),
        HOUR(2, TimeUnit.HOURS),
        MINUTE(3, TimeUnit.MINUTES),
        SECOND(5, TimeUnit.SECONDS),
        MILLISECOND(7, TimeUnit.MILLISECONDS);

        private final int groupId;
        private final TimeUnit timeUnit;

        Unit(int groupId, TimeUnit timeUnit) {
            this.groupId = groupId;
            this.timeUnit = timeUnit;
        }

        private long toMillis(Matcher matcher) {
            String number = matcher.group(groupId);
            if (number == null) {
                return 0;
            }
            if (this == SECOND) {
                String symbol = matcher.group(groupId - 1);
                String ms = matcher.group(groupId + 1);
                if (ms == null) {
                    ms = "";
                }
                if (ms.length() < 3) {
                    ms += String.format("%0" + (3 - ms.length()) + "d", 0);
                }
                long result = 0;
                result += timeUnit.toMillis(Long.parseLong(symbol + number));
                result += TimeUnit.MILLISECONDS.toMillis((Long.parseLong(symbol + ms)));
                return result;
            }
            return timeUnit.toMillis(Long.parseLong(number));
        }
    }
}
