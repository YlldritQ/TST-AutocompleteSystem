import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("txt.txt"));
            String val;
            while ((val = br.readLine()) != null) {
                String[] sys = val.split(" ");
                autocompleteSystem.insert(sys[0], Integer.parseInt(sys[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<String> autocompleteResult = autocompleteSystem.autocomplete("ba");
        FileOutput.writeToFile(autocompleteResult, "output.txt");
        TSTVisualizer.visualizeTST(autocompleteSystem.getRoot(), "", true);

        Scanner scanner = new Scanner(System.in);     
        System.out.println("Start typing, and press Enter to submit:");

        while (true) {
            // Read the whole line of input
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                break; // Exit the loop if input is empty
            }
            
            System.out.println("You typed: " + input);

            // Get autocomplete suggestions
            List<String> suggestions = autocompleteSystem.autocomplete(input);

            // Display suggestions or "No Suggestions Found!"
            if (suggestions.isEmpty()) {
                System.out.println("No Suggestions Found!");
            } else {
                System.out.println("Suggestions: " + suggestions);
            }
        }

        scanner.close();
    }
}

