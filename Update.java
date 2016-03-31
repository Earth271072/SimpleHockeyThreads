import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.*;

public class Update {
	final private static String currentVersion = "1.0.0";

	public static void checkForUpdates() {
		String latestVersion = "";
		System.out.println("Checking for updates...");
		Document doc;
		try {
			doc = Jsoup.connect("http://reid.space/SimpleHockeyThreads").get();
			latestVersion = doc.select("p").text();
			System.out.println(latestVersion);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		if (currentVersion.equals(latestVersion)) {
			System.out.println("You are up to date!");
		} else {
			System.out.println("Version " + latestVersion + " has been released. Please visit /r/SimpleHockeyThreads to get the newest version!");
			System.out.print("Would you like to exit so you can download the newest version? (Y/N)");
		}
	}

}
