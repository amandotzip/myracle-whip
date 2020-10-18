import java.util.Scanner;

public class App {
    // use az lyrics instead
    public static void main(String[] args) throws Exception {
        String splash = "~~~~~~~~~~~~\n" + "Myracle Whip\n" + "~~~~~~~~~~~~\n";

        System.out.println(splash);

        String songName;
        String artistName;
        boolean found = false;
        SongRequest songRequest;

        // Loops requesting for input until a song was found
        while (!found) {
            try {
                Scanner scanner = new Scanner(System.in);
                // User input
                System.out.println("Enter song name: ");
                songName = scanner.nextLine();
                System.out.println("Enter artist name: ");
                artistName = scanner.nextLine();

                // Creates song request object to parse
                System.out.println("I love that one! Processing...");
                songRequest = new SongRequest(songName, artistName);
                found = true;

                // Parsed on succesful request, so print
                String lyricsURL = songRequest.getLyricsURL();
                System.out.println("\n" + lyricsURL);
                songRequest.printLyrics();

            } catch (Exception e) {

                System.out.println("ERROR: No such song by that artist was found." + "\nPerhaps a typo? Try again.");
            }
        }
    }
}
