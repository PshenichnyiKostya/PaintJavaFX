package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class MyEllipse {
    public static boolean flag;
    public static GraphicsContext paintTool;

    public static void drawPressed(ColorPicker colorPick, ColorPicker linePick) {
        paintTool.setStroke(linePick.getValue());
        paintTool.setFill(colorPick.getValue());
    }
}

