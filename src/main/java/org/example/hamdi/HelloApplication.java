package org.example.hamdi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        // نجيب أبعاد الشاشة
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // ناخذ 80% من حجم الشاشة (تنجم تبدّلها)
        double windowWidth = screenWidth * 1;
        double windowHeight = screenHeight * 0.95;

        Scene scene = new Scene(fxmlLoader, windowWidth, windowHeight);

        // نضيف Drag للنافذة كيما قبل
        fxmlLoader.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        fxmlLoader.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(.8);
        });
        fxmlLoader.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });

        stage.setScene(scene);
        //stage.setFullScreen(true);

        // نخليها تتمدّد (Maximized) إذا تحب
       // إذا تحب Fullscreen بدّلها بـ true

        stage.show();
        Api mailScheduler = new Api();
        mailScheduler.startAutoMailScheduler();
    }

    public static void main(String[] args) {
        launch();
    }
}