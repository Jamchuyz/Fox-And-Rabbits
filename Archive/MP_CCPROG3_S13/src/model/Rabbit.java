package model;

import java.util.Random;

public class Rabbit {

    final public int nRabbitValuePlacement = 1;// value of rabbit on board
    // 1 - not moved , 2 - moved
    public int nRabbitCount = 3;// total number of rabbits
    public int nBeforeRabbitCount;// condition if the rabbit was/not eaten
    final public int nRabbitMax = 50;//Max number of rabbits
    //public int nRabbitOnBoard;// number of rabbits on the board


    //doubles the rabbits
    public void doubles() {
        nRabbitCount *= 2;
    }

    //Mating Function
    public int Mating(int nRabbitCount){

        //Check if Rabbit A is 1 tile away of Rabbit B
        /*Write Code*/

        //Spawn 1 Rabbit per pair(?) in a random tile(4 tiles away from source)
        /*Write Code*/

        //Rabbit A and Rabbit B can't move for 3-5 turns(?)
        /*Write Code*/

        //Spawned Rabbit moves
        /*Write Code*/

        return nRabbitCount;
    }

    //finding number of rabbits on the board after resetRabbitValues
    public int findNumberOfRabbits(int[][] nPlacement) {
        int nCount = 0;

        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 20; j++){
                if(nPlacement[i][j] == nRabbitValuePlacement)
                    nCount ++;
            }
        }
        return nCount;
    }

    //this gets a random number 1-4 to determine the movement of the rabbit
    public int generateAutoNumberMovementForRabbit() {
        Random rand = new Random();
        // generate random number from 1 to 4
        int randomNum = rand.nextInt((4 - 1) + 1) + 1;

        //System.out.println(randomNum);
        return randomNum;
    }

    //resets the values of rabbits that moved/2 back to 1
    //so that the automatic move function for the rabbit will not repeat on the same rabbit because the value would be 2
    public void resetRabbitValuesOnBoard(int[][] nPlacement) {
        for(int j = 0; j < 20; j++){
            for(int k = 0; k < 20; k++){
                if (nPlacement[j][k] == nRabbitValuePlacement + 1){
                    nPlacement[j][k] = nRabbitValuePlacement;
                }
            }
        }
    }
}

