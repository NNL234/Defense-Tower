package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Pane layer = new Pane();
    List<Enemy> enemies = new ArrayList<>();
    List<Tower> towers = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    Menu menuTower = new Menu(layer);
    Tower newTower = new Tower();
    Enemy enemy = new Enemy();
    public static final int TIME = 50;
    int x = 100;
    int timer = 0;
    boolean isOk ;
    int countClick = 0;
    boolean isDragging ;
    boolean isPressed;
    int towerType;
    boolean isReleasing ;
    Point2D mousePos = new Point2D(0,0 );
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Defense Tower");
        primaryStage.setScene(new Scene(root, 900, 660));
        Path path = new Path(layer);
        path.setPath();
        root.getChildren().addAll(path.draw());


        root.getChildren().add(layer);



        primaryStage.show();
        AnimationTimer Timer = (new AnimationTimer() {


            @Override
            public void handle(long l) {
                if(timer % TIME == 0 && x > 0){
                    createEnemy();
                    x--;
                }
                timer = (timer + 1) % TIME;

                if(!enemies.isEmpty())
                    enemies.forEach(e->{
                        e.move();
                        e.update();
                    });
                menuTower.towerBtnArrayList.forEach(btn ->{
                    btn.btn.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            isPressed = true;
                            mousePos = new Point2D(event.getSceneX(),event.getSceneY());
                            towerType = btn.index;
                            menuTower.addNewTower(towerType,mousePos,new Pane());

                            layer.getChildren().add(menuTower.animationLayer);


                        }
                    });
                    btn.btn.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            isDragging = true;
                            mousePos = new Point2D(event.getSceneX(),event.getSceneY() );
                        }
                    });
                    btn.btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            isDragging = false;
                            isReleasing = true;
                        }
                    });

                });

                if(isPressed) {

                    menuTower.animationLayer.relocate(mousePos.getX(),mousePos.getY());
                    if(isDragging) {
                        menuTower.animationLayer.relocate(mousePos.getX(),mousePos.getY());
                    }

                    if(isReleasing) {

                        layer.getChildren().remove(menuTower.animationLayer);
                        newTower =menuTower.addNewTower(towerType,mousePos,layer);
                        isOk = true;
                        towers.forEach(t->{
                            if(newTower.hasCollision(t)){
                                isOk = false;
                                System.out.println(1);
                            }
                        });

                        path.map.forEach(c->{
                            if(newTower.pos.getX() + newTower.width >= c.pos.getX() && newTower.pos.getX() <= c.pos.getX() + c.width && newTower.pos.getY() + newTower.height >= c.pos.getY() && newTower.pos.getY() <= c.pos.getY() + c.height) {
                                isOk = false;
                                System.out.println(2);
                            }
                        });

                        if(newTower.pos.getX() <0  || newTower.pos.getX() > 660 - newTower.width ||newTower.pos.getY() < 0 || newTower.pos.getY() > 660 - newTower.height ) {
                            isOk = false;
                            System.out.println(3);
                            System.out.println(newTower.pos + " " + newTower.width + " " + newTower.height);
                        }
                        if(isOk)
                            towers.add(newTower);
                        else layer.getChildren().remove(newTower.animationLayer);
                        isReleasing = false;

                    }
                }



                enemies.forEach(e -> {
                    e.move();
                    e.update();
                });

                bullets.forEach(b->{
                    b.move();
                    b.update();
                });

                towers.forEach(t-> {
                    if(enemies.isEmpty()) return;
                    double minDistance =t.shootingRange;
                    for(Enemy e: enemies){
                        if(e.getCenter().distance(t.getCenter()) < minDistance){
                            enemy = e;
                            minDistance = e.getCenter().distance(t.getCenter());
                        }
                    };
                    try{
                        t.addBulletAndShoot(bullets,enemy);}
                    catch (Exception e) {}
                    t.update();
                });

                handleCollision();

                try {
                    remove();
                } catch (Exception e){

                }
            }

        });
        Timer.start();

        menuTower.pauseBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                countClick +=event.getClickCount() ;
                if(countClick % 2 ==1){
                    Timer.stop();
                    menuTower.pauseBtn.setText("CONTINUE");
                }
                else {
                    Timer.start();
                    menuTower.pauseBtn.setText("PAUSE");
                }
            }
        });

        createEnemy();
    }



    public void handleCollision() {
        enemies.forEach(e->{
            if(e.blood <= 0)
                e.bloodBar.layer.getChildren().removeAll(e.bloodBar.imageViewBloodBar,e.bloodBar.imageViewMaxBloodBar);
            bullets.forEach(b->{
                if(e.hasCollision(b)) {
                    b.removable = true;
                    if(e.blood > 0)
                        e.getDamagedBy(b);
                }
            });
        });
    }

    public void remove() {
        enemies.forEach(e->{
            if(e.removable) {
                enemies.remove(e);
                e.removeImageView();
                e.bloodBar.layer.getChildren().removeAll(e.bloodBar.imageViewBloodBar,e.bloodBar.imageViewMaxBloodBar);

            }
        });

        bullets.forEach(b-> {
            if(b.removable) {
                bullets.remove(b);
                b.removeImageView();
            }
        });
    }



    public void createEnemy() {
        Enemy e = new Enemy(layer,40,40,Math.PI,1,10,30);
        enemies.add(e);
    }

}
