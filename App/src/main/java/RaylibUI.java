// import List types
import java.util.ArrayList;
import java.util.HashMap;

// import raylib
import static com.raylib.Raylib.*;


// Importing color defines
import static com.raylib.Jaylib.DARKGRAY;
import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Jaylib.GREEN;
import static com.raylib.Jaylib.ORANGE;



public class RaylibUI implements UI {

    int size = 50;

    int SCREENWIDTH = 1920;
    int SCREENHEIGHT = 1080;
    HashMap<String, Texture> textureMap = new HashMap<String,Texture>();


    @Override
    public void load() {
        InitWindow(SCREENWIDTH, SCREENHEIGHT, "Logiq");
        SetTargetFPS(30);
        textureMap.put("AND", LoadTexture("src/main/resources/AND.png"));
        textureMap.put("NOT", LoadTexture("src/main/resources/NOT.png"));
        textureMap.put("OR", LoadTexture("src/main/resources/OR.png"));
        textureMap.put("Unknown", LoadTexture("src/main/resources/Unknown.png"));
        SetWindowIcon(LoadImage("src/main/resources/Icon.png"));
    }

    @Override
    public void render(ArrayList<Component> comps, Pin[] inputPins, Pin[] outputPins){
        BeginDrawing();
        ClearBackground(DARKGRAY);
        DrawFPS(20, 20);

        drawComponents(comps);

        drawSimulationPins(inputPins); // Draw Input simulation Pins
        drawSimulationPins(outputPins); // Draw Output Simulation Pins

        EndDrawing();
    }

    @Override
    public void close(){
        textureMap.get("AND").close();
        textureMap.get("OR").close();
        textureMap.get("NOT").close();
        textureMap.get("Unknown").close();
        CloseWindow();
    }

    @Override
    public boolean shouldClose() {
        return WindowShouldClose();
    }

    private void drawSimulationPins(Pin[] pins){
        int pinSize = size/2; // Value for Simulation Pin size
        drawPins(pins, pinSize);
    }

    private void drawComponents(ArrayList<Component> comps){
        int pinSize = size/5; // Value for the Component Pin size

        for (Component comp : comps) {
            int outlinesize = size/10; 
            Texture icon = pickTexture(comp.getComponentType());
            Color color = pickColor(comp.getComponentType());
            int x = comp.getPosition().getX();
            int y = comp.getPosition().getY();
            int width = size*3;
            int height =size*comp.largestPinArray();
            DrawRectangle(x, y, width, height, BLACK); // Drawing OutLine
            DrawRectangle(x+outlinesize, y+outlinesize, width-(outlinesize*2), height-(outlinesize*2), color); // Draw Component Base
            DrawText(comp.getID(), x+outlinesize+(outlinesize/2), y+outlinesize+(outlinesize/2), size/4, WHITE); // Draw Components Name
            
            DrawTexture(icon, x+(size*2), y+(size/2)-(outlinesize*2), WHITE); // Draw Component Type through Icon
        
            drawPins(comp.getInPins(), pinSize); // Draw Input Pins
            drawPins(comp.getOutPins(), pinSize); // Draw Output Pins
        }
    }

    private void drawPins(Pin[] pins, int pinSize){
        for(Pin pin : pins){
            drawPin(pin, pinSize);
        }
    }

    private void drawPin(Pin pin, int pinSize){
        Color startStateColor = pin.getState() ? RED : WHITE;

        Vector2 startVec = getPinVector2(pin);

        if (!(pin.getConnected() == null)){
            Vector2 endVec = getPinVector2(pin.getConnected());
            drawConnection(startVec, endVec);
            endVec.close();
        }

        DrawCircle((int)startVec.x(), (int)startVec.y(), pinSize, startStateColor);
        startVec.close();
    }

    private Vector2 getPinVector2(Pin pin){
        Vector2 vector = new Vector2();
        
        if (!(pin.getParent() == null)) { // Component Pins
            vector.x(pin.getParent().getPosition().getX()+((size*pin.getPosition().getX())*3));
            vector.y(pin.getParent().getPosition().getY()+((size*pin.getPosition().getY())-(size/2)));
        }else{ // Simulation Pins
            vector.x(pin.getPosition().getX()*SCREENWIDTH);
            vector.y(pin.getPosition().getY()*(SCREENHEIGHT/8)-((SCREENHEIGHT/8)/2));
        } //((pin.getPosition().getY()*size)*(SCREENHEIGHT/1000)*(float)1.6)
        return vector;
    }

    private void drawConnection(Vector2 startPinPos, Vector2 endPinPos){
        DrawLineBezier(startPinPos, endPinPos, size/15, BLACK);
    }

    private Color pickColor(ComponentType type){
        switch (type) {
            case AND:
                return GREEN;
            case OR:
                return ORANGE;
            case NOT:
                return RED;
            default:
                System.err.println("Unknown/Unimplemented Component Type ");
                return BLACK;
        }
    }

    private Texture pickTexture(ComponentType type){
        switch (type) {
            case AND:
                return textureMap.get("AND");
            case OR:
                return textureMap.get("OR");
            case NOT:
                return textureMap.get("NOT");
            default:
                System.err.println("Unknown/Unimplemented Component Type ");
                return textureMap.get("Unknown");
        }
    }

}