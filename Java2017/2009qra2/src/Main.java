/*
 * total time taken: 6:29:22
 */

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
            int L = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            String[][] patterns = new String[N][L];
            for (int n = 0; n < N; n++) {
                for (int l = 0; l < L; l++) {
                    patterns[n][l] = "";
                }
            }
            for(int n = 0; n<N; n++){
                int lineNum = n + 1 + D;
                String pattern = inputLines.get(lineNum);
                int l = 0;
                boolean inParens = false;
                for (int j = 0; j < pattern.length(); j++) {
                    if(pattern.charAt(j) == '('){
                        inParens = true;
                    }else if(pattern.charAt(j) == ')'){
                        inParens = false;
                        l++;
                    }else if(inParens){
                        System.out.printf("n=%d l=%d j=%d\n", n, l, j);
                        patterns[n][l] += ("" + pattern.charAt(j));
                        System.out.printf("patterns[n][l] = %s\n", patterns[n][l]);
                    }else{
                        System.out.printf("n=%d l=%d j=%d\n", n, l, j);
                        patterns[n][l] += (""+ pattern.charAt(j));
                        System.out.printf("patterns[n][l] = %s\n", patterns[n][l]);
                        l++;
                    }
                }
            }

            int[] matches = new int[N];
            int[] totalMatches = new int[N]; //initialized to 0
            for (int d = 0; d < D; d++) {
                for (int n = 0; n < N; n++) {
                    matches[n] = 1;
                }
                int lineNum = d + 1;
                for (int l = 0; l < L; l++) {
                    for (int n = 0; n < N; n++) {
                        if(matches[n] == 1 && !patterns[n][l].contains("" + inputLines.get(lineNum).charAt(l))){
                            matches[n] = 0;
                            System.out.printf("patterns[%d][%d] = %s does not contain %s\n", n, l, patterns[n][l], ""+inputLines.get(lineNum).charAt(l));
                        }
                    }
                }
                for (int n = 0; n < N; n++) {
                    //System.out.printf("totalMatches[%d] += matches[%d]\n %d += %d\n", n, n, totalMatches[n], matches[n]);
                    totalMatches[n] += matches[n];
                }
            }

            for (int n = 0; n < N; n++) {
                bw.write("Case #" + Integer.toString(n+1) + ": " + Integer.toString(totalMatches[n]) + "\n");
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
