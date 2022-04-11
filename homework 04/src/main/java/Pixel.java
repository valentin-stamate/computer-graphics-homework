import processing.core.PApplet;

public class Pixel {
    private boolean pixelOn = false;
    private final PApplet pApplet;

    /* The position in the grid */
    private final int x;
    private final int y;

    private final int pixelWidth;
    private final int pixelHeight;

    public Pixel(PApplet pApplet, int y, int column, int pixelWidth, int pixelHeight) {
        this.pApplet = pApplet;

        this.y = y;
        this.x = column;

        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
    }

    public void draw() {
        int x = this.x * this.pixelWidth + pixelWidth / 2;
        int y = this.y * this.pixelHeight + pixelHeight / 2;

        pApplet.stroke(80);
        pApplet.strokeWeight(1);
        pApplet.line(x, y - pixelHeight / 2.0f, x, y + pixelHeight / 2.0f);
        pApplet.line(x - pixelWidth / 2.0f, y, x + pixelWidth / 2.0f, y);

        if (pixelOn) {
            pApplet.fill(30);
            pApplet.noStroke();
            pApplet.ellipse(x, y, pixelWidth, pixelHeight);
        }
    }

    public void activatePixel() {
        this.pixelOn = true;
    }

    public void deactivatePixel() {
        this.pixelOn = false;
    }

    public boolean isIn(int x, int y, int ox, int oy, int radius) {
        return (x - ox) * (x - ox) + (y - oy) * (y - oy) <= radius * radius;
    }

}
