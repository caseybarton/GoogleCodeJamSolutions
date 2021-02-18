import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

import static java.lang.System.exit;

public class Main {
    public static String inputFileName = "in";
    public static String outputFileName = "in.out";
    public static TreeMap map;

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
                String Sstring = st.nextToken();
                char[] S = Sstring.toCharArray();
                map = new TreeMap<Character, Integer>();

                for (char c : S) {
                    Integer charCount = (Integer)map.get(new Character(c));
                    if(charCount == null){
                        map.put(new Character(c), new Integer(1));
                    }else{
                        map.put(new Character(c), new Integer(charCount.intValue() + 1));
                    }
                }
                System.out.printf("map = %s\n", map.toString());

                //order is important!
                int[] numCount = new int[10];
                numCount[0] = zeroCount();
                System.out.printf("map = %s\n", map.toString());
                numCount[2] = twoCount();
                numCount[4] = fourCount();
                System.out.printf("map = %s\n", map.toString());
                numCount[6] = sixCount();
                numCount[7] = sevenCount();
                numCount[5] = fiveCount();
                System.out.printf("map = %s\n", map.toString());
                numCount[1] = oneCount();
                System.out.printf("map = %s\n", map.toString());
                numCount[9] = nineCount();
                numCount[3] = threeCount();
                numCount[8] = eightCount();


                String result = "";
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < numCount[i]; j++) {
                        result = result + Integer.toString(i);
                    }
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

    private static int zeroCount(){
        Integer charCount = (Integer)map.get(new Character('Z'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('Z'), new Integer((Integer)(((Integer) map.get(new Character('Z'))).intValue() - charCount));
            map.put(new Character('E'), new Integer((Integer)(((Integer) map.get(new Character('Z'))).intValue() - charCount));
            map.put(new Character('R'), new Integer((Integer)(((Integer) map.get(new Character('Z'))).intValue() - charCount));
            map.put(new Character('O'), new Integer((Integer)(((Integer) map.get(new Character('Z'))).intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int oneCount(){
        Integer charCount = (Integer)map.get(new Character('O'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('O'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('N'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int twoCount(){
        Integer charCount = (Integer)map.get(new Character('W'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('T'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('W'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('O'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int threeCount(){
        Integer charCount = (Integer)map.get(new Character('R'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('T'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('H'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('R'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int fourCount(){
        Integer charCount = (Integer)map.get(new Character('U'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('F'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('O'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('U'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('R'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int fiveCount(){
        Integer charCount = (Integer)map.get(new Character('V'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('F'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('I'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('V'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }
    private static int sixCount(){
        Integer charCount = (Integer)map.get(new Character('X'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('S'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('I'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('X'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }
    private static int sevenCount(){
        Integer charCount = (Integer)map.get(new Character('S'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('S'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('V'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('N'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int eightCount(){
        Integer charCount = (Integer)map.get(new Character('E'));
        if(charCount == null){
            return 0;
        }else{
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('I'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('G'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('H'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('T'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }

    private static int nineCount(){
        Integer charCount = (Integer)map.get(new Character('N'));
        if(charCount == null){
            return 0;
        }else{
            charCount = new Integer(charCount.intValue()/2);
            map.put(new Character('N'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('I'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('N'), new Integer(charCount.intValue() - charCount));
            map.put(new Character('E'), new Integer(charCount.intValue() - charCount));
        }
        return charCount.intValue();
    }


}
