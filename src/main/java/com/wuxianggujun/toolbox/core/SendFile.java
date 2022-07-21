package com.wuxianggujun.toolbox.core;

import com.wuxianggujun.toolbox.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SendFile extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("局域网文件共享");
        primaryStage.setScene(scene);
        primaryStage.show();
//        primaryStage.setTitle("发送端");
//        FileChooser fileChooser = new FileChooser();
//        Button button = new Button("选择文件");
//        button.setOnAction(e->{
//            File selectedFile = fileChooser.showOpenDialog(primaryStage);
//        });
//        VBox vBox = new VBox(button);
//        Scene scene = new Scene(vBox,960,600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        
    }
}
