import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Creates a SongRequest object that has a Song Name,
 * artist name, URL, content, lyricsList
 * */
public class SongRequest {

    private String songName;
    private String artistName;
    private String URL;
    private String content;// Raw HTML Content unprased
    private ArrayList<String> lyricsList;// list of lyrics line by line

    /**  Song request constructor. */
    public SongRequest(String songName, String artistName) {
        this.songName = parseText(songName);
        this.artistName = parseText(artistName);
        this.URL = getLyricsURL();
        this.content = getLyricsURLContent(URL);// url undef
        this.lyricsList = getLyricsList(content);
    }

    /** Returns current objects song name */
    public String getSongName() {
        return songName;
    }

    /** Returns current objects artist name */
    public String getArtistName() {
        return artistName;
    }

    /**  removes special chars and sets to lower */
    public String parseText(String text) {
        text = text.replaceAll("[^a-zA-Z0-9_-]", "");
        text = text.toLowerCase();
        text = text.replace(" ", "");
        return text;
    }

    /** generates lyrics url from atoz lyrics */
    public String getLyricsURL() {
        return "https://azlyrics.com/lyrics/" + this.artistName + "/" + this.songName + ".html";
    }

    /**  Gets raw html text */
    public String getLyricsURLContent(String URL) {
        // gets website HTML from URL
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(URL).openConnection();
            Scanner scannerIS = new Scanner(connection.getInputStream());
            scannerIS.useDelimiter("\\Z");
            content = scannerIS.next();
            scannerIS.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return content;

    }

    /** Parses raw html content variable, makes lyrics each element is a line */
    public ArrayList<String> getLyricsList(String content) {
        // Content is between start and end tag identified
        int start = content.indexOf(
                "<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->");
        int end = content.indexOf("<br><br>");
        content = content.substring(start, end);

        // remove tags from string
        content = content.replace("<br>", "");
        content = content.replace("<i>", "");
        content = content.replace("</i>", "");
        content = content.replace("</div>", "");

        // seperate by line
        String[] lyricsArray = content.split("\\\n");
        ArrayList<String> lyricsList = new ArrayList<>();

        for (String line : lyricsArray)
            lyricsList.add(line);
        // Usage of azlyrics.com comment tag still in list so remove it
        lyricsList.remove(0);

        return lyricsList;
    }

    /** Loops printing of lyrics list */
    public void printLyrics() {
        System.out.println("Link to website source: " + this.URL + "\n");

        for (String line : lyricsList)
            System.out.println(line);

        System.out.println("\nLink to website source: " + this.URL);
    }
}
