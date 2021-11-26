import java.util.Random;

public class Foe extends AnimatedThing{

    public Foe(double x, double y, String fileName, Integer attitude) {
        super(x, y, fileName, attitude);
    }

    public void update(){
        Random rand = new Random();
        this.getAnimatedImage().setX(900+8*rand.nextInt((1300-900)+1)); //A modifier si on veut changer la fréquence d'apparition des foes.
    }
}
