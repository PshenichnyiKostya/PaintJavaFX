package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Paint");
        primaryStage.getIcons().add(new Image("/icons/icon.png"));

        scene = new Scene(root, 600, 600);
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Controller.canvasHeight = scene.getHeight();
                Controller.canvasWidth = scene.getWidth();
                System.out.println(Controller.canvasHeight);
                System.out.println(Controller.canvasWidth);
            }
        });
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Controller.canvasHeight = scene.getHeight();
                Controller.canvasWidth = scene.getWidth();
                System.out.println(Controller.canvasHeight);
                System.out.println(Controller.canvasWidth);
            }
        });
       /* primaryStage.resizableProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Controller.canvasHeight=scene.getHeight();
                Controller.canvasWidth =scene.getWidth();
                System.out.println(Controller.canvasHeight);
                System.out.println(Controller.canvasWidth);
            }
        });*/
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SHIFT) {
                    Controller.flagShift = true;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SHIFT) {
                    Controller.flagShift = false;
                }
            }
        });
        primaryStage.setScene(scene);
//        root.getStylesheets().add((getClass().getResource("/Style.css")).toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
