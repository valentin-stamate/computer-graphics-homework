import processing.core.PApplet;

public record Pixel(PApplet pApplet, int x, int y, int size) {
    public void draw() {
        pApplet.stroke(200);
        pApplet.line(x, y - size / 2.0f, x, y + size / 2.0f);
        pApplet.line(x - size / 2.0f, y, x + size / 2.0f, y);

        pApplet.noFill();
        pApplet.stroke(80);
        pApplet.rect(x - size / 2.0f, y - size / 2.0f, size, size);
    }
}
