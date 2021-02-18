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


            int testFirstLine = 1;
            for (int t = 0; t < T; t++) {
                st = new StringTokenizer(inputLines.get(testFirstLine));
                int N = Integer.parseInt(st.nextToken()); //num ingredients
                int P = Integer.parseInt(st.nextToken()); //num packages

                st = new StringTokenizer(inputLines.get(testFirstLine+1));
                int[] R = new int[N]; //required ingredients
                for (int n = 0; n < N; n++) {
                    R[n] = Integer.parseInt(st.nextToken());
                }

                int[][] Q = new int[N][P]; //packages
                for (int n = 0; n < N; n++) {
                    st = new StringTokenizer(inputLines.get(testFirstLine+n+2));
                    for (int p = 0; p < P; p++) {
                        Q[n][p] = Integer.parseInt(st.nextToken());
                    }
                }
                testFirstLine = testFirstLine + N + 2;





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
