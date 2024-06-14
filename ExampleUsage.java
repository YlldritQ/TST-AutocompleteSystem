import java.util.List;

public class ExampleUsage {
    public static void main(String[] args) {
        AutocompleteSystem system = new AutocompleteSystem();
        List<String> suggestions = system.autocomplete("prefix_here"); // Get suggestions for a specific prefix
        suggestions.forEach(System.out::println); // Print suggestions to the console

        // Visualize the TST (Ternary Search Tree)
        TSTVisualizer.visualizeTST(system.getRoot(), "", true); // Call the method to visualize the Trie
    }
}