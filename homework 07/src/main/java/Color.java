class Color {
    public final int r;
    public final int g;
    public final int b;

    private Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Color getColor(int r, int g, int b) {
        return new Color(r, g, b);
    }
}
