/*
    time taken: 1:19:44
 */
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
                st = new StringTokenizer(inputLines.get(t*2+1));
                int R = Integer.parseInt(st.nextToken());
                int K = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(inputLines.get(t*2+2));
                int[] G = new int[N];
                for (int n = 0; n < N; n++) {
                    G[n] = Integer.parseInt(st.nextToken());
                }

                int nextGroupNum = 0;
                long eurosMade = 0;
                boolean queueFits = false;
                int queueSum = 0;
                for (int r = 0; r < R; r++) {
                    int numSittingGroups = 0;
                    int remainingSeats = K;

                    while(G[nextGroupNum] <= remainingSeats && numSittingGroups < N){
                        numSittingGroups++;
                        remainingSeats -= G[nextGroupNum];
                        eurosMade += G[nextGroupNum];
                        //System.out.printf("ride = %d, group=%d, remainingSeats = %d, eurosMade = %d\n", r, G[nextGroupNum], remainingSeats, eurosMade);
                        nextGroupNum = loopingIterate(nextGroupNum, N-1);
                    }
                    if(numSittingGroups == N){
                        queueFits = true;
                        queueSum = K - remainingSeats;
                        break;
                    }
                }
                if(queueFits){
                    eurosMade = (long)R*(long)queueSum;
                }


                bw.write("Case #" + Integer.toString(t+1) + ": " + Long.toString(eurosMade) + "\n");
                System.out.print("Case #" + Integer.toString(t+1) + ": " + Long.toString(eurosMade) + "\n");
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

    static int loopingIterate(int num, int max){
        if(num == max){
            return 0;
        }else{
            return ++num;
        }
    }

}
