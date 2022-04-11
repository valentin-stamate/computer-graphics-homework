import processing.core.PApplet;

public class CartesianGrid {

    final PApplet pApplet;
    final Pixel[][] pixels;
    final int rows;
    final int columns;

    final int pixelWidth;
    final int pixelHeight;

    public CartesianGrid(PApplet pApplet, int canvasWidth, int canvasHeight, int rows, int columns) {
        this.pApplet = pApplet;
        this.rows = rows;
        this.columns = columns;

        this.pixelWidth = canvasWidth / columns;
        this.pixelHeight = canvasHeight / rows;

        this.pixels = new Pixel[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pixels[i][j] = new Pixel(pApplet, i, j, pixelWidth, pixelHeight);
            }
        }
    }

    public void draw() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pixels[i][j].draw();
            }
        }
    }

    public void drawCircle() {

    }

    public void activatePixel(int x, int y) {
        pixels[y][x].activatePixel();
    }

}
