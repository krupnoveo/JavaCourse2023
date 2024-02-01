package edu.project4.models;

import java.util.Objects;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public Pixel(int r, int g, int b, int hitCount, int normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pixel pixel = (Pixel) o;
        return r == pixel.r && g == pixel.g && b == pixel.b && hitCount == pixel.hitCount
            && Double.compare(normal, pixel.normal) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, hitCount, normal);
    }
}
