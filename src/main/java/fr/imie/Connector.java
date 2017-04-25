package fr.imie;

import fr.imie.Exceptions.InvalidVolumeException;
import fr.imie.enums.Messages;

import java.util.Locale;

public class Connector implements IConnector {
    private Long volumeTotal = 0L;

    public Messages placeOrder(Long volume, String product) {
        Messages msg = getCheckVolume(volume);

        if(msg.equals(Messages.ACCEPT)) {
            volumeTotal += volume;
        }

        return msg;
    }

    public Messages closeOrder(Long volume, String product) throws InvalidVolumeException {
        if(volumeTotal < volume) {
            throw new InvalidVolumeException();
        }

        return getCheckVolume(volume);
    }

    private Messages getCheckVolume(Long volume) {
        if(volume == 0) {
            return Messages.REJECT;
        }

        return Messages.ACCEPT;
    }
}
