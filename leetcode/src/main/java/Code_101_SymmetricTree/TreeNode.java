package Code_101_SymmetricTree;

import lombok.Data;

/**
 * @author pengtao.geng on 2020/1/31 4:53 下午
 */
@Data
public class TreeNode {
	private int val;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
