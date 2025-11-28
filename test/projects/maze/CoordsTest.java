package projects.maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordsTest
{
    private Coords coords;

    @BeforeEach
    public void setup()
    {
        this.coords = new Coords(
            2,
            3
        );
    }

    @Test
    public void testGetRow()
    {
        assertEquals(
            2,
            this.coords.getRow()
        );
    }

    @Test
    public void testGetCol()
    {
        assertEquals(
            3,
            this.coords.getCol()
        );
    }

    @Test
    public void testEquals()
    {
        Coords same = new Coords(
            2,
            3
        );
        Coords different = new Coords(
            3,
            2
        );

        assertTrue(
            this.coords.equals(same)
        );

        assertFalse(
            this.coords.equals(different)
        );
    }
}