package edu.project4.models;

public record FractalImage(Pixel[][] data, Resolution resolution) {
    public static FractalImage create(Resolution resolution) {
        int width = resolution.width();
        int height = resolution.height();
        Pixel[][] data = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i][j] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return new FractalImage(data, resolution);
    }

    boolean contains(int x, int y) {
        return x >= 0 && x < resolution().width() && y >= 0 && y < resolution.height();
    }

    public Pixel getPixel(int x, int y) {
        if (!contains(x, y)) {
            return null;
        }
        return data[y][x];
    }
}
