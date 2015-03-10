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
            Hashtable hashtable = passValuesIntoHashtable(allIntegers, converter, reader);
            boolean isItThere;
            BigInteger t = new BigInteger("-10000");

//            for (int i = -10000; i <= 10000; i++) {
                int a = 0;
                do {
                    isItThere = false;
                    BigInteger x = allIntegers[a];
                    BigInteger y = t.subtract(x);
                    BigInteger keyInBigIntForm = y.divide(new BigInteger("1000000"));
                    String keyInStringForm = converter.toString(keyInBigIntForm);
                    double key = Double.parseDouble(keyInStringForm);
                    Object weFoundY = hashtable.get(key);
                    if (weFoundY == null) {
                        a++;
                    } else {
                        isItThere = true;
                        System.out.println("Got it. a: " + a + "\n x: " + x + "\n y: " + y + "\n weFoundY: " + weFoundY
                                + "\n keyInBigIntForm: " + keyInBigIntForm + "\n keyInStringForm: " + keyInStringForm
                                + "\n key: " + key);
                    }
                    if (a == 999999) {
                        System.out.println("Not found.");
                        isItThere = true;
                    }
                } while (!isItThere);
                t = t.add(BigInteger.ONE);
//            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static Hashtable passValuesIntoHashtable(BigInteger[] allIntegers, BigIntegerStringConverter converter, BufferedReader reader) throws IOException {
        String line;
        int i = 0;
        Double d = 0D;
        Hashtable<Double, BigInteger> hashtable = new Hashtable<>();

        while ((line = reader.readLine()) != null) {
            allIntegers[i] = converter.fromString(line);
            //cast string as long
            double hashKey = Double.parseDouble(line)/1000000;
            System.out.println(hashKey);
            hashtable.put(hashKey, allIntegers[i]);
            i++;
            d++;
        }
        return hashtable;
    }
}

//Insert elements of array A into hash table H
//For each x in A, look up t-x in H


//THINGS I LEARNED
//I can't afford to put all these numbers into an array on the computer. 3/8 update: yes, I can.
//Going through the million in order, or looking at the twenty-thousand-one in order, is not going to
//necessarily yield the maximum.
//Searching number by number is incredibly slow, unless it's just the printing that's slow.