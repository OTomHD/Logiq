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
            DrawRectangle(comp.getX(), comp.getY(), size*3, size*comp.largestPinArray(), BLACK); // Drawing Component
            DrawRectangle(comp.getX()+outlinesize, comp.getY()+outlinesize, size*3-(outlinesize*2), size*comp.largestPinArray()-(outlinesize*2), GREEN);
            int index = 1;
            for (Pin pin : comp.getInPins()) {
                drawPins(comp, pin, index,0);
                index++;
            }
            index = 1;
            for (Pin pin : comp.getOutPins()) {
                drawPins(comp,pin,index,size*3);
                drawConnection(pin);
                index++;
            }
        }
    }

    private void drawPins(Component comp ,Pin pin, int index, int xdelta){
        Color state = WHITE;
        if(pin.getState()){
            state = RED;
        }
        DrawCircle(comp.getX()+xdelta, comp.getY()+((size*index)-size/2), size / 5, state);    // Drawing Input Pins
        
    }

    private void drawConnection(Pin pin){
        DrawLineBezier(null, null, size/15, BLACK);
    }

    

}