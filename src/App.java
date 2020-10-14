import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    //use az lyrics instead
    public static void main(String[] args) throws Exception {
        System.out.println("~~~~~~~~~~~~\n"
                        + "Myracle Whip\n" 
                        + "~~~~~~~~~~~~\n");
        String songName;
        String artistName;
        boolean found = false;
        SongRequest songRequest;

        while (!found){
            try {
                Scanner scanner = new Scanner(System.in);  
                System.out.println("Enter song name: ");
                songName = scanner.nextLine();
                System.out.println("Enter artist name: ");
                artistName = scanner.nextLine();
                
                System.out.println("I love that one! Processing...");
                songRequest = new SongRequest(songName, artistName);
                found = true;
                
                String lyricsURL = songRequest.getLyricsURL();
                System.out.println("\n" + lyricsURL);
                songRequest.printLyrics();



            }catch (Exception e){
                
                System.out.println("ERROR: No such song by that artist was found."
                                    + "\nPerhaps a typo? Try again.");
            }
        }   







            

        
    }
}
