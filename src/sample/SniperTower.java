package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public class SniperTower extends Tower {

    SniperTower(Pane layer,double x,double y) {
        super(layer, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_163/1.png"), x, y, 300, 40, 1, Math.PI / 2, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_138/1.png"),new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_157/1.png"),44,45,42,37,"file:src/sample/AssetsKit_1/sprites/DefineSprite_163/");
        this.spritesImageAddress =    "file:src/sample/AssetsKit_1/sprites/DefineSprite_163/";
    }

    public void addBulletAndShoot(List<Bullet> bullets, Enemy e) {
        super.addBulletAndShoot(bullets,e);
    }
}
