//Google Code Jam 2016 Round 1A Problem C
//Author: Casey Barton
//Completed
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        int numCases;
        Scanner scanner = new Scanner(System.in);
        numCases = Integer.parseInt(scanner.nextLine());
        int numLines;

        for(int i = 1; i <= numCases; i++) {
            String[][] input = new String[inputSize][inputSize];
            if(multiLineCases) {
                numLines = numLinesPerCase == -1 ? Integer.parseInt(scanner.nextLine()) : numLinesPerCase;
                for (int j = 0; j < numLines; j++) {
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
            System.out.printf("Case #%d: %s\n", i, solve(input));
        }
    }

    /////////////INPUT CONFIGURATION/////////////
    static boolean multiLineCases = true;
    static int numLinesPerCase = 2; //-1 if first line dictates number of lines. If so, first line will be omitted
    static int inputSize = 1000;
    static int intNullValue = -1;

    static String solve(String[][] in){
        //int input = Integer.parseInt(in[0][0]);
        //int[] input = new int[inputSize]; for (int i = 0; i < inputSize; i++) {input[i] = in[0][i] != null ? Integer.parseInt(in[0][i]) : intNullValue;}
        int[][] input = new int[inputSize][inputSize]; for (int i = 0; i < inputSize; i++) { for (int j = 0; j < inputSize; j++) { input[i][j] = in[i][j] != null ? Integer.parseInt(in[i][j]) : intNullValue;}}
        //String inputStr = in[0][0];
        //String[] input = in[0];
        //String[][] input = in;
        String ret = "";

        //////////code goes here////////////
        //kids' bffs form chains that can end either by looping back to the first link (looping) or the final link linking to the previous link (terminating)
        //these chains already exist, we just have to find them and find either the longest combination of terminating chains or one long looping chain
        //terminating chains can be identified by the largest or smallest of their final two links

        int[][] chains = new int[inputSize][3]; //[0] - second to last link, [1] - last link, [2] - chain length
        int longestLoop = 0; //no need to keep track of all loops, we'll just record the longest

        //initialize chains to -1 since 0 is a valid link number
        for (int i = 0; i < chains.length; i++) {
            chains[i][0] = -1;
            chains[i][1] = -1;
        }


        int N = input[0][0]; //number of kids
        for (int startingKid = 0; startingKid < N; startingKid++) {
            int chainLength = 1;
            int prevKid = -1;
            int currentKid = startingKid;
            int nextKid = input[1][startingKid] - 1;
            while(true){
                if(nextKid == prevKid){
                    //this is the last link of a terminating chain
                    addToChains(chains, prevKid, currentKid, chainLength);
                    break;
                }
                if(nextKid == startingKid){
                    //this is the last link of a looping chain
                    if(longestLoop < chainLength){
                        longestLoop = chainLength;
                    }
                    break;
                }
                if(chainLength == N + 1){
                    //we entered a looping chain and we're just looping through it
                    break;
                }
                chainLength++;
                prevKid = currentKid;
                currentKid = nextKid;
                nextKid = input[1][currentKid] - 1;
            }
        }

        int sumAllTerminatingChains = sumAllChainLength(chains);

        int retval;
        if(sumAllTerminatingChains > longestLoop){
            retval = sumAllTerminatingChains;
        }else{
            retval = longestLoop;
        }

        ret = "" + retval;

        return ret;
    }

    static void addToChains(int[][] chains, int secondToFinalLink, int finalLink, int chainLength){
        int i = 0;
        while(chains[i][1] != finalLink && chains[i][0] != -1){
            i++;
        }
        chains[i][0] = secondToFinalLink;
        chains[i][1] = finalLink;
        if(chains[i][2] < chainLength) {
            chains[i][2] = chainLength;
        }
    }


    static int sumAllChainLength(int[][] chains){
        int ret = 0;
        LinkedList<int[]> chainsList = new LinkedList<>(); //This should have been a linkedlist from the start but I was lazy
        LinkedList<int[]> combinedChainsList = new LinkedList<>();
        int i = 0;
        while(chains[i][0] != -1){
            chainsList.add(chains[i]);
            i++;
        }
        while(!chainsList.isEmpty()){
            int[] chain = chainsList.remove();
            //search for matching chain going opposite direction and if found, combine them
            Iterator<int[]> itr = chainsList.iterator();
            while(itr.hasNext()){
                int[] next = itr.next();
                if(next[0] == chain[1]){
                    chain[2] += next[2] - 2;
                    itr.remove();
                    combinedChainsList.add(chain);
                    break;
                }
            }
        }
        while(!combinedChainsList.isEmpty()){
            int[] chain = combinedChainsList.remove();
            ret += chain[2];
        }
        return ret;
    }


}
