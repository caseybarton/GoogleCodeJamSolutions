import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.exit;

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
            int T = Integer.parseInt(st.nextToken());



            for (int t = 0; t < T; t++) {
                st = new StringTokenizer(inputLines.get(t+1));
                long N = Long.parseLong(st.nextToken()); //numStalls
                long K = Long.parseLong(st.nextToken()); //numPeople
                long minLengthSegment;
                long maxLengthSegment;
                long min;
                long max;


                int i = 0;

                long preK = K - 1; //K before adding the last person

                double log2maxEvenK = Math.floor(Math.log10(preK + 1) / Math.log10(2.0));
                long maxEvenK = Math.round(Math.pow(2.0, log2maxEvenK)) - 1; //greatest number that evenly divides N


                long numRemainingStalls = N - maxEvenK;
                long numSegments = maxEvenK + 1;
                double avgSegmentLength = numRemainingStalls / numSegments;
                System.out.printf("avgSegmentLength = %f\n", avgSegmentLength);
                if (numRemainingStalls % numSegments == 0){
                    minLengthSegment = Math.round(avgSegmentLength);
                    maxLengthSegment = minLengthSegment;
                }else{
                    minLengthSegment = Math.round(Math.floor(avgSegmentLength));
                    maxLengthSegment = minLengthSegment + 1;
                }

                if (maxLengthSegment % 2 == 0){
                    min = (maxLengthSegment - 1) / 2;
                    max = min + 1;
                }else{
                    min = (maxLengthSegment - 1) / 2;
                    max = min;
                }



                String result = Long.toString(max) + " " + Long.toString(min);
                bw.write("Case #" + Integer.toString(t+1) + ": " + result + "\n");
                System.out.print("Case #" + Integer.toString(t+1) + ": " + result + "\n");
            }

            //Code goes here ^
            bw.flush();
        }catch(FileNotFoundException fnfe){
            System.err.print("Error: File \"" + inputFileName + "\" not found?: " + fnfe.getMessage());
            exit(1);
        }catch(IOException ioe){
            System.err.print("Error writing file: " + ioe.getMessage());
            exit(2);
        }

    }


}
