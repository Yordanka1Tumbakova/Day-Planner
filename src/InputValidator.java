import java.util.Calendar;
import java.util.Scanner;

public class InputValidator {
	public static String markerEvent[] = { "public", "confidential", "private" };
	static Scanner scan = new Scanner(System.in);

	public static boolean checkIfMonthHas31Days(int day, String currentMonth) {
		boolean flag = false;
		if (currentMonth.equals("January") || currentMonth.equals("March") || currentMonth.equals("May")
				|| currentMonth.equals("July") || currentMonth.equals("August") || currentMonth.equals("October")
				|| currentMonth.equals("December")) {
			if (dayForMonthWith31Days(day)) {
				flag = true;
			}
		} else
			flag = false;
		return flag;
	}

	public static boolean checkIfMonthHas30Days(int day, String currentMonth) {
		boolean flag = false;
		if (currentMonth.equals("April") || currentMonth.equals("June") || currentMonth.equals("September")
				|| currentMonth.equals("November")) {
			if (dayForMonthWith30Days(day)) {
				flag = true;
			}
		}

		else
			flag = false;
		return flag;
	}

	public static boolean checkIfMonthHas29Days(int day) {
		if (day < 1 || day > 29) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkIfMonthHas28Days(int day) {
		if (day < 1 || day > 28) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean dayForMonthWith31Days(int day) {
		if (day < 1 || day > 31)
			return false;
		else
			return true;
	}

	public static boolean dayForMonthWith30Days(int day) {
		if (day < 1 || day > 30)
			return false;
		else
			return true;
	}

	public static boolean dayForMonthWith29Days(int day) {
		if (day < 1 || day > 29)
			return false;
		else
			return true;
	}

	public static boolean dayForMonthWith28Days(int day) {
		if (day < 1 || day > 28)
			return false;
		else
			return true;
	}

	public static int dayForMonthWith31Days(int day, String currentMonth) {
		boolean flag = false;
		while (!flag) {
			try {
				do {
					System.out.print("\t\tEnter a day between 1 and 31:");
					day = Integer.parseInt(scan.nextLine());
					if (day < 1 || day > 31) {
						System.out.println("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (day < 1 || day > 31);
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return day;
	}

	public static int dayForMonthWith30Days(int day, String currentMonth) {
		boolean flag = false;
		while (!flag) {
			try {
				do {
					System.out.print("\t\tEnter a day between 1 and 30:");
					day = Integer.parseInt(scan.nextLine());
					if (day < 1 || day > 30) {
						System.out.print("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (day < 1 || day > 30);
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return day;
	}

	public static int dayForMonthWith29Days(int day, String currentMonth) {
		boolean flag = false;
		while (!flag) {
			try {
				do {
					System.out.print("\t\tEnter a day between 1 and 29:");
					day = Integer.parseInt(scan.nextLine());
					if (day < 1 || day > 29) {
						System.out.print("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (day < 1 || day > 29);
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return day;
	}

	public static int dayForMonthWith28Days(int day, String currentMonth) {
		boolean flag = false;
		while (!flag) {
			try {
				do {
					System.out.print("\t\tEnter a day between 1 and 28:");
					day = Integer.parseInt(scan.nextLine());
					if (day < 1 || day > 28) {
						System.out.print("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (day < 1 || day > 28);
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return day;
	}

	private static boolean checkIfMonthIsFebruary(int day, String currentMonth) {
		if (currentMonth.equals("February"))
			return true;
		else
			return false;
	}

	public static boolean dayValidator(int day, String month, int year) {
		boolean flag = true;
		if ((checkIfMonthHas31Days(day, month)) == true) {
			flag = false;
		} else if ((checkIfMonthHas30Days(day, month) == true)) {
			flag = false;
		} else if ((checkIfMonthIsFebruary(day, month)) == true) {
			boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));

			if (isLeapYear) {
				if (checkIfMonthHas29Days(day))
					flag = false;

			} else {
				if (checkIfMonthHas28Days(day))
					flag = false;
			}
		} else {
			flag = true;
		}
		return flag;
	}

	public static int dateValidator(String currentMonth) {

		int day = 0;

		if ((checkIfMonthHas31Days(day, currentMonth) == true)) {
			day = dayForMonthWith31Days(day, currentMonth);

			return day;
		} else if ((checkIfMonthHas30Days(day, currentMonth) == true)) {
			day = dayForMonthWith30Days(day, currentMonth);

			return day;
		} else if ((checkIfMonthIsFebruary(day, currentMonth)) == true) {

			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			boolean isLeapYear = ((currentYear % 4 == 0) && (currentYear % 100 != 0) || (currentYear % 400 == 0));

			if (isLeapYear) {
				day = dayForMonthWith29Days(day, currentMonth);
				return day;

			} else {
				day = dayForMonthWith28Days(day, currentMonth);
				return day;
			}

		}

		return day;

	}

	public static boolean monthValidator(int month) {
		if (month < 1 || month > 12) {
			return true;
		} else {
			return false;
		}
	}

	public static String markerValidator() {
		int markerChoice = 0;
		String marker = "";
		boolean flag = false;
		while (!flag) {
			try {
				do {
					System.out.print("\t\tMarkers: \n\t\t\t 1 for public \n\t\t\t 2 for confidential "
							+ "\n\t\t\t 3 for private\n\t\tEnter your choice:");
					markerChoice = Integer.parseInt(scan.nextLine());
					marker = markerChecker(markerChoice);
				} while (markerChoice < 1 || markerChoice > 3);
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return marker;
	}

	public static String markerChecker(int marker) {

		switch (marker) {

		case 1:
			return markerEvent[0];
		case 2:
			return markerEvent[1];
		case 3:
			return markerEvent[2];

		default:
			return "";
		}
	}

	public static int markerChecker(String marker) {
		switch (marker) {

		case "public":
			return 1;
		case "confidential":
			return 2;
		case "private":
			return 3;

		default:
			return 0;
		}
	}

	public static int monthChecker(String month) {
		switch (month) {

		case "January":
			return 1;
		case "February":
			return 2;
		case "March":
			return 3;
		case "April":
			return 4;
		case "May":
			return 5;
		case "June":
			return 6;
		case "July":
			return 7;
		case "August":
			return 8;
		case "September":
			return 9;
		case "October":
			return 10;
		case "November":
			return 11;
		case "December":
			return 12;

		default:
			return 0;
		}
	}

	public static boolean checkHourAndMinutes(String time) {
		if (HourAndMinutesValidator.IsValid(time)) {

			return true;
		} else {
			System.out.println("\t\t\tInvalid time! Please, try again!");
			return false;
		}
	}

	public static boolean checkDate(String date) {

		if (DateValidator.IsValid(date)) {
			String[] str = date.split("/");

			if (InputValidator.monthValidator(Integer.parseInt(str[1]))) {

				System.out.println("\t\t\tInvalid month! Please, try again!");
				return false;
			} else {
				String monthName = InputValidator.reverseMonthChecker(Integer.parseInt(str[1]) - 1);

				if (InputValidator.dayValidator(Integer.parseInt(str[0]), monthName,
						Integer.parseInt(str[2])) == true) {
					System.out.println("\t\t\tInvalid day! Please, try again!");
					return false;
				} else
					return true;
			}
		} else {
			System.out.println("\t\t\tInvalid date! Please, try again!");
			return false;
		}

	}

	public static String reverseMonthChecker(int month) {
		switch (month) {

		case 0:
			return "January";
		case 1:
			return "February";
		case 2:
			return "March";
		case 3:
			return "April";
		case 4:
			return "May";
		case 5:
			return "June";
		case 6:
			return "July";
		case 7:
			return "August";
		case 8:
			return "September";
		case 9:
			return "October";
		case 10:
			return "November";
		case 11:
			return "December";

		default:
			return "";
		}
	}

	public static int eventToDeleteValidator() {

		int eventToDelete = 0;
		boolean flag = false;
		int counter = EventsManager.queueSize();

		while (!flag) {
			try {
				do {
					System.out.print("\t\tEnter event number from 1 to " + counter + ":");
					eventToDelete = Integer.parseInt(scan.nextLine());
					if (eventToDelete < 1 || eventToDelete > counter) {
						System.out.println("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (eventToDelete < 1 || eventToDelete > counter);
				flag = true;
			}

			catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return eventToDelete;
	}
}