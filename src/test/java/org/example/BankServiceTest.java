package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private ClientStorage clientStorage;

    @InjectMocks
    private BankService bankService;

    @Test
    void testAkceptujePrzelewSrodkiWystarczajace() {
        Client client = new Client(1, 1000.0);
        when(clientStorage.getClientById(1)).thenReturn(client);

        TransactionResult result = bankService.makeTransfer(1, 500.0);

        assertEquals(TransactionStatus.ACCEPTED, result.getStatus());
        assertEquals(500.0, result.getNewBalance());
    }

    @Test
    void testOdrzucaSrodkiNieSaWystarczajace() {
        Client client = new Client(1, 100.0);
        when(clientStorage.getClientById(1)).thenReturn(client);

        TransactionResult result = bankService.makeTransfer(1, 500.0);

        assertEquals(TransactionStatus.DECLINED, result.getStatus());
        assertEquals(100.0, result.getNewBalance());
    }

}
