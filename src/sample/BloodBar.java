package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BloodBar {
    double scale = 1;
    Pane layer = new Pane();
    Image maxBloodBar = new Image("file:src/sample/AssetsKit_1/shapes/241.png");
    Image bloolBar = new Image("file:src/sample/AssetsKit_1/shapes/243.png");
    ImageView imageViewMaxBloodBar = new ImageView(maxBloodBar);
    ImageView imageViewBloodBar = new ImageView(bloolBar);
    BloodBar(Pane layer) {
        imageViewMaxBloodBar.setFitWidth(36);
        imageViewMaxBloodBar.setFitHeight(8);
        imageViewBloodBar.setFitWidth(36);
        imageViewBloodBar.setFitHeight(8);
        this.layer.getChildren().addAll(imageViewMaxBloodBar,imageViewBloodBar);
        layer.getChildren().add(this.layer);
    }
    public void setBloodBarWidth() {
        imageViewBloodBar.setFitWidth(36 * scale);
        imageViewBloodBar.setFitHeight(8);
    }

    public void setScale(double blood, double maxBlood ) {
        scale = blood/ maxBlood;
    }
    public  void update(double x,double y) {
        this.imageViewMaxBloodBar.relocate(x,y);
        this.imageViewBloodBar.relocate(x,y);

    }

}
