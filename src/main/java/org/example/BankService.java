package org.example;

import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final ClientStorage clientStorage;

    public BankService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public Client register(double balance) {
        return clientStorage.registerClient(balance);
    }

    public TransactionResult makeTransfer(int clientId, double amount) {
        Client client = clientStorage.getClientById(clientId);
        if (client == null) {
            return new TransactionResult(TransactionStatus.DECLINED, 0.0);
        }
        if(client.getBalance() < amount) {
            return new TransactionResult(TransactionStatus.DECLINED, client.getBalance());
        }

        double newBalance = client.getBalance() - amount;
        client.setBalance(newBalance);
        return new TransactionResult(TransactionStatus.ACCEPTED, newBalance);
    }

    public TransactionResult deposit(int clientId,double amount) {
        Client client = clientStorage.getClientById(clientId);

        if (client == null) {
            return new TransactionResult(TransactionStatus.DECLINED, 0.0);
        }
        if (amount < 0) {
            return new TransactionResult(TransactionStatus.DECLINED, client.getBalance());
        }
        double newBalance = client.getBalance() + amount;
        client.setBalance(newBalance);
        return new TransactionResult(TransactionStatus.ACCEPTED, newBalance);
    }


}
