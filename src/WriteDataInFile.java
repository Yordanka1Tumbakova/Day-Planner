import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class WriteDataInFile {
	private final static String EVENTS = "events.txt";
	private static BufferedWriter buffWriter = null;
	private static FileWriter fileWriter = null;

	public static String addMeeting(Meeting m) {
		return "Meeting" + "#" + InputValidator.markerChecker(m.getMarker()) + "#" + m.getStartMonth() + "#"
				+ m.getStartDay() + "#" + m.getStartYear() + "#" + m.getStartHour() + "#" + m.getStartMinutes() + "#"
				+ m.getEndMonth() + "#" + m.getEndDay() + "#" + m.getEndYear() + "#" + m.getEndHour() + "#"
				+ m.getEndMinutes() + "#" + m.getDescription() + "#";
	}

	public static String addTask(Task t) {
		return "Task" + "#" + InputValidator.markerChecker((t.getMarker())) + "#" + t.getStartMonth() + "#"
				+ t.getStartDay() + "#" + t.getStartYear() + "#" + t.getStartHour() + "#" + t.getStartMinutes() + "#"
				+ t.getEndMonth() + "#" + t.getEndDay() + "#" + t.getEndYear() + "#" + t.getEndHour() + "#"
				+ t.getEndMinutes() + "#" + t.getDescription() + "#";
	}

	public static void writeMeetingToFile(Meeting m) {
		try {
			buffWriter = new BufferedWriter(fileWriter = new FileWriter(EVENTS, true));
			String writeToFile = addMeeting(m);
			buffWriter.write(writeToFile);
			buffWriter.newLine();

			System.out.println("File exported.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeTaskToFile(Task t) {
		try {
			buffWriter = new BufferedWriter(fileWriter = new FileWriter(EVENTS, true));
			String writeToFile = addTask(t);
			buffWriter.write(writeToFile);
			buffWriter.newLine();

			System.out.println("File exported.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendEventInFile(Event event) {
		try {
			buffWriter = new BufferedWriter(fileWriter = new FileWriter(new File(EVENTS), true));

			if (event instanceof Meeting) {
				Meeting m = (Meeting) event;
				String writeToFile = addMeeting(m);

				buffWriter.write(writeToFile);
				buffWriter.newLine();
			} else if (event instanceof Task) {
				Task t = (Task) event;
				String writeToFile = addTask(t);

				buffWriter.write(writeToFile);
				buffWriter.newLine();
			}
			System.out.println("\n\t\tThe events are exported succesfully!\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffWriter != null) {
					buffWriter.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void saveInFile(Queue<Event> events) {
		try {
			buffWriter = new BufferedWriter(fileWriter = new FileWriter(new File(EVENTS)));
			for (Event e : events) {
				if (e instanceof Meeting) {
					Meeting m = (Meeting) e;
					String writeToFile = addMeeting(m);

					buffWriter.write(writeToFile);
					buffWriter.newLine();
				} else if (e instanceof Task) {
					Task t = (Task) e;
					String writeToFile = addTask(t);

					buffWriter.write(writeToFile);
					buffWriter.newLine();
				}
			}
			System.out.println("\n\t\tThe events are exported succesfully!\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffWriter != null) {
					buffWriter.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
