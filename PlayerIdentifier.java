package TenPinBowling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PlayerIdentifier { //identifies the number of players in the input file
    int player = 0;
    private static Scanner fileInput;

    public int PlayerIdentifier() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your scores file path:");
        ArrayList<String> players = new ArrayList<>();
        int playersNum = 0;
        String playerName = null;
        String playerName1 = null;
        String playerName2 = null;
        
        //creates a Path object based on the user input
        Path path = Paths.get(input.nextLine());
        
        if (Files.exists(path)) // if path exists,  o/p info abt it
        {
            try {
                fileInput = new Scanner(path);
                while (fileInput.hasNext()) {
                    String curLine = fileInput.nextLine();
                    String[] splitted = curLine.split("\t");
                    String name = splitted[0].trim();
                    players.add(name); 
                }
                List<String> noOfPlayers = players.stream().distinct().collect(Collectors.toList());
                playersNum = noOfPlayers.size(); // number of player in file. 
                
            } catch (IOException ioException) {
                System.err.println("Error opening file. Terminating.");

            } catch (NoSuchElementException elementException) {
                System.err.println("File improperly formed. Terminating.");
            } catch (IllegalStateException stateException) {
                System.err.println("Error reading from file. Terminating.");
            }

            if (fileInput != null) {
                fileInput.close();
            }

        } else {
            System.out.printf("%s does not exists%n", path);
        }
        
        return playersNum;
    }
}