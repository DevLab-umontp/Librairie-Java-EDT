package edt.umontp.fr;

import net.fortuna.ical4j.model.DateTime;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws ParseException {
        LocalTime a = LocalTime.of(10,0);
        LocalTime b = LocalTime.of(12, 30);
        Duration duration = Duration.between(a, b);
        System.out.println((int) (duration.toMinutes()));
    }
}
