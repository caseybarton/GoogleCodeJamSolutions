//Google Code Jam 20xx Round xx Problem x
//Author: Casey Barton
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        int numCases;
        Scanner scanner = new Scanner(System.in);
        numCases = Integer.parseInt(scanner.nextLine());

        for(int i = 1; i <= numCases; i++) {
            int caseNumProblemLines;
            LinkedList<LinkedList<String>> configLines = new LinkedList<LinkedList<String>>();
            LinkedList<LinkedList<String>> problemLines = new LinkedList<LinkedList<String>>();

            //read config lines
            for (int j = 0; j < numConfigLines; j++) {
                LinkedList<String> configLine = new LinkedList<String>();
                String str = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(str);
                while(st.hasMoreElements()){
                    configLine.add(st.nextToken());
                }
                configLines.add(configLine);
            }

            //read problem lines
            caseNumProblemLines = numProblemLines;
            if(numProblemLines == -1){
                caseNumProblemLines = Integer.parseInt(configLines.peekFirst().peekFirst());
            }
            for (int j = 0; j < caseNumProblemLines; j++) {
                LinkedList<String> problemLine = new LinkedList<String>();
                String str = scanner.nextLine();
                if(!valuesAreSpaceDelimited){
                    char[] charArr = str.toCharArray();
                    for (int k = 0; k < charArr.length; k++) {
                        problemLine.add(Character.toString(charArr[k]));
                    }
                }else {
                    StringTokenizer st = new StringTokenizer(str);
                    while (st.hasMoreElements()) {
                        problemLine.add(st.nextToken());
                    }
                }
                problemLines.add(problemLine);
            }

            String[][] config = new String[configLines.size()][];
            for (int j = 0; j < config.length; j++) {
                config[j] = configLines.removeFirst().toArray(new String[0]);
            }

            String[][] problem = new String[problemLines.size()][];
            for (int j = 0; j < problem.length; j++) {
                problem[j] = problemLines.removeFirst().toArray(new String[0]);
            }

            System.out.printf("Case #%d: %s\n", i, solve(problem, config));
        }
    }

    /////////////INPUT CONFIGURATION/////////////
    static int numProblemLines = -1; //-1 if first config line dictates number of problem lines
    static int numConfigLines = 1; //lines preceding the problem lines, config values are assumed to be integers
    static boolean valuesAreSpaceDelimited = true; //false if problem values are single chars without spaces in between

    static String solve(String[][] problem, String[][] config){
        //int in = Integer.parseInt(problem[0][0]); int out = Integer.parseInt(problem[0][0]);
        //int[] in = new int[problem[0].length]; int[] out = new int[problem[0].length]; for (int i = 0; i < problem[0].length; i++) {in[i] = Integer.parseInt(problem[0][i]);}
        int[][] in = new int[problem.length][];for (int i = 0; i < problem.length; i++) { in[i] = new int[problem[i].length]; for (int j = 0; j < problem[i].length; j++) { in[i][j] = Integer.parseInt(problem[i][j]); } }int[][] out = new int[problem.length][];for (int i = 0; i < problem.length; i++) { out[i] = new int[problem[i].length]; }
        //String in = problem[0][0]; String out = problem[0][0];
        //String[] in = problem[0]; String[] out = problem[0].clone();
        //String[][] in = problem; String[][] out = new String[problem.length][]; for (int i = 0; i < problem.length; i++) { out[i] = new String[problem[i].length]; }

        int R, C;   R = 0;C = 0;if(config.length > 0){ if(config[0].length > 0){ R = Integer.parseInt(config[0][0]); }if(config[0].length > 1){ C = Integer.parseInt(config[0][1]); } }
        String ret = "";
        //////////code goes here////////////

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret += Integer.toString(in[i][j]);
            }
            ret += "\n";
        }
        return ret;
    }

}
