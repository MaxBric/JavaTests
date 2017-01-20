package fr.imie;

import fr.imie.Exceptions.InvalidVolumeException;
import fr.imie.enums.Messages;

/**
 * Created by Nicolas MORICET on 19/01/2017.
 */
public interface IConnector {
    Messages placeOrder(Long volume, String product);
    Messages closeOrder(Long volume, String product) throws InvalidVolumeException;
}
