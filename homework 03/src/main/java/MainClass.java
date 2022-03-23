import processing.core.PApplet;

public class MainClass extends PApplet {

    public void settings() {
        size(420, 420, P2D);
    }

    public void setup() {
        background(25);
        frameRate(60);

    }

    @Override
    public void draw() {
        background(20);
        ellipse(mouseX, mouseY, 50, 50);
    }

    @Override
    public void stop() {

    }

    @Override
    public void mouseReleased() {

    }

    public static void main(String... args){
        PApplet.main("MainClass");
    }
}