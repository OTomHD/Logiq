import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);
    static Simulation sim ;
    static String[] mainMenuItems = {"List component","Set Pin 1", "Print In Pin states", "Print Out Pin States","Add component"};

    public static void main(String[] args) {
        sim = new Simulation(0, 0);
        if (args.length > 1) {
            sim = new Simulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

        boolean isOpen = true;
        int option = 0;
        while (isOpen){
            printMenu(mainMenuItems);// Options to display to User
            option = input.nextInt();
            switch (option) {
                case 0 -> { // Close case (always zero and displayed last in options)
                    System.out.println("Closing...");
                    isOpen = false;
                }
                case 1 -> System.out.println(sim.components);
                case 2 -> sim.setPin(0, true);
                case 3 -> printPins(sim.getInPins());
                case 4 -> printPins(sim.getOutPins());
                case 5 -> addComponent();
                default -> System.out.println("Option " + option + " is not implemented or does not exist please try another...");
            }
        }
        input.close();

    }

    public static void printMenu(String[] options){
        for (int i = 0; i < options.length ; i++) {
            System.out.println((i+1)+". "+options[i]);
        }
        System.out.println("0. Close");
    }

    public static void printPins(boolean[] states){
        String output = new String();
        for (boolean b : states) {
            output = output +"["+ b + "],";
        }
        System.out.println(output);
    }

    public static void addComponent(){
        String[] componentItems = {"Set Name", "Set Input", "Set Output","Confirm Component"};
        String name = "";
        int in = 0;
        int out = 0;
        printMenu(componentItems);
        int option = 0;
        boolean confirmed = false;
        while (!confirmed) {
            option = input.nextInt();
            switch(option){
                case 0 -> { System.out.println("Canceling..."); 
                            return;}
                case 1 -> { System.out.println("Input new Component Name");
                name = input.next();}
                case 2 -> { System.out.println("Set amount of input pins");
                in = input.nextInt();}
                case 3 -> { System.out.println("Set amount of output pins");
                out = input.nextInt();}
                case 4 -> {confirmed = true;}
            } 
        }
        

        Component component = new Component(name, in, out);
        sim.addComponent(component);
    }

}