import processing.core.PApplet;

public class CartesianGrid {

    final PApplet pApplet;
    final Pixel[][] pixels;
    public int rows;
    public int columns;

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
        pApplet.ellipse(ox * pixelWidth - pixelWidth / 2f, (columns - oy - 1) * pixelHeight - pixelHeight / 2f, 2 * r * pixelWidth, 2 * r * pixelHeight);

        int x = 0;
        int y = r;

        int d = 3 - 2 * r;
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

    /* Exercise 2 */
    public void fillEllipse(int ox, int oy, int a, int b) {
        int x = 0;
        int y = b;

        double fxpyp = 0;
        double deltaE;
        double deltaSE;
        double deltaS;

        /* Regiunea 1 */
        while (a * a * (y - 0.5) > b * b * (x + 1)) {
            deltaE = b * b * (2 * x + 1);
            deltaSE = b * b * (2 * x + 1) + a * a * (-2 * y + 1);

            if (fxpyp + deltaE <= 0) {
                /* E este in interior */
                fxpyp += deltaE;
                x++;
            } else if (fxpyp + deltaSE <= 0) {
                /* SE este in interior */
                fxpyp += deltaSE;
                x++;
                y--;
            }

            activatePixel(ox + x, oy + y);
        }

        /* Regiunea 2 */
        while (y < 0) {
            deltaSE = b * b * (2 * x + 1) + a * a * (-2 * y + 1);
            deltaS = a * a * (-2 * y + 1);

            if (fxpyp + deltaSE <= 0) {
                /* SE este in interior */
                fxpyp += deltaSE;
                x++;
                y--;
            } else {
                /* S este in interior */
                fxpyp += deltaS;
                y--;
            }
            activatePixel(ox + x, oy + y);
        }

    }

    public void activatePixel(int x, int y) {
        try {
            pixels[y][x].activatePixel();
        } catch (Exception ignored) {
        }
    }

}
