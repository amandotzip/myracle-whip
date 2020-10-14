import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class App {
    public static void main(String[] args) throws Exception {
        /*we used AZ lyrics
        Genius can be used

        public void setUrl(String artist, String track) {
		    url = "https://www.azlyrics.com/lyrics/" + artist.trim().toLowerCase().replaceAll(" ", "") + "/" + track.trim().toLowerCase().replaceAll(" ", "") + ".html";
	    }
        */


        String songName;
        String artistName;
        String albumName;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter song name: ");
        songName = scanner.nextLine();
        System.out.println("Enter artist name: ");
        artistName = scanner.nextLine();
        // System.out.println("Enter album name: ");
        albumName = scanner.nextLine();
        // https://genius.com/Migos-bad-and-boujee-lyrics

        //reaplce spaces, capitalize first letter of artist, special chars
        String songNameParsed = songName;
        String artistNameParsed = artistName;
        String albumNameParsed = albumName;
        

        songNameParsed.replaceAll("[^a-zA-Z0-9_-]", "");
        artistNameParsed.replaceAll("[^a-zA-Z0-9_-]", "");
        albumNameParsed.replaceAll("[^a-zA-Z0-9_-]", "");


        
        songNameParsed = songNameParsed.toLowerCase();
        artistNameParsed = artistNameParsed.toLowerCase();
        albumNameParsed = albumName.toLowerCase();

        //capitalize first letter
        artistNameParsed = Character.toUpperCase(artistNameParsed.charAt(0)) + artistNameParsed.substring(1);

        //change space to -
        songNameParsed = songNameParsed.replace(" ", "-");
        artistNameParsed = artistNameParsed.replace(" ", "-");
        albumNameParsed = albumNameParsed.replace(" ", "-");

        //final url
        String lyricsURL = "https://genius.com/" + artistNameParsed + "-"+ songNameParsed + "-lyrics";

        System.out.println(lyricsURL);

        //gets website HTML from URL
        final String URL = lyricsURL;
        String content = null;
        URLConnection connection = null;
        try {
          connection =  new URL(URL).openConnection();
          Scanner scannerIS = new Scanner(connection.getInputStream());
          scannerIS.useDelimiter("\\Z");
          content = scannerIS.next();
          scannerIS.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        //store the parsed html soup
        Document doc = Jsoup.parse(content);
        // System.out.println(doc);



    }
}
