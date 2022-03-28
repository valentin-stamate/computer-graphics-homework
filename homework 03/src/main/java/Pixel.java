import processing.core.PApplet;

public record Pixel(PApplet pApplet, int x, int y, int size) {
    public void draw() {
        pApplet.stroke(20);

        pApplet.line(x, y - size / 2.0f, x, y + size / 2.0f);
        pApplet.line(x - size / 2.0f, y, x + size / 2.0f, y);
    }
}
