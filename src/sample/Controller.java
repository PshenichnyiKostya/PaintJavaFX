package sample;

import Shapes.Brush;
import Shapes.MyCircle;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

import java.awt.*;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import static java.awt.Color.*;
import static java.awt.Color.BLACK;
import static java.awt.Color.black;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ColorPicker colorPick;

    @FXML
    private ColorPicker linePick;
    @FXML
    private TextField brushSize;

    @FXML
    private Button brush;
    @FXML
    private Button circle;

    @FXML
    public Canvas canvas;

    @FXML
    void initialize() {
        Brush.paintTool = canvas.getGraphicsContext2D();
        MyCircle.paintTool = canvas.getGraphicsContext2D();
        Circle circle = new Circle();
        canvas.setOnMouseDragged(event -> {
            double size;
            size = Double.parseDouble(brushSize.getText());
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;
            if (Brush.flag && !brushSize.getText().isEmpty()) {
                Brush.draw(colorPick, x, y, size);
            }
            if (MyCircle.flag) {

            }

        });
        canvas.setOnMousePressed(event -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;
            if (Brush.flag && !brushSize.getText().isEmpty()) {
                Brush.draw(colorPick, x, y, size);
            }
            if (MyCircle.flag) {
                MyCircle.paintTool.setStroke(linePick.getValue());
                MyCircle.paintTool.setFill(colorPick.getValue());
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
            }

        });
        canvas.setOnMouseReleased(event -> {
            if (MyCircle.flag) {
                circle.setRadius((Math.abs(event.getX() - circle.getCenterX()) + Math.abs(event.getY() - circle.getCenterY())) / 2);
                if (circle.getCenterX() > event.getX()) {
                    circle.setCenterX(event.getX());
                }
                if (circle.getCenterY() > event.getY()) {
                    circle.setCenterY(event.getY());
                }

                MyCircle.paintTool.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
                MyCircle.paintTool.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
            }
        });
    }

    @FXML
    void brushClickPress(MouseEvent event) {
        Brush.flag = !Brush.flag;
        MyCircle.flag = false;
        if (Brush.flag) {
            brush.setStyle("-fx-background-color: yellow;");
        }
        if (!Brush.flag) {
            brush.setStyle("-fx-background-color: white;");
        }
        if (MyCircle.flag) {
            circle.setStyle("-fx-background-color: yellow;");
        }
        if (!MyCircle.flag) {
            circle.setStyle("-fx-background-color: white;");

        }
    }

    @FXML
    void circleClickPress(MouseEvent event) {
        MyCircle.flag = !MyCircle.flag;
        Brush.flag = false;
        if (MyCircle.flag) {
            circle.setStyle("-fx-background-color: yellow;");
        }
        if (!MyCircle.flag) {
            circle.setStyle("-fx-background-color: white;");
        }
        if (Brush.flag) {
            brush.setStyle("-fx-background-color: yellow;");
        }
        if (!Brush.flag) {
            brush.setStyle("-fx-background-color: white;");
        }
    }

}
