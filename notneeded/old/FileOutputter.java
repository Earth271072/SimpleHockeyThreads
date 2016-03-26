import java.util.*;
import java.io.*;

public class FileOutputter {

	public static void updateStats (Team t) {
		Calendar cal = Calendar.getInstance();
		int intMonth = (cal.get(Calendar.MONTH)+1);
		int intDay = (cal.get(Calendar.DATE));
		final long milliDay = (1000*60*60*24);
		final long milliTest = 1000;
		String strYear = Integer.toString(cal.get(Calendar.YEAR));
		String strMonth;
		String strDay;
		String strDate;
		Scanner scan = new Scanner(System.in);
		String answer;
		String inputLine;
		boolean update = false;

		if (intMonth < 10)
			strMonth = "0" + Integer.toString(intMonth);
		else
			strMonth = Integer.toString(intMonth);
		if (intDay < 10)
			strDay = "0" + Integer.toString(intDay);
		else
			strDay = Integer.toString(intDay);
		strDate = strYear + strMonth + strDay;

		System.out.println(strDate);
		try {
			File folder = new File("stats/");
			File f = new File("stats/" + t.getTeamName()/*+ strDate*/);

				if (!folder.exists()) {
						System.out.println("Stats folder does not exist. Attempting to create...");
						update = true;
					if (folder.mkdir()) {
						System.out.println("Successfully created stats folder!");
					}
					else
						System.out.println("Failed to create stats folder...");
				}

				if(!f.exists()) {
					update = true;
				}

				if (!update && (f.lastModified() + milliDay) > (System.currentTimeMillis())) {
					System.out.println("It's been less than a day since you last updated team stats.\nWould you like to update the stats now? ");
					answer = scan.nextLine().toUpperCase();
					if (answer.equals("YES") || answer.equals("Y")) {
						update = true;
					}

				}

				if (!update && (f.lastModified() + milliDay) < (System.currentTimeMillis())) {
					update = true;
				}

			System.out.println("Last modified: " + f.lastModified());
			System.out.println("Current time: " + System.currentTimeMillis());
			System.out.println(milliDay);
			System.out.println((f.lastModified() + milliTest) > (System.currentTimeMillis()));

			if (update) {
				StatGrabber.getStatsFile(t);
			}
/*			BufferedWriter buf = new BufferedWriter(new FileWriter("stats/" + t.getTeamCity() + t.getTeamName() + strDate));
			while ((inputLine = getStatsPage(t)) != null) {
				try {
					buf.write(inputLine);
				}
				catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}*/


		}
		catch (Exception e) {
			System.out.print(e);
		}


	}

}
