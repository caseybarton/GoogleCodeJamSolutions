//Google Code Jam 2017 Round 1A Problem C
//Author: Casey Barton
//Incomplete
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
    static int numProblemLines = 1; //-1 if first config line dictates number of problem lines
    static int numConfigLines = 0; //lines preceding the problem lines, config values are assumed to be integers
    static boolean valuesAreSpaceDelimited = true; //false if problem values are single chars without spaces in between

    static String solve(String[][] problem, String[][] config){
        int[] in = new int[problem[0].length]; int[] out = new int[problem[0].length]; for (int i = 0; i < problem[0].length; i++) {in[i] = Integer.parseInt(problem[0][i]);}
        //int[][] in = new int[problem.length][];for (int i = 0; i < problem.length; i++) { in[i] = new int[problem[i].length]; for (int j = 0; j < problem[i].length; j++) { in[i][j] = Integer.parseInt(problem[i][j]); } }int[][] out = new int[problem.length][];for (int i = 0; i < problem.length; i++) { out[i] = new int[problem[i].length]; }
        //String in = problem[0][0]; String out = problem[0][0];
        //String[] in = problem[0]; String[] out = problem[0].clone();
        //String[][] in = problem; String[][] out = new String[problem.length][]; for (int i = 0; i < problem.length; i++) { out[i] = new String[problem[i].length]; }

        int R, C;   R = 0;C = 0;if(config.length > 0){ if(config[0].length > 0){ R = Integer.parseInt(config[0][0]); }if(config[0].length > 1){ C = Integer.parseInt(config[0][1]); } }
        String ret = "";
        //////////code goes here////////////
        Battle battle = new Battle(in[0],in[1],in[2],in[3],in[4],in[5]);

        //buff until not worth buffing any more
        Battle hypotheticalBattle = battle.clone();
        hypotheticalBattle.debuff();
        while(battle.getRemainingTurnsUntilVictory() > hypotheticalBattle.getRemainingTurnsUntilVictory()
                || (battle.getRemainingTurnsUntilVictory() == Integer.MAX_VALUE && battle.getTurnsTaken() < 3)){ //if victory is not possible after 3 turns, it never will be

            //heal if necessary
            if(hypotheticalBattle.getDragonHealth() <= 0){
                battle.heal();
            }else{
                battle.debuff();
            }
            hypotheticalBattle = battle.clone();
            hypotheticalBattle.debuff();
        }
        if(battle.getRemainingTurnsUntilVictory() == Integer.MAX_VALUE){
            return "IMPOSSIBLE";
        }

        int retval = battle.getTurnsTaken() + battle.getRemainingTurnsUntilVictory();

        return "" + retval;
    }

}

//contains the state of a battle between a dragon and knight
class Battle implements Cloneable{
    private int dragonMaxHealth;
    private int dragonAttack;
    private int knightHealth;
    private int knightMaxAttack;
    private int buffAmount;
    private int debuffAmount;
    private int dragonCurrentHealth;
    private int knightCurrentAttack;
    private int turnsTaken = 0;
    private int remainingTurnsUntilVictory = -1; //to optimize repeated method calls, -1 if unknown

    public Battle(int dragonMaxHealth, int dragonAttack, int knightHealth, int knightMaxAttack, int buffAmount, int debuffAmount){
        this.dragonMaxHealth = dragonMaxHealth;
        this.dragonAttack = dragonAttack;
        this.knightHealth = knightHealth;
        this.knightMaxAttack = knightMaxAttack;
        this.buffAmount = buffAmount;
        this.debuffAmount = debuffAmount;

        this.dragonCurrentHealth = dragonMaxHealth;
        this.knightCurrentAttack = knightMaxAttack;
    }

    public int getTurnsTaken(){
        return turnsTaken;
    }

    public int getDragonHealth(){
        return dragonCurrentHealth;
    }

    public int getKnightAttack(){
        return knightCurrentAttack;
    }

    //returns the optimal number of buffs and attacks to kill the knight
    public int getNumberBuffsAndAttacks(){
        int optimalBuffs = 0;
        int buffedAttack = dragonAttack;
        int attacksToKillKnight;
        while(true){
            attacksToKillKnight = (int)Math.ceil(knightHealth / (1.0 * buffedAttack));
            if(attacksToKillKnight * buffAmount > buffedAttack){
                optimalBuffs++;
                buffedAttack += buffAmount;
            }else{
                break;
            }
        }
        return optimalBuffs + attacksToKillKnight;
    }

    //returns number of moves left of only buffing, attacking, and healing; Integer.MAX_VALUE if impossible
    public int getRemainingTurnsUntilVictory(){
        if(remainingTurnsUntilVictory != -1){
            return remainingTurnsUntilVictory;
        }
        if(dragonCurrentHealth <= 0)
            return Integer.MAX_VALUE;
        int turns = 0;
        int remainingAttacks = this.getNumberBuffsAndAttacks();


        //first simulate turns until next heal
        int sDragonCurrentHealth = dragonCurrentHealth;
        int sKnightCurrentAttack = knightCurrentAttack;
        while(sDragonCurrentHealth - sKnightCurrentAttack >= 0){
            if(remainingAttacks <= 2) { //if knight can be beaten this turn or next turn
                this.remainingTurnsUntilVictory = turns + remainingAttacks;
                return remainingTurnsUntilVictory;
            }
            turns++;
            remainingAttacks--;
            sDragonCurrentHealth -= sKnightCurrentAttack;
        }


        //simulate heal
        sDragonCurrentHealth = dragonMaxHealth;
        sDragonCurrentHealth -= knightCurrentAttack;
        turns++;

        //determine remaining turns
        //the remaining turns will be repeating patters of attacks followed by a heal
        int attacksBeforeHeal = (dragonMaxHealth - knightCurrentAttack - 1) / knightCurrentAttack; //the -1 to ensure at least 1 hp remains
        if(attacksBeforeHeal <= 0)
            return Integer.MAX_VALUE;
        int heals = remainingAttacks / attacksBeforeHeal;
        if(remainingAttacks % attacksBeforeHeal == 0) {//no need to heal if knight was defeated previous turn aahaahaah -> aahaahaa
            heals--;
            if(attacksBeforeHeal == 1)//corner case: ahahah -> ahaha -> ahaa
                heals--;
        }
        if(remainingAttacks % attacksBeforeHeal == 1)//no need to heal if final attack can be performed instead aahaaha -> aahaaa
            heals--;

        this.remainingTurnsUntilVictory = turns + remainingAttacks + heals;
        return remainingTurnsUntilVictory;
    }

    public void heal(){
        dragonCurrentHealth = dragonMaxHealth - knightCurrentAttack;
        turnsTaken++;
        this.remainingTurnsUntilVictory = -1;
    }

    public void debuff(){
        knightCurrentAttack -= debuffAmount;
        if(knightCurrentAttack < 0)
            knightCurrentAttack = 0;
        dragonCurrentHealth -= knightCurrentAttack;
        turnsTaken++;
        this.remainingTurnsUntilVictory = -1;
    }

    public Battle clone(){
        try {
            return (Battle) super.clone();
        }catch(Exception e){
            return null;
        }
    }

}

