package sample;

import Shapes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;


public class Controller {
    @FXML
    private Button clear;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    static double canvasWidth, canvasHeight;
    static boolean flagShift = false;
    static boolean canvasChange = false;
    @FXML
    private ColorPicker colorPick;

    @FXML
    private Button ellipse;

    @FXML
    private Button rectangle;

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
    private Button line;
    @FXML
    private Slider slider;
    private static final int INIT_VALUE = 20;


    public void changeCanvas() {
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
    }

    @FXML
    void initialize() {

        brushSize.setOnKeyTyped(Event::consume);
        Brush.paintTool = canvas.getGraphicsContext2D();
        MyCircle.paintTool = canvas.getGraphicsContext2D();
        MyEllipse.paintTool = canvas.getGraphicsContext2D();
        MyRectangle.paintTool = canvas.getGraphicsContext2D();
        MyLine.paintTool = canvas.getGraphicsContext2D();
        Circle circle = new Circle();
        slider.setValue(INIT_VALUE);
        brushSize.setText(new Integer(INIT_VALUE).toString());
        brushSize.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getIntegerInstance());
        Ellipse ellipse = new Ellipse();
        Rectangle rectangle = new Rectangle();
        Line line = new Line();

        canvas.setOnMouseDragged(event -> {
            double size;
            size = Double.parseDouble(brushSize.getText());
            getCoordinates(event, size);

            canvas.setHeight(canvasHeight);
            canvas.setWidth(canvasWidth);

        });
        canvas.setOnMousePressed(event -> {

            canvas.setHeight(canvasHeight);
            canvas.setWidth(canvasWidth);

            double size = Double.parseDouble(brushSize.getText());
            getCoordinates(event, size);
            if (MyCircle.flag) {
                MyCircle.drawPressed(colorPick, linePick);
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
            }
            if (MyEllipse.flag) {
                MyEllipse.drawPressed(colorPick, linePick);
                ellipse.setCenterX(event.getX());
                ellipse.setCenterY(event.getY());
            }
            if (MyRectangle.flag) {
                MyRectangle.drawPressed(colorPick, linePick);
                rectangle.setX(event.getX());
                rectangle.setY(event.getY());
            }
            if (MyLine.flag) {
                MyLine.drawPressed(colorPick);
                line.setStartX(event.getX());
                line.setStartY(event.getY());
            }

        });
        canvas.setOnMouseReleased(event -> {

            canvas.setHeight(canvasHeight);
            canvas.setWidth(canvasWidth);

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
            if (MyEllipse.flag) {
                ellipse.setRadiusX(Math.abs(event.getX() - ellipse.getCenterX()));
                ellipse.setRadiusY(Math.abs(event.getY() - ellipse.getCenterY()));

                if (ellipse.getCenterX() > event.getX()) {
                    ellipse.setCenterX(event.getX());
                }
                if (ellipse.getCenterY() > event.getY()) {
                    ellipse.setCenterY(event.getY());
                }

                MyEllipse.paintTool.strokeOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
                MyEllipse.paintTool.fillOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
            }
            if (MyRectangle.flag && !flagShift) {
                rectangle.setWidth(Math.abs((event.getX() - rectangle.getX())));
                rectangle.setHeight(Math.abs((event.getY() - rectangle.getY())));
                rectangleDraw(rectangle, event);
            }
            if (MyRectangle.flag && flagShift) {
                rectangle.setWidth(Math.abs(event.getY() - rectangle.getY()));
                rectangle.setHeight(rectangle.getWidth());
                rectangleDraw(rectangle, event);
            }
            if (MyLine.flag) {
                line.setEndX(event.getX());
                line.setEndY(event.getY());
                MyLine.paintTool.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
            }

        });
    }

    private void rectangleDraw(Rectangle rectangle, MouseEvent event) {
        if (rectangle.getX() > event.getX()) {
            rectangle.setX(event.getX());
        }
        if (rectangle.getY() > event.getY()) {
            rectangle.setY(event.getY());
        }
        MyRectangle.paintTool.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        MyRectangle.paintTool.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    private void getCoordinates(MouseEvent event, double size) {
        double x = event.getX() - size / 2;
        double y = event.getY() - size / 2;
        if (Brush.flag && !brushSize.getText().isEmpty()) {
            Brush.draw(colorPick, x, y, size);
        }
    }

    @FXML
    void brushClickPress(MouseEvent event) {
        Brush.flag = !Brush.flag;
        MyCircle.flag = false;
        MyEllipse.flag = false;
        MyRectangle.flag = false;
        MyLine.flag = false;
        if (Brush.flag) {
            brush.setStyle("-fx-background-color: yellow;");
        }
        if (!Brush.flag) {
            brush.setStyle("-fx-background-color: white;");
        }
        circle.setStyle("-fx-background-color: white;");
        ellipse.setStyle("-fx-background-color: white;");
        rectangle.setStyle("-fx-background-color: white;");
        line.setStyle("-fx-background-color: white;");
    }

    @FXML
    void circleClickPress(MouseEvent event) {
        MyCircle.flag = !MyCircle.flag;
        Brush.flag = false;
        MyEllipse.flag = false;
        MyRectangle.flag = false;
        MyLine.flag = false;
        if (MyCircle.flag) {
            circle.setStyle("-fx-background-color: yellow;");
        }
        if (!MyCircle.flag) {
            circle.setStyle("-fx-background-color: white;");
        }
        brush.setStyle("-fx-background-color: white;");
        ellipse.setStyle("-fx-background-color: white;");
        rectangle.setStyle("-fx-background-color: white;");
        line.setStyle("-fx-background-color: white;");
    }

    @FXML
    void ellipseClickPress(MouseEvent event) {
        MyEllipse.flag = !MyEllipse.flag;
        MyCircle.flag = false;
        Brush.flag = false;
        MyRectangle.flag = false;
        MyLine.flag = false;
        brush.setStyle("-fx-background-color: white;");
        circle.setStyle("-fx-background-color: white;");
        rectangle.setStyle("-fx-background-color: white;");
        line.setStyle("-fx-background-color: white;");
        if (MyEllipse.flag) {
            ellipse.setStyle("-fx-background-color: yellow;");
        }
        if (!MyEllipse.flag) {
            ellipse.setStyle("-fx-background-color: white;");
        }

    }

    @FXML
    void rctClickPress(MouseEvent event) {
        MyEllipse.flag = false;
        MyCircle.flag = false;
        MyLine.flag = false;
        Brush.flag = false;
        MyRectangle.flag = !MyRectangle.flag;
        brush.setStyle("-fx-background-color: white;");
        circle.setStyle("-fx-background-color: white;");
        ellipse.setStyle("-fx-background-color: white;");
        line.setStyle("-fx-background-color: white;");
        if (MyRectangle.flag) {
            rectangle.setStyle("-fx-background-color: yellow;");
        }
        if (!MyRectangle.flag) {
            rectangle.setStyle("-fx-background-color: white;");
        }
    }

    @FXML
    void lineClickPress(MouseEvent event) {
        MyLine.flag = !MyLine.flag;
        Brush.flag = false;
        MyRectangle.flag = false;
        MyEllipse.flag = false;
        MyCircle.flag = false;
        brush.setStyle("-fx-background-color: white;");
        circle.setStyle("-fx-background-color: white;");
        ellipse.setStyle("-fx-background-color: white;");
        rectangle.setStyle("-fx-background-color: white;");
        if (MyLine.flag) {
            line.setStyle("-fx-background-color: yellow;");
        }
        if (!MyLine.flag) {
            line.setStyle("-fx-background-color: white;");
        }
    }

    @FXML
    void clearClickPress(MouseEvent event) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
