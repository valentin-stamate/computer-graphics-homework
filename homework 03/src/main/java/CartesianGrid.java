import processing.core.PApplet;

public class CartesianGrid {

    final PApplet pApplet;
    final Pixel[][] pixels;
    final int pixelSize;
    final int widthPixels;
    final int heightPixels;

    public CartesianGrid(PApplet pApplet, int canvasWidth, int canvasHeight, int widthPixels) {
        this.pApplet = pApplet;

        this.widthPixels = widthPixels;
        this.pixelSize = canvasWidth / widthPixels;
        this.heightPixels = canvasHeight / pixelSize;

        this.pixels = new Pixel[heightPixels][widthPixels];

        for (int i = 0; i < heightPixels; i++) {
            for (int j = 0; j < widthPixels; j++) {
                pixels[i][j] = new Pixel(pApplet, j * this.pixelSize + pixelSize / 2, i * this.pixelSize + pixelSize / 2, pixelSize);
            }
        }
    }

    public void start() {
        this.pApplet.registerMethod("draw", this);
    }

    public void draw() {
        for (int i = 0; i < heightPixels; i++) {
            for (int j = 0; j < widthPixels; j++) {
                pixels[i][j].draw();
            }
        }
    }

    public void activatePixel(int x, int y) {
        pixels[y][x].activatePixel();
    }

}
