import java.util.*;

class Solution {

    int preIndex = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // store inorder indices
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(preorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int left, int right) {

        if (left > right) return null;

        // pick root from preorder
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        // find position in inorder
        int index = map.get(rootVal);

        // build left subtree
        root.left = helper(preorder, left, index - 1);

        // build right subtree
        root.right = helper(preorder, index + 1, right);

        return root;
    }
}