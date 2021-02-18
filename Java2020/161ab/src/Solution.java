//Google Code Jam 2016 Round 1A Problem B
//Author: Casey Barton
//Completed
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        int numCases;
        Scanner scanner = new Scanner(System.in);
        numCases = Integer.parseInt(scanner.nextLine());
        int N = 0;
        for(int i = 1; i <= numCases; i++) {
            String[][] input = new String[inputSize][inputSize];
            if(multiLineCases) {
                N = Integer.parseInt(scanner.nextLine());
                for (int j = 0; j < N * 2 - 1; j++) {
                    StringTokenizer st = new StringTokenizer(scanner.nextLine());
                    int k = 0;
                    while (st.hasMoreTokens()) {
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
            System.out.printf("Case #%d: %s\n", i, solve(input, N));
        }
    }

    /////////////INPUT CONFIGURATION/////////////
    static boolean multiLineCases = true;
    static int inputSize = 1000;
    static String inputType = "int"; //"int" or "String"

    static String solve(String[][] in, int N){
        //int input = Integer.parseInt(in[0][0]);
        //int[] input = new int[inputSize]; for (int i = 0; i < inputSize; i++) {input[i] = Integer.parseInt(in[0][i]);}
        int[][] input = new int[inputSize][inputSize]; for (int i = 0; i < inputSize; i++) {for (int j = 0; j < inputSize; j++) {input[i][j] = in[i][j] != null ? Integer.parseInt(in[i][j]) : -1;}}
        //String inputStr = in[0][0];
        //String[] input = in[0];
        //String[][] input = in;
        String ret = "";

        //////////code goes here////////////
        LinkedList<Integer> list = new LinkedList<Integer>();

        for (int i = 0; i < N * 2 - 1; i++) {
            for (int j = 0; j < N; j++) {
                //add element to list unless already there, in which case remove from list
                int index = list.indexOf(Integer.valueOf(in[i][j]));
                if(index == -1){//if element wasn't found
                    list.push(Integer.valueOf(in[i][j]));
                }else{
                    list.remove(index);
                }
            }
        }
        //we now have a list of all elements that occurred an odd number of times
        //sort it and return
        list.sort(null);
        for(Integer i : list){
            ret += i.toString() + " ";
        }
        return ret;
    }


}
