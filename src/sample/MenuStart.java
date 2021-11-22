package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuStart {
    Button button2 = new Button("START");
    Button button3 = new Button("RULE");
    Button button4 = new Button("HIGH SCORE");
    Button button5 = new Button("EXIT");
    ImageView menu = new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/53.png"));
    public  MenuStart(Group root) {
        menu.setFitWidth(880);
        menu.setFitHeight(660);
        root.getChildren().addAll(menu,button2,button3,button4,button5);

        button2.relocate(420, 200);
        button3.relocate(425, 240);
        button4.relocate(400, 280);
        button5.relocate(420, 320);

        // Create welcome
        Text welcome = new Text();
        welcome.setText("WELCOME");
        welcome.setFont(Font.font(null, FontWeight.MEDIUM, 50));
        welcome.setStroke(Color.BURLYWOOD);
        welcome.setFill(Color.RED);
        welcome.relocate(330, 100);
        root.getChildren().add(welcome);
    }
}
