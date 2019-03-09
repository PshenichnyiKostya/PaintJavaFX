package Shapes;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import sample.Controller;

public class Brush {
    public static boolean flag;
    public static GraphicsContext paintTool;

    public static void draw(ColorPicker colorPicker, double x, double y, double size) {
        paintTool.setFill(colorPicker.getValue());
        paintTool.fillRoundRect(x, y, size, size, size, size);
    }
}
