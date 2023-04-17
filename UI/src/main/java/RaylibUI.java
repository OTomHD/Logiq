import java.util.ArrayList;

import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.DARKGRAY;
import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Jaylib.GREEN;



public class RaylibUI implements UI {

    int size = 75;

    @Override
    public void load() {
        InitWindow(1920, 1080, "Logiq");
        SetTargetFPS(30);
        if(!IsWindowFullscreen()){
            ToggleFullscreen();
        }

    }

    @Override
    public void render(ArrayList<Component> comps){
        BeginDrawing();
        ClearBackground(DARKGRAY);
        DrawFPS(20, 20);

        drawComponents(comps);

        EndDrawing();
    }

    @Override
    public void close(){
        CloseWindow();
    }

    @Override
    public boolean shouldClose() {
        return WindowShouldClose();
    }


    private void drawComponents(ArrayList<Component> comps){
        for (Component comp : comps) {
            int outlinesize = size/10;
            DrawRectangle(comp.getX(), comp.getY(), size*3, size*comp.largestPinArray(), BLACK); // Drawing OutLine
            DrawRectangle(comp.getX()+outlinesize, comp.getY()+outlinesize, size*3-(outlinesize*2), size*comp.largestPinArray()-(outlinesize*2), GREEN); // Draw Component Base
            drawPins(comp.getInPins(), 0); // Draw Input Pins
            drawPins(comp.getOutPins(), size*3); // Draw Output Pins
        }
    }

    private void drawPins(Pin[] pins, int xdelta){
        for(Pin pin : pins){
            drawPin(pin, xdelta);
        }
    }

    private void drawPin(Pin pin, int xdelta){
        Color startStateColor = pin.getState() ? RED : WHITE;
        Vector2 startVec = getPinPos(pin, xdelta);
        DrawCircle((int)startVec.x(), (int)startVec.y(), size / 5, startStateColor);
        if (!(pin.getConnected() == null)){
            Vector2 endVec = getPinPos(pin.getConnected(), 0);
            drawConnection(startVec, endVec);
        }
    }

    private void drawConnection(Vector2 startPinPos, Vector2 endPinPos){
        DrawLineBezier(startPinPos, endPinPos, size/15, BLACK);
    }

    private Vector2 getPinPos(Pin pin, int xdelta){
        Vector2 vec = new Vector2();

        vec.x(pin.getParent().getX()+xdelta);
        vec.y(pin.getParent().getY()+((size*(pin.getIndex()+1))-size/2));
        return vec;
    }

    

}