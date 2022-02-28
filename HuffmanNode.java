import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode> {
    
    public char ch;
    public int fre;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(char ch, int fre) {
        this(ch, fre, null, null);
    }

    public HuffmanNode(char ch, int fre, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.fre = fre;
        this.left = left;
        this.right = right;
    }

    public int compareTo(HuffmanNode other) {
        return this.fre - other.fre;
    }
}
