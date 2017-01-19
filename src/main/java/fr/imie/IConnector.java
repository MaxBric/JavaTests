package fr.imie;

public interface IConnector {
    void placeOrder(Long volume, String product);
    void closeOrder(Long volume, String product);
}
