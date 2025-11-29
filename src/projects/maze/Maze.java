package projects.maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Maze
{
    private final Grid grid;

    public Maze(int maxCells)
    {
        this.grid = new Grid(maxCells);
    }

    public Grid getGrid()
    {
        return this.grid;
    }

    public Cell getStart()
    {
        return this.getFirstCellWithStatus(CellStatus.S);
    }

    public Cell getEnd()
    {
        return this.getFirstCellWithStatus(CellStatus.E);
    }

    public boolean insertCell(Cell cell)
    {
        return this.grid.insertCell(cell);
    }

    private Cell getFirstCellWithStatus(CellStatus status)
    {
        for (Cell cell : this.grid.getAllCells())
        {
            if (cell.getStatus() == status)
            {
                return cell;
            }
        }
        return null;
    }

    public void discoverAndSetupNeighbors()
    {
        for (Cell cell : this.grid.getAllCells())
        {
            Coords[] neighbors = {
                new Coords(cell.getCoords().getRow() - 1, cell.getCoords().getCol()),
                new Coords(cell.getCoords().getRow() + 1, cell.getCoords().getCol()),
                new Coords(cell.getCoords().getRow(), cell.getCoords().getCol() - 1),
                new Coords(cell.getCoords().getRow(), cell.getCoords().getCol() + 1)
            };
            cell.setNeighbors(neighbors);
        }
    }

    /**
     * Provided by Dusel. Assumes grid cell has a {@code getStatus()} method.
     * @param filename - Output filename.
     */
    public void serialize(String filename)
    {
        Cell[] cells = this.grid.getAllCells();

        FileWriter writer;
        try
        {
            writer = new FileWriter(new File(filename));
            // discover dimension of maze's underlying grid
            int maxRow = 0, maxCol = 0;
            int idxCell;
            for (idxCell = 0; idxCell < cells.length; idxCell ++)
            {
                int row = cells[idxCell].getCoords().getRow();
                int col = cells[idxCell].getCoords().getCol();
                if (row > maxRow)
                {
                    maxRow = row;
                }
                if (col > maxCol)
                {
                    maxCol = col;
                }
            }
    
            // write cell statuses, using 'X' for absent (inaccessible) cells
            idxCell = 0;
            for (int row = 0; row <= maxRow; row ++)
            {
                for (int col = 0; col <= maxCol; col ++)
                {
                    Cell maybeCell = this.grid.getCell(
                        new Coords(row, col)
                    );
                    if (maybeCell != null)
                    {
                        writer.write(maybeCell.getStatus().name());
                        idxCell ++;
                    }
                    else
                    {
                        writer.write('X');
                    }
                    writer.write(' ');
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }   
    }
}