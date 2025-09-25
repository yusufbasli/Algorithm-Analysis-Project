package HW;

import java.util.ArrayList;
import java.util.List;

public class SearchComparison {

    // Binary Search Tree Implementation
    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    static class BinarySearchTree {
        private Node root;

        public BinarySearchTree() {
            root = null;
        }

        // Put operation (Insert)
        public void put(int key) {
            root = insert(root, key);
        }

        private Node insert(Node root, int key) {
            if (root == null) {
                root = new Node(key);
                return root;
            }

            if (key < root.value) {
                root.left = insert(root.left, key);
            } else if (key > root.value) {
                root.right = insert(root.right, key);
            }

            return root;
        }

        // Get operation (Search)
        public Integer get(int key) {
            return search(root, key);
        }

        private Integer search(Node root, int key) {
            if (root == null) {
                return null;
            }
            if (key == root.value) {
                return root.value;
            } else if (key < root.value) {
                return search(root.left, key);
            } else {
                return search(root.right, key);
            }
        }
    }

    // Hash Table Implementation
    static class HashTable {
        private List<List<Integer>> table;
        private final int size;

        public HashTable(int size) {
            this.size = size;
            table = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                table.add(new ArrayList<>());
            }
        }

        // Hash function to find index
        private int hash(int key) {
            return key % size;
        }

        // Put operation (Insert)
        public void put(int key) {
            int index = hash(key);
            List<Integer> bucket = table.get(index);
            if (!bucket.contains(key)) {
                bucket.add(key);
            }
        }

        // Get operation (Search)
        public Integer get(int key) {
            int index = hash(key);
            List<Integer> bucket = table.get(index);
            for (Integer k : bucket) {
                if (k == key) {
                    return k;
                }
            }
            return null;
        }
    }

    // Measure execution time of a sequence of operations
    public static long measureTime(List<String[]> operations, Object algorithm) {
        long startTime = System.nanoTime();

        for (String[] operation : operations) {
            if (operation[0].equals("Put")) {
                if (algorithm instanceof BinarySearchTree) {
                    ((BinarySearchTree) algorithm).put(Integer.parseInt(operation[1]));
                } else if (algorithm instanceof HashTable) {
                    ((HashTable) algorithm).put(Integer.parseInt(operation[1]));
                }
            } else if (operation[0].equals("Get")) {
                if (algorithm instanceof BinarySearchTree) {
                    ((BinarySearchTree) algorithm).get(Integer.parseInt(operation[1]));
                } else if (algorithm instanceof HashTable) {
                    ((HashTable) algorithm).get(Integer.parseInt(operation[1]));
                }
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        // Define the sequences of operations
        List<String[]> sequence1 = new ArrayList<>();
        sequence1.add(new String[]{"Put", "5"});
        sequence1.add(new String[]{"Put", "11"});
        sequence1.add(new String[]{"Put", "3"});
        sequence1.add(new String[]{"Get", "10"});
        sequence1.add(new String[]{"Get", "5"});
        sequence1.add(new String[]{"Get", "2"});

        List<String[]> sequence2 = new ArrayList<>();
        sequence2.add(new String[]{"Put", "2"});
        sequence2.add(new String[]{"Put", "4"});
        sequence2.add(new String[]{"Put", "1"});
        sequence2.add(new String[]{"Get", "3"});
        sequence2.add(new String[]{"Get", "4"});
        sequence2.add(new String[]{"Put", "6"});
        sequence2.add(new String[]{"Get", "6"});

        // Initialize both search algorithms
        BinarySearchTree bst = new BinarySearchTree();
        HashTable hashTable = new HashTable(10);

        // Measure execution times for each algorithm and sequence
        long bstTimeSeq1 = measureTime(sequence1, bst);
        long hashTableTimeSeq1 = measureTime(sequence1, hashTable);

        long bstTimeSeq2 = measureTime(sequence2, bst);
        long hashTableTimeSeq2 = measureTime(sequence2, hashTable);

        // Output the execution times
        System.out.println("BST Time Sequence 1: " + bstTimeSeq1 + " ns");
        System.out.println("Hash Table Time Sequence 1: " + hashTableTimeSeq1 + " ns");
        System.out.println("BST Time Sequence 2: " + bstTimeSeq2 + " ns");
        System.out.println("Hash Table Time Sequence 2: " + hashTableTimeSeq2 + " ns");

        // Discussion based on results:
        // Sequence 1 favors Hash Table because it benefits from constant-time average insertions and lookups.
        // Sequence 2 favors BST because of ordered insertions, keeping the tree balanced.
        
    }
}

