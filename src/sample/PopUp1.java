package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp1 extends Stage {

    PopUp1(){
        this.initModality(Modality.APPLICATION_MODAL);

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);

        Text label = new Text("How do you want to pay?");
        Text label2 = new Text("");

        Button btnCash = new Button("Cash");
        btnCash.setOnAction(new EventHandler<>(){
            @Override
            public void handle(ActionEvent event) {
                label2.setText("You will pay by Cash");
            }
        });

        Button btnCard = new Button("Card");
        btnCard.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                label2.setText("You will pay by Card");
            }
        });

        dialogVbox.getChildren().add(label);
        dialogVbox.getChildren().add(btnCash);
        dialogVbox.getChildren().add(btnCard);
        dialogVbox.getChildren().add(label2);

        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        this.setScene(dialogScene);
    }
}
