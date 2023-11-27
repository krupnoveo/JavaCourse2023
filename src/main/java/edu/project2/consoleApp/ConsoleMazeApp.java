package edu.project2.consoleApp;

import edu.project2.consoleOutput.ConsoleOutput;
import edu.project2.consoleOutput.Message;
import edu.project2.consoleOutput.Output;
import edu.project2.input.ConsoleInput;
import edu.project2.input.Input;
import edu.project2.mazeGeneration.DepthFirstGenerator;
import edu.project2.mazeGeneration.MazeGenerator;
import edu.project2.mazeGeneration.PrimGenerator;
import edu.project2.mazeSolve.BreadthFirstSeacrhSolver;
import edu.project2.mazeSolve.DepthFirstSearchSolver;
import edu.project2.mazeSolve.Solver;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.visualizer.ConsoleVisualizer;
import edu.project2.visualizer.Visualizer;
import java.util.List;

public class ConsoleMazeApp {
    private final Output output;
    private final Input input;
    private int height;
    private int width;
    private MazeGenerator generator;
    private final Visualizer visualizer;
    private Solver solver;
    private Maze maze;

    public ConsoleMazeApp() {
        this.output = new ConsoleOutput();
        this.input = new ConsoleInput();
        this.visualizer = new ConsoleVisualizer();
    }

    public ConsoleMazeApp(Input input, Output output) {
        this.output = output;
        this.input = input;
        this.visualizer = new ConsoleVisualizer();
    }

    public void run() {
        output.print(new Message.Greeting().message());
        chooseGenerator();
        inputMazeDimensions();
        maze = generator.generate(width, height);
        output.print(visualizer.visualizeGeneratedMaze(maze));
        chooseSolver();
        List<Cell> cells = inputCoordinates();
        List<Cell> solvedPath = solver.getPath(maze, cells.get(0), cells.get(1));
        output.print(visualizer.visualizeSolvedMaze(maze, solvedPath));
    }

    private void inputMazeDimensions() {
        output.print(new Message.InputDimensions().message());
        width = input.input();
        height = input.input();
    }

    private void chooseGenerator() {
        output.print(new Message.ChooseGenerationAlgorithm().message());
        int variant = input.input();
        generator = switch (variant) {
            case 1 -> new DepthFirstGenerator();
            case 2 -> new PrimGenerator();
            default -> throw new IllegalArgumentException("Incorrect variant of generation algorithm");
        };
    }

    private void chooseSolver() {
        output.print(new Message.ChooseSolver().message());
        int variant = input.input();
        solver = switch (variant) {
            case 1 -> new DepthFirstSearchSolver();
            case 2 -> new BreadthFirstSeacrhSolver();
            default -> throw new IllegalArgumentException("Incorrect variant of solver algorithm");
        };
    }

    private List<Cell> inputCoordinates() {
        output.print(new Message.InputCoordinatesForPathSearch().message());
        int row1 = input.input() - 1;
        int column1 = input.input() - 1;
        int row2 = input.input() - 1;
        int column2 = input.input() - 1;
        Cell start = maze.getSurface()[row1][column1];
        Cell end = maze.getSurface()[row2][column2];
        return List.of(start, end);
    }
}
