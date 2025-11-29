package projects.maze;

public class Cell
{
    private final Coords coords;
    private Coords[] neighbors;
    private boolean explored;
    private CellStatus status;

    public Cell(
        Coords coords,
        CellStatus status
    ) {
        if (coords != null
        &&  status != null)
        {
            this.coords = coords;
            this.neighbors = new Coords[0];
            this.explored = false;
            this.status = status;
        }
        else if (coords == null)
        {
            throw new IllegalArgumentException("New Cell's coords value must not be null.");
        }
        else
        {
            throw new IllegalArgumentException("New Cell's status value must not be null.");
        }
    }

    public Coords getCoords()
    {
        return this.coords;
    }

    public Coords[] getNeighbors()
    {
        return this.neighbors;
    }

    public boolean isExplored()
    {
        return this.explored;
    }

    public CellStatus getStatus()
    {
        return this.status;
    }

    public void setNeighbors(Coords[] neighbors)
    {
        if (neighbors != null)
        {
            this.neighbors = neighbors;
        }
        else
        {
            throw new IllegalArgumentException("Cell.setNeighbors() cannot use a null value.");
        }
    }

    public void setExplored(boolean explored)
    {
        this.explored = explored;
    }

    public void setStatus(CellStatus status)
    {
        this.status = status;
    }

    public boolean equals(Cell other)
    {
        int numNeighborsInThis = this.getNeighbors().length;
        int numNeighborsInOther = other.getNeighbors().length;
        
        if (numNeighborsInThis == numNeighborsInOther)
        {
            for (int i = 0; i < numNeighborsInThis; i ++)
            {
                Coords thisNeighbor = this.getNeighbors()[i];
                Coords otherNeighbor = other.getNeighbors()[i];

                if (!thisNeighbor.equals(otherNeighbor))
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
        return (
            this.getCoords().equals(other.getCoords())
        &&  this.getStatus() == other.getStatus()
        );
    }
}