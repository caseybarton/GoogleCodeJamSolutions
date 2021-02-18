/*
    total time: 1:38:23
 */
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Main {
    public static String inputFileName = "in";
    public static String outputFileName = "in.out";

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br;
        BufferedWriter bw;
        ArrayList<String> inputLines;

        if(args.length > 0) {
            inputFileName = args[0];
            int i = 0;
            while (args[0].length() > i && args[0].charAt(i) != '.') {
                i++;
            }
            outputFileName = args[0].substring(0, i) + ".out";
        }

        try{
            br = new BufferedReader(new FileReader(inputFileName));
            bw = new BufferedWriter(new FileWriter(outputFileName));
            String nextLine;
            inputLines = new ArrayList<String>();
            while((nextLine = br.readLine()) != null){
                inputLines.add(nextLine);
            }
            //Code goes here v

            StringTokenizer st = new StringTokenizer(inputLines.get(0));
            int C = Integer.parseInt(st.nextToken());

            for (int c = 0; c < C; c++) {
                st = new StringTokenizer(inputLines.get(c+1));
                int N = Integer.parseInt(st.nextToken());
                BigInteger[] t = new BigInteger[N];
                for (int n = 0; n < N; n++) {
                    t[n] = new BigInteger(st.nextToken());
                    System.out.printf("t[%d] = %s\n", n, t[n].toString());
                }

                BigInteger diffGCD = t[0].subtract(t[1]).abs(); //T
                for (int n = 2; n < N; n++) {
                    diffGCD = diffGCD.gcd(t[n-1].subtract(t[n]).abs());
                    System.out.printf("diffGCD = %s\n", diffGCD.toString());
                }

                BigInteger y = diffGCD.subtract(t[0].mod(diffGCD));
                if(t[0].mod(diffGCD).compareTo(new BigInteger("0")) == 0){
                    y = new BigInteger("0");
                }
//                System.out.printf("y = %s, t[0] = \n");
//                System.out.printf("Case #" + Integer.toString(c+1) + ": " + y.toString() + "\n");

                bw.write("Case #" + Integer.toString(c+1) + ": " + y.toString() + "\n");
            }



            //Code goes here ^
            bw.flush();
        }catch(FileNotFoundException fnfe){
            System.err.print("Error: File \"" + inputFileName + "\" not found!\n");
            exit(1);
        }catch(IOException ioe){
            System.err.print("Error writing file: " + ioe.getMessage());
            exit(2);
        }

    }


}
