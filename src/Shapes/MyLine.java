package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class MyLine {
    public static boolean flag;
    public static GraphicsContext paintTool;

    public static void drawPressed(ColorPicker colorPick) {
        /*paintTool.setStroke(linePick.getValue());
        paintTool.setFill(colorPick.getValue());*/
        paintTool.setStroke(colorPick.getValue());
    }
}
