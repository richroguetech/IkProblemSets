package Trees;

import jdk.nashorn.internal.runtime.StoredScript;

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


    static boolean isBSTIterative(Solution.TreeNode root){
        System.out.println("here at beginning");
       Stack<Solution.TreeNode> stack = new Stack<>();
       int left_ptr_val = Integer.MIN_VALUE;

       while (!stack.isEmpty() || root != null) {
           while (root != null) {
               stack.push(root);
               root = root.left_ptr;
           }
           root = stack.pop();

           if (root.val <= left_ptr_val) {
               return false;
           }
           left_ptr_val = root.val;
           root = root.right_ptr;
       }
       return true;
    }

    static int[] postorderTraversal(Solution.TreeNode root){
        // Create two stacks
        List<Integer> returnList = new ArrayList<>();

        Stack<Solution.TreeNode> s1, s2;

        s1 = new Stack<>();
        s2 = new Stack<>();

        if (root == null)
            return null;

        // push root to first stack
        s1.push(root);

        // Run while first stack is not empty
        while (!s1.isEmpty()) {
            // Pop an item from s1 and push it to s2
            Solution.TreeNode temp = s1.pop();
            s2.push(temp);

            // Push left and right children of
            // removed item to s1
            if (temp.left_ptr != null)
                s1.push(temp.left_ptr);
            if (temp.right_ptr != null)
                s1.push(temp.right_ptr);
        }

        // Print all elements of second stack
        while (!s2.isEmpty()) {
            Solution.TreeNode temp = s2.pop();
            returnList.add(temp.val);
        }
        int[] ints = new int[returnList.size()];
        int i = 0;
        for (Integer n : returnList) {
            ints[i++] = n;
        }
        return ints;
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
                    //2. problem 2- solveIsBSTIterative();
                    solvePostorderTraversal();
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

    public static void solveIsBSTIterative(){
        try{
            Solution.TreeNode root = Solution.readBinaryTree();
            boolean result = isBSTIterative(root);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printArray(int[] result){
        if(result == null){
            System.out.println();
            return;
        }
        for(int i = 0; i < result.length; i++){
            if(i>0){
                System.out.print(" ");
            }
            System.out.print(result[i]);
        }
        System.out.println();
    }

    public static void solvePostorderTraversal(){
        try{
            Solution.TreeNode root = Solution.readBinaryTree();
            int[] result = postorderTraversal(root);
            printArray(result);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
