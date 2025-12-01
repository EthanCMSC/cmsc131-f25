package projects.maze;

public class Grid
{
    private final Cell[] cells;
    private int cellCount;

    /**
     * {@code Grid} constructor
     * @param maxCells - The highest number of cells the grid should have at any given time
     */
    public Grid(int maxCells)
    {
        this.cells = new Cell[maxCells];
        this.cellCount = 0;
    }

    /**
     * Inserts a new cell into the grid and increments the value of {@code cellCount} accordingly.
     * @param cell - The cell to insert
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean insertCell(Cell cell)
    {
        if (this.cellCount < this.cells.length)
        {
            this.cells[this.cellCount ++] = cell;
            return true;
        }
        return false;
    }

    /**
     * Returns cell at specified coordinates.
     * @param vh - Coordinates of target cell
     * @return The cell at coordinates {@code vh} if one exists; {@code null} otherwise
     */
    public Cell getCell(Coords vh)
    {
        for (int idx = 0; idx < this.cellCount; idx ++)
        {
            if (this.cells[idx].getCoords().equals(vh))
            {
               return this.cells[idx];
            }
        }
        return null;
    }

    public int getCellCount()
    {
        return this.cellCount;
    }

    /**
     * Returns all cells in grid.
     * @return An array of all {@code Cell}s present in the grid
     */
    public Cell[] getAllCells()
    {
        Cell[] allCells = new Cell[this.cellCount];
        for (int idx = 0; idx < this.cellCount; idx ++)
        {
            allCells[idx] = this.cells[idx];
        }
        return allCells;
    }

    public boolean equals(Grid other)
    {
        int numCellsInThis = this.getCellCount();
        int numCellsInOther = other.getCellCount();

        if (numCellsInThis == numCellsInOther)
        {
            for (int i = 0; i < numCellsInThis; i ++)
            {
                Cell thisCell = this.getAllCells()[i];
                Cell otherCell = other.getAllCells()[i];

                if (!thisCell.equals(otherCell))
                {
                    return false;
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}