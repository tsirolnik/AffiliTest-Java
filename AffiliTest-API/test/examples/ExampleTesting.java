package examples;

import affilitest.AffiliTestAPI;
import affilitest.Devices;

public class ExampleTesting {
       public static void main(String[] args) {
        AffiliTestAPI api = new AffiliTestAPI();
        // Another option is to use the API key, which is the preferable way
        // AffiliTestAPI api = new AffiliTestAPI(<api_key>);

        try {
             System.out.println(api.login("mail@example.com", "password"));

            // Perform a regular test
            System.out.println(api.test(
                    "https://play.google.com/store/apps/details?id=com.whatsapp&hl=en",
                    "us",
                    Devices.ANDROID
            ));

            // Perform a compare to preview test
            System.out.println(api.compareToPreview(
                    "https://itunes.apple.com/us/app/whatsapp-messenger/id310633997?mt=8",
                    "https://itunes.apple.com/us/app/whatsapp-messenger/id310633997?mt=8",
                    "us",
                    Devices.IPHONE
            ));

            api.logout();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
