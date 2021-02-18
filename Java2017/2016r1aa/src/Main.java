import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
                String string = st.nextToken();
                char[] S = string.toCharArray();

                LinkedList S2 = new LinkedList<Character>();
                S2.add(new Character(S[0]));
                char firstLetter = S[0];
                for (int i = 1; i < S.length; i++) {
                    if(S[i] >= firstLetter){
                        firstLetter = S[i];
                        S2.addFirst(new Character(S[i]));
                    }else{
                        S2.addLast(new Character(S[i]));
                    }
                }

                String result = "";
                Character nextChar;
                while(!S2.isEmpty()){
                    nextChar = (Character)S2.remove();
                    result = result + nextChar.toString();
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
