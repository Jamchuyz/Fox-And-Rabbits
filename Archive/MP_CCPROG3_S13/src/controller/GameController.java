package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.IOException;


public class GameController {

    Rabbit ObjRabbit = new Rabbit();
    Fox ObjFox = new Fox();
    Board ObjBoard = new Board();
    Movement ObjMovement = new Movement();
    private static Rectangle[][] rec;
    int i,j;
    boolean lose = false;
    boolean win = false;
    int nUserInput;
    int nMoves = 0;
    int nMoveCounter = 0;

    private static Image Grass = new Image("/images/grass.png");
    private static ImagePattern tGrass = new ImagePattern(Grass);
    private static Image Fox = new Image("/images/fox.png");
    private static ImagePattern tFox = new ImagePattern(Fox);
    private static Image Bunny = new Image("/images/bunny.png");
    private static ImagePattern tBunny = new ImagePattern(Bunny);

    Timeline animate;


    @FXML
    private Pane gridPane;
    @FXML
    private Label labelRabbits;
    @FXML
    private Label labelEaten;
    @FXML
    private Label labelMoves;
    @FXML
    private Label labelPositionFox;

    public void initialize(){
        ObjBoard.InitializenBoardPlacement();
        //generate fox
        ObjBoard.generateAnimalOnBoard(ObjFox.nFoxValuePlacement);
        //generate 3 rabbits
        for(int j = 0; j < ObjRabbit.nRabbitCount; j++) {
            ObjBoard.generateAnimalOnBoard(ObjRabbit.nRabbitValuePlacement);
        }
        rec = new Rectangle[20][20];

        for ( i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                rec[i][j] = new Rectangle();
                rec[i][j].setX(i * 25);

                rec[i][j].setY(j * 25);
                rec[i][j].setWidth(25);
                rec[i][j].setHeight(25);
                if (ObjBoard.nBoardPlacement[i][j] == 0)
                    rec[i][j].setFill(tGrass);
                if (ObjBoard.nBoardPlacement[i][j] == 1)
                    rec[i][j].setFill(tBunny);
                if (ObjBoard.nBoardPlacement[i][j] == 5)
                    rec[i][j].setFill(tFox);
                gridPane.getChildren().add(rec[i][j]);
            }
        }
        startMoving();
    }


    public void openWin(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/winView.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("CONGRATS");
            stage.setScene(new Scene(root, 500, 500));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLose(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/loseView.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("YOU LOSE");
            stage.setScene(new Scene(root, 500, 500));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void exitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void openMainMenu(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainView.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("MAIN MENU");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startGame(){
        ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);
    }


    public void startMoving(){
        animate = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            try {
                if(!lose&&!win){
                    startGame();
                    updateGUI();
                }
                else if(lose){
                    updateGUI();
                    animate.stop();
                    openLose(event);
                }
                else if (win){
                    updateGUI();
                    animate.stop();
                    openWin(event);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();
    }

    public void updateGUI(){
        for ( i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                if (ObjBoard.nBoardPlacement[i][j] == 0)
                    rec[i][j].setFill(tGrass);
                if (ObjBoard.nBoardPlacement[i][j] == 1)
                    rec[i][j].setFill(tBunny);
                if (ObjBoard.nBoardPlacement[i][j] == 5)
                    rec[i][j].setFill(tFox);
            }
        }
    }

    public void moveRabbit(){
        for (int j = 0; j < ObjRabbit.nRabbitCount; j++) {
            ObjBoard.findAnimalPlace(ObjRabbit.nRabbitValuePlacement);

            nUserInput = ObjRabbit.generateAutoNumberMovementForRabbit();
            switch (nUserInput) {
                case 1:
                    ObjMovement.moveUp(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y);
                    break;
                case 2:
                    ObjMovement.moveDown(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y);
                    break;
                case 3:
                    ObjMovement.moveLeft(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y);
                    break;
                case 4:
                    ObjMovement.moveRight(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y);
                    break;
            }
        }
        ObjRabbit.resetRabbitValuesOnBoard(ObjBoard.nBoardPlacement);
    }

    public void moveFox(KeyEvent keyEvent) {
        if (!lose && !win) {
            ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);
            labelEaten.setText("" + ObjFox.nRabbitsEaten);
            labelRabbits.setText("" + ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement));
            labelMoves.setText("" + nMoves);
            ObjRabbit.nBeforeRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);

            if (keyEvent.getCode() == KeyCode.UP) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveUp(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y);
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveDown(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y);
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveLeft(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y);
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveRight(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y);
            }
            labelPositionFox.setText("" + ObjBoard.x + "-" + ObjBoard.y);

            nMoveCounter++;
            nMoves++;

            ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);

            if (ObjRabbit.nBeforeRabbitCount > ObjRabbit.nRabbitCount) {
                ObjFox.nRabbitsEaten += ObjRabbit.nBeforeRabbitCount - ObjRabbit.nRabbitCount;
            }
            if (ObjRabbit.nRabbitCount == 0)
            {
                win = true;
            }

            moveRabbit();
            ObjRabbit.resetRabbitValuesOnBoard(ObjBoard.nBoardPlacement);


            //rabbits multiply
            if (nMoveCounter == 3 && ObjRabbit.nRabbitCount * 2 < ObjRabbit.nRabbitMax && ObjRabbit.nRabbitCount != 0) {
                for (int j = 0; j < ObjRabbit.nRabbitCount; j++) {
                    ObjBoard.generateAnimalOnBoard(ObjRabbit.nRabbitValuePlacement);
                }
                ObjRabbit.doubles();
                nMoveCounter = 0;
            }

            if (nMoveCounter == 3 && ObjRabbit.nRabbitCount * 2 >= ObjRabbit.nRabbitMax && ObjRabbit.nRabbitCount != 0) {
                for (int k = 0; k < ObjRabbit.nRabbitMax - ObjRabbit.nRabbitCount; k++) {
                    ObjBoard.generateAnimalOnBoard(ObjRabbit.nRabbitValuePlacement);
                }
                ObjRabbit.nRabbitCount = ObjRabbit.nRabbitMax;
                //lose = true;
            }



            if(ObjRabbit.nRabbitCount == ObjRabbit.nRabbitMax){
                lose = true;
            }

            labelEaten.setText("" + ObjFox.nRabbitsEaten);
            labelRabbits.setText("" + ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement));
            labelMoves.setText("" + nMoves);
        }

    }

}

