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
                String result = "";
                st = new StringTokenizer(inputLines.get(t+1));
                int N = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());
                int O = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                int G = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());

                boolean impossible = false;
                char prev = 'n';
                while(true){
                    boolean done = true;
                    if(R > 0 && prev != 'R' && prev != 'O' && prev != 'V'){
                        result = result + "R";
                        R--;
                        prev = 'R';
                        done = false;
                    }
                    if(O > 0 && prev != 'R' && prev != 'O' && prev != 'Y'){
                        result = result + "O";
                        O--;
                        prev = 'O';
                        done = false;
                    }
                    if(Y > 0 && prev != 'O' && prev != 'Y' && prev != 'G'){
                        result = result + "Y";
                        Y--;
                        prev = 'Y';
                        done = false;
                    }
                    if(G > 0 && prev != 'Y' && prev != 'G' && prev != 'B'){
                        result = result + "G";
                        G--;
                        prev = 'G';
                        done = false;
                    }
                    if(B > 0 && prev != 'G' && prev != 'B' && prev != 'V'){
                        result = result + "B";
                        B--;
                        prev = 'B';
                        done = false;
                    }
                    if(V > 0 && prev != 'B' && prev != 'V' && prev != 'R'){
                        result = result + "V";
                        V--;
                        prev = 'V';
                        done = false;
                    }
                    if(done){
                        break;
                    }
                }

                System.out.print("Case #" + Integer.toString(t+1) + ": " + result + "\n");
                if(R > 0 || O > 0 || Y > 0 || G > 0 || B > 0 || V > 0){
                    result = "IMPOSSIBLE";
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
