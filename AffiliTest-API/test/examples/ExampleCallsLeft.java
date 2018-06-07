package examples;

import affilitest.AffiliTestAPI;

public class ExampleCallsLeft {
     public static void main(String[] args) {
        AffiliTestAPI api = new AffiliTestAPI();
        // Another option is to use the API key, which is the preferable way
        // AffiliTestAPI api = new AffiliTestAPI(<api_key>);


        try {
             System.out.println(api.login("mail@example.com", "password"));

            System.out.println(api.callsLeft());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
