import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class GameScene extends Scene {

    private Hero hero = new Hero(100,250,"heros.png",1);
    private Camera myCamera = new Camera(0,0);
    private StaticThing backGroundLeft = new StaticThing("desert.png");
    private StaticThing backGroundRight = new StaticThing("desert.png");
    private Bullet bullet = new Bullet(0,0,"bullet.png",0);
    private ArrayList<Foe> foelist = new ArrayList<Foe>();
    private ArrayList<StaticThing> hearts  = new ArrayList<StaticThing>();
    private ImageView heroSprite;
    private boolean hit;
    private int numberOfLives=3;
    private int reload=0;

    public GameScene(Group root, double v, double v1) {
        super(root, v, v1);

        //Background GameScene:

        ImageView backGroundLeftImage = backGroundLeft.getStaticImage();
        ImageView backGroundRightImage = backGroundRight.getStaticImage();

        //Hero display:

        heroSprite = hero.getAnimatedImage();
        heroSprite.setX(hero.getHeroX());

        //Foe display:

        Foe foe1 = new Foe(0,0,"foe.png",0);
        Foe foe2 = new Foe(0,0,"foe.png",0);
        Foe foe3 = new Foe(0,0,"foe.png",0);
        foelist.add(foe1);
        foelist.add(foe2);
        foelist.add(foe3);

        foe1.getAnimatedImage().setY(250);
        foe2.getAnimatedImage().setY(250);
        foe3.getAnimatedImage().setY(250);

        //Hearts display:

        StaticThing heart1 = new StaticThing("heart.png");
        StaticThing heart2 = new StaticThing("heart.png");
        StaticThing heart3 = new StaticThing("heart.png");
        hearts.add(heart1);
        hearts.add(heart2);
        hearts.add(heart3);

        heart1.getStaticImage().setX(700);
        heart1.getStaticImage().setY(10);
        heart2.getStaticImage().setX(730);
        heart2.getStaticImage().setY(10);
        heart3.getStaticImage().setX(760);
        heart3.getStaticImage().setY(10);

        heart1.getStaticImage().setFitHeight(30);
        heart2.getStaticImage().setFitHeight(30);
        heart3.getStaticImage().setFitHeight(30);

        heart1.getStaticImage().setFitWidth(30);
        heart2.getStaticImage().setFitWidth(30);
        heart3.getStaticImage().setFitWidth(30);

        //Bullet display:

        bullet.getAnimatedImage().setY(250);
        bullet.getAnimatedImage().setX(850);

        timer.start();

        root.getChildren().add(backGroundLeftImage);
        root.getChildren().add(backGroundRightImage);
        root.getChildren().add(heroSprite);
        root.getChildren().add(foe1.getAnimatedImage());
        root.getChildren().add(foe2.getAnimatedImage());
        root.getChildren().add(foe3.getAnimatedImage());
        root.getChildren().add(heart1.getStaticImage());
        root.getChildren().add(heart2.getStaticImage());
        root.getChildren().add(heart3.getStaticImage());
        root.getChildren().add(bullet.getAnimatedImage());
    }

    //Game Loop:

    AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            hero.update();

            myCamera.update(backGroundLeft, backGroundRight, myCamera.getX());

            hero.setHeroX(myCamera.getX());

            myCamera.setCameraX(myCamera.getX()+5);

            heroSprite.setY(hero.getHeroY());

            if (hero.isInvisible(hero.getInvincibility())==false) {
                hit = ifCollision();
                if (hit==true){
                    hearts.get(3-numberOfLives).getStaticImage().setImage(null);
                    System.out.println("TOUCHED");
                    numberOfLives-=1;
                }
            }

            moveBullet();

            ifLose(numberOfLives);

            moveFoes();

            bulletCollision();

            updateGameScene();
        }
    };

    public void updateGameScene(){
        //Permet les changements d'attitude du héro
        this.setOnKeyPressed( (event)->{
            if (event.getCode() == KeyCode.SPACE) {
                hero.startJump();
                hero.attitude=2;
            }
            if((event.getCode() == KeyCode.ENTER) && (reload==0) && (hero.attitude!=2)) {
                hero.attitude = 3;
                bullet.getAnimatedImage().setX(145);
                reload=225;
            }
        });
    }

    public void moveFoes(){
        for(Foe foe : foelist) {
            if (foe.getAnimatedImage().getX()<0) {
                foe.update();
                foe.attitude=0; //Le foe mort redevient vivant.
            }
            foe.getAnimatedImage().setX(foe.getAnimatedImage().getX() - 5);
        }
    }

    public void moveBullet(){
        bullet.getAnimatedImage().setX((bullet.getAnimatedImage().getX() + 3));
        if (reload != 0) {
            reload--;
            if (reload==0){
                System.out.println("WEAPON RELOADED");
            }
        }
    }

    public boolean ifCollision(){
        for(Foe foe : foelist){
            boolean hitfoe = hero.getHitBox().intersects(foe.getHitBox());
            if ((hitfoe==true)&&(foe.attitude!=1)) {
                hero.setInvincibility(1000);
                return true;
            }
        }
        return false;
    }

    public void bulletCollision(){
        for (Foe foe: foelist) {
            if((bullet.getAnimatedImage().getX()>145) && (bullet.getAnimatedImage().getX()<800)){
                boolean hitbullet = bullet.getHitBox().intersects(foe.getHitBox());
                if (hitbullet==true){
                    foe.getAnimatedImage().setX(900);//On place l'élemine de l'écran en le placant hors champ.
                    foe.attitude=1; //Attitude pour un foe mort
                    bullet.getAnimatedImage().setX(900);//Quand la balle a touché un foe, elle disparait de l'écran en la placant hors champ.
                }
            }
        }
    }

    public void ifLose(int numberOfLives){
        if (numberOfLives==0){
            System.out.println("LOST");
            Platform.exit();
        }
    }
}
