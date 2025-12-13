package projects.maze;

public class Main
{

    public static void main(String[] args)
    {
        phase2();
        hardMazes();
    }

    private static void hardMazes() 
    {
        Maze maze = MazeReader.load("data/hard_maze.txt");
        if (maze.depthFirstSolve()) {
            maze.serialize("data/hard_maze.out");
        }
        maze = MazeReader.load("data/hard_maze_nosol.txt");
        if (maze.depthFirstSolve()) {
            maze.serialize("data/hard_maze_nosol.out");
        }
    }

    private static void phase1()
    {
        Maze maze = MazeReader.load("data/sample_maze.txt");
        System.out.println("Maze successfully loaded!");
        maze.serialize("data/sample_maze_out.txt");
    }

    private static void phase2()
    {
        Maze solvable1 = MazeReader.load("data/sample_maze.txt");
        System.out.println("Maze successfully loaded!");
        System.out.println("Solve attempt returned " + solvable1.depthFirstSolve() + ".");
        solvable1.serialize("data/sample_maze_out.txt");

        Maze solvable2 = MazeReader.load("data/sample_maze2.txt");
        System.out.println("Maze successfully loaded!");
        System.out.println("Solve attempt returned " + solvable2.depthFirstSolve() + ".");
        solvable2.serialize("data/sample_maze2_out.txt");

        Maze solvable3 = MazeReader.load("data/sample_maze3.txt");
        System.out.println("Maze successfully loaded!");
        System.out.println("Solve attempt returned " + solvable3.depthFirstSolve() + ".");
        solvable3.serialize("data/sample_maze3_out.txt");

        Maze unsolvable = MazeReader.load("data/sample_maze_no_solution.txt");
        System.out.println("Maze successfully loaded!");
        System.out.println("Solve attempt returned " + unsolvable.depthFirstSolve() + ".");
        unsolvable.serialize("data/sample_maze_no_solution_out.txt");
    }
}