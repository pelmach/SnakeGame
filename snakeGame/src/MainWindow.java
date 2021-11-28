import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
            setTitle("Snake");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(320,350);
            setLocation(400,400);
            add(new gameField());
            setVisible(true);
    }
    public static void main(String[] args){
        MainWindow window = new MainWindow();
    }

}
