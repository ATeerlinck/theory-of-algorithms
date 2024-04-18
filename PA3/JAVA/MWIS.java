import java.io.FileNotFoundException;

public class MWIS extends Tree {

	public int computedSum[];
	boolean isIncludedSumLarger[];
	boolean isInSet[];

	public MWIS(final String filePath) throws FileNotFoundException {
		super(filePath);
		computedSum = new int[numNodes];
		isIncludedSumLarger = new boolean[numNodes];
		isInSet = new boolean[numNodes];
		for (int i = 0; i < numNodes; i++) {
			computedSum[i] = Integer.MIN_VALUE;
			isIncludedSumLarger[i] = false;
			isInSet[i] = false;
		}
	}

	public int computeSum(int node) { // complete this function
		if (computedSum[node] != Integer.MIN_VALUE)
			return computedSum[node];
		int excl = 0, incl = weights[node];
		int[] children = adjList.get(node).stream().mapToInt(i -> i).toArray();
		for (int child : children) {
			excl += computeSum(child);
			int[] grandchildren = adjList.get(child).stream().mapToInt(i -> i).toArray();
			for (int grandchild : grandchildren) {
				incl += computeSum(grandchild);
			}
		}
		if (incl > excl) {
			computedSum[node] = incl;
			isIncludedSumLarger[node] = true;
		} else
			computedSum[node] = excl;
		return computedSum[node];
	}

	public void computeSet(int root) { // complete this function
		if (isIncludedSumLarger[root])
			isInSet[root] = true;
		for (int i : adjList.getFirst()) {
			computeSetHelper(i, root);
		}
	}

	private void computeSetHelper(int node, int parent) { // complete this function
		if (isIncludedSumLarger[node] && !isInSet[parent])
			isInSet[node] = true;
		for (int i : adjList.get(node)) {
			computeSetHelper(i, node);
		}
	}
}
