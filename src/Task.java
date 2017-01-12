public class Task extends Event {

	public Task() {
		super();
		setMarker();
		setStartDate();
		setStartHourAndMinutes();
		setEndDate();
		setEndHourAndMinutes();
		setDescription();
	}

	public Task(int marker, int startMonth, int startDay, int startYear, int startHour, int startMinutes, int endMonth,
			int endDay, int endYear, int endHour, int endMinutes, String description) {
		super(marker, startMonth, startDay, startYear, startHour, startMinutes, endMonth, endDay, endYear, endHour,
				endMinutes, description);
	}

	@Override
	public String toString() {
		String startDate = String.format("%d %s %d", getStartDay(), getStartMonthName(), getStartYear());
		String endDate = String.format("%d %s %d", getEndDay(), getEndMonthName(), getEndYear());
		String value = String.format(
				"%8s | %14s | %17s | %17s | %40s%n"
						+ "                    |          |                | %8d:%-8d | %8d:%-8d |%n"
						+ "\t\t----|----------|----------------|-------------------|-------------------|-----------------------------------------%n",
				"Task", getMarker(), startDate, endDate, getDescription(), getStartHour(), getStartMinutes(),
				getEndHour(), getEndMinutes());
		return value;
	}

}
