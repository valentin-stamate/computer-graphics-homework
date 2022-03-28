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

    public void drawLine(int x0, int y0, int x1, int y1) {
        if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
            if (x0 > x1) {
                drawLineLow(x1, y1, x0, y0);
            } else {
                drawLineLow(x0, y0, x1, y1);
            }
        } else {
            if (y0 > y1) {
                drawLineHigh(x1, y1, x0, y0);
            } else {
                drawLineHigh(x0, y0, x1, y1);
            }
        }
    }

    public void drawLineHigh(int x0, int y0, int xn, int yn) {
        /* Drawing the real line */
        pApplet.stroke(255, 20, 20);
        pApplet.line(x0 * pixelSize + pixelSize / 2f, y0 * pixelSize + pixelSize / 2f, xn * pixelSize + pixelSize / 2f, yn * pixelSize + pixelSize / 2f);
        /* */

        int dx = xn - x0;
        int dy = yn - y0;
        int xi = 1;

        if (dx < 0) {
            xi = -1;
            dx = - dx;
        }

        int d = 2 * dx - dy;
        int x = x0;

        for (int y = y0; y <= yn; y++) {
            activatePixel(x, y);

            if (d > 0) {
                x = x + xi;
                d = d + (2 * (dx - dy));
            } else {
                d = d + 2 * dx;
            }
        }

    }

    public void drawLineLow(int x0, int y0, int xn, int yn) {
        /* Drawing the real line */
        pApplet.stroke(255, 20, 20);
        pApplet.line(x0 * pixelSize + pixelSize / 2f, y0 * pixelSize + pixelSize / 2f, xn * pixelSize + pixelSize / 2f, yn * pixelSize + pixelSize / 2f);
        /* */

        int dx = xn - x0;
        int dy = yn - y0;

        int yi = 1;

        if (dy < 0) {
            yi = -1;
            dy = -dy;
        }

        int d = (2 * dy) - dx;
        int y = y0;

        for (int x = x0; x <= xn; x++) {
            activatePixel(x, y);

            if (d > 0) {
                y = y + yi;
                d = d + (2 * (dy - dx));
            } else {
                d = d + 2 * dy;
            }
        }

    }

    public void activatePixel(int x, int y) {
        pixels[y][x].activatePixel();
    }

}
