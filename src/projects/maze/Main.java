package projects.maze;

public class Main
{

    public static void main(String[] args)
    {
        phase2();
    }

    private static void phase1()
    {
        Maze maze = MazeReader.load("data/sample_maze.txt");
        System.out.println("Maze successfully loaded!");
        maze.serialize("data/sample_maze_out.txt");
    }

    private static void phase2()
    {
        Maze solvable = MazeReader.load("data/sample_maze.txt");
        System.out.println("Maze successfully loaded!");
        solvable.depthFirstSolve();
        solvable.serialize("data/sample_maze_out.txt");

        Maze unsolvable = MazeReader.load("data/sample_maze_no_solution.txt");
        System.out.println("Maze successfully loaded!");
        unsolvable.depthFirstSolve();
        unsolvable.serialize("data/sample_maze_no_solution_out.txt");
    }
}