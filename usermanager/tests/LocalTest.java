package usermanager.tests;

import java.io.IOException;

import modelo.Token;

import org.junit.BeforeClass;
import org.junit.Test;

import usermanager.UserManager;
import usermanager.bridge.IPersistanceBridge;
import usermanager.bridge.PersistanceBridge;
import usermanager.model.User;
import usermanager.util.Encoder;

public class LocalTest {

    static UserManager userManager;
    static User user;

    @BeforeClass
    public static void setUp() {
        userManager = UserManager.getInstance();
        user = userManager.getCurrentUser();
    }

    @Test
    public void testSuite() {
        assert (true);
    }

    @Test
    public void testUserExists() {
        assert (user != null);
    }

    @Test
    public void testPersistanceIntegration() {
        IPersistanceBridge persistance = new PersistanceBridge();

        int expected = user.getId();

        byte[] digest = null;
        try {
            digest = persistance.save(String.valueOf(user.getId()), "laptop", Encoder.toString(userManager));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Token recovered = persistance.recoverFromDigest(digest);
        String rawUserManager = recovered.getMessage();
        try {
            userManager = (UserManager) Encoder.fromString(rawUserManager);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int value = userManager.getCurrentUser().getId();

        assert (expected == value);
    }

    @Test
    public void testEncoder() {
        String message = "test-message";
        String encoded = null;
        try {
            encoded = Encoder.toString(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String decoded = null;
        try {
            decoded = (String) Encoder.fromString(encoded);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (message.equals(decoded));
    }

    @Test
    public void testPersistance() {
        IPersistanceBridge persistance = new PersistanceBridge();

        String userId = "user";
        String deviceId = "laptop";
        String message = "message";

        byte[] digest = null;
        try {
            digest = persistance.save("user", "laptop", Encoder.toString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Token recovered = persistance.recoverFromDigest(digest);

        assert (userId.equals(recovered.getUserId()));
        System.out.println("Test Persistance: expected: " + userId + ", value: " + recovered.getUserId());
        assert (deviceId.equals(recovered.getDeviceId()));
        System.out.println("Test Persistance: expected: " + deviceId + ", value: " + recovered.getDeviceId());

        String decodedMessage = null;
        try {
            decodedMessage = (String) Encoder.fromString(recovered.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (message.equals(decodedMessage));
        System.out.println("Test Persistance: expected: " + message + ", value: " + decodedMessage);
    }

}
