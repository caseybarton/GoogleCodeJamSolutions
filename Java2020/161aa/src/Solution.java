//Google Code Jam 2016 Round 1A Problem A
//Author: Casey Barton
//Status: Completed
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        int numCases;
        Scanner scanner = new Scanner(System.in);
        numCases = Integer.parseInt(scanner.nextLine());

        for(int i = 1; i <= numCases; i++) {
            String[][] input = new String[inputSize][inputSize];
            if(multiLineCases) {
                int numLines = Integer.parseInt(scanner.nextLine());
                for (int j = 0; j < numLines; j++) {
                    StringTokenizer st = new StringTokenizer(scanner.nextLine());
                    int k = 0;
                    while (st.hasMoreTokens()) {c
                        input[j][k] = st.nextToken();
                        k++;
                    }
                }
            }else{
                StringTokenizer st = new StringTokenizer(scanner.nextLine());
                int k = 0;
                while (st.hasMoreTokens()) {
                    input[0][k] = st.nextToken();
                    k++;
                }
            }
            System.out.printf("Case #%d: %s\n", i, solve(input));
        }
    }

    /////////////INPUT CONFIGURATION/////////////
    static boolean multiLineCases = false;
    static int inputSize = 1000;
    static String inputType = "int"; //"int" or "String"

    static String solve(String[][] in){
        //int input = Integer.parseInt(in[0][0]);
        //int[] input = new int[inputSize]; for (int i = 0; i < inputSize; i++) {input[i] = Integer.parseInt(in[0][i]);}
        //int[][] input = new int[inputSize][inputSize]; for (int i = 0; i < inputSize; i++) {for (int j = 0; j < 1000; j++) {input[i][j] = Integer.parseInt(int[i][j]);}}
        String inputStr = in[0][0];
        //String[] input = in[0];
        //String[][] input = in;
        String ret = "";

        //////////code goes here////////////
        char[] input = inputStr.toCharArray();
        ret = "" + input[0];
        for (int i = 1; i < input.length; i++) {
            ret = input[i] < ret.charAt(0) ? ret + input[i] : input[i] + ret;
        }

        return ret;
    }

}
