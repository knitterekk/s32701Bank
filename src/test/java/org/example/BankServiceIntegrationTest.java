package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BankServiceIntegrationTest {
    @Autowired
    private BankService bankService;

    @Test
    void powinienZapisywacZmianyPoTranzakcjach() {
        Client client = bankService.register(500);
        assertNotNull(client);
        int id = client.getId();

        TransactionResult deposit = bankService.deposit(id, 200);
        assertEquals(TransactionStatus.ACCEPTED, deposit.getStatus());
        assertEquals(700.0, deposit.getNewBalance());

        TransactionResult transfer = bankService.makeTransfer(id, 300.0);
        assertEquals(TransactionStatus.ACCEPTED, transfer.getStatus());
        assertEquals(400.0, transfer.getNewBalance());

        TransactionResult failTransfer = bankService.makeTransfer(id, 1000.0);
        assertEquals(TransactionStatus.DECLINED, failTransfer.getStatus());
    }
}
