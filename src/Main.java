import java.util.Random;
import java.util.Scanner;

/**
 * Created by Emina on 2/4/2017.
 */
public class Main implements Constants {

        Tree tree=new Tree();
        List list=new List();
        Random rand=new Random();
        Scanner input=new Scanner(System.in);
        int winnersList[] ;
        int occur;

        public static void main(String[] args) {
            Main program=new Main();
            program.run();
        }


        //Method runs the whole program,
        private  void run() {
            int nPlayers=numberOfPlayers();
            getName(nPlayers,1,"");
            playGame(N_SCORING_CATEGORIES,0,nPlayers);
            calculateWinner(nPlayers,1);
        }


        //Gets nr of players
        private int numberOfPlayers(){
            int nPlayers = -1;
            while (nPlayers < 0 || nPlayers > MAX_PLAYERS+1){
                System.out.println("Enter number of players. You can only enter up to " + MAX_PLAYERS + " players.");
                try {
                    nPlayers = Integer.valueOf(input.nextLine());}
                catch (NumberFormatException e) {
                    System.out.println("Invalid input try again");
                }
            }
            return nPlayers;
        }


        //BINARY TREE
        //Gets players name and registers in binary tree
        private String getName(int nPlayers,int i,String name){
            if( i <= nPlayers) {
                System.out.println("Enter name for player " + i);
                name = input.next();
                tree.addNode(name,i,0,0);
                getName(nPlayers,i+1,name);
            }
            return name;
        }


        //Method runs game 13 times(or any value given in the interface Constants) per player
        private void playGame(int N_SCORING_CATEGORIES,int i,int nPlayers){
            if(i<N_SCORING_CATEGORIES+1){
                playID(nPlayers,1);
                playGame(N_SCORING_CATEGORIES,i+1,nPlayers);
            }
        }


        //Yahtzee game logic, each player gets 3 rolls, selects category to register score, score registers in binary tree, dices get emptied
        private void playID(int nPlayers,int ID) {
            for(int i=0;i<nPlayers;i++) {
                System.out.println("It's " + tree.findID(ID+i).name + " turn to roll the dice.");
                initializeFirstRoll();
                secondAndThirdRoll(0);
                selectCategory(tree.findID(ID+i).name, ID+i);
                tree.inorder();
                list.removeAll();
            }
        }


        //LINKED LIST
        //Method saves the rolled dice nr in a list,and prints the whole list
        private void initializeFirstRoll(){
            for(int i = 0; i < N_DICE; i++) {
                int dieNumbers = rand.nextInt((6 - 1) + 1) + 1;
                list.addFirst(dieNumbers);
            }
            System.out.println("The dice show:");
            list.print();
        }


        //LINKED LIST & REKURSION
        //Method gives player chance to re roll chosen dices,register or skip roll
        private void secondAndThirdRoll(int rolls) {
            if (rolls<2) {
                System.out.println("Write the numbers you want to re-roll,when done or if you wish to skip roll, press nr 0 ");
                rolls(0);
                System.out.println("The dice show");
                list.print();
                //repeat this sequence until its been 2 rolls
                secondAndThirdRoll( rolls + 1);
            }
        }


        //REKURSION
        //Method manages rerolls of dices and checks valid input
        private void rolls(int dice){
          int number;
          if(dice<5){
              try {
                number = Integer.parseInt(input.nextLine());
                  if(number!=0) {

                      if (list.find(number)) {
                          list.removeAt(number);
                          int newNr = rand.nextInt((6 - 1) + 1) + 1;
                          list.addSort(newNr);
                          rolls(dice + 1);
                      } else {
                          System.out.println("Invalid input. Not in the list. Try again");
                          rolls(dice);
                      }
                  }
              }catch (NumberFormatException e) {
              System.out.println("Invalid input. Try again.");
              rolls(dice);}
          }
        }

        //BINARY TREE
        //Method gets category number and checks valid input
        private void selectCategory(String name, int ID){
            int category;
            System.out.println("Categories: ONES 1,              TWOS 2,              THREES 3 ");
            System.out.println("            FOURS 4,             FIVES 5,             SIXES 6");
            System.out.println("            THREE OF A KIND 7,   FOUR OF A KIND 8,    FULL HOUSE 9,");
            System.out.println("            SMALL STRAIGHT 10,   LARGE_STRAIGHT 11,   YAHTZEE 12,     CHANCE 13");
            System.out.println("Write the category nr you want to add the result to:");
            try {
                category = Integer.parseInt(input.nextLine());
                if(category>0 && category<14) {
                    if(checkCategory(ID, category)){
                        System.out.println("Category taken.Try again.");
                        selectCategory(name,ID);}
                    else
                        tree.addNode(name,ID,category,calculateCategoryScore(category));
                }
                else{
                    System.out.println("Number must be between 1 and "+N_SCORING_CATEGORIES+". Try again." );
                    selectCategory(name,ID);}
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Write a number between 1 and " + N_SCORING_CATEGORIES );
                selectCategory(name,ID);
            }
        }


        //BINARY TREE
        //Category taken? Returns true if yes and false if no
        private boolean checkCategory(int ID,int category){
            boolean state=false;
            try {
                if (tree.findCategory(category).ID == ID)
                state= true;}
            catch (NullPointerException e) {
                state= false;}

            return state;
        }


        //REKURSION
        //Method sums up score for upper categories ONES to SIXES
        private int onesToSixes(int category,int score,int dice){

            if(dice<N_DICE) {
                if (category == list.get(dice))
                    score += category;
                score=onesToSixes(category,score,dice+1);
            }
            return score;
        }


        //LINKED LIST
        //Method checks how many times a number occurs in the list
        private  int diceOccuranceNr(int timesOfOccurance){
            for(int i=1;i<7;i++){
                occur= list.counting(i);
                if(occur==timesOfOccurance)
                    break;
                else
                    System.out.print("");
            }
            return occur;
        }


        //LINKED LIST
        //Method sums up same numbers in the list
        private  int sumOfSameNumbers(int sum){
            for(int i=1;i<7;i++){
                occur= list.counting(i);
                if(occur>=2) {
                    sum = list.sumUp(i);
                    break;
                }else
                    System.out.print("");
            }
            return sum;
        }


        //Method returns score for 3 or 4 of a kind categories
         private int thressOrFours( int score,int occur) {
            System.out.println("here lies the problem " + diceOccuranceNr(3));
                if (diceOccuranceNr(occur) == occur) {
                    score = sumOfSameNumbers(0);
                    System.out.println(score);}
                else
                    score=0;
             return score;
         }


         //Method checks if there is 3 and 2 of a kind in the dices,returns score
        private int fullHouse(int score){
            if(diceOccuranceNr(3)==3 && diceOccuranceNr(2)==2){
              score=25;
                System.out.println(score);
                return score;}
            else
                return 0;
        }


        //LINKED LIST
        //Method checks if the dice contain four increments
        private boolean smallStraight(boolean foundAll){
            if(list.find(1)&& list.find(2) && list.find(3) && list.find(4)){
                foundAll= true;
            }else if(list.find(2) && list.find(3) && list.find(4) && list.find(5)){
                foundAll=true;
            }else if(list.find(3)&& list.find(4)&& list.find(5)&& list.find(6)){
                foundAll= true;
            }
            return foundAll;
         }


        //LINKED LIST
        //Method checks if the dice contin five increments
        private boolean largeStraight(boolean foundAll){
            if(list.find(1) && list.find(2) && list.find(3) && list.find(4) && list.find(5)){
                foundAll= true;
            }else if(list.find(2)&& list.find(3) && list.find(4) && list.find(5) && list.find(6) ){
                foundAll= true;
            }
            return foundAll;
        }


        //Method  sums up the score of a chosen category
        private int calculateCategoryScore(int category) {
            int score = 0;

            if (category >= ONES && category <= SIXES) {

                score = onesToSixes(category, score, 0);

            } else if (category == THREE_OF_A_KIND) {

                score = thressOrFours(0, 3);

            } else if (category == FOUR_OF_A_KIND) {

                score = thressOrFours(0, 4);

            } else if (category == FULL_HOUSE) {

                score = fullHouse(0);

            } else if (category == SMALL_STRAIGHT) {

                if (smallStraight(false))
                    score = 30;
                else
                    score = 0;

            } else if (category == LARGE_STRAIGHT) {

                if (largeStraight(false))
                    score = 40;
                else
                    score = 0;

            } else if (category == YAHTZEE){

                if (diceOccuranceNr(5) == 5)
                    score = 50;
                else
                    score = 0;

            }else if (category == CHANCE)

                score=list.sumRec();

         return score;
        }

        //BINARY TREE
        //Method sums up the total score of a player
        private int calculateResults(int ID){
            int total,upperSum,lowerSum;

            upperSum=tree.sum(ID,6);
            lowerSum=tree.sum(ID,7);
            if (upperSum>63)
                upperSum+=35;
            total=upperSum+lowerSum;

            return total;
        }


        //Method compares players scores and picks the winner
        private void calculateWinner(int nPlayers,int ID){
            winnersList=new int[nPlayers];
            for(int i=0;i<nPlayers;i++) {

                winnersList[i]=calculateResults(ID+i);
                System.out.println("The result of " + tree.findID(ID+i).name + " is " +calculateResults(ID+i));
                }
            Sort sortwinner=new Sort(winnersList);

            System.out.println("And so the winner has :"+sortwinner.getWinner()+"points");
        }

}
