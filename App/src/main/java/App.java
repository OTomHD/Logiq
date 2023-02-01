public class App {
    static Simulation sim ;

    public static void main(String[] args) {
        sim = new Simulation(0, 0);
        if (args.length > 1) {
            sim = new Simulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

    }
}