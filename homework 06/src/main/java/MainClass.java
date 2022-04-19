import processing.core.PApplet;

public class MainClass extends PApplet {

    public void settings() {
        size(630, 630, P2D);
    }

    public void setup() {
        background(255);
        frameRate(30);

        surface.setTitle("Homework 6");
    }

    @Override
    public void draw() {
        background(20);
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