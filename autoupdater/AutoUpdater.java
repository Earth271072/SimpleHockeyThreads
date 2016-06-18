import net.dean.jraw.*;
import net.dean.jraw.auth.*;
import net.dean.jraw.fluent.*;
import net.dean.jraw.http.*;
import net.dean.jraw.http.oauth.*;
import net.dean.jraw.managers.*;
import net.dean.jraw.models.*;
import net.dean.jraw.models.attr.*;
import net.dean.jraw.models.meta.*;
import net.dean.jraw.paginators.*;
import java.io.*;
import java.util.*;
import java.net.*;


public class AutoUpdater {
	public static void main (String args[]) {
		UserAgent myUserAgent = UserAgent.of("desktop", "AutoUpdater", "v0.1", "Earth271072");
		RedditClient redditClient = new RedditClient(myUserAgent);
		Console console = System.console();
		OAuthData authData = null;
	//	System.out.print("Please enter your username: ");
		char[] password = console.readPassword("Please enter your password (password will be masked): ");
		String pass = new String(password);
		Credentials credentials = Credentials.script("Earth271072", pass, "v1", "No idea");
		try {
			authData = redditClient.getOAuthHelper().easyAuth(credentials);
		}
		catch (OAuthException e) {
			System.out.println("OAuth authentication error.");
		}
		redditClient.authenticate(authData);
		redditClient.me();
	}








}
