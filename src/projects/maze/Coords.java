package projects.maze;

public class Coords
{
    private final int row;
    private final int col;

    public Coords(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }

    public boolean equals(Coords other)
    {
        return (
            this.getRow() == other.getRow()
            && this.getCol() == other.getCol()
        );
    }
}