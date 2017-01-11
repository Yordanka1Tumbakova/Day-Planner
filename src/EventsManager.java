import java.util.Calendar;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class EventsManager {
	public static Scanner scan = new Scanner(System.in);
	public static Queue<Event> events = new LinkedList<Event>();
	public static Stack<Event> temp = new Stack<Event>();
	public static Stack<Event> temp2 = new Stack<Event>();
	public static boolean flag = false;

	public static boolean isQueueEmpty() {
		if (events.isEmpty()) {
			return true;
		}
		return false;
	}

	public static Event addEvent(int eventType) {

		Event e = null;
		if (eventType == 1) {
			Meeting meeting = new Meeting();
			e = addEventToQueue(meeting);

		} else if (eventType == 2) {
			Task task = new Task();
			e = addEventToQueue(task);

		}
		return e;
	}

	public static void addChangedEvent(int eventType) {
		if (eventType == 1) {
			Meeting meeting = new Meeting();
			addEventToStack(meeting);
		} else if (eventType == 2) {
			Task task = new Task();
			addEventToStack(task);
		}
	}

	public static void addEventToStack(Event e) {
		temp.add(e);
	}

	public static Event addEventToQueue(Event e) {
		events.add(e);
		return e;
	}

	public static void checkingInstance(Event e) {
		if (e instanceof Meeting) {
			Meeting m = (Meeting) e;
			System.out.print(m.toString());
		} else if (e instanceof Task) {
			Task t = (Task) e;
			System.out.print(t.toString());
		}
	}

	public static void checkingAndPrintingByType() {
		System.out.println("\n\t\t\tPrinting the events:");
		int i = 1;
		System.out.println();
		tableMaker();
		for (Event e : events) {
			System.out.printf("\t\t%3d | ", i);
			i++;
			if (e instanceof Meeting) {
				Meeting m = (Meeting) e;
				System.out.print(m.toString());

			} else if (e instanceof Task) {
				Task t = (Task) e;
				System.out.print(t.toString());

			}
		}

	}

	public static void processEvents() {

		EventLoader evLoad = new EventLoader();
		Queue<Event> eventFromFile = new LinkedList<Event>(evLoad.importDataFromFile());

		for (Event e : eventFromFile) {
			if (e instanceof Meeting) {
				Meeting m = (Meeting) e;
				addEventToQueue(m);
			} else if (e instanceof Task) {
				Task t = (Task) e;
				addEventToQueue(t);
			}
		}
		System.out.println();

	}

	public static void LoadInfoFromFile() {
		processEvents();
	}

	public static void choosingMonthOption(int options) {
		switch (options) {
		case 1: {
			printingByCurrentMonth();
			break;
		}
		case 2: {
			printingByEnteredMonth();
			break;
		}
		case 3:
			break;
		}
	}

	public static void choosingDeleteOptions(int optionChoice) {

		switch (optionChoice) {
		case 1: {
			deleteEventsByMonthAndYear();
			break;
		}
		case 2: {
			deleteEventsByDayMonthAndYear();
			break;
		}
		case 3: {
			deleteEventById(eventToDelete());
			break;
		}
		case 4:
			break;
		}
	}

	public static void choosingEditingOptions(int optionChoice) {

		switch (optionChoice) {
		case 1: {
			editEventsByDayMonthAndYear();
			break;
		}
		case 2: {
			editEventById(eventToDelete());
			break;
		}
		case 3:
			break;
		}
	}

	public static void printingByMonth() {

		boolean optionsFlag = false;

		int options = 0;

		while (!optionsFlag) {
			try {
				do {

					System.out.print("\n\t\t\tChoose from the following options:"
							+ "\n\t\t\t\t1.Printing the events for the current month\n\t\t\t\t2.Printing the events for the month of your choice"
							+ "\n\t\t\t\t3.Go back to the main menu"
							+ "\n\t\t\tPlease, enter the option (1 - 3) you want to use:");

					options = Integer.parseInt(scan.nextLine());

					if (options < 1 || options > 3) {
						System.out.println("\n\t\tInvalid input! Please, enter your choice again!");
						optionsFlag = false;
					} else {
						choosingMonthOption(options);
						optionsFlag = true;
					}

				} while (options < 1 || options > 3);

				optionsFlag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}

	}

	public static void printingByEnteredMonth() {

		String monthName = "";

		int year = 0;
		int i = 1;
		int flag = 0;
		String date = "";
		boolean flagMonthYear = false;
		while (!flagMonthYear) {
			System.out.print("\t\tEnter the date in format MM/YEAR :");
			date = scan.nextLine();
			if (DateValidator.IsMonthAndYearValid(date))
				flagMonthYear = true;
		}
		String str[] = date.split("/");
		monthName = InputValidator.reverseMonthChecker(Integer.parseInt(str[0]) - 1);
		year = Integer.parseInt(str[1]);
		System.out.println();
		tableMaker();
		for (Event e : events) {

			if (e.getStartMonthName().equals(monthName) && e.getStartYear() == year) {
				System.out.printf("\t\t%3d | ", i);
				i++;
				checkingInstance(e);
				flag = 1;
			}
		}
		System.out.println();
		if (flag == 0) {
			System.out.println("\t\t\tThere are no matching records!\n");
		}
		System.out.println();

	}

	public static void printingByCurrentMonth() {
		int flag = 0;
		int i = 1;
		String currentMonth = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println();
		tableMaker();
		for (Event e : events) {

			if (e.getStartMonthName().equals(currentMonth) && e.getStartYear() == currentYear) {
				System.out.printf("\t\t%3d | ", i);
				i++;
				checkingInstance(e);
				flag = 1;
			}
		}
		System.out.println();
		if (flag == 0) {
			System.out.println("\t\t\tThere are no matching records!\n");
		}
		System.out.println();
	}

	public static void printingByDay() {
		int flag = 0;
		int i = 1;
		System.out.println();
		String date = setDate();
		String str[] = date.split("/");
		int day = Integer.parseInt(str[0]);
		String monthName = InputValidator.reverseMonthChecker(Integer.parseInt(str[1]) - 1);
		int year = Integer.parseInt(str[2]);
		System.out.println("\n");
		tableMaker();
		for (Event e : events) {

			if (e.getStartDay() == day && e.getStartMonthName().equals(monthName) && e.getStartYear() == year) {
				System.out.printf("\t\t%3d | ", i);
				i++;
				checkingInstance(e);
				flag = 1;
			}
		}
		System.out.println();
		if (flag == 0) {
			System.out.println("\t\t\tThere are no matching records!\n");
		}
		System.out.println();
	}

	public static void tableMaker() {
		System.out.printf(
				"\t\t%3s | %8s | %14s | %17s | %17s | %40s %n\t\t    |          |                | %17s |"
						+ "                   |%n",
				"ID", "  Event ", "Marker    ", "Beginning of  ", "End of the event", "Description               ",
				"  the event   ");
		System.out.printf(
				"\t\t____|__________|________________|___________________|___________________|__________________________________________%n");
	}

	public static Queue<Event> returnQueue() {
		return events;
	}

	public static void deleteEventOptions() {
		int optionChoice;
		boolean flag = false;

		while (!flag) {
			do {
				System.out.print("\t\t\tDelete events by:\n\t\t1.Entering month and year\n\t\t"
						+ "2.Entered day, month and year\n\t\t3.Entering the event's ID\n\t\t4.Go back to the main menu"
						+ "\n\t\tPlease, enter the number of the option (1-4) you want to use:");

				optionChoice = Integer.parseInt(scan.nextLine());
				if (optionChoice < 1 || optionChoice > 4)
					System.out.println("\t\tInvalid input! Please, enter your choice again!");
				else
					flag = true;
			} while (optionChoice < 1 || optionChoice > 4);
			choosingDeleteOptions(optionChoice);
		}
	}

	public static void editEventOptions() {
		int optionChoice;
		boolean flag = false;

		while (!flag) {
			do {
				System.out
						.print("\t\t\tEdit events by:\n\t\t1.Entering the start day, month and year\n\t\t2.Entering the event's ID\n\t\t3.Go back to the main menu"
								+ "\n\t\tPlease, enter the number of the option (1 - 3) you want to use:");

				optionChoice = Integer.parseInt(scan.nextLine());
				if (optionChoice < 1 || optionChoice > 3) {
					System.out.println("\t\tInvalid input! Please, enter your choice again!");
					flag = false;
				} else
					flag = true;
			} while (optionChoice < 1 || optionChoice > 3);
			choosingEditingOptions(optionChoice);
		}
	}

	public static int eventToDelete() {
		return InputValidator.eventToDeleteValidator();

	}

	public static int eventToChange() {
		return InputValidator.eventToDeleteValidator();

	}

	public static void deleteEventsByMonthAndYear() {
		String monthName = "";
		int year;
		String date = "";
		int count = 1;
		int i = 1;
		int n;
		int[] elementsToDelete = new int[events.size()];
		boolean flag = false;
		boolean flagMonthYear;

		while (!flag) {
			flagMonthYear = false;
			while (!flagMonthYear) {
				System.out.print("\t\tEnter date in format MM/YEAR :");

				date = scan.nextLine();
				if (DateValidator.IsMonthAndYearValid(date))
					flagMonthYear = true;
				else {
					System.out.println("Invalid input! Please, try again!");
					flagMonthYear = false;
				}
			}
			String str[] = date.split("/");
			monthName = InputValidator.reverseMonthChecker(Integer.parseInt(str[0]) - 1);
			year = Integer.parseInt(str[1]);

			for (Event e : events) {
				if (e.getStartMonthName().equals(monthName) && e.getStartYear() == year) {
					flag = true;
					elementsToDelete[i] = count;
					i++;
				}
				count++;
			}
			for (n = i; n > 0; n--) {

				deleteEventById(elementsToDelete[n]);
			}
			if (i <= 1) {
				int answer = tryAgainYesOrNo();
				if (answer == 1)
					flag = false;
				else if (answer == 2) {
					flag = true;
					System.out.println();
				}
			}
		}

	}

	public static void deleteEventsByDayMonthAndYear() {
		String monthName = "";
		int year;
		String date = "";
		int count = 1;
		int i = 1;
		int n;
		int day;
		int[] elementsToDelete = new int[events.size()];
		boolean flag = false;
		boolean flagMonthYear;

		while (!flag) {
			flagMonthYear = false;
			while (!flagMonthYear) {
				System.out.print("\t\tEnter date in format DD/MM/YEAR :");
				date = scan.nextLine();
				if (DateValidator.IsValid(date))
					flagMonthYear = true;
				else {
					System.out.println("Invalid input! Please, try again!");
					flagMonthYear = false;
				}
			}
			String str[] = date.split("/");
			day = Integer.parseInt(str[0]);
			monthName = InputValidator.reverseMonthChecker(Integer.parseInt(str[1]) - 1);
			year = Integer.parseInt(str[2]);

			for (Event e : events) {
				if (e.getStartDay() == day && e.getStartMonthName().equals(monthName) && e.getStartYear() == year) {
					flag = true;
					elementsToDelete[i] = count;
					i++;
				}
				count++;
			}
			for (n = i; n > 0; n--) {

				deleteEventById(elementsToDelete[n]);
			}
			if (i <= 1) {
				int answer = tryAgainYesOrNo();
				if (answer == 1)
					flag = false;
				else if (answer == 2) {
					flag = true;
					System.out.println();
				}
			}
		}

	}

	public static void deleteEventById(int eventToDelete) {
		int count = 0;
		int stackSize = 0;
		int queueSize = events.size();

		System.out.println();
		for (count = 1; count <= queueSize; ++count) {
			temp.add(events.poll());
			if (count == eventToDelete) {
				temp.pop();
			}
		}
		stackSize = temp.size();
		try {
			for (count = 0; count < stackSize; count++) {
				temp2.add(temp.pop());
			}
		} catch (EmptyStackException e) {
			System.out.println("The stack is empty");
		}
		try {
			for (count = 0; count < stackSize; count++) {
				events.add(temp2.pop());
			}
		} catch (EmptyStackException e) {
			System.out.println("The stack is empty");
		}
	}

	public static void editEventsByDayMonthAndYear() {
		String monthName = "";
		int year;
		String date = "";
		int count = 1;
		int i = 1;
		int day;
		int[] elementsToEdit = new int[events.size()];
		boolean flag = false;
		boolean flagMonthYear;

		while (!flag) {
			flagMonthYear = false;

			while (!flagMonthYear) {
				System.out.print("\t\tEnter date in format DD/MM/YEAR :");
				date = scan.nextLine();
				if (DateValidator.IsValid(date))
					flagMonthYear = true;
				else {
					System.out.println("\t\tInvalid input! Please, try again!");
					flagMonthYear = false;
				}
			}
			String str[] = date.split("/");
			day = Integer.parseInt(str[0]);
			monthName = InputValidator.reverseMonthChecker(Integer.parseInt(str[1]) - 1);
			year = Integer.parseInt(str[2]);
			System.out.println();
			for (Event e : events) {
				if (e.getStartDay() == day && e.getStartMonthName().equals(monthName) && e.getStartYear() == year) {
					tableMaker();
					System.out.printf("\t\t%3d | ", count);
					System.out.print(e.toString());
					flag = true;
					elementsToEdit[i] = count;
					i++;
				}
				count++;
			}
			if (i == 2) {
				editEventById(elementsToEdit[1]);
				flag = true;
			}
			if (i > 2) {
				editEventById(checkingEventByHour(day, monthName, year));
				flag = true;

			}
			if (i <= 1) {
				System.out.println("\t\tThere are no events on that day.");
				int answer = tryAgainYesOrNo();
				if (answer == 1)
					flag = false;
				else if (answer == 2) {
					flag = true;
					System.out.println();
				}

			}
		}

	}

	public static void editEventById(int eventToChange) {
		int count = 0;
		int stackSize = 0;
		int queueSize = events.size();
		eventToChange--;
		for (count = 0; count < queueSize; count++) {
			temp.add(events.poll());
			if (count == eventToChange) {
				temp.pop();
				System.out.print("\n\t\t\tEnter the changed event:");
				EventsManager.addChangedEvent(DayPlannerStarter.chooseOption());

				System.out.println();
			}
		}
		stackSize = temp.size();
		try {
			for (count = 0; count < stackSize; count++) {
				temp2.add(temp.pop());
			}
		} catch (EmptyStackException e) {
			System.out.println("The stack is empty");
		}
		try {
			for (count = 0; count < stackSize; count++) {
				events.add(temp2.pop());
			}
		} catch (EmptyStackException e) {
			System.out.println("The stack is empty");
		}

	}

	/*
	 * public static void saveInFileChoice(Event e) { char answer; boolean flag
	 * = false; while (!flag) { do {
	 * System.out.print("\n\t\tDo you want to save this event? " +
	 * "Enter \'y\' for YES or \'n\' for NO:"); answer =
	 * scan.nextLine().charAt(0); if (answer != 'y' && answer != 'n' && answer
	 * != 'Y' && answer != 'N') {
	 * System.out.println("\t\tInvalid input! Please, enter your choice again!"
	 * ); flag = false; } else { if (answer == 'Y' || answer == 'y') {
	 * WriteDataInFile.appendEventInFile(e); flag = true; } else if (answer ==
	 * 'N' || answer == 'n') { flag = true; } } } while (answer != 'y' && answer
	 * != 'n' && answer != 'Y' && answer != 'N'); }
	 * 
	 * }
	 */

	public static void saveEventInFile(Event e) {
		WriteDataInFile.appendEventInFile(e);
	}

	public static void saveEventsInFile() {
		WriteDataInFile.saveInFile(events);
	}

	public static int tryAgainYesOrNo() {
		char answer;
		int flag = 0;
		while (flag == 0) {
			do {
				System.out.print("\n\t\tDo you want to try again? " + "Enter \'y\' for YES or \'n\' for NO:");
				answer = scan.nextLine().charAt(0);
				if (answer != 'y' && answer != 'n' && answer != 'Y' && answer != 'N') {
					System.out.println("\t\tInvalid input! Please, enter your choice again!");
					flag = 0;
				} else {
					if (answer == 'Y' || answer == 'y') {
						flag = 1;
					} else if (answer == 'N' || answer == 'n') {
						flag = 2;
					}
				}
			} while (answer != 'y' && answer != 'n' && answer != 'Y' && answer != 'N');
		}
		return flag;

	}

	public static String setDate() {
		String date = "";
		int month = 0;
		String monthName = "";

		boolean flag = false;
		while (!flag) {
			System.out.print("\t\tEnter date in format DD/MM/YEAR :");

			date = scan.nextLine();
			if (DateValidator.IsValid(date)) {
				String[] str = date.split("/");

				if (InputValidator.monthValidator(Integer.parseInt(str[1]))) {

					System.out.println("\t\t\tInvalid month! Please, try again!");
					flag = false;
				} else {
					month = Integer.parseInt(str[1]);
					monthName = InputValidator.reverseMonthChecker(month - 1);

					flag = true;
					if (InputValidator.dayValidator(Integer.parseInt(str[0]), monthName,
							Integer.parseInt(str[2])) == true) {
						System.out.println("\t\t\tInvalid day! Please, try again!");
						flag = false;
					} else {
						flag = true;
					}
				}
			} else {
				System.out.println("\t\t\tInvalid date! Please, try again!");
				flag = false;
			}
		}
		return date;
	}

	public static int queueSize() {

		return events.size();
	}

	public static boolean IsQueueEmpty() {
		if (events.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static int checkingEventByHour(int day, String monthName, int year) {
		int hour = 0;
		int minutes = 0;
		String hourAndMinutes;
		int count = 1;
		int elementToEdit = 0;
		int flagInput = 1;
		boolean flag;
		while (flagInput == 1) {
			flag = false;
			while (!flag) {
				System.out
						.print("\n\t\tSince there is more than one event in this particular day,\n\t\tplease enter the"
								+ " time of the event you want to change in format HH:MM :");
				hourAndMinutes = scan.nextLine();
				if (HourAndMinutesValidator.IsValid(hourAndMinutes)) {
					String[] str = hourAndMinutes.split(":");
					hour = Integer.parseInt(str[0]);
					minutes = Integer.parseInt(str[1]);
					flag = true;
				} else {
					System.out.println("\t\t\tInvalid time! Please, try again!");
					flag = false;
				}
			}
			for (Event e : events) {
				if (e.getStartDay() == day && e.getStartMonthName().equals(monthName) && e.getStartYear() == year
						&& e.getStartHour() == hour && e.getStartMinutes() == minutes) {

					System.out.println();
					tableMaker();
					System.out.printf("\t\t%3d | ", count);
					System.out.print(e.toString());
					elementToEdit = count;
					flagInput = 0;

				}
				count++;
			}
			if (elementToEdit == 0) {
				System.out.println("\t\tThere are no matches found. Try again!");
				flagInput = 1;
			}
		}
		return elementToEdit;
	}

	public static void LoadSavedEvents() {
		while (!flag) {
			LoadInfoFromFile();
			flag = true;
		}
	}
}
