# CMPE 211 - Algorithm Performance Analysis

This project was completed for the CMPE 211 course. It involves a practical analysis and performance comparison of fundamental sorting and searching algorithms. The goal is to observe how these algorithms perform under different data conditions.

**Author:** Yusuf Baslı
**ID:** 122200109

## Project Structure

The project is divided into two main parts:

1.  **Part 1: Sorting Algorithms:** Compares the performance of Quick Sort and Merge Sort.
2.  **Part 2: Search Algorithms:** Compares the performance of a Binary Search Tree (BST) and a Hash Table.

---

## Part 1: Sorting Algorithms Comparison

This section analyzes the efficiency of Quick Sort versus Merge Sort using two distinct data instances designed to favor one algorithm over the other.

### Algorithms Implemented

* **Quick Sort**: Known for its fast average-case performance.
* **Merge Sort**: Known for its consistent worst-case performance.

### Methodology & Findings

Two primary test cases were used:

**1. Instance 1: Randomly Generated Arrays**

* **Hypothesis**: Quick Sort is expected to be faster for completely random data distributions.
* **Results**: The tests confirmed the hypothesis across various array sizes (10, 1000, 100000). Quick Sort consistently outperformed Merge Sort.

  * *Example (100k array)*: Quick Sort took ~8.76 ms while Merge Sort took ~13.31 ms.

**2. Instance 2: Nearly Sorted Arrays**

* **Hypothesis**: Merge Sort is expected to be more efficient for arrays that are already mostly in order.
* **Results**: The results were nuanced. Merge Sort was faster for smaller, nearly sorted arrays. However, as the array size grew, Quick Sort often became faster again.

  * *Example (100 element array)*: Merge Sort (0.0337 ms) was faster than Quick Sort (0.0686 ms).
  * *Example (100m element array)*: Quick Sort (8339 ms) was slightly faster than Merge Sort (8537 ms).

---

## Part 2: Search Algorithms Comparison

This section analyzes the performance of a Binary Search Tree (BST) versus a Hash Table. The comparison is based on two sequences of `Put` (insert) and `Get` (search) operations.

### Data Structures Implemented

* **Binary Search Tree (BST)**: A node-based binary tree data structure.
* **Hash Table**: A data structure that implements an associative array abstract data type.

### Methodology & Findings

Two operation sequences were used to test performance:

**1. Sequence 1: Unordered Insertions**

* **Description**: This sequence involves inserting elements in a non-sequential, unordered fashion.
* **Hypothesis**: The Hash Table will perform better due to its O(1) average time complexity for insertions and lookups. The BST is expected to become unbalanced with unordered data, degrading its performance.
* **Results**: The Hash Table was significantly faster. Unordered insertions caused the BST to become unbalanced, leading to much slower search and insert operations compared to the Hash Table's constant-time operations.

  * *Example Result*: BST Time: 234,100 ns vs. Hash Table Time: 15,800 ns.

**2. Sequence 2: Ordered Insertions**

* **Description**: This sequence involves inserting elements in a mostly ordered fashion.
* **Hypothesis**: The BST will perform better. Ordered data helps the BST remain balanced, allowing it to leverage its efficient O(log n) search and insertion time. Hash Tables might experience more collisions with ordered data.
* **Results**: The BST demonstrated better performance in the examples. By staying balanced, it provided more efficient insertions and lookups than the Hash Table in this specific scenario.

  * *Example Result*: BST Time: 4,100 ns vs. Hash Table Time: 5,000 ns.

---

## How to Run the Code

1. Ensure you have a Java Development Kit (JDK) installed.
2. Navigate to the directory containing the `.java` files.
3. Compile the code:

   ```sh
   javac HW/SortingComparison.java HW/SearchComparison.java