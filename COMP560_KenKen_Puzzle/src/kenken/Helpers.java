package kenken;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;
import java.lang.*;

//Table of Contents:
//	I. Array functions
//	II. List functions
//	III. Conversion functions
//	IV. Printing functions

public class Helpers {

	// I. ARRAY FUNCTIONS
    
    public static boolean arrayIsUnique(int[] arr) {
    	// determines if an int array contains distinct values
    	for (int i =0; i < arr.length - 1; i++) {
    		for (int j=i+1; j < arr.length; j++) {
    			if (arr[j] == arr[i]) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
	public static int arraySum(int[] arr) {
        // returns the sum of an array
		return IntStream.of(arr).sum();
       
    }
    public static int arrayProduct(int[] arr) {
    	// returns the product of an int array's contents
        int x = 1; 
        for (int answer : arr) {
        	x *= answer;
        }
        return x;
    }
    
    public static boolean contains(int[] arr, int key) {
       // checks if an int array contains a certain integer
    	for (int i =0; i < arr.length; i++) {
    	   if (arr[i] == key) {
    		   return true;
    	   }
       }
       return false;
       }
    
    public static int min(int[] arr) {
    	//returns min value in an int array
    	int min = arr[0];
    	for (int i = 1; i < arr.length; i++) {
    		if (arr[i] < min) {
    			min = arr[i];
    		}
    	}
    	return min;
    
    }
    public static int max(int[] arr) {
        // returns max value in an int array
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
        }

    
    // II. ARRAYLIST FUNCTIONS

    public static int count(ArrayList<Integer> arr, int i) {
        // returns the number of times an integer "i" appears in a list of integers
        int count = 0;
        for (int x : arr) {
            if (x == i) {
                count++;
            }
        }
        return count;
    }
    public static boolean areAllUnique(ArrayList<Integer> list) {
        // determines if a list contains unique vallues
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            if (!newList.contains(n)) {
                newList.add(n);
            } else {
                return false;
            }
        }
        return true;
    }
    
    // III. CONVERSION FUNCTIONS

    public static int[] intStringToIntArray(String s) {
    	// converts a string like "2 4 12 6" to {2, 4, 12, 6}
        return Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] convertToIntArray(ArrayList<Integer> list) {
        // converts a list of integers into an integer array
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    

     // IV. PRINTING FUNCTIONS

    public static void printIntArray(int[] ar) {
        // prints out an int array separated by spaces
        for (int i = 0; i < ar.length-1; i++) {
            System.out.print(ar[i]+" ");
        }
        System.out.println(ar[ar.length-1]);
    }

    public static void printStringArray(String[] ar) {
        // prints out a String array
        for (int i = 0; i < ar.length-1; i++) {
            System.out.print(ar[i]+" ");
        }
        System.out.println(ar[ar.length-1]);
    }

    public static void printIntArray2D(int[][] arr) {
        // prints out a 2D int array separated by spaces
        for (int[] ar : arr) {
            printIntArray(ar);
        }
    }

	
}