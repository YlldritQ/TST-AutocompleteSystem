public class TSTVisualizer {
    public static void visualizeTST(AutocompleteSystem.TSTNode root, String prefix, boolean isRoot) {
        // Implementation for visualizing the TST
        if (root == null) return;

        if (isRoot) {
            System.out.println("Root: " + root.character);
        } else {
            System.out.println(prefix + root.character);
        }

        visualizeTST(root.left, prefix + "L-", false);
        visualizeTST(root.middle, prefix + "M-", false);
        visualizeTST(root.right, prefix + "R-", false);
    }
}

