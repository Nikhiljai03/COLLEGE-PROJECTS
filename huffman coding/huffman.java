// Create a C program that implements the Huffman coding algorithm to compress a set of binary strings. The program should use a Binary Trie data structure to construct a Huffman tree based on the frequency of the binary strings. Users should be able to input binary strings, encode them using the Huffman tree, and decode the encoded data back to the original binary strings. This project will demonstrate the practical use of data structures in creating efficient compression algorithms.


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Class to represent a node in the Huffman tree
class TreeNode {
    // Binary string and frequency for each node
    String binaryStr;
    int freq;
    TreeNode left, right; // Left and right child nodes

    // Constructor to create a new tree node
    public TreeNode(String binaryStr, int freq) {
        this.binaryStr = binaryStr;
        this.freq = freq;
        this.left = this.right = null;
    }
}

public class huffman {

    // Function to build the Huffman tree
    public static TreeNode constructHuffmanTree(List<TreeNode> nodes) {
        while (nodes.size() > 1) {
            // Sort nodes by frequency in ascending order
            Collections.sort(nodes, Comparator.comparingInt(node -> node.freq));

            // Pick the two nodes with the smallest frequencies
            TreeNode leftChild = nodes.get(0);
            TreeNode rightChild = nodes.get(1);

            // Combine the two nodes into a new parent node
            TreeNode combinedNode = new TreeNode("", leftChild.freq + rightChild.freq);
            combinedNode.left = leftChild;
            combinedNode.right = rightChild;

            // Remove the two nodes from the list and add the new parent node
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(combinedNode);
        }

        // Return the root of the Huffman tree
        return nodes.get(0);
    }

    // Function to generate Huffman codes by traversing the tree
    public static void generateHuffmanCodes(TreeNode node, String code) {
        if (node == null) return; // Base case: if node is null, stop

        // If it's a leaf node, print the binary string and its code
        if (node.left == null && node.right == null) {
            System.out.println("Binary String: " + node.binaryStr + ", Huffman Code: " + code);
            return;
        }

        // Recurse left with '0' added to the code
        generateHuffmanCodes(node.left, code + "0");
        // Recurse right with '1' added to the code
        generateHuffmanCodes(node.right, code + "1");
    }

    public static void main(String[] args) {
        // Create a list to hold nodes (each representing a binary string and its frequency)
        List<TreeNode> nodes = new ArrayList<>();

        // Input binary strings and their frequencies
        nodes.add(new TreeNode("101", 5));
        nodes.add(new TreeNode("111", 9));
        nodes.add(new TreeNode("100", 12));
        nodes.add(new TreeNode("110", 13));
        nodes.add(new TreeNode("010", 16));
        nodes.add(new TreeNode("011", 45));

        // Build the Huffman tree from the list of nodes
        TreeNode root = constructHuffmanTree(nodes);

        // Print the generated Huffman codes
        System.out.println("Huffman Codes:");
        generateHuffmanCodes(root, "");
    }
}
