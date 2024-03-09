
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Iterator;

public class HuffmanEncoder {

	private HashMap<Character, String> charToEncodingMapping;

	private HashMap<Character, Integer> frequencyMap;

	private int encodingLength, tableSize;

	public HuffmanEncoder(HashMap<Character, Integer> frequencyMap) {
		charToEncodingMapping = new HashMap<>();
		this.frequencyMap = frequencyMap;
		this.encodingLength = this.tableSize = 0;
	}

	public void encode() throws IndexOutOfBoundsException { // complete this method
		BinaryTreeNode root = buildTree();
		createTable(root, "");
		Iterator<Character> it = frequencyMap.keySet().iterator();
		while (it.hasNext()) {
			Character c = it.next();
			Integer f = frequencyMap.get(c);
			String code = getEncoding(c);
			encodingLength = encodingLength + f * code.length();
			tableSize = tableSize + code.length() + 8;
		}

	}

	private BinaryTreeNode buildTree() throws IndexOutOfBoundsException { // complete this method
		PriorityQueue<BinaryTreeNode> pq = new PriorityQueue<>(new BinaryTreeNodeComparator());
		Iterator<Character> it = frequencyMap.keySet().iterator();
		while (it.hasNext()) {
			Character c = it.next();
			Integer f = frequencyMap.get(c);
			BinaryTreeNode z = new BinaryTreeNode(c, f);
			pq.offer(z);
		}
		while (pq.size() > 1) {
			BinaryTreeNode min = pq.poll(), secondMin = pq.poll();
			BinaryTreeNode z = new BinaryTreeNode('\0', (min.value + secondMin.value));
			z.left = min;
			z.right = secondMin;
			pq.offer(z);
		}
		return pq.poll();
	}

	private void createTable(BinaryTreeNode node, String encoding) { // complete this method
		if (node.left == null && node.right == null)
			charToEncodingMapping.put(node.character, encoding);
		else {
			if (node.left != null)
				createTable(node.left, encoding + "0");
			if (node.right != null)
				createTable(node.right, encoding + "1");
		}
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getEncodingLength() {
		return encodingLength;
	}

	public String getEncoding(char c) {
		return charToEncodingMapping.get(c);
	}
}
