module com.wuxianggujun.toolbox {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wuxianggujun.toolbox to javafx.fxml;
    exports com.wuxianggujun.toolbox;
    exports com.wuxianggujun.toolbox.cache;
    opens com.wuxianggujun.toolbox.cache to javafx.fxml;
}