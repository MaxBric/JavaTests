package fr.imie.mock;


import fr.imie.IConnector;
import fr.imie.enums.Messages;

public class Bank implements IConnector {
    @Override
    public Messages placeOrder(Long volume, String product) {
        return null;
    }

    @Override
    public Messages closeOrder(Long volume, String product) {
        return null;
    }
}
