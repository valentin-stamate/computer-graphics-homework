import processing.core.PApplet;

public class MainClass extends PApplet {

    public void settings() {
        size(630, 630, P3D);
    }

    public void setup() {
        background(255);
        frameRate(30);

        surface.setTitle("Homework 6");
    }

    float r = 0;
    float angle = ((float) Math.PI) / 180f;

    @Override
    public void draw() {
        background(20);
        rectMode(CENTER);

        noFill();
        stroke(255);

        playTriangle();
    }

    public void playRectangle() {
        translate(width / 2f, height / 2f, 0);
        rotateX(r);
        rotateY(r);
        rotateZ(r);
        rect(0,0, 100,100);

        r += angle;

        rotateX(0);
        rotateY(0);
        rotateZ(0);
    }

    float zPosition = -1000;
    float zAngle = 180 * angle;
    float yAngle = 180 * angle;
    float xAngle = 180 * angle;

    public void playTriangle() {
        translate(width / 2f, height / 2f, zPosition);
        rotateX(xAngle);
        rotateY(yAngle);
        rotateZ(zAngle);
        triangle(0, -66, 50, 33, -50, 33);

        if (xAngle > 0f) {
            xAngle -= 1.8 * angle;
        }

        if (yAngle > 0f) {
            yAngle -= 1.8 * angle;
        }

        if (zAngle > 0f) {
            zAngle -= 1.8 * angle;
        }

        if (zPosition < 0) {
            zPosition += 10f;
        }

        rotateX(0);
        rotateY(0);
        rotateZ(0);
    }

    @Override
    public void stop() {}

    @Override
    public void mouseReleased() {

    }

    public static void main(String... args){
        PApplet.main("MainClass");
    }
}