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

    public void draw() {
        for (int i = 0; i < heightPixels; i++) {
            for (int j = 0; j < widthPixels; j++) {
                pixels[i][j].draw();
            }
        }
    }

    public void drawLine(int x0, int y0, int xn, int yn) {
        /* Drawing the real line */
        pApplet.stroke(255, 20, 20);
        pApplet.line(x0 * pixelSize + pixelSize / 2f, y0 * pixelSize + pixelSize / 2f, xn * pixelSize + pixelSize / 2f, yn * pixelSize + pixelSize / 2f);
        /* */

        float slope = (1f * xn - x0) / (yn - y0);
        int dir = slope < 0 ? -1 : 1;

        int dx = xn - x0;
        int dy = yn - y0;

        int d = 2 * dy - dx;
        int dE = 2 * dy;
        int dNE = 2 * (dy - dx);

        int x = x0;
        int y = y0;

        activatePixel(x, y);

        while (x < xn) {
            if (d <= 0) {
                d += dir * dE;
            } else {
                d += dir * dNE;
                y = y + dir;
            }

            x++;

            activatePixel(x, y);
        }

    }

    public void activatePixel(int x, int y) {
        pixels[y][x].activatePixel();
    }

}
