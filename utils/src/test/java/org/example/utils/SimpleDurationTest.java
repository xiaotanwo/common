package org.example.utils;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SimpleDurationTest {

    @Test
    public void testD() {
        int tmp = 542;
        test(String.format("%dD", tmp), String.format("P%dDT0S", tmp));
        test(String.format("%dd", tmp), String.format("P%ddT0S", tmp));
        test(String.format("+%dD", tmp), String.format("P%dDT0S", tmp));
        test(String.format("+%dd", tmp), String.format("P%dDT0S", tmp));
        test(String.format("-%dD", tmp), String.format("-P%dDT0S", tmp));
        test(String.format("-%dd", tmp), String.format("-P%dDT0S", tmp));
    }

    @Test
    public void testH() {
        int tmp = 5123;
        test(String.format("%dH", tmp), String.format("PT%dH", tmp));
        test(String.format("%dh", tmp), String.format("PT%dH", tmp));
        test(String.format("+%dH", tmp), String.format("PT%dH", tmp));
        test(String.format("+%dh", tmp), String.format("PT%dH", tmp));
        test(String.format("-%dH", tmp), String.format("-PT%dH", tmp));
        test(String.format("-%dh", tmp), String.format("-PT%dH", tmp));
    }

    @Test
    public void testM() {
        int tmp = 8576;
        test(String.format("%dM", tmp), String.format("PT%dM", tmp));
        test(String.format("%dm", tmp), String.format("PT%dM", tmp));
        test(String.format("+%dM", tmp), String.format("PT%dM", tmp));
        test(String.format("+%dm", tmp), String.format("PT%dM", tmp));
        test(String.format("-%dM", tmp), String.format("-PT%dM", tmp));
        test(String.format("-%dm", tmp), String.format("-PT%dM", tmp));
    }

    @Test
    public void testS() {
        int tmp = 195;
        test(String.format("%dS", tmp), String.format("PT%dS", tmp));
        test(String.format("%ds", tmp), String.format("PT%dS", tmp));
        test(String.format("+%ds", tmp), String.format("PT%dS", tmp));
        test(String.format("+%ds", tmp), String.format("PT%dS", tmp));
        test(String.format("-%ds", tmp), String.format("-PT%dS", tmp));
        test(String.format("-%ds", tmp), String.format("-PT%dS", tmp));
    }

    @Test
    public void testMS() {
        int tmp = 76;
        test(String.format("%dMS", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("%dms", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("%dMs", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("%dmS", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("+%dMS", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("+%dms", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("+%dMs", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("+%dmS", tmp), String.format("PT0.%03dS", tmp));
        test(String.format("-%dmS", tmp), String.format("-PT0.%03dS", tmp));
        test(String.format("-%dmS", tmp), String.format("-PT0.%03dS", tmp));
        test(String.format("-%dmS", tmp), String.format("-PT0.%03dS", tmp));
        test(String.format("-%dmS", tmp), String.format("-PT0.%03dS", tmp));
    }

    @Test
    public void testDHSMS() {
        int days = 3412;
        int hours = 84329;
        int minutes = 921384;
        int seconds = 65546;
        int millisSeconds = 431;
        String duration = String.format("P%dDT%dH%dM%d.%03dS", days, hours, minutes, seconds, millisSeconds);
        test(String.format("%dD%dH%dM%dS%dMS", days, hours, minutes, seconds, millisSeconds), duration);
        test(String.format("%dd%dh%dm%ds%dms", days, hours, minutes, seconds, millisSeconds), duration);
        test(String.format("+%dD+%dH+%dM+%dS+%dMS", days, hours, minutes, seconds, millisSeconds), duration);
        test(String.format("+%dd+%dh+%dm+%ds+%dms", days, hours, minutes, seconds, millisSeconds), duration);
        String duration1 = String.format("-P%dDT%dH%dM%d.%03dS", days, hours, minutes, seconds, millisSeconds);
        test(String.format("-%dD-%dH-%dM-%dS-%dMS", days, hours, minutes, seconds, millisSeconds), duration1);
        test(String.format("-%dd-%dh-%dm-%ds-%dms", days, hours, minutes, seconds, millisSeconds), duration1);
    }

    @Test
    public void testDHS() {
        int days = 3412;
        int hours = 84329;
        int minutes = 921384;
        String seconds = "65546.32";
        String duration = String.format("P%dDT%dH%dM%sS", days, hours, minutes, seconds);
        test(String.format("%dD%dH%dM%sS", days, hours, minutes, seconds), duration);
        test(String.format("%dd%dh%dm%ss", days, hours, minutes, seconds), duration);
        test(String.format("+%dD+%dH+%dM+%sS", days, hours, minutes, seconds), duration);
        test(String.format("+%dd+%dh+%dm+%ss", days, hours, minutes, seconds), duration);
        String duration1 = String.format("-P%dDT%dH%dM%sS", days, hours, minutes, seconds);
        test(String.format("-%dD-%dH-%dM-%sS", days, hours, minutes, seconds), duration1);
        test(String.format("-%dd-%dh-%dm-%ss", days, hours, minutes, seconds), duration1);
    }

    @Test
    public void testDefault() {
        test(null, "PT0S");
        test("", "PT0S");
        test("0", "PT0S");
        test("8431287", "PT8431287S");
        test("-8431287", "-PT8431287S");
        test("0.", "PT0S");
        test("231.", "PT231S");
        test("123.342", "PT123.342S");
        test("13.32", "PT13.32S");
        test("1123.2", "PT1123.2S");
        test("0.245", "PT0.245S");
        test("-0.", "-PT0S");
        test("-231.", "-PT231S");
        test("-123.342", "-PT123.342S");
        test("-13.32", "-PT13.32S");
        test("-1123.2", "-PT1123.2S");
        test("-0.245", "-PT0.245S");
    }

    @Test
    public void testSpecial() {
        test("21d34.32s1ms", "P21DT34.321S");
        test("-21d-34.32s-1ms", "-P21DT34.321S");
        test("21H34.32s1ms", "PT21H34.321S");
        test("-21H-34.32s-1ms", "-PT21H34.321S");
        test("21M34.32s1ms", "PT21M34.321S");
        test("-21M-34.32s-1ms", "-PT21M34.321S");
        test("+2D-3H+4M-5.23S+42MS", "P2DT-3H+4M-5.188S");
        test("-132D+3421H-32M+54.694S-32MS", "P-132DT+3421H-32M+54.662S");
        test("+2D-3H+4M-5.23S42MS", "P2DT-3H+4M-5.188S");
        test("-132D3421H-32M+54.694S-32MS", "P-132DT+3421H-32M+54.662S");
        test("+2D-3H+4M-5.23S42MS", "P2DT-3H+4M-5.188S");
        test("-132D3421H-32M+54.694S-32MS", "P-132DT+3421H-32M+54.662S");
    }

    private void test(String sDuration, String duration) {
        SimpleDuration simpleDuration = new SimpleDuration(sDuration);
        Duration dDuration = Duration.parse(duration);
        assertEquals(simpleDuration.getMillis(), dDuration.toMillis());
    }
}
