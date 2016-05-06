import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Austin on 3/23/2016.
 *
 * !!!!!!IMPORTANT!!!!!!!!!
 * This program uses the Chess Cases font, which
 * is contained with the class files. In order for
 * the program to display correctly, open and install
 * the CASEFONT.TTF file.
 *
 * I have borrowed the idea of a world as a grid and
 * entities that move around the grid. I turned this
 * idea into a chess simulation.
 *
 */

public class Prog3 extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String color = Welcome.display("Chess (Prog3)", "Pick a Color", getClass().getResource("font.css").toExternalForm());

        World world = new World(new GameBoard(8, 8, color));

        //Dimensions of board
        int[] dimensions = new int[2];
        dimensions[0] = world.getRows();
        dimensions[1] = world.getCols();

        //The players need to know the dimensions
        //of the board in order to place themselves
        //correctly.
        world.addPlayer(new King(dimensions, 0));
        world.addPlayer(new Queen(dimensions, 0));
        world.addPlayer(new Bishop(dimensions, 0));
        world.addPlayer(new Bishop(dimensions, 0));
        world.addPlayer(new Knight(dimensions, 0));
        world.addPlayer(new Knight(dimensions, 0));
        world.addPlayer(new Rook(dimensions, 0));
        world.addPlayer(new Rook(dimensions, 0));
        for(int i = 0; i < 8; i++) {
            world.addPlayer(new Pawn(dimensions, 0));
        }

        world.addPlayer(new King(dimensions, 1));
        world.addPlayer(new Queen(dimensions, 1));
        world.addPlayer(new Bishop(dimensions, 1));
        world.addPlayer(new Bishop(dimensions, 1));
        world.addPlayer(new Knight(dimensions, 1));
        world.addPlayer(new Knight(dimensions, 1));
        world.addPlayer(new Rook(dimensions, 1));
        world.addPlayer(new Rook(dimensions, 1));
        for(int i = 0; i < 8; i++) {
            world.addPlayer(new Pawn(dimensions, 1));
        }

        world.draw();


    }

}
