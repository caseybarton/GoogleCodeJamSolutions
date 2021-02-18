import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

            TreeMap alienLang = new TreeMap<String, Integer>();
            for (int i = 0; i < D; i++) {
                int line = i+1;
                alienLang.put(inputLines.get(line), 1);
            }

            for (int i = 0; i < N; i++) {
                int lineNum = i + 1 + D;
                String line = inputLines.get(lineNum);

                ArrayList groups = new ArrayList<String>();
                boolean inParenthesis = false;
                int groupNum = 0;
                for (int j = 0; j < line.length(); j++) {
                    if(line.charAt(j) == '('){
                        inParenthesis = true;
                    }else if(line.charAt(j) == ')'){
                        inParenthesis = false;
                        groupNum++;
                    }else if(inParenthesis){
                        if(groups.size() <= groupNum){
                            groups.add(""+line.charAt(j));
                        }else{
                            groups.set(groupNum, (String)groups.get(groupNum) + line.charAt(j));
                        }
                    }else{
                        if(groups.size() <= groupNum){
                            groups.add(""+line.charAt(j));
                        }else{
                            groups.set(groupNum, (String)groups.get(groupNum) + line.charAt(j));
                        }
                        groupNum++;
                    }
                }

                Object[] groupsObjects = groups.toArray();
                String[] groupsArray = new String[groupsObjects.length];
                for (int j = 0; j < groupsObjects.length; j++) {
                    groupsArray[j] = (String)groupsObjects[j];
                }
                ArrayList<String> permutations = new ArrayList<String>();
                getPermutations(groupsArray, permutations);

                int matchCtr = 0;
                for (int j = 0; j < permutations.size(); j++) {
                    if(alienLang.containsKey(permutations.get(j))){
                        matchCtr++;
                    }
                }
                bw.write("Case #" + Integer.toString(i) + ": " + Integer.toString(matchCtr) + "\n");

            }


            //Code goes here ^
            bw.flush();
        }catch(FileNotFoundException fnfe){
            System.err.print("Error: File \"" + inputFileName + "\" not found!: " + fnfe.getMessage());
            exit(1);
        }catch(IOException ioe){
            System.err.print("Error writing file: " + ioe.getMessage());
            exit(2);
//        }catch(Exception e){
//            System.err.print("Error: " + e.getMessage());
        }

    }

    static void getPermutations(String[] remainingGroups, ArrayList permutations){
        getPermutations(remainingGroups, permutations, "");
    }

    static void getPermutations(String[] remainingGroups, ArrayList permutations, String currentString){

        for (int i = 0; i < remainingGroups[0].length(); i++) {
            if(remainingGroups.length == 1) {
                permutations.add(currentString + remainingGroups[0].charAt(i));
            }else{
                String[] nextRemainingGroups = Arrays.copyOfRange(remainingGroups, 1, remainingGroups.length);
                getPermutations(nextRemainingGroups, permutations, currentString + remainingGroups[0].charAt(i));
            }
        }
    }
}
