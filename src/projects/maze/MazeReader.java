package projects.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeReader
{
    /**
     * Provided by Dusel.
     * Loads and returns a {@code Maze} object from a text ({@code .txt}) file.
     * Assmes existence of {@code CellStatus} {@code enum} with values {@code S}, {@code O}, {@code E}.
     * Assumes existence of {@code discoverAndSetupNeighbors} instance method for {@code Maze}.
     * @param filename - The name of the text file to read {@code Maze} data from.
     * @return {@code Maze} object representing the text in the file.
     */
    public static Maze load(String filename)
    {
        Scanner scanner;
        int spaceCount = countSpacesIn(filename);
        if (spaceCount == 0)
        {
            return null;
        }
        try
        {
            scanner = new Scanner(new File(filename));
            Maze maze = new Maze(spaceCount);   
            int row = 0, col = 0;
            while (scanner.hasNextLine())
            {
                col = 0; // row begins with first column
                String[] tokens = scanner.nextLine().split(",");
                for (int i = 0; i < tokens.length; i ++)
                {
                    if (!tokens[i].equals("X"))
                    {
                        maze.insertCell(
                            new Cell(
                                new Coords(
                                    row,
                                    col
                                ),
                                CellStatus.valueOf(tokens[i])
                            )
                        );
                    }
                    col ++;  // next space along same row
                }
                row ++; // new row
            }
            scanner.close();
            maze.discoverAndSetupNeighbors();
            return maze;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Counts the number of non-wall spaces in a maze text file, and returns that number as an {@code int}.
     * @param filename - The name of the text file to read {@code Maze} data from.
     * @return {@code int} value representing the number of non-wall spaces in maze text file.
     */
    private static int countSpacesIn(String filename)
    {
        try
        {
            Scanner scanner = new Scanner(new File(filename));
            int spaceCount = 0;
            while (scanner.hasNextLine())
            {
                String[] tokens = scanner.nextLine().split(",");
                for (int i = 0; i < tokens.length; i ++)
                {
                    if (!tokens[i].equals("X"))
                    {
                       spaceCount ++;
                    }
                }
            }
            scanner.close();
            return spaceCount;
        }
        catch (java.io.FileNotFoundException ex)
        {
            return 0;
        }
    }
}