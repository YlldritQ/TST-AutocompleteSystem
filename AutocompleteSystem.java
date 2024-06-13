import java.util.List;

public class AutocompleteSystem {
    private TSTNode root;

    public AutocompleteSystem() {
        // Initialize the root and other necessary parts of the TST
    }

    public void insert(String word, int weight) {
        // Implementation for inserting a word with its weight
    }

    public List<String> autocomplete(String prefix) {
        // Implementation for autocompleting based on the prefix
        return null; // Replace with actual implementation
    }

    public TSTNode getRoot() {
        return root;
    }

    public static class TSTNode {
        char character;
        TSTNode left, middle, right;
        int weight;
        // Other attributes and methods for TSTNode
    }
}
