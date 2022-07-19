package com.wuxianggujun.toolbox;

import com.wuxianggujun.toolbox.entiy.Word;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class EnglishTools extends Application {
    private Word word = new Word();
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = FXMLLoader.load(Objects.requireNonNull(EnglishTools.class.getResource("english-tools.fxml")));
        Scene scene = new Scene(fxmlLoader.load(),320,240);
        stage.setTitle("英语工具箱");
        stage.setScene(scene);
        stage.setOpacity(0.8);
        stage.show();

        TextField input = (TextField) scene.lookup("#input");
        ListView<String> listView = (ListView<String>) scene.lookup("#listView");
        input.textProperty().addListener((observable,oldValue,newValue) -> {
            String trimmed = newValue.trim();
            if (trimmed.length() > 0){
                List<String> searchResult = Word.search(trimmed,word.words,String::contains);
                listView.getItems().clear();
                listView.getItems().addAll(searchResult);
                stage.setHeight(600);
            }else {
                stage.setHeight(200);
            }
            
        });
    }

    public static void main(String[] args) {
        launch();
    }
}