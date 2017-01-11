import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface HourAndMinutesValidator {

	public static boolean IsValid(String hourAndMinutes) {
		final String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern pattern = Pattern.compile(TIME_PATTERN);
		Matcher matcher = pattern.matcher(hourAndMinutes);
		if (matcher.matches())
			return true;
		else
			return false;

	}
}
