import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class EventLoader {
	public static boolean flag = false;

	public static boolean flagCheck() {
		return flag;
	}

	public Queue<Event> importDataFromFile() {

		BufferedReader buffReader = null;
		FileReader fileReader = null;
		Queue<Event> events = new LinkedList<Event>();
		try {
			buffReader = new BufferedReader(fileReader = new FileReader("events.txt"));
			String line;

			while ((line = buffReader.readLine()) != null) {

				String[] str = line.split("#");
				if (str[0].equals("Meeting")) {

					Meeting meetings = new Meeting(Integer.parseInt(str[1]), Integer.parseInt(str[2]),
							Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]),
							Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]),
							Integer.parseInt(str[9]), Integer.parseInt(str[10]), Integer.parseInt(str[11]), str[12]);
					events.add(meetings);
				} else if (str[0].equals("Task")) {

					Task tasks = new Task(Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]),
							Integer.parseInt(str[4]), Integer.parseInt(str[5]), Integer.parseInt(str[6]),
							Integer.parseInt(str[7]), Integer.parseInt(str[8]), Integer.parseInt(str[9]),
							Integer.parseInt(str[10]), Integer.parseInt(str[11]), str[12]);
					events.add(tasks);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("\t\t\tThere is no existing file with the required information. Enter new event.\n");
			flag = true;
		}

		catch (IOException e) {
			System.out.println("\t\t\tThere is a problem with the file\n");
			e.printStackTrace();
		}

		catch (NumberFormatException e) {
			e.printStackTrace();

		} finally {
			try {
				if (buffReader != null) {
					buffReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return events;
	}

}
