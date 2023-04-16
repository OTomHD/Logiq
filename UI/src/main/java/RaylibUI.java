import java.util.ArrayList;

import com.raylib.Jaylib;


public class RaylibUI extends Jaylib implements UI {

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
            //System.out.println("New Comp");
            DrawRectangle(comp.getX(), comp.getY(), size*3, size*comp.largestPinArray(), BLACK);
        }
    }

    

}