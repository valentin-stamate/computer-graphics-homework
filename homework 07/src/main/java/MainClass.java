import processing.core.PApplet;

public class MainClass extends PApplet {
    private final int canvasWidth = 630;
    private final int canvasHeight = 630;

    private float rotationX = radians(35);
    private float rotationY = radians(-45);
    private float rotationZ = 0f;

    private float zoom = 0;

    private int keyCodePressed = 0;
    private boolean pressed = false;

    public void settings() {
        size(canvasWidth, canvasHeight, P3D);
    }

    public void setup() {
        background(255);
        frameRate(60);

        surface.setResizable(true);
        surface.setTitle("Homework 7");
    }

    @Override
    public void draw() {
        background(20);

        float currentWidthScale = 1f * width / canvasWidth;
        float currentHeightScale = 1f * height / canvasHeight;

        pushMatrix();
        translate(width / 2f, height / 2f, 0);
        scale(currentWidthScale, currentHeightScale);
        translate(-width / 2f, -height / 2f, 0);

        playCube();
        popMatrix();

        refreshKeyPress();
    }

    public void playCube() {
        pushMatrix();
        translate(width / 2f, height / 2f, 0);
        scale(1, -1, 1);

        /* Here the magic happens */
        translate(0, 0, zoom);

        rotateX(rotationX);
        rotateY(rotationY);
        rotateZ(rotationZ);
        /* Here the magic ends */

        Color col;
        /* Draw The Axis Lines */
        int axisLength = 400;

        strokeWeight(2);
        col = Colors.GREEN;
        stroke(col.r, col.g, col.b);
        line(axisLength, 0, 0, 0, 0, 0);
        col = Colors.BLUE;
        stroke(col.r, col.g, col.b);
        line(0, axisLength, 0, 0, 0, 0);
        col = Colors.RED;
        stroke(col.r, col.g, col.b);
        line(0, 0, axisLength, 0, 0, 0);

        int l = 100;
        int hl = l / 2;

        translate(hl, hl, hl);

        drawCube(l);
        popMatrix();
    }

    public void drawCube(int l) {
        pushMatrix();

        Color col;
        int hl = l / 2;

        stroke(255);

        /* Front */
        col = Colors.RED;
        fill(col.r, col.g, col.b);
        beginShape();
        vertex(-hl, hl, hl);
        vertex(hl, hl, hl);
        vertex(hl, -hl, hl);
        vertex(-hl, -hl, hl);
        endShape(CLOSE);

        /* Top */
        col = Colors.GREEN;
        fill(col.r, col.g, col.b);
        beginShape();
        vertex(-hl, hl, hl);
        vertex(hl, hl, hl);
        vertex(hl, hl, -hl);
        vertex(-hl, hl, -hl);
        endShape(CLOSE);

        /* Bottom */
        col = Colors.CYAN;
        fill(col.r, col.g, col.b);
        beginShape();
        vertex(-hl, -hl, hl);
        vertex(hl, -hl, hl);
        vertex(hl, -hl, -hl);
        vertex(-hl, -hl, -hl);
        endShape(CLOSE);

        /* Back */
        col = Colors.ORANGE;
        fill(col.r, col.g, col.b);
        beginShape();
        vertex(-hl, hl, -hl);
        vertex(hl, hl, -hl);
        vertex(hl, -hl, -hl);
        vertex(-hl, -hl, -hl);
        endShape(CLOSE);

        /* Left */
        col = Colors.BLUE;
        fill(col.r, col.g, col.b);
        beginShape();
        vertex(-hl, hl, hl);
        vertex(-hl, -hl, hl);
        vertex(-hl, -hl, -hl);
        vertex(-hl, hl, -hl);
        endShape(CLOSE);

        /* Right */
        col = Colors.YELLOW;
        fill(col.r, col.g, col.b);
        beginShape();
        vertex(hl, hl, hl);
        vertex(hl, -hl, hl);
        vertex(hl, -hl, -hl);
        vertex(hl, hl, -hl);
        endShape(CLOSE);

        popMatrix();
    }

    private void refreshKeyPress() {
        if (!pressed) {
            return;
        }

//        System.out.println();
//        System.out.println(rotationX);
//        System.out.println(rotationY);
//        System.out.println(rotationZ);
//        System.out.println(zoom);

        int step = 2;
        float eps = 10e-3f;

        if (keyCodePressed == Controls.UP) {
            rotationX += radians(step);
        } else if (keyCodePressed == Controls.DOWN) {
            rotationX += radians(-step);
        } else if (keyCodePressed == Controls.LEFT) {
            rotationY += radians(-step);
        } else if (keyCodePressed == Controls.RIGHT) {
            rotationY += radians(step);
        } else if (keyCodePressed == Controls.PLUS) {
            zoom += 5;
        } else if (keyCodePressed == Controls.MINUS) {
            zoom -= 5;
        } else if (keyCodePressed == Controls.ONE) {

            /* Exercise 1 */
            float rotXDif = rotationX - (float) Math.PI / 2f;
            if (Math.abs(rotXDif) > eps) {
                rotationX = rotationX - Math.signum(rotXDif) * radians(1f);
            }

            float rotYDif = rotationY - 0f;
            if (Math.abs(rotYDif) > eps) {
                rotationY = rotationY - Math.signum(rotYDif) * radians(1f);
            }

            float rotZDif = rotationZ - 0f;
            if (Math.abs(rotYDif) > eps) {
                rotationZ = rotationZ - Math.signum(rotZDif) * radians(1f);
            }

            float zoomDif = zoom - 0f;
            if (Math.abs(zoomDif) > eps) {
                zoom = zoom - Math.signum(zoomDif) * 3f;
            }

        } else if (keyCodePressed == Controls.TWO) {

            /* Exercise 2 */
            float rotXDif = rotationX - 0.47f;
            if (Math.abs(rotXDif) > eps) {
                rotationX = rotationX - Math.signum(rotXDif) * radians(1f);
            }

            float rotYDif = rotationY - (-0.51f);
            if (Math.abs(rotYDif) > eps) {
                rotationY = rotationY - Math.signum(rotYDif) * radians(1f);
            }

            float rotZDif = rotationZ - 0f;
            if (Math.abs(rotZDif) > eps) {
                rotationZ = rotationZ - Math.signum(rotZDif) * radians(1f);
            }

            float zoomDif = zoom - 100;
            if (Math.abs(zoomDif) > eps) {
                zoom = zoom - Math.signum(zoomDif) * 3f;
            }

        } else if (keyCodePressed == Controls.THREE) {

            /* Exercise 3 */
            float rotXDif = rotationX - 0.39f;
            if (Math.abs(rotXDif) > eps) {
                rotationX = rotationX - Math.signum(rotXDif) * radians(1f);
            }

            float rotYDif = rotationY - (-0.49f);
            if (Math.abs(rotYDif) > eps) {
                rotationY = rotationY - Math.signum(rotYDif) * radians(1f);
            }

            float rotZDif = rotationZ - 0f;
            if (Math.abs(rotZDif) > eps) {
                rotationZ = rotationZ - Math.signum(rotZDif) * radians(1f);
            }

            float zoomDif = zoom - 185;
            if (Math.abs(zoomDif) > eps) {
                zoom = zoom - Math.signum(zoomDif) * 3f;
            }

        }
    }

    @Override
    public void stop() {}

    @Override
    public void mouseReleased() { }

    @Override
    public void keyPressed() {
        pressed = true;
        keyCodePressed = keyCode;
    }

    @Override
    public void keyReleased() {
        pressed = false;
    }

    public static void main(String... args){
        PApplet.main("MainClass");
    }

}