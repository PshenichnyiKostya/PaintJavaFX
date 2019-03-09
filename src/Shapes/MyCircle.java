package Shapes;

import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;
import sample.Controller;

public class MyCircle {
    public static boolean flag;
    public static GraphicsContext paintTool;
    public static void drawPressed(ColorPicker colorPick, ColorPicker linePick, Circle circle) {
        paintTool.setStroke(linePick.getValue());
        paintTool.setFill(colorPick.getValue());
    }
}
