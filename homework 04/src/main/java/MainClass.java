import processing.core.PApplet;

public class MainClass extends PApplet {

    private final CartesianGrid cartesianGrid;
    private final int canvasWidth = 630;
    private final int canvasHeight = 630;


    public MainClass() {
        this.cartesianGrid = new CartesianGrid(this, canvasWidth , canvasHeight, 30, 30);
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

        cartesianGrid.draw();
        cartesianGrid.drawCircle(15, 15, 10);
//        cartesianGrid.fillEllipse(14, 14, 10, 14);
    }

    @Override
    public void stop() {

    }

    @Override
    public void mouseReleased() {

    }

    public static void main(String... args){
        PApplet.main("MainClass");
    }
}