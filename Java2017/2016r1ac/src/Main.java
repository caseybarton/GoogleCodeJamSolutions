import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

import static java.lang.System.exit;

public class Main {
    public static String inputFileName = "in";
    public static String outputFileName = "in.out";
    public static TreeMap[] headChildrenSets;

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
                st = new StringTokenizer(inputLines.get(2*t+1));
                int Ncount = Integer.parseInt(st.nextToken());

                TreeMap headChildren = new TreeMap<Integer, Object>();
                for (int n = 1; n < Ncount+1; n++) {
                    headChildren.put(new Integer(n), null);
                    //System.out.printf("headChildren = %s\n", headChildren.toString());
                }

                int[] N = new int[Ncount];
                st = new StringTokenizer(inputLines.get(2*t+2));
                for (int n = 0; n < Ncount; n++) {
                    N[n] = Integer.parseInt(st.nextToken());
                    headChildren.remove(new Integer(N[n]));
                    System.out.printf("headChildren = %s\n", headChildren.toString());
                }

                //build sets
                headChildrenSets = new TreeMap[headChildren.size()];
                for (int i = 0; i < headChildren.size(); i++) {
                    TreeMap headChildSet = new TreeMap<Integer, Object>();
                    Integer headChild = (Integer)headChildren.firstKey();
                    headChildren.remove(headChild);
                    headChildSet.put(headChild, null);
                    int nextChildInSet = N[headChild.intValue() - 1];
                    while(!headChildSet.containsKey(new Integer(nextChildInSet))){
                        headChildSet.put(new Integer(nextChildInSet), null);
                        nextChildInSet = N[nextChildInSet - 1];
                    }

                    headChildrenSets[i] = headChildSet;
                }

                //find greatest combination of mutually exclusive sets
                int maxLength = 0;
                for (int i = 0; i < headChildrenSets.length; i++) {
                    System.out.printf("headChildrenSets[%d] = %s\n", i, headChildrenSets[i].toString());
                    int length = findMaxLength(i, new TreeMap<Integer, Object>());
                    length = length > maxLength ? length : maxLength;
                }




                String result = Integer.toString(maxLength);
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

    private static int findMaxLength(int i, TreeMap<Integer, Object> usedSets){
        usedSets.put(new Integer(i), null);
        int maxLength = 0;
        int length = 0;
        for (int j = 0; j < headChildrenSets.length - 1; j++) {
            if(!usedSets.containsKey(new Integer(j))){
                length = findMaxLength(j, usedSets);
                maxLength = length > maxLength ? length : maxLength;
            }
        }

        if(maxLength == 0){ //at the last set
            maxLength = headChildrenSets[headChildrenSets.length-1].size();
        }
        return maxLength;
    }


}
