import javafx.geometry.Rectangle2D;

public class Camera {
    private int x;
    private int y;

    public Camera(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCameraX(Integer x){
        this.x = x;
    }

    //Camera update:

    public void update(StaticThing Left, StaticThing Right, double x){
        Left.getStaticImage().setViewport(new Rectangle2D(x%800,0,800,400));
        Right.getStaticImage().setX((800-(x%800)));
    }

    @Override
    public String toString(){
        return "(" + x + ","+ y + ")";
    }
}
