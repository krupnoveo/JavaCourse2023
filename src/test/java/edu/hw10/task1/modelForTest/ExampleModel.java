package edu.hw10.task1.modelForTest;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import lombok.Getter;

@Getter
public class ExampleModel {
    private byte a;
    private short b;
    private int c;
    private long d;
    private float e;
    private double f;
    private boolean g;
    private String h;

    public ExampleModel(
        @Min(10) @Max(12)byte a,
        @Min(10) @Max(12)short b,
        @Min(10) @Max(12)int c,
        @Min(10) @Max(12)long d,
        @Min(10) @Max(12)float e,
        @Min(10) @Max(12)double f,
        boolean g,
        @Min(10) @Max(12) @NotNull String h
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    public static ExampleModel create(
        @Min(10) @Max(12)byte a,
        @Min(10) @Max(12)short b,
        @Min(10) @Max(12)int c,
        @Min(10) @Max(12)long d,
        @Min(10) @Max(12)float e,
        @Min(10) @Max(12)double f,
        boolean g,
        @Min(10) @Max(12) @NotNull String h
    ) {
        return new ExampleModel(a, b, c, d, e, f, g, h);
    }


}
