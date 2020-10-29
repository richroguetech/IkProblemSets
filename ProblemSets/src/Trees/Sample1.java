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

    static boolean isBST(Solution.TreeNode root){

        if (root == null) return true;
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean helper(Solution.TreeNode root, int min, int max) {

        // base case
        if (root == null) return true;

        if (root.val < min || root.val > max)
            return false;

        boolean left = helper(root.left_ptr, min, root.val);
        boolean right = helper(root.right_ptr, root.val, max);

        return left && right;
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
                    //1. problem 1 - solveSingleValueTree();
                    //2. problem 2 - solveIsBST();
                    solveIsBST();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static void solveSingleValueTree(){
        try{
            Solution.TreeNode root = Solution.readBinaryTree();
            int result = findSingleValueTrees(root);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void solveIsBST(){
        try{
            Solution.TreeNode root = Solution.readBinaryTree();
            boolean result = isBST(root);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
