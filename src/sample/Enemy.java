package sample;

import javafx.geometry.Point2D;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class Enemy extends GameEntity{
    Path path = new Path();
    CellRoad nextCell = new CellRoad(0,0);
    int nthCell = 0; // cell hien tai trong path enemy di chuyen den
    double speed;
    double armor;
    double blood;
    double maxBlood;
    BloodBar bloodBar ;
    Enemy(Pane layer,double width, double height, double rad0,double speed,double armor, double maxBlood){
        super(layer,new Image("file:src/sample/AssetsKit_1/shapes/234.png"),5 * 110 + 55 - 20, 55-20,-Math.PI/2,width,height);
        this.movable = true;
        path = new Path(layer);
        this.speed = speed;
        this.armor = armor;
        this.maxBlood = maxBlood;
        this.blood  = this.maxBlood;
        path.setPath();
        this.rad0 = rad0;
        this.rad = rad0;
        nextCell = path.map.get(1);
        bloodBar= new BloodBar(layer);
        bloodBar.update(this.pos.getX(),this.pos.getY());

}

public Enemy(){}

    public void getDamagedBy(Bullet bullet) {

        if( this.armor >= bullet.damage){
            this.armor -= bullet.damage;
        } else if(this.armor > 0 && this.armor < bullet.damage){
            this.armor -= bullet.damage;
            this.blood += this.armor;
            this.armor = 0;
        } else {
            this.blood -= bullet.damage;
        }
    }

    public void move() {
        if (nthCell >= path.map.size() - 1) {
            removable = true;
            return;
        }
        double angle = path.map.get(nthCell).radDirection;
        this.rad = angle + this.rad0;
        if (this.getCenter().distance(nextCell.getCenter()) >= this.speed) {
            this.pos = new Point2D(this.pos.getX() + this.speed * Math.cos(angle), this.pos.getY() + this.speed * Math.sin(angle));

        } else if (this.getCenter().distance(nextCell.getCenter()) < speed) {
            double d = speed - this.getCenter().distance(nextCell.getCenter());
            this.pos = nextCell.getCenter().subtract(20, 20);
            nthCell++;
            if(nthCell + 1 < path.map.size() )
            nextCell = path.map.get(nthCell + 1);
            angle = path.map.get(nthCell).radDirection;
            this.pos = new Point2D(this.pos.getX() + d * Math.cos(angle), this.pos.getY() + d * Math.sin(angle));
        }

        if (blood > 0) {
            bloodBar.setScale(blood, maxBlood);
//            System.out.println(blood);
            bloodBar.setBloodBarWidth();
            bloodBar.update(this.pos.getX(), this.pos.getY());
        } else {
            blood = 0;
            this.removable = true;
        }
    }
}
