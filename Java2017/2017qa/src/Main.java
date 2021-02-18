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
                String pancakes = st.nextToken();
                char[] pancakesArr = pancakes.toCharArray();
                int K = Integer.parseInt(st.nextToken()); //flipper size

                boolean IMPOSSIBLE = false;
                int flips = 0;
                for (int i = 0; i < pancakesArr.length; i++) {
                    if(pancakesArr[i] == '-'){
                        if(pancakesArr.length - i < K){
                            IMPOSSIBLE = true;
                            break;
                        }
                        for (int k = 0; k < K; k++) {
                            if(pancakesArr[i+k] == '+'){
                                pancakesArr[i+k] = '-';
                            }else{
                                pancakesArr[i+k] = '+';
                            }
                        }
                        flips++;
                    }
                }


                String result;
                if(IMPOSSIBLE){
                    result = "IMPOSSIBLE";
                }else{
                    result = Integer.toString(flips);
                }

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
