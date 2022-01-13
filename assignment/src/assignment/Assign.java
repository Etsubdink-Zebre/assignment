/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wku-cslab1
 */
public class Assign extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBConnectionC db = new DBConnectionC();
                try {
                    Connection con = db.connMethod();
                    if (con != null) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("you have successfully connected");
                        a.showAndWait();
                    } else {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("you have not successfully connected");
                        a.showAndWait();
                    }

                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(Assign.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("Hello World!");
            }

        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}
