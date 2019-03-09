package sample;

import Shapes.Brush;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

import javax.print.DocFlavor;
import java.awt.*;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ColorPicker colorPick;

    @FXML
    private TextField brushSize;

    @FXML
    private Button brush;

    @FXML
    public Canvas canvas;

    @FXML
    void initialize() {
        Brush.brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(event -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;
            if (Brush.flag && !brushSize.getText().isEmpty()) {
                Brush.draw(colorPick, x, y, size);
            }
        });
    }
    @FXML
    void brushClickPress(MouseEvent event) {
        Brush.flag = !Brush.flag;
        if (Brush.flag) {
            brush.setStyle("-fx-background-color: yellow;");
        }
        if (!Brush.flag) {
            brush.setStyle("-fx-background-color: white;");
        }
    }

}
