package model;

public class Movement {
    //Movements of the rabbit or the fox
    //needs the 2d integer board, animal value (1 or 5), x and y coordinates
    //will not move if on the wall
    public void moveDown(int[][] nPlacement, int nAnimalValue, int x, int y) {
        //for the fox
        if(nAnimalValue == 5) {
            if (y < 19 && nPlacement[x][y + 1] == 1) {
                while(y < 19 && nPlacement[x][y + 1] == 1) {
                    nPlacement[x][y] = 0;
                    nPlacement[x][y + 1] = nAnimalValue;
                    y++;
                }
                if (y < 19){
                    nPlacement[x][y] = 0;
                    nPlacement[x][y + 1] = nAnimalValue;
                    y++;
                }
            }
            else if (y < 19) {
                nPlacement[x][y] = 0;
                nPlacement[x][y + 1] = nAnimalValue;
                y++;
            }
        }
        //for the rabbit
        if(nAnimalValue == 1){
            if (y < 19 && nPlacement[x][y + 1] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x][y + 1] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2

            }
        }
    }

    public void moveUp(int[][] nPlacement,int nAnimalValue, int x, int y) {
        //for the fox
        if(nAnimalValue == 5) {
            if (y > 0 && nPlacement[x][y - 1] == 1) {
                while(y > 0 && nPlacement[x][y - 1] == 1) {
                    nPlacement[x][y] = 0;
                    nPlacement[x][y - 1] = nAnimalValue;
                    y--;
                }
                if (y > 0) {
                    nPlacement[x][y] = 0;
                    nPlacement[x][y - 1] = nAnimalValue;
                    y--;
                }
            }
            else if (y > 0) {
                nPlacement[x][y] = 0;
                nPlacement[x][y - 1] = nAnimalValue;
                y--;
            }
        }
        //for the rabbit
        if(nAnimalValue == 1){
            if (y > 0 && nPlacement[x][y - 1] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x][y - 1] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2
            }
        }
    }

    public void moveLeft(int[][] nPlacement,int nAnimalValue, int x, int y) {
        //for the fox
        if(nAnimalValue == 5) {
            if (x > 0 && nPlacement[x - 1][y] == 1) {
                while(x > 0 && nPlacement[x - 1][y] == 1) {
                    nPlacement[x][y] = 0;
                    nPlacement[x - 1][y] = nAnimalValue;
                    x--;
                }
                if (x > 0) {
                    nPlacement[x][y] = 0;
                    nPlacement[x - 1][y] = nAnimalValue;
                    x--;
                }
            }
            else if (x > 0) {
                nPlacement[x][y] = 0;
                nPlacement[x - 1][y] = nAnimalValue;
                x--;
            }
        }
        //for the rabbit
        if(nAnimalValue == 1){
            if (x > 0 && nPlacement[x - 1][y] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x - 1][y] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2
            }
        }
    }

    public void moveRight(int[][] nPlacement,int nAnimalValue, int x, int y) {
        //for the fox
        if(nAnimalValue == 5) {
            if (x < 19 && nPlacement[x + 1][y] == 1) {
                while(x < 19 && nPlacement[x + 1][y] == 1) {
                    nPlacement[x][y] = 0;
                    nPlacement[x + 1][y] = nAnimalValue;
                    x++;
                }
                if (x < 19) {
                    nPlacement[x][y] = 0;
                    nPlacement[x + 1][y] = nAnimalValue;
                    x++;
                }
            }
            else if (x < 19) {
                nPlacement[x][y] = 0;
                nPlacement[x + 1][y] = nAnimalValue;
                x++;
            }
        }
        //for the rabbit
        if(nAnimalValue == 1) {
            if (x < 19 && nPlacement[x + 1][y] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x + 1][y] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2
            }
        }
    }
}

