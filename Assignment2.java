package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Creates Binary Search Trees and AVLTrees and compares their performances.
 *
 * @author Bilal Zahid
 * Assignment 2
 * @since 12 February 
 * I have followed the UNCG Academic Integrity policy on this assignment.
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testBST();
        testAVL();
        runBenchMark();
    }

    /**
     * Creates a BST and prints it out.
     */
    private static void testBST() {
        List<Integer> intList = new ArrayList<>();
        //Generate 20 random integers and insert them into a BST
        intList.addAll(ThreadLocalRandom.current().ints(10, 99)
                .distinct().limit(20).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll));

        BinarySearchTree<Integer> binST = new BinarySearchTree<>();

        for (int i = 0; i < intList.size(); i++) {
            binST.insert(intList.get(i));
        }

        binST.printSideways("Binary Search Tree");

        System.out.println("\nIs the BST full?: " + binST.isFull());
        System.out.println(
                "Number of leaf nodes in BST: " + binST.countLeafNodes());
        
    }

    /**
     * Creates an AVL Tree and prints it out while checking for balance.
     */
    private static void testAVL() {
        List<Integer> intList = new ArrayList<>();

        intList.addAll(Arrays.asList(32, 15, 5, 8, 40, 68, 18, 25, 17, 35, 33,
                39, 2, 98, 55, 96, 60));
        AVLTree<Integer> avltree = new AVLTree<>();

        for (int i = 0; i < intList.size(); i++) {
            avltree.insert(intList.get(i));
            avltree.checkBalance();
        }
        avltree.printSideways("AVL Tree");
        avltree.checkBalance();

        while (!avltree.isEmpty()) {
            avltree.remove(avltree.getRoot().element);
            avltree.printSideways("Remove root");
            avltree.checkBalance();
        }
    }

    /**
     * Runs a performance comparison between ordinary Binary Search Trees and
     * AVL trees.
     */
    private static void runBenchMark() {
        List<Integer> intList = new ArrayList<>();
        Random random = new Random();
        int currentNum = 0;
        int upperBound = 60000;
        for (int i = 0; i < 50000; i++) {
            intList.add(currentNum + random.nextInt(upperBound - currentNum + 100));
            currentNum = intList.get(i);
        }

        long startTime = System.currentTimeMillis();
        BinarySearchTree<Integer> testBST = new BinarySearchTree<>();
        for (int i = 0; i < intList.size(); i++) {
            testBST.insert(intList.get(i));
        }
        long endTime = System.currentTimeMillis();

        System.out.println("The elapsed time for a BST is " + (endTime - startTime));

        long StartTime2 = System.currentTimeMillis();
        AVLTree<Integer> testAVL = new AVLTree<>();
        for (int i = 0; i < intList.size(); i++) {
            testAVL.insert(intList.get(i));
        }
        long endTime2 = System.currentTimeMillis();
        
        System.out.println("The elapsed time for a AVLT is " + (endTime2 - StartTime2));
        
        List<Integer> intList2 = new ArrayList<>();
        Random random2 = new Random();
        int currentNum2 = 0;
        int upperBound2 = 70000;
        for (int i = 0; i < 50000; i++) {
            intList2.add(currentNum2 + random.nextInt(upperBound2 - currentNum2 + 100));
            currentNum2 = intList2.get(i);
        }
        
        long startTime3 = System.currentTimeMillis();
        for (int i = 0; i < intList2.size(); i++){
            testBST.contains(intList2.get(i));
        }
        long endTime3 = System.currentTimeMillis();
        System.out.println("The elapsed time search for a BST is " + (endTime3 - startTime3));
        
        long startTime4 = System.currentTimeMillis();
        for (int i = 0; i < intList2.size(); i++){
            testAVL.contains(intList2.get(i));
        }
        long endTime4 = System.currentTimeMillis();
        System.out.println("The elapsed time search for a AVLT is " + (endTime4 - startTime4));
    }

}
