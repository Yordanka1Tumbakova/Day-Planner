import java.util.Calendar;
import java.util.Scanner;

public abstract class Event {
	Scanner scan = new Scanner(System.in);

	private int startDay;
	private int startMonth;
	private int startYear;
	private int startHour;
	private int startMinutes;

	private int endDay;
	private int endMonth;
	private int endYear;
	private int endHour;
	private int endMinutes;

	private String date;
	private String time;

	private String marker;
	private String description;

	Calendar calendarStart = Calendar.getInstance();
	Calendar calendarEnd = Calendar.getInstance();
	public static boolean sameDateFlag = false;

	public Event() {

	}

	public Event(int marker, int startMonth, int startDay, int startYear, int startHour, int startMinutes, int endMonth,
			int endDay, int endYear, int endHour, int endMinutes, String description) {

		this.marker = InputValidator.markerChecker(marker);
		calendarStart.set(Calendar.MONTH, startMonth);
		calendarStart.set(Calendar.DAY_OF_MONTH, startDay);
		calendarStart.set(Calendar.YEAR, startYear);
		calendarStart.set(Calendar.HOUR_OF_DAY, startHour);
		calendarStart.set(Calendar.MINUTE, startMinutes);
		calendarEnd.set(Calendar.MONTH, endMonth);
		calendarEnd.set(Calendar.DAY_OF_MONTH, endDay);
		calendarEnd.set(Calendar.YEAR, endYear);
		calendarEnd.set(Calendar.HOUR_OF_DAY, endHour);
		calendarEnd.set(Calendar.MINUTE, endMinutes);
		this.description = description;

	}

	public void setMarker() {
		this.marker = InputValidator.markerValidator();
	}

	public void setStartDate() {
		System.out.println("\n\t\t\tSetting the start date:");
		boolean flag = false;
		while (!flag) {
			System.out.print("\t\tEnter date in format DD/MM/YEAR :");
			this.date = scan.nextLine();
			flag = InputValidator.checkDate(this.date);
		}

		String[] str = this.date.split("/");
		setStartDay(Integer.parseInt(str[0]));
		setStartMonth(Integer.parseInt(str[1]));
		setStartYear(Integer.parseInt(str[2]));

	}

	public void setEndDate() {
		System.out.println("\n\t\t\tSetting the end date:");
		boolean choiceFlag = false;
		int optionChoice = 0;
		while (!choiceFlag) {
			try {
				do {
					System.out.print(
							"\t\tWhen does this event end:\n\t\t\t1.Today\n\t\t\t2.Another date\n\t\tPlease, enter your choice (1 or 2):");
					optionChoice = Integer.parseInt(scan.nextLine());
					if (optionChoice < 1 || optionChoice > 2) {
						System.out.println("\n\t\tInvalid input! Please, try again!");
						choiceFlag = false;
					} else {
						choiceFlag = true;
					}
				} while (optionChoice < 1 || optionChoice > 2);
			} catch (NumberFormatException e) {
				System.out.println("\n\t\tInvalid input! Please, try again!");
			}
		}
		if (optionChoice == 1) {
			sameDateFlag = true;
			String[] str = this.date.split("/");
			setEndDay(Integer.parseInt(str[0]));
			setEndMonth(Integer.parseInt(str[1]));
			setEndYear(Integer.parseInt(str[2]));
		} else {
			String[] str;
			boolean flag = false;
			while (!flag) {
				System.out.print("\t\tEnter date in format DD/MM/YEAR :");
				this.date = scan.nextLine();
				flag = InputValidator.checkDate(this.date);
				if (flag) {
					str = this.date.split("/");
					if (getStartYear() > Integer.parseInt(str[2]) || getStartMonth() + 1 > Integer.parseInt(str[1])
							|| getStartDay() > Integer.parseInt(str[0])) {
						System.out.println("\n\t\tInvalid input! The entered end date is before the start one. "
								+ "Please, try again!");
						flag = false;
					} else {
						sameDateFlag = true;
						flag = true;
					}
				}
			}

			str = this.date.split("/");
			setEndDay(Integer.parseInt(str[0]));
			setEndMonth(Integer.parseInt(str[1]));
			setEndYear(Integer.parseInt(str[2]));
		}
	}

	public void setStartHourAndMinutes() {
		System.out.println("\n\t\t\tSetting the start time:");
		boolean flag = false;
		while (!flag) {
			System.out.print("\t\tEnter time in format HH:MM :");
			this.time = scan.nextLine();
			flag = InputValidator.checkHourAndMinutes(this.time);
		}
		String[] str = this.time.split(":");
		setStartHour(Integer.parseInt(str[0]));
		setStartMinutes(Integer.parseInt(str[1]));

	}

	public void setEndHourAndMinutes() {
		System.out.println("\n\t\t\tSetting the end time:\n\t\t (the minutes must have at least 2 digits)");
		String[] str;
		boolean flag = false;
		while (!flag) {
			System.out.print("\t\tEnter time in format HH:MM :");
			this.time = scan.nextLine();
			flag = InputValidator.checkHourAndMinutes(this.time);
			str = this.time.split(":");
			if (flag) {

				if (sameDateFlag) {
					if (getStartHour() > Integer.parseInt(str[0])) {
						System.out.println("\n\t\tInvalid input! The entered end time is before the start one. "
								+ "Please, try again!");
						flag = false;
					} else if (getStartHour() == Integer.parseInt(str[0])) {
						if (getStartMinutes() >= Integer.parseInt(str[1])) {
							System.out
									.println("\n\t\tInvalid input! The entered end time is the same as the start one. "
											+ "Please, try again!");
							flag = false;
						} else
							flag = true;
					} else
						flag = true;
				}
			}
		}
		System.out.println();

		str = this.time.split(":");

		setEndHour(Integer.parseInt(str[0]));
		setEndMinutes(Integer.parseInt(str[1]));
	}

	public void setStartDay(int startDay) {

		this.startDay = startDay;
		calendarStart.set(Calendar.DAY_OF_MONTH, this.startDay);

	}

	private void setEndDay(int endDay) {
		this.endDay = endDay;
		calendarEnd.set(Calendar.DAY_OF_MONTH, this.endDay);

	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth - 1;
		calendarStart.set(Calendar.MONTH, this.startMonth);

	}

	private void setEndMonth(int endMonth) {
		this.endMonth = endMonth - 1;
		calendarEnd.set(Calendar.MONTH, this.endMonth);

	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
		calendarStart.set(Calendar.YEAR, this.startYear);
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
		calendarEnd.set(Calendar.YEAR, this.endYear);
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
		calendarStart.set(Calendar.HOUR_OF_DAY, this.startHour);
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
		calendarEnd.set(Calendar.HOUR_OF_DAY, this.endHour);
	}

	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
		calendarStart.set(Calendar.MINUTE, this.startMinutes);
	}

	public void setEndMinutes(int endMinutes) {
		this.endMinutes = endMinutes;
		calendarEnd.set(Calendar.MINUTE, this.endMinutes);
	}

	public void setDescription() {
		System.out.print("\t\tEnter event description:");
		this.description = scan.nextLine();
		if (this.description.isEmpty()) {
			this.description = " ";
		}

	}

	public String getMarker() {
		return this.marker;
	}

	public int getStartDay() {
		return calendarStart.get(Calendar.DAY_OF_MONTH);
	}

	public int getEndDay() {
		return calendarEnd.get(Calendar.DAY_OF_MONTH);
	}

	public int getStartMonth() {
		return calendarStart.get(Calendar.MONTH);

	}

	public int getEndMonth() {
		return calendarEnd.get(Calendar.MONTH);

	}

	public String getStartMonthName() {
		return InputValidator.reverseMonthChecker(getStartMonth());

	}

	public String getEndMonthName() {
		return InputValidator.reverseMonthChecker(getEndMonth());

	}

	public int getStartYear() {
		return calendarStart.get(Calendar.YEAR);
	}

	public int getEndYear() {
		return calendarEnd.get(Calendar.YEAR);
	}

	public int getStartHour() {
		return calendarStart.get(Calendar.HOUR_OF_DAY);
	}

	public int getEndHour() {
		return calendarEnd.get(Calendar.HOUR_OF_DAY);
	}

	public int getStartMinutes() {
		return calendarStart.get(Calendar.MINUTE);
	}

	public int getEndMinutes() {
		return calendarEnd.get(Calendar.MINUTE);
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		String startDate = String.format("%d %s %d", getStartDay(), getStartMonthName(), getStartYear());
		String endDate = String.format("%d %s %d", getEndDay(), getEndMonthName(), getEndYear());
		String value = String.format(
				"%8s | %14s | %17s | %17s | %40s%n"
						+ "                    |          |                | %8d:%-8d | %8d:%-8d |%n"
						+ "\t\t----|----------|----------------|-------------------|-------------------|-----------------------------------------%n",
				"Event", getMarker(), startDate, endDate, getDescription(), getStartHour(), getStartMinutes(),
				getEndHour(), getEndMinutes());
		return value;

	}

}
