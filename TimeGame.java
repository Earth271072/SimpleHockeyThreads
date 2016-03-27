import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

public class TimeGame {

	private String originalDateTime;
	private String gameTime;
	private String gameDate;
	private String easternTime;
	private String centralTime;
	private String mountainTime;
	private String pacificTime;
	private String dayOfWeek;

	public TimeGame(String dateTime, Team home) {
		String originalDateTime = dateTime;
		String d;
		String t;
		d = originalDateTime.substring(0, originalDateTime.indexOf("T"));
		t = originalDateTime.substring(originalDateTime.indexOf("T") + 1);
		t = t.substring(0, t.indexOf("Z"));
		originalDateTime = d + " " + t;
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		dayOfWeek = simpleDateformat.format(now);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter newFormatTime = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter newFormatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime zuluTime = LocalDateTime.parse(originalDateTime, format);

		ZoneId zuluZone = ZoneId.of("Zulu");
		ZonedDateTime zuluTimeZoned = ZonedDateTime.of(zuluTime, zuluZone);

		ZoneId localZone = ZoneId.of(home.getTimeZone());
		ZonedDateTime localTime = zuluTimeZoned.withZoneSameInstant(localZone);
		gameTime = localTime.format(newFormatTime);
		gameDate = localTime.format(newFormatDate);

		ZoneId easternZone = ZoneId.of("America/New_York");
		ZonedDateTime easternTimeZone = zuluTimeZoned.withZoneSameInstant(easternZone);
		easternTime = easternTimeZone.format(newFormatTime);

		ZoneId centralZone = ZoneId.of("America/Chicago");
		ZonedDateTime centralTimeZone = zuluTimeZoned.withZoneSameInstant(centralZone);
		centralTime = centralTimeZone.format(newFormatTime);

		ZoneId mountainZone = ZoneId.of("America/Edmonton");
		ZonedDateTime mountainTimeZone = zuluTimeZoned.withZoneSameInstant(mountainZone);
		mountainTime = mountainTimeZone.format(newFormatTime);

		ZoneId pacificZone = ZoneId.of("America/Vancouver");
		ZonedDateTime pacificTimeZone = zuluTimeZoned.withZoneSameInstant(pacificZone);
		pacificTime = pacificTimeZone.format(newFormatTime);
	}

	public String getGameTime() {
		return gameTime;
	}

	public String getOriginalDateTime() {
		return originalDateTime;
	}

	public String getGameDate() {
		return gameDate;
	}

	public String getEastern() {
		return easternTime;
	}

	public String getCentral() {
		return centralTime;
	}

	public String getMountain() {
		return mountainTime;
	}

	public String getPacific() {
		return pacificTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}
}
