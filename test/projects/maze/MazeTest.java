package projects.maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest
{
    private Maze maze;

    @BeforeEach
    public void setup()
    {
        this.maze = new Maze(4);
    }

    @Test
    public void testGetStart()
    {
        Cell expected = new Cell(
            new Coords(2, 3),
            CellStatus.S
        );

        this.maze.insertCell(expected);

        Cell actual = this.maze.getStart();

        assertTrue(
            expected.equals(actual)
        );
    }

    @Test
    public void testGetEnd()
    {
        Cell expected = new Cell(
            new Coords(2, 3),
            CellStatus.E
        );

        this.maze.insertCell(expected);

        Cell actual = this.maze.getEnd();

        assertTrue(
            expected.equals(actual)
        );
    }

    @Test
    public void testInsertCell()
    {
        Cell testCell = new Cell(
            new Coords(
                0, 0
            ),
            CellStatus.O
        );
        assertTrue(
            maze.insertCell(testCell)
        );
    }
}