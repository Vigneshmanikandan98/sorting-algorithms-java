/**
 * 5311 - 011 Design and Analysis of Algorithms
 * Vignesh Manikandan - 1002012757
 * Ruban Eswaravelu - 1002018133
 */
import java.util.*;
import java.io.File;
import java.io.IOException;

public class QuickSort{
    
    public static Map<Integer, Long> sortTimeMap = new HashMap<Integer, Long>(); //Using this map to main the time taken for the algorithm to sort the array of different sizes.

    /**
    * Function Name: readFiles
    * Purpose: To read the Integers from the files and calls the quickSort function to sort them.
    */
    public static void readFiles(int n){

        String fileName = "arr"+n+".txt"; //Setting the file name
        ArrayList<Integer> listNumbers = new ArrayList<Integer>(); //to keep the numbers in a ArrayList.

        try{

            File numFile = new File(fileName); //Used to read the file and add the integer to the list to sort them.
            Scanner Reader = new Scanner(numFile);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                listNumbers.add(Integer.parseInt(data));
            }
            Reader.close();

            Integer[] numArr = listNumbers.toArray(new Integer[listNumbers.size()]); // Converting the ArrayList to Integer array for sorting.

            Long startTime = System.nanoTime(); //Used to get the start time to calculate the time taken for the algorithm
            quickSort(numArr, 0, numArr.length-1);
            Long endTime = System.nanoTime(); //Used to get the end time to calculate the time taken for the algorithm
            Long elapsedTime = endTime - startTime;
            System.out.println();
            System.out.println("Time taken to sort "+n+" elements is "+elapsedTime+"ns");
            System.out.println("Sorted Array of size "+n+": ");
            for(Integer numObj: numArr){ // To print the Sorted array
                System.out.print(numObj+" ");
            }
            System.out.println();

            sortTimeMap.put(n,elapsedTime);

        }catch(IOException e1){
            System.out.println("An error has occurred.");
            e1.printStackTrace();
        }
    }

    /**
    * Function Name: swapValues
    * Purpose: To swap two elements of the array
    */
    public static void swapValues(Integer[] numArr, int i, int j) {
        int temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = temp;
    }
 
    /**
    * Function Name: partitionArray
    * Purpose: This function takes the last element as pivot and places the pivot element at its correct position in the array, and places all smaller (smaller than pivot)
    *          to left of pivot and all greater elements to right of the pivot.
    */
    public static int partitionArray(Integer[] numArr, int left, int right){
        int pivot = numArr[right];
        int i = (left - 1);
    
        for(int j = left; j <= right - 1; j++)
        {
            if (numArr[j] < pivot){ //If the current value is smaller than the pivot.
                i++;
                swapValues(numArr, i, j);
            }
        }
        swapValues(numArr, i + 1, right);
        return (i + 1);
    }
    
    /**
    * Function Name: quickSort
    * Purpose: To sort the integers of the array using the quicksort algorithm.
    */
    public static void quickSort(Integer[] numArr, int left, int right){

        if (left < right){
            int partitionIndex = partitionArray(numArr, left, right);
    
            quickSort(numArr, left, partitionIndex - 1);
            quickSort(numArr, partitionIndex + 1, right);
        }
    }

    /**
     * Function Name: main
     * Purpose: To call the readFiles function with different sizes of array and display a summary of time taken to sort them.
     */
    public static void main(String[] args) {
         
        readFiles(20);
        readFiles(100);
        readFiles(1000);
        readFiles(4000);

        System.out.println();
        
        for (Map.Entry<Integer, Long> timeObj : sortTimeMap.entrySet()) { //To display the time taken by the algorithm to sort.
            System.out.print("Time taken to sort array of size "+timeObj.getKey() + ": ");
            System.out.println(timeObj.getValue()+"ns");
        }
    }
}