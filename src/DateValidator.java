import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface DateValidator {

	public static boolean IsValid(String date) {
		final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		Pattern pattern = Pattern.compile(DATE_PATTERN);
		Matcher matcher = pattern.matcher(date);
		if (matcher.matches())
			return true;
		else
			return false;

	}

	public static boolean IsMonthAndYearValid(String monthAndYear) {
		final String MONTH_YEAR_PATTERN = "(0?[1-9]|1[012])/((19|20)\\d\\d)";
		Pattern pattern = Pattern.compile(MONTH_YEAR_PATTERN);
		Matcher matcher = pattern.matcher(monthAndYear);
		if (matcher.matches())
			return true;
		else
			return false;
	}

}
