//Google Code Jam 2017 Round 1A Problem A
//Author: Casey Barton
//Completed
//Time Taken: 1:48
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        int numCases;
        Scanner scanner = new Scanner(System.in);
        numCases = Integer.parseInt(scanner.nextLine());
        int numLines;
        int C = 0;
        int R = 0;

        for(int i = 1; i <= numCases; i++) {
            String[][] input = new String[inputSize][inputSize];
            if(multiLineCases) {
                StringTokenizer st2 = new StringTokenizer(scanner.nextLine());
                R = Integer.parseInt(st2.nextToken());
                numLines = R;
                C = Integer.parseInt(st2.nextToken());
                for (int j = 0; j < numLines; j++) {
                    String str = scanner.nextLine();
                    char[] ca = str.toCharArray();
                    for (int k = 0; k < ca.length; k++) {
                        input[j][k] = Character.toString(ca[k]);
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
            System.out.printf("Case #%d: \n%s\n", i, solve(input, R, C));
        }
    }

    /////////////INPUT CONFIGURATION/////////////
    static boolean multiLineCases = true;
    static int numLinesPerCase = -1; //-1 if first line dictates number of lines. If so, first line will be omitted
    static int inputSize = 25;
    static int intNullValue = -1;

    static String solve(String[][] in, int R, int C){
        //int input = Integer.parseInt(in[0][0]);
        //int[] input = new int[inputSize]; for (int i = 0; i < inputSize; i++) {input[i] = in[0][i] != null ? Integer.parseInt(in[0][i]) : intNullValue;}
        //int[][] input = new int[inputSize][inputSize]; for (int i = 0; i < inputSize; i++) { for (int j = 0; j < inputSize; j++) { input[i][j] = in[i][j] != null ? Integer.parseInt(in[i][j]) : intNullValue;}}
        //String inputStr = in[0][0];
        //String[] input = in[0];
        String[][] input = in;
        String ret = "";
        //////////code goes here////////////

        //fill all non-empty rows
        for (int i = 0; i < R; i++) {
            //fill all letters to the right
            String currentLetter = "null";
            for (int j = 0; j < C; j++) {
                if(input[i][j].equals("?")){
                    if(currentLetter.equals("null")){
                        //do nothing
                    }else{
                        input[i][j] = currentLetter;
                    }
                }else{
                    if(currentLetter.equals("null")){
                        //fill first letter to the left
                        currentLetter = input[i][j];
                        for (int k = 0; k < j; k++) {
                            input[i][k] = currentLetter;
                        }
                    }else{
                        currentLetter = input[i][j];
                    }
                }
            }
        }

        //fill all columns
        for (int j = 0; j < C; j++) {
            //fill all letters to the right
            String currentLetter = "null";
            for (int i = 0; i < R; i++) {
                if(input[i][j].equals("?")){
                    if(currentLetter.equals("null")){
                        //do nothing
                    }else{
                        input[i][j] = currentLetter;
                    }
                }else{
                    if(currentLetter.equals("null")){
                        //fill first letter to the left
                        currentLetter = input[i][j];
                        for (int k = 0; k < i; k++) {
                            input[k][j] = currentLetter;
                        }
                    }else{
                        currentLetter = input[i][j];
                    }
                }
            }
        }

        //print to string
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret += input[i][j];
            }
            ret += "\n";
        }

        return ret;
    }

}
