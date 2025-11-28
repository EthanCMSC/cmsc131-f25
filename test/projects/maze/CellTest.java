package projects.maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest
{
    private Cell cell;
    private Coords[] neighbors;

    @BeforeEach
    public void setup()
    {
        this.cell = new Cell(
            new Coords(
                2,
                3
            ),
            CellStatus.O
        );
        
        this.neighbors = new Coords[4];
        this.neighbors[0] = new Coords(
            1,
            3
        );
        this.neighbors[1] = new Coords(
            3,
            3
        );
        this.neighbors[2] = new Coords(
            2,
            2
        );
        this.neighbors[3] = new Coords(
            2,
            4
        );

        this.cell.setNeighbors(this.neighbors);
    }

    @Test
    public void testConstructorDataValidation()
    {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Cell(
                    null,
                    CellStatus.O
                );
            }
        );

        String expected = "New Cell's coords value must not be null.";
        String actual = e.getMessage();
        assertTrue(
            expected.equals(actual)
        );
    }

    @Test
    public void testGetCoords()
    {
        Coords expected = new Coords(
            2,
            3
        );
        Coords actual = this.cell.getCoords();

        assertTrue(
            expected.equals(actual)
        );
    }

    @Test
    public void testGetNeighbors()
    {
        Coords[] expected = this.neighbors;
        Coords[] actual = this.cell.getNeighbors();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void testIsExplored()
    {
        assertFalse(
            this.cell.isExplored()
        );
    }

    @Test
    public void testGetStatus()
    {
        CellStatus expected = CellStatus.O;
        CellStatus actual = this.cell.getStatus();

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void testSetNeighbors()
    {
        Coords[] newNeighbors = {
            new Coords(
                2,
                4
            ),
            new Coords(
                4,
                4
            ),
            new Coords(
                3,
                3
            ),
            new Coords(
                3,
                5
            )
        };
        this.cell.setNeighbors(newNeighbors);

        Coords[] expected = newNeighbors;
        Coords[] actual = this.cell.getNeighbors();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void testSetExplored()
    {
        this.cell.setExplored(true);

        assertTrue(this.cell.isExplored());
    }

    @Test
    public void testSetStatus()
    {
        this.cell.setStatus(CellStatus.P);
    }
}