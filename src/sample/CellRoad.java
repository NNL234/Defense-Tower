package sample;

import javafx.geometry.Point2D;

public class CellRoad {
    Point2D pos;//topLeft
    double radDirection;

    double width = Setting.CELLROAD_HALFWIDTH * 2;
    double height = Setting.CELLROAD_HALFHEIGHT * 2;

    boolean isChecked =false;

    CellRoad(double x, double y) {
        this.pos = new Point2D(x,y);
    }

    public Point2D getPos() {
        return pos;
    }

    public Point2D getCenter() {
        return new Point2D(pos.getX()+ this.width/2, pos.getY() + this.height/2);
    }
}
