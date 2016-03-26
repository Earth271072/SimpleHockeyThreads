import java.util.*;
import java.io.*;

public class FileOutputter {

	private static boolean update = false;
	private static boolean prompt = true;

	public static void updateStats() {
		System.out.println("Getting team stats...");
		for (String key: Team.teamKeys.keySet()) {
//			System.out.println(key);
			updateStats (Team.teamKeys.get(key));
		}
	}

	public static void updateStats (Team t) {
		final long milliDay = (1000*60*60*24);
		boolean localUpdate = false;
		Scanner scan = new Scanner(System.in);
		String answer;

		try {
//			System.out.println("TESTING UPDATE SHIT");
			File folder = new File("stats");

			if (!folder.exists()) {
					System.out.println("Stats folder does not exist. Attempting to create...");
					update = true;
				if (folder.mkdir()) {
					System.out.println("Successfully created stats folder!");
				}
				else
					System.out.println("Failed to create stats folder...");
			}

			File f = new File(folder.getAbsolutePath(), t.getFullTeamName().replaceAll("\\s","") + ".html");
			
			if(!f.exists()) {
				System.out.println("THE FILE DOESN'T EXIST, APPARENTLY");
				localUpdate = true;
			}
			else {
//				System.out.println("It exists!");
			}

			if (prompt && ((f.lastModified() + milliDay) > (System.currentTimeMillis()))) {
				prompt = false;
				System.out.println("It's been less than a day since you last updated team stats.\nWould you like to update the stats now? ");
				answer = scan.nextLine().toUpperCase();
				if (answer.equals("YES") || answer.equals("Y")) {
					update = true;
				}

			}

			if (!update && (f.lastModified() + milliDay) < (System.currentTimeMillis())) {
				update = true;
			}

			if (update || localUpdate) {
				StatGrabber.getStatsFile(t);
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}

}
