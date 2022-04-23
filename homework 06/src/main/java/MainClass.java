import processing.core.PApplet;

public class MainClass extends PApplet {
    private final float radAngle = (float) Math.PI / 180f;

    private final int canvasWidth = 630;
    private final int canvasHeight = 630;

    float zPosition = -1000;
    float zAngle = radians(180);
    float yAngle = radians(180);
    float xAngle = radians(180);

    public void settings() {
        size(canvasWidth, canvasHeight, P3D);
    }

    public void setup() {
        background(255);
        frameRate(60);
        surface.setResizable(true);
        surface.setTitle("Homework 6");
    }

    float r = 0;
    char keyPressed = '1';

    @Override
    public void draw() {
        background(20);
        rectMode(CENTER);

        float currentWidthScale = 1f * width / canvasWidth;
        float currentHeightScale = 1f * height / canvasHeight;

        pushMatrix();
        /* Scaling when screen resize */
        translate(width / 2f, height / 2f, 0);
        scale(currentWidthScale, currentHeightScale);
        translate(-width / 2f, -height / 2f, 0);

        if (keyPressed == '1') {
            playTriangle();
        } else if (keyPressed == '2') {
            playCube();
        }
        popMatrix();
    }

    int cubeSize = 100;
    float offset = cubeSize / 2f;

    public void playCube() {
        fill(255);
        stroke(20);
        strokeWeight(2);

        pushMatrix();

        translate(width / 2f, height / 2f, 0);
        rotateZ(r);

        rotateX(radians(55));
        rotateZ(radians(45));

        translate(offset, offset, offset);
        box(cubeSize);

        r -= radians(1);
        popMatrix();
    }

    public void playRectangle() {
        pushMatrix();
        translate(width / 2f, height / 2f, 0);
        rotateX(r);
        rotateY(r);
        rotateZ(r);
        rect(0,0, 100,100);

        r += radians(1);

        rotateX(0);
        rotateY(0);
        rotateZ(0);
        popMatrix();
    }

    public void playTriangle() {
        pushMatrix();
        translate(width / 2f, height / 2f, zPosition);
        rotateX(xAngle);
        rotateY(yAngle);
        rotateZ(zAngle);

        noStroke();
        triangle(0, -66, 50, 33, -50, 33);

        if (xAngle > 0f) {
            xAngle -= 1.8 * radians(1);
        }

        if (yAngle > 0f) {
            yAngle -= 1.8 * radians(1);
        }

        if (zAngle > 0f) {
            zAngle -= 1.8 * radians(1);
        }

        if (zPosition < 0) {
            zPosition += 10f;
        }

        popMatrix();
    }

    @Override
    public void stop() {}

    @Override
    public void mouseReleased() {

    }

    @Override
    public void keyPressed() {
        keyPressed = key;

        /* Reset Everything */
        if (keyPressed == '1') {
            zPosition = -1000;
            zAngle = radians(180);
            yAngle = radians(180);
            xAngle = radians(180);
        }
    }

    public static void main(String... args){
        PApplet.main("MainClass");
    }

    public float radians(int angle) {
        return  radAngle * angle;
    }
}