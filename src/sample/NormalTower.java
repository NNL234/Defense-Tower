package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalTower extends Tower {

    NormalTower(Pane layer,double x,double y) {
        super(layer, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_148/1.png"), x, y, 500, 50, 5, Math.PI / 2, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_138/2.png"),new Image("file:src/sample/AssetsKit_1/shapes/73.png"),35,34,54,27,"file:src/sample/AssetsKit_1/sprites/DefineSprite_163/");
        this.spritesImageAddress =   "file:src/sample/AssetsKit_1/sprites/DefineSprite_148/";
    }
}
