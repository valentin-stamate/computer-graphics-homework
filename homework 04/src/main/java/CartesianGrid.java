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
                pixels[rows - i - 1][j] = new Pixel(pApplet, i, j, pixelWidth, pixelHeight);
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

    /* Exercise 1 */
    public void drawCircle(int ox, int oy, int r) {
        pApplet.stroke(255, 20, 20);
        pApplet.strokeWeight(2);
        pApplet.noFill();
        pApplet.ellipse(ox * pixelWidth - pixelWidth / 2f, oy * pixelHeight - pixelHeight / 2f, 2 * r * pixelWidth, 2 * r * pixelHeight);

        int x = 0;
        int y = r;

        int d = 1 - r;
        int dE = 3;
        int dSE = -2 * r + 5;

        while (y > x) {
            if (d < 0) {
                d += dE;
                dE += 2;
                dSE += 2;
            } else {
                d += dSE;
                dE += 2;
                dSE += 4;
                y--;
            }

            x++;
            activatePixel(ox - 1 + y, oy + x);
            activatePixel(ox - 1 + y + 1, oy + x);
            activatePixel(ox - 1 + y - 1, oy + x);
        }
    }

    public void activatePixel(int x, int y) {
        pixels[y][x].activatePixel();
    }

}
