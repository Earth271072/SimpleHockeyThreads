import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.net.*;

public class StatGrabber {

	public static void getStatsFile(Team t) {
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

		try {
			URL teamURL = new URL(t.getStatsURL());
			try {
				File folder = new File("stats/");
				File f = new File("stats/" + t.getTeamCity().replaceAll("\\s","") + t.getTeamName().replaceAll("\\s",""));

					BufferedReader in = new BufferedReader(new InputStreamReader(teamURL.openStream()));
					BufferedWriter buf = new BufferedWriter(new FileWriter("stats/" + t.getTeamCity().replaceAll("\\s","") + t.getTeamName().replaceAll("\\s", "")));

					while ((inputLine = in.readLine()) != null) {
						buf.write(inputLine + "\n");
					}
			}
			catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		catch(MalformedURLException u) {
			u.printStackTrace();
		}

		getStatsHTMLFile(t);
	}

	private static void getStatsHTMLFile(Team t) {
//		Document doc;
		Elements statHeader;
		Elements statSkaters;
		Elements statGoalies;
		Elements rowHeaders;
		int column = 0;
		Integer integer;
		String string;
		Double doub;
		ArrayList<Object[]> skatersList = new ArrayList<Object[]>();
		ArrayList<Object[]> goaliesList = new ArrayList<Object[]>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		Object skaters[] = new Object[14];
		Object goalies[] = new Object[16];
		Skater s;
		Goalie g;
		Scanner scan = new Scanner(System.in);
		String answer;

		try {

			File input = new File("stats/" + t.getTeamCity().replaceAll("\\s","") + t.getTeamName().replaceAll("\\s",""));
			Document doc = Jsoup.parse(input, "UTF-8", "");
//			System.out.println(doc.title());	// prints title of the page to confirm it is in the right place
			statHeader = doc.select("tr.hdr");	// is this needed now??
			statSkaters = doc.select(".tieUp table.data:eq(1) tr:not(.hdr) td");	// selects the skaters table
			statGoalies = doc.select(".tieUp table.data:eq(3) tr:not(.hdr) td");	// selects the goalie table

			for (int i = 0; i < statSkaters.size(); i++) {

				if (column == 1 || column == 2) {
					string = new String(statSkaters.get(i).text());
					skaters[column] = string;
				}

				else if (column == 13) {
					doub = new Double(statSkaters.get(i).text());
					skaters[column] = doub;
				}

				else {
					try {
						integer = new Integer(statSkaters.get(i).text());
					}
					catch (NumberFormatException e) {
						integer = new Integer(-1);
					}

						skaters[column] = integer;
				}

				if (column < 13) {
					column++;
				}	else {		// if the column is 13, then it needs to be reset and sent to the next row
						column = 0;
						skatersList.add(skaters);
						skaters = new Object[14];
					}
			}

				// resets column for the next loop
				column = 0;

				for (int i = 0; i < statGoalies.size(); i++) {
					if (column == 1) {
						string = new String(statGoalies.get(i).text());
						goalies[column] = string;
					}

					else if (column == 5 || column == 12) {
						doub = new Double(statGoalies.get(i).text());
						goalies[column] = doub;
					}

					else {
						try {
							integer = new Integer(statGoalies.get(i).text());
						}
						catch (NumberFormatException e) {
							integer = new Integer(-1);
						}

						goalies[column] = integer;
					}

					if (column < 15) {
							column++;
					}	else {
							column = 0;
							goaliesList.add(goalies);
							goalies = new Object[16];
						}
			}

		for (int i = 0; i < skatersList.size(); i++) {
			skaters = skatersList.get(i);
			s = new Skater(((Integer)skaters[0]).intValue(), skaters[1].toString(), skaters[2].toString(), ((Integer)skaters[3]).intValue(),
			((Integer)skaters[4]).intValue(), ((Integer)skaters[5]).intValue(),((Integer)skaters[6]).intValue(), ((Integer)skaters[7]).intValue(),
			((Integer)skaters[8]).intValue(), ((Integer)skaters[9]).intValue(), ((Integer)skaters[10]).intValue(), ((Integer)skaters[11]).intValue(),
			((Integer)skaters[12]).intValue(),((Double)skaters[13]).doubleValue());
			playerList.add(s);
		}

		for (int i = 0; i < goaliesList.size(); i++) {
			goalies = goaliesList.get(i);
			g = new Goalie(((Integer)goalies[0]).intValue(), goalies[1].toString(), ((Integer)goalies[2]).intValue(), ((Integer)goalies[3]).intValue(),
			((Integer)goalies[4]).intValue(), ((Double)goalies[5]).doubleValue(),((Integer)goalies[6]).intValue(), ((Integer)goalies[7]).intValue(),
			((Integer)goalies[8]).intValue(), ((Integer)goalies[9]).intValue(), ((Integer)goalies[10]).intValue(), ((Integer)goalies[11]).intValue(),
			((Double)goalies[12]).doubleValue(), ((Integer)goalies[13]).intValue(), ((Integer)goalies[14]).intValue(), ((Integer)goalies[15]).intValue());
			playerList.add(g);
		}
			t.addPlayerList(playerList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printSkaterHeader() {
		System.out.println("#\tPos\tPlayer\t\tGP\tG\tA\tP\t+/-\tPIM\tPP\tSH\tGW\tS\tS%");
	}

	public static void printGoalieHeader() {
		System.out.println("#\tGoalie\t\tGPI\tGS\tMin\tGAA\tW\tL\tOT\tSO\tSA\tGA\tSv%\tG\tA\tPIM");
	}

}
