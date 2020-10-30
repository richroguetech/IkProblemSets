package Trees;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static class TreeNode {
        public int val;
        public TreeNode left_ptr;
        public TreeNode right_ptr;

        public TreeNode() {
            this.left_ptr = null;
            this.right_ptr = null;
        }

        public TreeNode(int val) {
            this.val = val;
            this.left_ptr = null;
            this.right_ptr = null;
        }
    }

    private static class BinaryTree {
        public class Edge {
            public int parentNodeIndex;
            public int childNodeIndex;
            public String leftRightFlag;

            public Edge() {
            }

            public Edge(int parentNodeIndex, int childNodeIndex, String leftRightFlag) {
                this.parentNodeIndex = parentNodeIndex;
                this.childNodeIndex = childNodeIndex;
                this.leftRightFlag = leftRightFlag;
            }
        }

        public int noOfNodes;
        public ArrayList<Integer> nodeValues;
        public int rootIndex;
        public int noOfEdges;
        public ArrayList<Edge> edges;
        public TreeNode root;

        public BinaryTree() {
            noOfNodes = 0;
            rootIndex = -1;
            noOfEdges = 0;
            nodeValues = new ArrayList();
            edges = new ArrayList();
            root = null;
        }

        public void readRawValues() {
            Scanner scan = new Scanner(System.in);

            noOfNodes = scan.nextInt();
            for (int i = 0; i < noOfNodes; i++) {
                int nodeVal = scan.nextInt();
                nodeValues.add(nodeVal);
            }

            rootIndex = scan.nextInt();

            noOfEdges = scan.nextInt();
            for (int i = 0; i < noOfEdges; i++) {
                int parentNodeIndex = scan.nextInt();
                int childNodeIndex = scan.nextInt();
                String leftRightFlag = scan.next();
                edges.add(new Edge(parentNodeIndex, childNodeIndex, leftRightFlag));
            }
        }

        public void buildFromRawValues() {
            if (noOfNodes == 0) {
                root = null;
                return;
            }

            ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
            for (int i = 0; i < noOfNodes; i++) {
                nodes.add(new TreeNode(nodeValues.get(i)));
            }

            for (int i = 0; i < noOfEdges; i++) {
                if (edges.get(i).leftRightFlag.equals("L")) {
                    nodes.get(edges.get(i).parentNodeIndex).left_ptr
                            = nodes.get(edges.get(i).childNodeIndex);
                } else {
                    nodes.get(edges.get(i).parentNodeIndex).right_ptr = nodes.get(edges.get(i).childNodeIndex);
                }
            }

            root = nodes.get(rootIndex);
            return;
        }
    }

    public static TreeNode readBinaryTree() {
        BinaryTree inputBinaryTree = new BinaryTree();
        inputBinaryTree.readRawValues();
        inputBinaryTree.buildFromRawValues();
        return inputBinaryTree.root;
    }

    public static void printCircularList(TreeNode circularListHead) {
        if (circularListHead == null) {
            System.out.println();
            return;
        }
        TreeNode tmpHead = circularListHead;
        while (tmpHead.right_ptr != circularListHead) {
            System.out.print(tmpHead.val + " ");
            tmpHead = tmpHead.right_ptr;
        }
        System.out.println(tmpHead.val);
    }
}