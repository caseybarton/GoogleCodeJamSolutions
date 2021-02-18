//Google Code Jam 2017 Round 1A Problem B
//Author: Casey Barton
//Complete
import java.util.*;

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
    static int numConfigLines = 2; //lines preceding the problem lines, config values are assumed to be integers
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
        int kits = 0;
        int[] ingredients = new int[config[1].length];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = Integer.parseInt(config[1][i]);
        }
        int[] remainingPackages = new int[R]; //list of indices, all lower indices are packages that have been consumed/discarded

        //sort packages from smallest to largest
        for (int i = 0; i < in.length; i++) {
            Arrays.sort(in[i]);
        }

        //scan for packages, from smallest to largest
        //when valid kit is found, remove it's packages as well as all smaller packages as they shouldn't(?) be valid for any other kits
        LinkedList<Integer> cp = new LinkedList<Integer>();
        cp.add(0);

        int[] foundKit;
        findKits:
        while(true){
            foundKit = kitSearch(remainingPackages, in, ingredients, new int[0]);
            if(foundKit.length == 0){ //if no kits were found
                break;
            }
            kits++;
            //adjust remaining packages
            for (int i = 0; i < remainingPackages.length; i++) {
                remainingPackages[i] = foundKit[i] + 1;
                if(remainingPackages[i] >= in[i].length){
                    break findKits;
                }
            }
        }

        return "" + kits;
    }

    //todo not correctly starting with remaining packages
    //returns: empty array if no kits are found, otherwise returns indices of first kit found
    static int[] kitSearch(int[] remainingPackages, int[][] in, int[] ingredients, int[] currentPositions){
        boolean isValid = isValid(in, ingredients, currentPositions);
        if(currentPositions.length == in.length && isValid) {
            return currentPositions;
        }
        if(!isValid){
            return new int[0];
        }

        //if currentPositions.size() != in.length && isValid
        //create duplicate of current positions with one more row
        int[] localCurrentPositions = new int[currentPositions.length + 1];
        System.arraycopy(currentPositions, 0, localCurrentPositions, 0, currentPositions.length);
        localCurrentPositions[currentPositions.length] = remainingPackages[currentPositions.length];

        int[] result;
        while(true){//with this partial ingredient list, try all packages for the PARTIAL LIST'S last ingredient
            result = kitSearch(remainingPackages, in, ingredients, localCurrentPositions);
            if(result.length != 0 || localCurrentPositions[localCurrentPositions.length-1] == in[0].length - 1){
                return result;
            }
            ++localCurrentPositions[localCurrentPositions.length-1];
        }

    }

    //checks if INCOMPLETE kit defined by positions is valid SO FAR
    static boolean isValid(int[][] in, int[] ingredients, int[] positions){
        if(positions.length == 0){
            return true;
        }
        double lowerBound = 0.8999999999;
        double upperBound = 1.1000000001;

        int numKits = 0; //number of kits needed to utilize the package
        tryIncreasingKitAmounts:
        while(true){
            numKits++;
            for (int i = 0; i < positions.length; i++) {
                if(ingredients[i] * numKits * upperBound < in[i][positions[i]]){
                    //insufficient kits
                    continue tryIncreasingKitAmounts;
                }
                if(ingredients[i] * numKits * lowerBound > in[i][positions[i]]){
                    return false;
                }
            }
            //all packages were within bounds
            return true;
        }
    }


    ///////////////////////////////////
    //       Useful Functions
    ///////////////////////////////////
    public static int[] listToIntArray(List<Integer> integers){
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}

