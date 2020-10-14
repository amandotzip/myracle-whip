import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class SongRequest {

    private String songName;
    private String artistName;
    private String content;
    private String URL;
    private ArrayList<String> lyricsList;

    public SongRequest(String songName, String artistName){
        this.songName = parseText(songName);
        this.artistName = parseText(artistName);
        this.URL = getLyricsURL();
        this.content = getLyricsURLContent(URL);//url undef
        this.lyricsList = getLyricsList(content);
    }


    public String getSongName(){
        return songName;
    }
    public String getArtistName(){
        return artistName;
    }
    /*removes special chars and sets to lower*/
    public String parseText(String text){
        text = text.replaceAll("[^a-zA-Z0-9_-]", "");
        text = text.toLowerCase();
        text = text.replace(" ", "");
        return text;
    }


    public String getLyricsURL(){
        return "https://azlyrics.com/lyrics/" 
                + this.artistName + "/"
                + this.songName+ ".html";
    }

    public String getLyricsURLContent(String URL){
        //gets website HTML from URL
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

        return content;

    }
    public ArrayList<String> getLyricsList(String content){
        int start = content.indexOf("<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->");
        
        
        int end = content.indexOf("<br><br>");
        content = content.substring(start,end);
        content = content.replace("<br>", "");
        content = content.replace("<i>", "");
        content = content.replace("</i>", "");
        content = content.replace("</div>", "");

        String [] lyricsArray = content.split("\\\n");
        ArrayList<String> lyricsList = new ArrayList<>();
        
        
        for (String line : lyricsArray)
            lyricsList.add(line);
        
        lyricsList.remove(0);

        return lyricsList;
    }

    public void printLyrics(){
        System.out.println("Link to website source: " + this.URL + "\n");

        for (String line : lyricsList)
            System.out.println(line);
        
        System.out.println("\nLink to website source: " + this.URL);

    }


    
}
