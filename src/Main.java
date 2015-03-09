import javafx.util.converter.BigIntegerStringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

/**
 * Created by kyleofori on 3/8/15.
 */
public class Main {

    /*The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash table applications).
    The file contains 1 million integers, both positive and negative (there might be some repetitions!).
    This is your array of integers, with the ith row of the file specifying the ith entry of the array.

    Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that
    there are distinct numbers x,y in the input file that satisfy x+y=t.
    (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)

    Write your numeric answer (an integer between 0 and 20001) in the space provided.*/

    public static void main(String[] args) {
        Path path = Paths.get("/Users/kyleofori/Documents/Coursera/algo1-programming_prob-2sum.txt");
        Charset charset = Charset.forName("US-ASCII");
        BigInteger[] allIntegers = new BigInteger[1000000];
        BigIntegerStringConverter converter = new BigIntegerStringConverter();

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            passValuesIntoHashtable(allIntegers, converter, reader);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static void passValuesIntoHashtable(BigInteger[] allIntegers, BigIntegerStringConverter converter, BufferedReader reader) throws IOException {
        String line;
        int i = 0;
        Long l = 0L;
        Hashtable<Long, BigInteger> hashtable = new Hashtable<>();

        while ((line = reader.readLine()) != null) {
            allIntegers[i] = converter.fromString(line);
            hashtable.put(l, allIntegers[i]);
            i++;
            l++;
        }
        System.out.println(i);
        System.out.println(hashtable.get(3L));
    }
}

//Insert elements of array A into hash table H
//For each x in A, look up t-x in H


//THINGS I LEARNED
//I can't afford to put all these numbers into an array on the computer. 3/8 update: yes, I can.
//Going through the million in order, or looking at the twenty-thousand-one in order, is not going to
//necessarily yield the maximum.