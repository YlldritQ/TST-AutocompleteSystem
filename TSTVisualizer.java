// Klasë për vizualizimin e Ternary Search Tree (TST)
public class TSTVisualizer {
    // Kode ANSI për ngjyrosjen e tekstit në konzolë
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // Metodë për vizualizimin e TST-së duke përdorur tekst të ngjyrosur dhe indentim
    public static void visualizeTST(AutocompleteSystem.TSTNode node, String indent, boolean last) {
        // Nëse nyja është null, kthehu
        if (node == null) {
            return;
        }

        // Printo indentimin e dhënë
        System.out.print(indent);

        // Vendos karakterin e nyjës si nyjë të fundit ose jo të fundit
        if (last) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }

        // Ngjyros karakterin e nyjës bazuar nëse është fundi i një fjale
        if (node.isEnd) {
            System.out.print(ANSI_GREEN + node.character + " (end)" + ANSI_RESET);
        } else {
            System.out.print(ANSI_YELLOW + node.character + ANSI_RESET);
        }

        // Printo një linjë të re
        System.out.println();

        // Vizualizo nënpemën e majtë, të mesme dhe të djathtë rekursivisht
        visualizeTST(node.left, indent, false);
        visualizeTST(node.mid, indent, false);
        visualizeTST(node.right, indent, true);
    }
}
