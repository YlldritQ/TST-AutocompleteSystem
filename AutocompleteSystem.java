import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutocompleteSystem {
    //ndrru ne defult per visibility ne main.java
    static class Node {
        char c;
        Node left, mid, right;
        boolean isEnd;
        int frequency;
        
        public Node(char c) {
            this.c = c;
        }
    }
    // ndrru ne defult per visibility ne main.java
    Node root;
    private StringBuilder prefix;
    
    public AutocompleteSystem() {
        root = null;
        prefix = new StringBuilder();
    }
    
    public void insert(String key, int frequency) {
        root = insert(root, key, frequency, 0);
    }
    
    private Node insert(Node node, String key, int frequency, int d) {
        char c = key.charAt(d);
        if (node == null) {
            node = new Node(c);
        }
        
        if (c < node.c) {
            node.left = insert(node.left, key, frequency, d);
        } else if (c > node.c) {
            node.right = insert(node.right, key, frequency, d);
        } else if (d < key.length() - 1) {
            node.mid = insert(node.mid, key, frequency, d + 1);
        } else {
            node.isEnd = true;
            node.frequency = frequency;
        }
        
        return node;
    }
    
    public List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();
        Node node = findNode(root, prefix, 0);
        if (node != null) {
            collect(node.mid, new StringBuilder(prefix), result);
        }
        return result;
    }
    
    private Node findNode(Node node, String key, int d) {
        if (node == null) {
            return null;
        }
        
        char c = key.charAt(d);
        if (c < node.c) {
            return findNode(node.left, key, d);
        } else if (c > node.c) {
            return findNode(node.right, key, d);
        } else if (d < key.length() - 1) {
            return findNode(node.mid, key, d + 1);
        } else {
            return node;
        }
    }
    
    private void collect(Node node, StringBuilder prefix, List<String> result) {
        if (node == null) {
            return;
        }
        
        collect(node.left, prefix, result);
        
        prefix.append(node.c);
        if (node.isEnd) {
            result.add(prefix.toString());
        }
        
        collect(node.mid, prefix, result);
        prefix.deleteCharAt(prefix.length() - 1);
        
        collect(node.right, prefix, result);
    }
}

class FileOutput {
    public static void writeToFile(List<String> autocompleteResult, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String suggestion : autocompleteResult) {
                writer.write(suggestion);
                writer.newLine();
            }
            System.out.println("Autocomplete results have been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}

class TSTVisualizer {
    // ANSI color codes for console text
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void visualizeTST(AutocompleteSystem.Node node, String indent, boolean last) {
        if (node == null) {
            return;
        }

        System.out.print(indent);

        if (last) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }

        // Colorize node character based on its properties
        if (node.isEnd) {
            System.out.print(ANSI_GREEN + node.c + " (end)" + ANSI_RESET);
        } else {
            System.out.print(ANSI_YELLOW + node.c + ANSI_RESET);
        }

        System.out.println();

        visualizeTST(node.left, indent, false);
        visualizeTST(node.mid, indent, false);
        visualizeTST(node.right, indent, true);
    }
} 
// main class ne file te veqant