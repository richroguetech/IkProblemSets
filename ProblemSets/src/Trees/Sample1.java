package Trees;

import java.util.*;

public class Sample1
{
    /*
        Complete the function below
    */
    static int count = 0;

    static int findSingleValueTrees(Solution.TreeNode root){
        if (root == null) return 0;
        boolean isUnival = isUnivalueTree(root);
        return count;
    }


    static boolean isUnivalueTree(Solution.TreeNode root) {
        // base case - if the node has no children this is a unival subtree
        if (root.left_ptr == null && root.right_ptr == null) {
            //
            count++;
            return true;
        }

        boolean isUnival = true;

        // check if all node's children are univalue subtrees and if they have same subvalue...
        if (root.left_ptr != null) {
            isUnival = isUnivalueTree(root.left_ptr) && isUnival && root.left_ptr.val == root.val;
        }

        if (root.right_ptr != null) {
            isUnival = isUnivalueTree(root.right_ptr) && isUnival && root.right_ptr.val == root.val;
        }

        if (!isUnival) {
            return false;
        }
        count++;
        return true;
    }

    public static void main(String [] args)
    {
        /*
        This function is used to increase the size of recursion stack. It makes the size of stack
        2^26 ~= 10^8
        */
        new Thread(null, new Runnable() {
            public void run() {
                try{
                    solve();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static void solve(){
        try{
            Solution.TreeNode root = Solution.readBinaryTree();
            int result = findSingleValueTrees(root);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
