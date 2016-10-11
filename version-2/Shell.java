import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Arrays;

/**
 * This program implements Shellsort.  Shellsort is an analog
 * to insertionsort.  Insertionsort moves items from the
 * right to the left putting them into order.  The items on 
 * the left are in order, the items on the right have not
 * been looked at.  Each iteration of intsertionsort takes 
 * the very next element and then shifts (or exchanges) items 
 * in the left sorted array one position to the right until
 * the correct position for the new item is found.  One very
 * attractive characteristic of insertionsort is its O(n)
 * performance on partially sorted arrays.  Shellsort takes
 * advantage of this and efficiently makes a partially 
 * sorted array.  The final iteration of shellsort is 
 * insertionsort.  Shellsort is said to h-sort the array.
 * The h-sort uses an increment sequence h to determine
 * which elements to sort.  These elements start off
 * with greater distance and gradually decrease into
 * insertionsort when h is 1.
 * 
 * The code was adapated from Algorithms, 4th Ed.
 */

public class Shell
{
    private static final int MAX_INT = 1000000;
    
    public static void sort(int[] a)
    {
        int N = a.length;
        int h = 1;
        while (h <= N/3) h = 3 * h + 1;
        // H-sort arrays until sorted by insertionsort, i.e., h = 1
        // To put it another way, sort until nothing left to sort
        while (h > 0)
        {
            // Basically insertion sort
            for (int i = h; i < N; i++)
            {
                int key = a[i];
                int j;
                for (j = i; j >= h; j = j - h)
                {
                    // Shift values to the right key's posn is found
                    if (key < a[j - h])
                        a[j] = a[j - h];
                    else
                        break;
                }
                // insert key in posn
                a[j] = key;
            }
            h = h / 3;
        }
    }
        
    public static void main(String[] args)
    {
        int[] a1;
        int[] a2;
        Stopwatch sw1;
        Stopwatch sw2;
        
        for (int i = 250; true; i = i + i)
        {
            a1 = fillRandomly(i);
            a2 = a1.clone();
            StdOut.println("Size = " + i);
            StdOut.println("---------------------------");
            sw1 = new Stopwatch();
            Shell.sort(a1);
            StdOut.println("Shellsort time = " + sw1.elapsedTime());
            sw2 = new Stopwatch();
            Arrays.sort(a2);
            StdOut.println("Java sort time = " + sw1.elapsedTime());
            StdOut.println();
            StdOut.println();
        }
        
    }
    
    public static boolean isSorted(int[] a)
    {
        int N = a.length;
        for (int i = 1; i < N; i++)
            if (a[i - 1] > a[i]) return false;
        return true;
    }
    
    public static int[] fillRandomly(int sz)
    {
        int[] tmp = new int[sz];
        for (int i = 0; i < sz; i++)
            tmp[i] = StdRandom.uniform(-MAX_INT, MAX_INT);
        return tmp;
    }
}