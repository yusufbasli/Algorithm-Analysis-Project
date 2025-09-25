package HW;

import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    public static void main(String[] args) {
        // Instance 1: Random array (favors Quick Sort)
        int[] instance1 = generateRandomArray(1000);
        
        // Instance 2: Nearly sorted array (favors Merge Sort)
        int[] instance2 = generateNearlySortedArray(100000000);
        
        // Test Instance 1
        System.out.println("\nTesting Instance 1 (Random Array):");
        testSortingAlgorithms(instance1.clone(), instance1.clone());
        
        // Test Instance 2
        System.out.println("\nTesting Instance 2 (Nearly Sorted Array):");
        testSortingAlgorithms(instance2.clone(), instance2.clone());
    }
    
    private static void testSortingAlgorithms(int[] arr1, int[] arr2) {
        // Test Quick Sort
        long startTime = System.nanoTime();
        quickSort(arr1, 0, arr1.length - 1);
        long quickSortTime = System.nanoTime() - startTime;
        
        // Test Merge Sort
        startTime = System.nanoTime();
        mergeSort(arr2, 0, arr2.length - 1);
        long mergeSortTime = System.nanoTime() - startTime;
        
        System.out.println("Quick Sort time: " + quickSortTime / 1000000.0 + " ms");
        System.out.println("Merge Sort time: " + mergeSortTime / 1000000.0 + " ms");
    }
    
    // Quick Sort Implementation
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    // Merge Sort Implementation
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Helper methods to generate test arrays
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
    
    private static int[] generateNearlySortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        // Introduce small perturbations
        Random rand = new Random();
        for (int i = 0; i < size/10; i++) {
            int pos1 = rand.nextInt(size);
            int pos2 = rand.nextInt(size);
            int temp = arr[pos1];
            arr[pos1] = arr[pos2];
            arr[pos2] = temp;
        }
        return arr;
    }
}
