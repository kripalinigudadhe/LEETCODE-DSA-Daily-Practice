class Solution {
    int currVal, currCount = 0, maxCount = 0;
    List<Integer> res = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] ans = new int[res.size()];
        for(int i=0;i<res.size();i++) ans[i]=res.get(i);
        return ans;
    }

    void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);

        if(currCount==0 || root.val!=currVal){
            currVal = root.val;
            currCount = 0;
        }
        currCount++;

        if(currCount > maxCount){
            maxCount = currCount;
            res.clear();
            res.add(currVal);
        } else if(currCount == maxCount){
            res.add(currVal);
        }

        inorder(root.right);
    }
}