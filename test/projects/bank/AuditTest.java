package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import lib.Utils;

public class AuditTest
{
    private Audit audit;
    private String auditFileName;

    @BeforeEach
    void setup()
    {
        auditFileName = "test/projects/bank/audittest.log";

        try
        {
            audit = new Audit(auditFileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testRecordNSA()
    {
        auditFileName = "test/projects/bank/audittest.log";

        try
        {
            audit = new Audit(auditFileName);
            
            Transaction trs = new Withdrawal("id0", 1.00);
            
            audit.recordNSA(trs);
            audit.close();
            
            try
            {
                Scanner scanner = new Scanner(new File(auditFileName));
                assertTrue(scanner.hasNextLine());
                String auditLog = scanner.nextLine();
                scanner.close();
        
                assertTrue(
                    auditLog.contains(
                        String.format(
                            " ERROR %s failed; account %s could not be found.",
                            trs.toString(),
                            trs.getAccountID()
                        )
                ));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testRecordNSF()
    {
        auditFileName = "test/projects/bank/audittest.log";

        try
        {
            audit = new Audit(auditFileName);
            
            Transaction trs = new Withdrawal("id0", 1.00);
            Account acct = new CheckingAccount("id0", "Test Account", 0.00);
            
            audit.recordNSF(trs, acct);
            audit.close();
            
            try
            {
                Scanner scanner = new Scanner(new File(auditFileName));
                assertTrue(scanner.hasNextLine());
                String auditLog = scanner.nextLine();
                scanner.close();
        
                assertTrue(
                    auditLog.contains(
                        String.format(
                            "%s ERROR %s failed; account %s balance of $%.2f is insufficient.",
                            Utils.timestamp(),
                            trs.toString(),
                            trs.getAccountID(),
                            acct.getBalance()
                        )
                    )
                );
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testRecordSuccess()
    {
        auditFileName = "test/projects/bank/audittest.log";

        try
        {
            audit = new Audit(auditFileName);
            
            Transaction trs = new Withdrawal("id0", 1.00);
            Account acct = new CheckingAccount("id0", "Test Account", 0.00);
            
            audit.recordSuccess(trs, acct);
            audit.close();
            
            try
            {
                Scanner scanner = new Scanner(new File(auditFileName));
                assertTrue(scanner.hasNextLine());
                String auditLog = scanner.nextLine();
                scanner.close();
        
                assertTrue(
                    auditLog.contains(
                        String.format(
                            "%s INFO %s was successfully executed.",
                            Utils.timestamp(),
                            trs.toString()
                        )
                    )
                );
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}