import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {
    public static String inputFileName = "in";
    public static String outputFileName = "out";

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
            int numTestCases = Integer.parseInt(st.nextToken()); //T

            for (int i = 0; i < numTestCases; i++) {
                st = new StringTokenizer(inputLines.get(i+1));
                int N = Integer.parseInt(st.nextToken());
                int K = Integer.parseInt(st.nextToken());
                boolean isON = (K & (0x1 << N-1) >> N-1) == 1;
                String resultString = isON ? "ON" : "OFF";

                bw.write("Case #" + Integer.toString(i+1) + ": " + resultString + "\n");
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
