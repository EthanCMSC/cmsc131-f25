package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank
{
    // Instance variables
    private Account[] accounts;
    private int numberOfAccounts;
    private Audit audit;
    
    // Static variables
    private static int arrSizeIncrement = 100; // Increase size of accounts array by this value every overflow

    // Constructors
    /**
     * {@code Bank} constructor
     */
    public Bank()
    {
        this.accounts = new Account[100];
        this.numberOfAccounts = 0;
        try
        {
            this.audit = new Audit("src/projects/bank/audit.log");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Instance methods
    /**
     * Adds a new account to bank. New account's ID must be unique. Returns true if successful; returns false otherwise.
     * @param newAcct - The new Account to add
     * @return {@code true} if account was successfully added; {@code false} otherwise
     */
    public boolean add(Account newAcct)
    {
        // Check that passed account is not null
        if (newAcct != null)
        {
            // Check if account ID is unique
            if (this.find(newAcct.getID()) == -1)
            {
                // Copy accounts to larger array if overflow occurs
                if (this.numberOfAccounts >= this.accounts.length)
                {
                    Account[] temp = new Account[this.accounts.length + arrSizeIncrement];
                    for (int i = 0; i < this.accounts.length; i ++)
                    {
                        temp[i] = this.accounts[i];
                    }
                    this.accounts = temp;
                }
                // Add account to array
                accounts[numberOfAccounts] = newAcct;
                numberOfAccounts ++;
                return true;
            }
            else
            {
                // Return false if account ID is not unique
                return false;
            }
        }
        else
        {
            throw new IllegalArgumentException("New Account must not be null.");
        }
    }

    /**
     * Returns the index of a specified account by its ID if it exists.
     * @param acctID - The ID of the account to search for
     * @return Account's index in {@code accounts} array if it exists; {@code -1} otherwise
     */
    public int find(String acctID)
    {
        // Iterate through existing accounts
        for (int i = 0; i < this.numberOfAccounts; i ++)
        {
            // Return account if ID matches
            if (this.accounts[i] != null
            &&  this.accounts[i].getID().equals(acctID))
            {
                return i;
            }
        }
        // Return -1 if account was not found
        return -1;
    }

    /**
     * Returns the number of accounts in the bank
     * @return {@code int} equal to number of accounts in bank
     */
    public int getCount()
    {
        return this.numberOfAccounts;
    }

    /**
     * Load accounts into this Bank from a CSV file. 
     * 
     * Assumes each row follows the format savings,wz240833,Anna Gomez,8111.00
     * @param filename - Name of source CSV file.
     * @return {@code true} if and only if the operation is successful
     */
    public boolean loadAccounts(String filename)
    {
        File inputFile = new File(filename);
        Scanner scan;
        try
        {
            scan = new Scanner(inputFile);
            while (scan.hasNextLine())
            {
                String csvString = scan.nextLine();
                Account account = Account.make(csvString);
                add(account);
            }
            scan.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Write accounts in this Bank to CSV file.
     * @param filename - Name of destination CSV file.
     */
    public boolean writeAccounts(String filename)
    {
        File file = new File(filename);
        FileWriter writer;
        try
        {
            writer = new FileWriter(file);
            for (int i = 0; i < numberOfAccounts; i ++)
            {
                Account account = accounts[i];
                String accountCsv = account.toCSV();
                writer.write(accountCsv + System.lineSeparator());
            }
            writer.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Executes all transactions in transactions CSV file.
     * @param filename - The name of the CSV file holding saved transactions.
     * @return An {@code int} value equal to the number of transactions processed.
     */
    public int processTransactions(String filename)
    {
        int transactionsProcessed = 0;
        
        if (filename == null)
        {
            return -1;
        }

        try
        {
            File inputFile = new File(filename);
            Scanner scan = new Scanner(inputFile);
            while (scan.hasNextLine())
            {
                String csvString = scan.nextLine();
                Transaction trs = Transaction.make(csvString);
                Account acct = this.accounts[this.find(trs.getAccountID())];
                
                trs.execute(acct, this.audit);
                
                transactionsProcessed ++;
            }
            scan.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        this.audit.close();
        return transactionsProcessed;
    }

    /**
     * Compares two Bank objects; returns true if their data is the same
     * @param other - Bank to compare with
     * @return {@code true} if Banks' data match; {@code false} otherwise
     */
    public boolean equals(Bank other)
    {
        if (this.getCount() == other.getCount())
        {
            for (int i = 0; i < this.accounts.length; i ++)
            {
                if (this.accounts[i] != null
                &&  !this.accounts[i].equals(other.accounts[i]))
                {
                    // Return false if data does not match
                    return false;
                }
            }
            // Return true if data matches
            return true;
        }
        // Return false if data does not match
        return false;
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.accounts);
    }
}