module com.wuxianggujun.toolbox {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wuxianggujun.toolbox to javafx.fxml;
    exports com.wuxianggujun.toolbox;
}