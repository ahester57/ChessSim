import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Created by Austin on 3/29/2016.
 *
 *  * !!!!!!IMPORTANT!!!!!!!!!
 * This program uses the Chess Cases font, which
 * is contained with the class files. In order for
 * the program to display correctly, open and install
 * the CASEFONT.TTF file.
 *
 * This is pretty much just for picking a color,
 * which I thought was a necessary feature.
 *
 */
public class Welcome {

    static String answer;
    static ComboBox<String> input;
    static Stage window;

    public static String display(String title, String message, String font) {
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);

        input = new ComboBox<>();
        input.getItems().addAll("black", "maroon", "firebrick", "salmon",
                        "darkorange", "sandybrown", "darkseagreen", "olive",
                        "cadetblue", "darkblue", "indigo");


        input.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if(item != null) {
                            setText("tMvWlRnBqK");  //Looks very random but look at CHESCASE.DOC
                            getStyleClass().set(0, item);
                        }

                    }
                };
            }
        });

        //


        Button bGo = new Button("Go");
        bGo.setOnAction(e -> tryInput());
//
//        bGo.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle (KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    tryInput();
//                }
//            }
//        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, input, bGo);
        layout.setAlignment(Pos.CENTER);




        window.setResizable(false);


        Scene scene = new Scene(layout);
        scene.getStylesheets().add(font);
        window.setScene(scene);
        window.showAndWait();


        //Added bounds checking. If the number of sets of entities is over 10,000, then
        //the program stops responding and crashes.


        return answer;

    }

    private static void tryInput(){
        try{
            if(input.getValue() != null) {
                answer = input.getValue();
                window.close();
            }else{
                answer = "black";
                window.close();
            }
        }catch (Exception ex){
            //AlertBox.display("C3PO's Fortune", "Please enter an integer value.");
        }

    }
}
