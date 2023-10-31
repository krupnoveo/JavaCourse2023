package edu.project2.consoleOutput;

public sealed interface Message {
    String message();

    record Greeting() implements Message {
        @Override
        public String message() {
            return "In this program you can generate mazes by using one of the available "
                + "algorithms and then get the path from one entered point to another";
        }
    }

    record InputDimensions() implements Message {
        @Override
        public String message() {
            return "Enter width and height of maze you want to generate splitting "
                + "them by space (sizes must be uneven numbers higher than 4): ";
        }
    }

    record ChooseGenerationAlgorithm() implements Message {
        @Override
        public String message() {
            return "There are two available generation algorithms. "
                + "Enter 1 for DepthFirst algorithm, 2 - Prim algorithm: ";
        }
    }

    record ChooseSolver() implements Message {
        @Override
        public String message() {
            return "There are two available solver algorithms. "
                + "Enter 1 for DepthFirstSearch algorithm, 2 - BreadthFirstSearch: ";
        }
    }

    record InputCoordinatesForPathSearch() implements Message {
        @Override
        public String message() {
            return "Enter coordinates for start (first two numbers) and "
                + "end (next two numbers) points, splitting them by space: ";
        }
    }
}
