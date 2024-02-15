/**
 * 5311 - 011 Design and Analysis of Algorithms
 * Vignesh Manikandan - 1002012757
 * Ruban Eswaravelu - 1002018133
 */
import java.util.*;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InsertionSort {

    public static Map<Integer, Long> sortTimeMap = new HashMap<Integer, Long>(); //Using this map to main the time taken for the algorithm to sort the array of different sizes.

    /**
     * Function Name: generateNumber
     * Purpose: To generate random numbers ranging from 0 to 6000 and save it in a text file and calls the insertion sort algorithm to sort them.
     */
    public static void generateNumber(int n){
        String fileName = "arr"+n+".txt"; //Setting the file name
        ArrayList<Integer> listNumbers = new ArrayList<Integer>(); //to keep the numbers in a ArrayList.
        try{
            int max = 6000;
            int min = 0;
            int range = max - min + 1;

            FileWriter Writer = new FileWriter(fileName);   //Writing the generated number to the file based on size of the array.
            for(int i=0;i<n;i++){
                int num = (int) (Math.random() * range) + min;
                String numStr = Integer.toString(num); //Converting to String because write() accepts only String variable.
                Writer.write(numStr+"\n");
            }
            Writer.close();

            File numFile = new File(fileName); //Used to read the file and add the integer to the list to sort them.
            Scanner Reader = new Scanner(numFile);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                listNumbers.add(Integer.parseInt(data));
            }
            Reader.close();

            Integer[] numArr = listNumbers.toArray(new Integer[listNumbers.size()]); // Converting the ArrayList to Integer array for sorting.

            Long startTime = System.nanoTime(); //Used to get the start time to calculate the time taken for the algorithm 
            Integer[] sortedArr = insertionSort(numArr);
            Long endTime = System.nanoTime(); //Used to get the end time to calculate the time taken for the algorithm 
            Long elapsedTime = endTime - startTime;
            System.out.println();
            System.out.println("Time taken to sort "+n+" elements is "+elapsedTime+"ns");
            System.out.println("Sorted Array of size "+n+": ");
            for(Integer numObj: sortedArr){ // To print the Sorted array
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
     * Function Name: insertionSort
     * Purpose: To sort the given array using insertion sort algorithm and returns the sorted array.
     */
    public static Integer[] insertionSort(Integer[] numArr){
        int key,i;
        for(int j=1;j<numArr.length;++j){
            key = numArr[j];
            i=j-1;
            while(i>=0 && numArr[i]>key){ //changing the position of the values which are greater than the key to one position ahead.
                numArr[i+1] = numArr[i];
                i=i-1;
            }
            numArr[i+1] = key;
        }
        return numArr;
    }

    /**
     * Function Name: main
     * Purpose: To call the generateNumber function with different sizes of array and display a summary of time taken to sort them.
     */
    public static void main(String[] args) {

        generateNumber(20);
        generateNumber(100);
        generateNumber(1000);
        generateNumber(4000);     
        
        System.out.println();

        for (Map.Entry<Integer, Long> timeObj : sortTimeMap.entrySet()) { //To display the time taken by the algorithm to sort.
            System.out.print("Time taken to sort array of size "+timeObj.getKey() + ": ");
            System.out.println(timeObj.getValue()+"ns");
        }
    }
}
