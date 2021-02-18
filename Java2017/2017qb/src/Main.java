import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                char[] N = st.nextToken().toCharArray();
                //int N = Integer.parseInt(st.nextToken());
                int max = 0;
                int maxSetter = 0;
                for (int i = 0; i < N.length; i++) {
                    if(Integer.parseInt("" + N[i]) > max){
                        max = Integer.parseInt("" + N[i]);
                        maxSetter = i;
                    }else if(Integer.parseInt("" + N[i]) < max){
                        int oldMax = Integer.parseInt(""+N[maxSetter]);
                        N[maxSetter] = Integer.toString(--oldMax).charAt(0);
                        for (int j = maxSetter+1; j < N.length; j++) {
                            N[j] = '9';
                        }
                    }
                }

                //remove leading 0
                int i = 0;
                while(N[i] == '0'){
                    i++;
                }


                String result = new String(Arrays.copyOfRange(N, i, N.length));
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
