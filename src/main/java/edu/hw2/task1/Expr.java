package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    record Negate(Expr expr) implements Expr {
        public Negate(double value) {
            this(new Constant(value));
        }

        @Override
        public double evaluate() {
            return expr.evaluate() * (-1.0);
        }
    }

    record Exponent(Expr base, Expr power) implements Expr {
        public Exponent(Expr base, double power) {
            this(base, new Constant(power));
        }

        public Exponent(double base, Expr power) {
            this(new Constant(base), power);
        }

        public Exponent(double base, double power) {
            this(new Constant(base), new Constant(power));
        }

        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), power().evaluate());
        }
    }

    record Addition(Expr expr1, Expr expr2) implements Expr {
        public Addition(double value, Expr expr) {
            this(new Constant(value), expr);
        }

        public Addition(Expr expr, double value) {
            this(expr, new Constant(value));
        }

        public Addition(double val1, double val2) {
            this(new Constant(val1), new Constant(val2));
        }

        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2().evaluate();
        }
    }

    record Multiplication(Expr expr1, Expr expr2) implements Expr {
        public Multiplication(double value, Expr expr) {
            this(new Constant(value), expr);
        }

        public Multiplication(Expr expr, double value) {
            this(expr, new Constant(value));
        }

        public Multiplication(double val1, double val2) {
            this(new Constant(val1), new Constant(val2));
        }

        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2().evaluate();
        }
    }
}
