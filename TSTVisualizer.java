public class TSTVisualizer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void visualizeTST(AutocompleteSystem.TSTNode node, String indent, boolean last) {
        // Implementation for visualizing the TST

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
                System.out.print(ANSI_GREEN + node.character + " (end)" + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + node.character + ANSI_RESET);
            }
    
            System.out.println();
    
            visualizeTST(node.left, indent, false);
            visualizeTST(node.mid, indent, false);
            visualizeTST(node.right, indent, true);
    }
}

