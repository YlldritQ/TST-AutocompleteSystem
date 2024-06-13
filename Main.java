import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem();
        BufferedReader br;
        try{
         br = new BufferedReader(new FileReader("txt.txt"));
         String val;
            while ((val = br.readLine()) != null) {
                String [] sys = val.split(" ");
                autocompleteSystem.insert(sys[0],Integer.parseInt(sys[1]));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
            System.err.println("Error parsing integer: " + e.getMessage());
        }
        
        List<String> autocompleteResult = autocompleteSystem.autocomplete("app");
        FileOutput.writeToFile(autocompleteResult, "output.txt");
        TSTVisualizer.visualizeTST(autocompleteSystem.root, "" , true);
            
    }
}