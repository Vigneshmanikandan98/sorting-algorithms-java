/**
 * 5311 - 011 Design and Analysis of Algorithms
 * Vignesh Manikandan - 1002012757
 * Ruban Eswaravelu - 1002018133
 */
import java.util.*;
import java.io.File;
import java.io.IOException;

public class MergeSort{

    public static Map<Integer, Long> sortTimeMap = new HashMap<Integer, Long>(); //Using this map to main the time taken for the algorithm to sort the array of different sizes.

     /**
     * Function Name: readFiles
     * Purpose: To read the Integers from the files and calls the mergeSort function to sort them.
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

            Long startTime = System.nanoTime();//Used to get the start time to calculate the time taken for the algorithm 
            mergeSort(numArr, 0, numArr.length-1);
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
    * Function Name: mergeArray
    * Purpose: To merge two subarrays of the numArr
    */
    public static void mergeArray(Integer numArr[], int a, int b, int c){
        int p = b - a + 1; //To find sizes of two subarrays which is to be merged.
        int q = c - b;
  
        Integer leftArr[] = new Integer[p]; //Temp arrays
        Integer rightArr[] = new Integer[q];
  
        for (int i = 0; i < p; ++i){ //To copy the values to the temp array
            leftArr[i] = numArr[a + i];
        }
        for (int j = 0; j < q; ++j){
            rightArr[j] = numArr[b + 1 + j];
        }
  
        int i = 0; //Starting indexes of the two subarrays
        int j = 0;
  
        int k = a; // Starting index of merged subarray array
        while (i < p && j < q) {
            if (leftArr[i] <= rightArr[j]){
                numArr[k] = leftArr[i];
                i++;
            }else{
                numArr[k] = rightArr[j];
                j++;
            }
            k++;
        }
  
        while(i < p){ //Copy remaining elements of leftArr if any
            numArr[k] = leftArr[i];
            i++;
            k++;
        }
  
        while(j < q){ //Copy remaining elements of rightArr if any
            numArr[k] = rightArr[j];
            j++;
            k++;
        }
    }
  
    /**
    * Function Name: mergeSort
    * Purpose: To sort the integers of the array using the mergesort algorithm.
    */
    public static void mergeSort(Integer numArr[], int a, int c){
        if(a < c){
            int b = (c+a)/2; // To find the middle point
  
            // To sort first and second halves
            mergeSort(numArr, a, b);
            mergeSort(numArr, b + 1, c);
  
            // To merge the sorted halves
            mergeArray(numArr, a, b, c);
        }
    }

    /**
     * Function Name: main
     * Purpose: To call the readFiles function with different sizes of array and display a summary of time taken to sort them.
     */
    public static void main(String[] args){
        
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