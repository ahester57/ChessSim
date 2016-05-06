/**
 * Created by Austin on 3/26/2016.
 *
 * !!!!!!IMPORTANT!!!!!!!!!
 * This program uses the Chess Cases font, which
 * is contained with the class files. In order for
 * the program to display correctly, open and install
 * the CASEFONT.TTF file.
 *
 *
 */



import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

class GameBoard {

    private Stage window;
    private Scene scene;
    private ArrayList<Node> labels;
    private String color;
    private int rows, cols;

    GameBoard(){
        window = new Stage();



        GridPane layout = new GridPane();

        scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();

    }

    GameBoard(int r, int c){
        window = new Stage();

        rows = r+2;
        cols = c+2;

        color = "black";
        GridPane layout = new GridPane();

        scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();
    }

    GameBoard(int r, int c, String clr){
        window = new Stage();

        rows = r+2;
        cols = c+2;

        if (clr != null) {
            color = clr;
        }else{
            color = "black";
        }

        GridPane layout = new GridPane();

        scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();
    }

    void drawGrid(char[][] field,  World w){
        rows = w.getRows();
        cols = w.getCols();

        GridPane layout = new GridPane();
        labels = new ArrayList<>();
        Label temp;



        //Font chessPieces = Font.loadFont(GameBoard.class.getResource("CASEFONT.TTF").toExternalForm(), 42);

       //good job

        for(int x = 0; x < rows; x++){

            for(int y = 0; y < cols; y++){

                temp = new Label(String.valueOf(field[x][y]));
                temp.getStyleClass().set(0, color);
                labels.add(new Node(temp, x, y));

            }

        }

        for(Node n : labels){
            layout.add(n.label, n.xLoc, n.yLoc);
        }


        //aqdd listener


        Button go = new Button("Next Action");
        go.setOnAction(e -> w.run());
        go.setOnKeyPressed(e -> w.run());


        layout.add(go, w.getCols()/2-1, w.getRows()+1, 10, 1);

        layout.setHgap(2);
        layout.setVgap(2);

        //layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        layout.getStyleClass().add("scene");

        scene = new Scene(layout, 40*cols+40, 46*rows+80);
        scene.getStylesheets().add(getClass().getResource("font.css").toExternalForm());

        window.setTitle("Chess (Prog3)");
        window.setScene(scene);

    }

    void updateGrid(char[][] field){
        for(int x = 0; x < rows; x++){

            for(int y = 0; y < cols; y++){
                labels.get((x * rows) + y).label.setText(String.valueOf(field[x][y]));
            }
        }
    }

    void kill(){
        window.close();
    }

    int getRows(){
        return rows;
    }

    int getCols(){
        return cols;
    }


    private class Node{

        Label label;
        int xLoc, yLoc;


        Node(Label l, int x, int y){
            label = l;
            xLoc = x;
            yLoc = y;
        }

    }



}
