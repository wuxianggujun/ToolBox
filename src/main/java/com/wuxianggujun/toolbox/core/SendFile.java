package com.wuxianggujun.toolbox.core;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SendFile extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FlowPane main = new FlowPane();
        main.setVgap(20);
        main.setHgap(20);
        
        JFXButton jfoenixButton = new JFXButton("JFoenix Button");
        main.getChildren().add(jfoenixButton);

        JFXButton button = new JFXButton("RAISED BUTTON");
        button.getStyleClass().add("button-raised");
        main.getChildren().add(button);

        JFXButton button1 = new JFXButton("DISABLED");
        button1.setDisable(true);
        main.getChildren().add(button1);

        StackPane pane = new StackPane();
        pane.getChildren().add(main);
        StackPane.setMargin(main, new Insets(100));
        pane.setStyle("-fx-background-color:WHITE");


        final Scene scene = new Scene(pane, 960, 600);
        scene.getStylesheets().add(SendFile.class.getResource("/css/jfoenix-components.css").toExternalForm());
        stage.setTitle("JFX Button Demo");
        stage.setScene(scene);
        stage.show();

    }
}
