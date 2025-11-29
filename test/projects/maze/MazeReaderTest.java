package projects.maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class MazeReaderTest
{
    @Test
    public void testLoad() // TODO: fix whatever is causing problems in this test
    {
        String filename = "test/projects/maze/maze_reader_test.txt";

        Maze expected = new Maze(7);
        expected.insertCell(new Cell(
            new Coords(
                0,
                0
            ),
            CellStatus.O)
        );
        expected.insertCell(new Cell(
            new Coords(
                0,
                1
            ),
            CellStatus.O)
        );
        expected.insertCell(new Cell(
            new Coords(
                0,
                2
            ),
            CellStatus.O)
        );
        expected.insertCell(new Cell(
            new Coords(
                1,
                0
            ),
            CellStatus.O)
        );
        expected.insertCell(new Cell(
            new Coords(
                1,
                2
            ),
            CellStatus.O)
        );
        expected.insertCell(new Cell(
            new Coords(
                2,
                0
            ),
            CellStatus.S)
        );
        expected.insertCell(new Cell(
            new Coords(
                2,
                2
            ),
            CellStatus.E)
        );
        expected.discoverAndSetupNeighbors();

        Maze actual = MazeReader.load(filename);

        assertTrue(
            expected.getGrid().equals(actual.getGrid())
        );
    }
}