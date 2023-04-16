import java.util.ArrayList;

public interface UI {
    public void load();
    public void render(ArrayList<Component> comps);
    public void close();
    public boolean shouldClose();
}
