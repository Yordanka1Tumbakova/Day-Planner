import java.util.Scanner;

public class DayPlannerStarter {
	static Scanner scan = new Scanner(System.in);

	public static int chooseOption() {
		int option = 0;
		boolean flag = false;

		while (!flag) {
			try {
				do {
					System.out.print("\n\t\tEnter \"1\" for Meeting or \"2\" for Task:");
					option = Integer.parseInt(scan.nextLine());
					if (option < 1 || option > 2) {
						System.out.println("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (option < 1 || option > 2);
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
		return option;
	}

	public static void choosingOption(int optionChoice) {
		switch (optionChoice) {

		case 1: {
			System.out.print("\t\t\tAdding new event");

			EventsManager.saveEventInFile(EventsManager.addEvent(chooseOption()));
			EventsManager.checkingAndPrintingByType();

			break;
		}
		case 2: {
			if (EventsManager.isQueueEmpty()) {

				System.out.print("\t\t\tThere are no existing events. Add or load some and try again!\n");
				break;

			} else {
				EventsManager.checkingAndPrintingByType();
				EventsManager.editEventOptions();
				EventsManager.checkingAndPrintingByType();
				EventsManager.saveEventsInFile();
				break;

			}
		}
		case 3: {
			if (EventsManager.isQueueEmpty()) {

				System.out.print("\t\t\tThere are no existing events. Add or load some and try again!\n");
				break;

			} else {
				EventsManager.checkingAndPrintingByType();
				EventsManager.deleteEventOptions();
				System.out.println("\t\t\tThe remaining events are:\n");
				EventsManager.saveEventsInFile();
				EventsManager.checkingAndPrintingByType();
				break;
			}
		}
		case 4: {

			EventsManager.checkingAndPrintingByType();
			break;
		}
		case 5: {
			if (EventsManager.isQueueEmpty()) {

				System.out.print("\t\t\tThere are no existing events. Add or load some and try again!\n");
				break;

			} else {
				EventsManager.checkingAndPrintingByType();
				EventsManager.printingByDay();
				break;
			}
		}
		case 6: {
			if (EventsManager.isQueueEmpty()) {

				System.out.print("\t\t\tThere are no existing events. Add or load some and try again!\n");
				break;

			} else {
				EventsManager.checkingAndPrintingByType();
				EventsManager.printingByMonth();
				break;
			}
		}
		case 7: {
			if (EventsManager.isQueueEmpty()) {

				System.out.print("\t\t\tThere are no existing events. Add or load some and try again!\n");
				break;

			} else {
				EventsManager.saveEventsInFile();
				// WriteDataInFile.saveInFile(EventsManager.returnQueue());
				System.out.println("\n\t\t\tThe events are successfully saved!");
				break;
			}
		}

		case 8: {
			System.out.println("\t\t\tExit succesful! Thank you for using this app!");
			System.exit(0);
			break;
		}
		default:
			System.out.println("\t\tInvalid input! Please, enter your choice again!");
		}

	}

	public static void main(String[] args) {

		int optionChoice = 0;
		boolean flag = false;
		EventsManager.LoadSavedEvents();
		System.out.println(
				"\t\t\t\t\tWELCOME TO THE DAY PLANNER APP\n\t\tThis app will help you manage your meetings and tasks."
						+ " You can easily save the information about your upcoming events. ");
		while (!flag) {
			try {
				do {
					System.out.print("\n\t\t\t\t\tChoose from these options:\n"
							+ "\n\t\t\t\t1.Adding new event\n\t\t\t\t2.Editing existing event\n\t\t\t\t3.Deleting event\n\t\t\t\t4.Displaying"
							+ " all of the existing events (including the previously saved ones)\n\t\t\t\t5.Displaying the events for a certain day\n\t\t\t\t6.Displaying the events for a certain month"
							+ "\n\t\t\t\t7.Saving the events\n\t\t\t\t8.Exiting the app\n\n\t\tPlease, enter the number of the option (1-8) you want to use:");

					optionChoice = Integer.parseInt(scan.nextLine());
					if (optionChoice < 1 || optionChoice > 8) {
						System.out.println("\t\tInvalid input! Please, enter your choice again!");
					}
				} while (optionChoice < 1 || optionChoice > 8);
				choosingOption(optionChoice);
				flag = false;
			}

			catch (NumberFormatException e) {
				System.out.println("\t\tInvalid input! Please, enter your choice again!");
			}
		}
	}

}
