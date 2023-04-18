package model;

import java.util.Random;
public class Board {

    public int[][] nBoardPlacement = new int[20][20];//2d integer array for the board
    public int x;// x coordinate
    public int y;// y coordinate


    //this function makes all values for the board to be 0
    public void InitializenBoardPlacement() {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
                nBoardPlacement[i][j] = 0;
        }
    }

    //this function shows the values in the board
     /*
    public void ShownBoardPlacement() {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                System.out.print(nBoardPlacement[i][j]+" ");
            }
            System.out.println();
        }
    }*/

    //places the animal value on the board in an empty space 0
    public void generateAnimalOnBoard(int nAnimalValue) {
        Random rand = new Random();
        int i = 0;
        int randomNumX;
        int randomNumY;
        // generate random number from 0 to 19
        while(i != 1){
            randomNumX = rand.nextInt((19 - 0) + 1) + 0;// x value
            randomNumY = rand.nextInt((19 - 0) + 1) + 0;// y value
            if (nBoardPlacement[randomNumX][randomNumY] == 0){
                nBoardPlacement[randomNumX][randomNumY] = nAnimalValue;
                i = 1;
            }
        }
    }


    // this searches and gets the x and y value of the fox, rabbit, or for eat function
    // animal value can only be 1 - rabbit / 100 - fox
    public void findAnimalPlace(int nAnimalValue) {
        find:
        for( int j = 0; j < 20; j++){
            for( int k = 0; k < 20; k++){
                if(nBoardPlacement[j][k] == nAnimalValue){
                    x = j;
                    y = k;
                    break find;// to break both loops
                }
            }
        }
    }

}
