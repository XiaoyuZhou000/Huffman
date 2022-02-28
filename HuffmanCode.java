import java.util.*;
import java.io.*;

public class HuffmanCode {

	// private Queue<HuffmanNode> freQueue;
	private HuffmanNode overallRoot;

	public HuffmanCode(int[] frequencies) {
		Queue<HuffmanNode> freQueue = new PriorityQueue<HuffmanNode>();
		overallRoot = new HuffmanNode('\0', 0);
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] > 0) {
				HuffmanNode newNode = new HuffmanNode((char) (i), frequencies[i]);
				freQueue.add(newNode);
			}
		}

		while (freQueue.size() != 1) {
			HuffmanNode first = freQueue.remove();
			HuffmanNode second = freQueue.remove();
			HuffmanNode mix = new HuffmanNode('\0', first.fre + second.fre, first, second);
			freQueue.add(mix);
		}

		overallRoot = freQueue.remove();

	}

	public HuffmanCode(Scanner input) {
		while (input.hasNextLine()) {
			int asciiValue = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			overallRoot = huffmanCodeHelper(overallRoot, asciiValue, code);
		}
	}

	private HuffmanNode huffmanCodeHelper(HuffmanNode root, int asciiValue, String binaryString) {
		if (root == null) {
			root = new HuffmanNode('\0', 0);
		}
		if (binaryString.length() == 1) {
			if (binaryString.charAt(0) == '0') {
				root.left = new HuffmanNode((char) asciiValue, 0);
			} else {
				root.right = new HuffmanNode((char) asciiValue, 0);
			}
		} else {
			String subBinaryString = binaryString.substring(1);
			if (binaryString.charAt(0) == '0') {
				root.left = huffmanCodeHelper(root.left, asciiValue, subBinaryString);
			} else {
				root.right = huffmanCodeHelper(root.right, asciiValue, subBinaryString);
			}
		}
		return root;
	}

	public void save(PrintStream output) {
		save(output, overallRoot, "");
	}

	private void save(PrintStream output, HuffmanNode root, String currBString) {
		if (root != null) {
			if (root.left == null) {
				output.println((int) root.ch);
				output.println(currBString);
			} else {
				save(output, root.left, currBString + "0");
				save(output, root.right, currBString + "1");
			}
		}
	}

	public void translate(BitInputStream input, PrintStream output) {
		while (input.hasNextBit()) {
			translate(input, output, overallRoot);
		}
	}

	private void translate(BitInputStream input, PrintStream output, HuffmanNode root) {
		if (root.left == null) {
			output.write((int) root.ch);
		} else if (input.nextBit() == 1) {
			translate(input, output, root.right);
		} else {
			translate(input, output, root.left);
		}
	}
}
