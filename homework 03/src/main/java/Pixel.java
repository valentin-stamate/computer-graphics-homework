import processing.core.PApplet;

public class Pixel {
    private boolean pixelOn = false;
    private final PApplet pApplet;
    private final int x;
    private final int y;
    private final int pixelSize;

    public Pixel(PApplet pApplet, int x, int y, int pixelSize) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.pixelSize = pixelSize;
    }

    public void draw() {
        pApplet.stroke(80);
        pApplet.line(x, y - pixelSize / 2.0f, x, y + pixelSize / 2.0f);
        pApplet.line(x - pixelSize / 2.0f, y, x + pixelSize / 2.0f, y);

//        pApplet.noFill();
//        pApplet.stroke(30);
//        pApplet.rect(x - pixelSize / 2.0f, y - pixelSize / 2.0f,pixelSize, pixelSize);

        if (pixelOn) {
            pApplet.fill(30);
            pApplet.noStroke();
//            pApplet.ellipse(x, y, pixelSize, pixelSize);
            drawCircle(x, y, pixelSize);
        }
    }

    public void activatePixel() {
        this.pixelOn = true;
    }

    public void deactivatePixel() {
        this.pixelOn = false;
    }

    private void drawCircle(int ox, int oy, int diameter) {
        pApplet.stroke(20);

        for (int y = oy - diameter / 2; y <= oy + diameter / 2; y++) {
            for (int x = ox - diameter / 2; x <= ox + diameter / 2; x++) {
                if (isIn(x, y, ox, oy, diameter / 2)) {
                    pApplet.point(x, y);
                }
            }
        }
    }

    public boolean isIn(int x, int y, int ox, int oy, int radius) {
        return (x - ox) * (x - ox) + (y - oy) * (y - oy) <= radius * radius;
    }

}
