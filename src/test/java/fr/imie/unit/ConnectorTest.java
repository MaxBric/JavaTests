package fr.imie.unit;

import fr.imie.Connector;
import fr.imie.Exceptions.InvalidVolumeException;
import fr.imie.enums.Messages;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConnectorTest {

    /**
     * Place order on the market
     * Should return an ACCEPT Messages
     */
    @Test
    public void testPlaceOrderACCEPT_Nominal() {
        Connector connector = new Connector();

        Long volume = 1L;

        Messages messages = connector.placeOrder(volume, "EURUSD");

        Assert.assertEquals(messages, Messages.ACCEPT);
    }

    /**
     * Try to place an order on the market
     * Should return an REJECT Messages
     */
    @Test
    public void testPlaceOrderREJECT() {
        Connector connector = new Connector();

        Long volume = 0L;

        Messages messages = connector.placeOrder(volume, "EURUSD");

        Assert.assertEquals(messages, Messages.REJECT);
    }

    @Test
    public void testPlaceOrderACCEPT_Increment_Volume() throws NoSuchFieldException, IllegalAccessException {
        Connector connector = new Connector();

        Field field = connector.getClass().getDeclaredField("volumeTotal");
        field.setAccessible(true);

        Long volume = 1L;

        connector.placeOrder(volume, "EURUSD");

        Assert.assertEquals(1L, field.get(connector));

        connector.placeOrder(volume, "EURUSD");

        Assert.assertEquals(2L, field.get(connector));
    }

    @Test
    public void testCloseOrderACCEPT_Nominal() throws InvalidVolumeException {
        Connector connector = new Connector();

        Long volume = 1L;

        connector.placeOrder(volume, "EURUSD");

        Messages messages = connector.closeOrder(volume, "EURUSD");

        Assert.assertEquals(Messages.ACCEPT, messages);
    }

    @Test
    public void testCloseOrderREJECT() throws InvalidVolumeException {
        Connector connector = new Connector();

        connector.placeOrder(1L, "EURUSD");

        Long volume = 0L;

        Messages messages = connector.closeOrder(volume, "EURUSD");

        Assert.assertEquals(Messages.REJECT, messages);
    }

    @Test
    public void testGetCheckVolume() throws Exception {
        Connector connector = new Connector();

        Method field = connector.getClass().getDeclaredMethod("getCheckVolume", Long.class);
        field.setAccessible(true);

        Messages value = (Messages) field.invoke(connector, 0L);

        Assert.assertEquals(Messages.REJECT, value);
    }

    @Test(expected = InvalidVolumeException.class)
    public void testCloseOrderREJECT_InvalidVolumeException() throws InvalidVolumeException {
        Connector connector = new Connector();

        connector.placeOrder(1L, "EURUSD");

        Long volume = 2L;

        Messages messages = connector.closeOrder(volume, "EURUSD");
    }
}
