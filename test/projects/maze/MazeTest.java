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

    @Test
    public void testDepthFirstSolveSuccess()
    {
        Maze expectedMaze = new Maze(5);

        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    0,
                    0
                ),
                CellStatus.S
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    0
                ),
                CellStatus.P
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    1
                ),
                CellStatus.P
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    2
                ),
                CellStatus.E
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    2,
                    0
                ),
                CellStatus.O
            )
        );
        expectedMaze.discoverAndSetupNeighbors();

        Cell[] expectedCells = expectedMaze.getAllCells();

        for (Cell cell : expectedCells)
        {
            cell.setExplored(true);
        }

        Maze actualMaze = new Maze(5);

        actualMaze.insertCell(
            new Cell(
                new Coords(
                    0,
                    0
                ),
                CellStatus.S
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    0
                ),
                CellStatus.O
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    1
                ),
                CellStatus.O
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    2
                ),
                CellStatus.E
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    2,
                    0
                ),
                CellStatus.O
            )
        );
        actualMaze.discoverAndSetupNeighbors();

        assertTrue(
            actualMaze.depthFirstSolve()
        );

        Cell[] actualCells = actualMaze.getAllCells();
        assertEquals(
            expectedCells.length,
            actualCells.length
        );

        for (int i = 0; i < expectedCells.length; i ++)
        {
            assertTrue(
                expectedCells[i].equals(actualCells[i])
            );
        }
    }

    @Test
    public void testDepthFirstSolveFail()
    {
        Maze expectedMaze = new Maze(4);

        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    0,
                    0
                ),
                CellStatus.S
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    0
                ),
                CellStatus.O
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    2
                ),
                CellStatus.E
            )
        );
        expectedMaze.insertCell(
            new Cell(
                new Coords(
                    2,
                    0
                ),
                CellStatus.O
            )
        );
        expectedMaze.discoverAndSetupNeighbors();

        Cell[] expectedCells = expectedMaze.getAllCells();

        for (Cell cell : expectedCells)
        {
            if (cell.getStatus() != CellStatus.E)
            {
                cell.setExplored(true);
            }
        }

        Maze actualMaze = new Maze(4);

        actualMaze.insertCell(
            new Cell(
                new Coords(
                    0,
                    0
                ),
                CellStatus.S
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    0
                ),
                CellStatus.O
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    1,
                    2
                ),
                CellStatus.E
            )
        );
        actualMaze.insertCell(
            new Cell(
                new Coords(
                    2,
                    0
                ),
                CellStatus.O
            )
        );
        actualMaze.discoverAndSetupNeighbors();

        assertFalse(
            actualMaze.depthFirstSolve()
        );

        Cell[] actualCells = actualMaze.getAllCells();
        assertEquals(
            expectedCells.length,
            actualCells.length
        );

        for (int i = 0; i < expectedCells.length; i ++)
        {
            assertTrue(
                expectedCells[i].equals(actualCells[i])
            );
        }
    }
}