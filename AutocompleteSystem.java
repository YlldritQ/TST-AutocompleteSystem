import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class AutocompleteSystem {
    
    private TSTNode root;
    private StringBuilder prefix;

    public AutocompleteSystem() {
        root = null;
        prefix = new StringBuilder();
    }

    public void insert(String word, int weight) {
        root = insert(root, word, weight, 0);
    }

    private TSTNode insert(TSTNode node, String key, int weight, int d) {
        char c = key.charAt(d);
        if (node == null) {
            node = new TSTNode(c);
        }
        
        if (c < node.character) {
            node.left = insert(node.left, key, weight, d);
        } else if (c > node.character) {
            node.right = insert(node.right, key, weight, d);
        } else if (d < key.length() - 1) {
            node.mid = insert(node.mid, key, weight, d + 1);
        } else {
            node.isEnd = true;
            node.weight = weight;
        }
        
        return node;
    }
    public List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();
        TSTNode node = findNode(root, prefix, 0);
        if (node != null) {
            collect(node.mid, new StringBuilder(prefix), result);
        }
        return result;
    }

    private TSTNode findNode(TSTNode node, String key, int d) {
        if (node == null) {
            return null;
        }
        
        char c = key.charAt(d);
        if (c < node.character) {
            return findNode(node.left, key, d);
        } else if (c > node.character) {
            return findNode(node.right, key, d);
        } else if (d < key.length() - 1) {
            return findNode(node.mid, key, d + 1);
        } else {
            return node;
        }
    }

    private void collect(TSTNode node, StringBuilder prefix, List<String> result) {
        if (node == null) {
            return;
        }
        
        collect(node.left, prefix, result);
        
        prefix.append(node.character);
        if (node.isEnd) {
            result.add(prefix.toString());
        }
        
        collect(node.mid, prefix, result);
        prefix.deleteCharAt(prefix.length() - 1);
        
        collect(node.right, prefix, result);
    }

    public TSTNode getRoot() {
        return root;
    }

    public static class TSTNode {
        char character;
        TSTNode left, mid, right;
        boolean isEnd;
        int weight;
        
        public TSTNode(char c) {
            this.character = c;
        }
    }
}