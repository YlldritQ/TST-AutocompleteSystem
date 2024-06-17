import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

// Klasa kryesore për ekzekutimin e programit të autokompletimit
public class Main {
    public static void main(String[] args) {
        // Krijo një instancë të sistemit të autokompletimit
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem();
        BufferedReader br = null;

        try {
            // Inicializo BufferedReader për të lexuar nga një skedar tekst "txt.txt"
            br = new BufferedReader(new FileReader("txt.txt"));
            String val;
            // Lexo linjë për linjë nga skedari
            while ((val = br.readLine()) != null) {
                // Nda linjën në fjalë dhe peshë
                String[] sys = val.split(" ");
                // Shto fjalën dhe peshën në sistemin e autokompletimit
                autocompleteSystem.insert(sys[0], Integer.parseInt(sys[1]));
            }
        } catch (IOException e) {
            // Printo gjurmën e gabimit në rast të një gabimi I/O
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Printo një mesazh gabimi në rast të një gabimi në formatin e numrit
            System.err.println("Error parsing integer: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    // Mbyll BufferedReader në fund
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Vizualizo pemën TST duke përdorur një metodë nga klasa TSTVisualizer (supozuar se ekziston)
        TSTVisualizer.visualizeTST(autocompleteSystem.getRoot(), "", true);

        // Inicializo Scanner për të lexuar inputin nga përdoruesi
        Scanner scanner = new Scanner(System.in);     
        System.out.println("Start typing, and press Enter to submit:");

        while (true) {
            // Lexo një linjë nga përdoruesi
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                // Dil nga cikli nëse inputi është bosh
                break; 
            }
            
            System.out.println("You typed: " + input);

            // Merr sugjerimet nga sistemi i autokompletimit për prefiksin e dhënë
            List<String> suggestions = autocompleteSystem.autocomplete(input);

            // Shfaq sugjerimet ose një mesazh që sugjerimet nuk u gjetën
            if (suggestions.isEmpty()) {
                System.out.println("No Suggestions Found!");
            } else {
                System.out.println("Suggestions: " + suggestions);
            }
        }

        // Mbyll Scanner në fund
        scanner.close();
    }
}
