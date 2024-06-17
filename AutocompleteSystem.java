import java.util.ArrayList;
import java.util.List;
import java.io.*;

// Klasë për sistemin e autokompletimit duke përdorur një Ternary Search Tree (TST)
public class AutocompleteSystem {

    // Deklarimi i rrënjës së TST dhe prefiksit aktual
    private TSTNode root;
    private StringBuilder prefix;

    // Konstruktori inicializon rrënjën si null dhe prefiksin si StringBuilder të ri
    public AutocompleteSystem() {
        root = null;
        prefix = new StringBuilder();
    }

    // Metoda për të shtuar një fjalë dhe peshën e saj në TST
    public void insert(String word, int weight) {
        root = insert(root, word, weight, 0);
    }

    // Metodë private rekursive për të shtuar një fjalë në TST
    private TSTNode insert(TSTNode node, String key, int weight, int d) {
        // Merr karakterin e radhës në çelës
        char c = key.charAt(d); 
        if (node == null) {
            // Krijo një nyje të re nëse nyja aktuale është null
            node = new TSTNode(c);
        }
        
        // Krahaso karakterin e çelësit me karakterin e nyjës aktuale
        if (c < node.character) {
            // Shko në nënpemën e majtë nëse karakteri është më i vogël
            node.left = insert(node.left, key, weight, d);
        } else if (c > node.character) {
            // Shko në nënpemën e djathtë nëse karakteri është më i madh
            node.right = insert(node.right, key, weight, d);
        } else if (d < key.length() - 1) {
            // Shko në nënpemën e mesme nëse karakteri është i njëjtë dhe nuk është fundi i çelësit
            node.mid = insert(node.mid, key, weight, d + 1);
        } else {
            // Nëse është fundi i çelësit, shëno nyjën si fund dhe cakto peshën
            node.isEnd = true;
            node.weight = weight;
        }
        
        return node;
    }

    // Metoda për të sugjeruar fjalët që përputhen me një prefiks të dhënë
    public List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();
        // Gjej nyjën që përputhet me prefiksin
        TSTNode node = findNode(root, prefix, 0);
        if (node != null) {
            // Mblidh të gjitha fjalët që fillojnë me këtë prefiks
            collect(node.mid, new StringBuilder(prefix), result);
        }
        return result;
    }

    // Metodë private rekursive për të gjetur nyjën që përputhet me një prefiks të dhënë
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

    // Metodë private rekursive për të mbledhur të gjitha fjalët nga një nyje e dhënë
    private void collect(TSTNode node, StringBuilder prefix, List<String> result) {
        if (node == null) {
            return;
        }
        
        // Shko në nënpemën e majtë
        collect(node.left, prefix, result);
        
        // Shto karakterin e nyjës aktuale në prefiks
        prefix.append(node.character);
        if (node.isEnd) {
            // Shto fjalën e formuar në rezultat nëse nyja është fund i një fjale
            result.add(prefix.toString());
        }
        
        // Shko në nënpemën e mesme
        collect(node.mid, prefix, result);
        // Hiq karakterin e fundit për të rikthyer prefiksin në gjendjen e mëparshme
        prefix.deleteCharAt(prefix.length() - 1);
        
        // Shko në nënpemën e djathtë
        collect(node.right, prefix, result);
    }

    // Metoda për të marrë rrënjën e TST
    public TSTNode getRoot() {
        return root;
    }

    // Klasë për nyjet e Ternary Search Tree
    public static class TSTNode {
        char character;
        TSTNode left, mid, right;
        boolean isEnd;
        int weight;
        
        // Konstruktori për të krijuar një nyje të re me karakterin e dhënë
        public TSTNode(char c) {
            this.character = c;
        }
    }
}
