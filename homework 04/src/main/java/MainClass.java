import processing.core.PApplet;

public class MainClass extends PApplet {

    private final CartesianGrid cartesianGrid;
    private final int canvasWidth = 630;
    private final int canvasHeight = 630;

    private final int gridRows = 30;
    private final int gridColumns = 30;

    float ratioWidth;
    float ratioHeight;

    public MainClass() {
        this.cartesianGrid = new CartesianGrid(this, canvasWidth, canvasHeight, gridRows, gridColumns);

        this.ratioWidth = 1f * canvasWidth / gridColumns;
        this.ratioHeight = 1f * canvasHeight / gridRows;

    }

    public void settings() {
        size(canvasWidth, canvasHeight, P2D);
    }

    public void setup() {
        background(255);
        frameRate(30);

        surface.setTitle("Homework 4");
        surface.setResizable(true);
    }

    @Override
    public void draw() {
        background(255);

        float currentRatioWidth = 1f * width / gridColumns;
        float currentRationHeight = 1f * height / gridRows;

        float scaleX = currentRatioWidth / ratioWidth;
        float scaleY = currentRationHeight / ratioHeight;

        pushMatrix();
        scale(scaleX, scaleY);

        cartesianGrid.draw();
        cartesianGrid.drawCircle(0, 0, 20);
//        cartesianGrid.fillEllipse(14, 14, 10, 14);

        popMatrix();
    }

    @Override
    public void stop() {

    }

    @Override
    public void mouseReleased() {

    }

    public static void main(String... args) {
        PApplet.main("MainClass");
    }
}