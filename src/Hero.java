import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class Hero extends AnimatedThing {

    private double gravity = 20;
    private double deltaTime = 0.1;
    private double speedX;
    private double speedY;
    private boolean onGround = true;
    private double invincibility;
    private boolean finAnim=false;
    private Timeline t1 = new Timeline();
    private Timeline t2 = new Timeline();
    private Timeline t3 = new Timeline();
    private Timeline t4 = new Timeline();

    public Hero(double x, double y, String fileName, Integer attitude) {
        super(x, y, fileName, attitude);
    }

    //Get x Hero:

    public double getHeroX(){
        return x;
    }

    //Get y Hero:

    public double getHeroY(){
        return y;
    }

    //Set x Hero:

    public void setHeroX(double x){
        this.x = x;
    }

    //Set y Hero:

    public void setHeroY(double y){
        this.y = y;
    }

    //Methode pour modifier le view port
    public void setViewRectangle(double x, double y ,double w, double h) {
        this.sprite.setViewport(new Rectangle2D(x,y,w,h));
    }

    //Methode pour calculer l'acceleration:

    public void velocity (double accelerationX, double accelerationY){
        speedX += accelerationX*deltaTime;
        speedY += accelerationY*deltaTime;
    }
    
    public void positionHero(double speedX, double speedY){
        x += speedX*deltaTime;
        y += speedY*deltaTime;
    }

    public void startJump() {
        if (onGround) {
            velocity(0, -700);
            onGround=false;
        }
    }

    public void jumpUpdate(){
        velocity(0, gravity);
        positionHero(speedX, speedY);

        if(getHeroY()>250){
            setHeroY(250);
            speedY=0;
            onGround = true;
        }
    }

    //Timeline pour run:

    public void timelineRun(){

        t1.setCycleCount(Timeline.INDEFINITE);

        t1.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0.15), e -> setViewRectangle(20,0,60,100)),
                new KeyFrame(Duration.seconds(0.3), e -> setViewRectangle(95,0,70,100)),
                new KeyFrame(Duration.seconds(0.45), e -> setViewRectangle(170,10,85,90)),
                new KeyFrame(Duration.seconds(0.6), e -> setViewRectangle(270,10,65,90)),
                new KeyFrame(Duration.seconds(0.75), e -> setViewRectangle(345,0,70,100)),
                new KeyFrame(Duration.seconds(0.9), e -> setViewRectangle(425,15,80,85)));

    }

    //Timeline pour jump:

    public void timelineJump(){

        t1.stop();
        t2.setCycleCount(1);

        t2.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0.8), e -> setViewRectangle(20,160,60,105)),
                new KeyFrame(Duration.seconds(1.0), e -> setViewRectangle(95,160,70,105)));

    }

    //Timeline pour run and shoot:

    public void timelineRunShoot(){

        t3.setCycleCount(1);

        t3.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0.15), e -> setViewRectangle(10,335,70,90)),
                new KeyFrame(Duration.seconds(0.3), e -> setViewRectangle(80,325,80,100)),
                new KeyFrame(Duration.seconds(0.45), e -> setViewRectangle(160,340,80,85)),
                new KeyFrame(Duration.seconds(0.6), e -> setViewRectangle(255,335,70,90)),
                new KeyFrame(Duration.seconds(0.75), e -> setViewRectangle(340,330,75,95)),
                new KeyFrame(Duration.seconds(0.90), e -> setViewRectangle(425,340,80,90)));
                finAnim = true;

    }

    //Timeline pour jump and shoot:

    public void timelineJumpShoot(){

        t4.setCycleCount(1);

        t4.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(0.15), e -> setViewRectangle(15,490,70,105)),
                new KeyFrame(Duration.seconds(0.3), e -> setViewRectangle(95,490,75,100)));
    }

    public void update(){
        //Permet suivant l'attitude du héro, de générer la bonne séquence d'images

        if (attitude == 1) {
            timelineRun();
            t1.play();
        }

        if (attitude == 2) {
            t1.stop();
            timelineJump();
            t2.play();
            jumpUpdate();

            if (onGround) {
                t2.stop();
                attitude=1;
            }
        }

        if (attitude == 3) {
            t1.stop();
            timelineRunShoot();
            //t3.play();
            if (finAnim) {
                t3.stop();
                attitude=1;
            }
            t3.play();
        }

        if (attitude == 4) {
            t1.stop();
            timelineJumpShoot();
            t4.play();
        }

        if(getInvincibility()!=0){
            setInvincibility(getInvincibility()-10);
        }
    }

    public double getInvincibility(){
        return invincibility;
    }

    public void setInvincibility(double data){
        invincibility=data;
    }

    public boolean isInvisible(double invincibility){
        if(invincibility>0){
            return true;
        }
        return false;
    }
}