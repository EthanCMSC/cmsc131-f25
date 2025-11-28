package projects.maze;

public class Grid
{
    private final Cell[] cells;
    private int cellCount;

    public Grid(int maxCells)
    {
        this.cells = new Cell[maxCells];
        this.cellCount = 0;
    }

    public boolean insertCell(Cell cell)
    {
        if (this.cellCount < this.cells.length)
        {
            this.cells[this.cellCount ++] = cell;
            return true;
        }
        return false;
    }

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

    public Cell[] getAllCells()
    {
        Cell[] allCells = new Cell[this.cellCount];
        for (int idx = 0; idx < this.cellCount; idx ++)
        {
            allCells[idx] = this.cells[idx];
        }
        return allCells;
    }
}