import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    protected double y;
    protected double x;
    protected ImageView sprite;
    protected Integer attitude;

    public AnimatedThing(double x, double y, String fileName,Integer attitude){
        this.x = x;
        this.y = y;
        this.attitude = attitude;

        //The view Port of the ImageView is set to the first frame:
        Image spriteSheet = new Image(fileName);
        this.sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(20,0,60,100)); //set to first image
    }

    public ImageView getAnimatedImage(){
        return sprite;
    }

    public Rectangle2D getHitBox(){
        return(new Rectangle2D(getAnimatedImage().getX(),getAnimatedImage().getY(),70,14));
    }
}





