package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientStorage {
    private final List<Client> clients = new ArrayList<>();
    private int idCounter = 1;

    public Client registerClient(double initialBalance) {
        Client client = new Client(idCounter, initialBalance);
        clients.add(client);
        idCounter++;
        return client;
    }

    public Client getClientById(int id) {
        for (Client client : clients) {
            if (client.getId() == id)
                return client;
        }
        return null;
    }

    public void clear() {
        clients.clear();
        idCounter = 1;
    }
}
