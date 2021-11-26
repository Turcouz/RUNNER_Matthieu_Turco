import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    private ImageView sprite;

    public StaticThing( String fileName){
        Image spriteSheet = new Image(fileName);
        this.sprite = new ImageView(spriteSheet);
    }

    public ImageView getStaticImage(){
        return sprite;
    }
}
