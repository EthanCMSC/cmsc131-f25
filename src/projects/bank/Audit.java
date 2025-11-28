package projects.bank;

import java.io.FileWriter;
import java.io.IOException;

import lib.Utils;

public class Audit
{
    private FileWriter writer;

    /**
     * {@code Audit} constructor
     * @param fileName - The name of the transactions file
     * @throws IOException if I/O error occurs
     */
    public Audit(
        String fileName
    )
    throws IOException
    {
        if (fileName != null)
        {
            this.writer = new FileWriter(fileName);
        }
        else
        {
            throw new IllegalArgumentException(
                "New Audit's fileName value must not be null."
            );
        }
    }

    /**
     * Records a No Such Account (NSA) feedback message to the specified log file
     * @param trs - Transaction to refer to in feedback message
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean recordNSA(Transaction trs)
    {
        String msg = String.format(
            "%s ERROR %s failed; account %s could not be found.\n",
            Utils.timestamp(),
            trs.toString(),
            trs.getAccountID()
        );
        return this.write(msg);
    }
    
    /**
     * Records a Non-Sufficient Funds (NSF) feedback message to the specified log file
     * @param trs - Transaction to refer to in feedback message
     * @param acct - Account to refer to in feedback message
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean recordNSF(Transaction trs, Account acct)
    {
        String msg = String.format(
            "%s ERROR %s failed; account %s balance of $%.2f is insufficient.\n",
            Utils.timestamp(),
            trs.toString(),
            trs.getAccountID(),
            acct.getBalance()
        );
        return this.write(msg);
    }
    
    /**
     * Records a successful transaction feedback message to the specified log file
     * @param trs - Transaction to refer to in feedback message
     * @param acct - Account to refer to in feedback message
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean recordSuccess(Transaction trs, Account acct)
    {
        String msg = String.format(
            "%s INFO %s was successfully executed.\n",
            Utils.timestamp(),
            trs.toString()
        );
        return this.write(msg);
    }

    /**
     * Writes a message to the log file that the {@code Audit} object points to
     * @param message - Message to write to log file
     * @return {@code true} if successful; {@code false} otherwise
     */
    private boolean write(String message)
    {
        try
        {
            writer.write(message);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Closes the {@code FileWriter} that the {@code Audit} was instantiated with
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean close()
    {
        try
        {
            writer.flush();
            writer.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}