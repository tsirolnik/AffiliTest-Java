/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import affilitest.AffiliTestAPI;

/**
 *
 * @author debian
 */
public class ExampleAppInfo {

    public static void main(String[] args) {
        AffiliTestAPI api = new AffiliTestAPI();
        // Another option is to use the API key, which is the preferable way
        // AffiliTestAPI api = new AffiliTestAPI(<api_key>);

        try {
             System.out.println(api.login("mail@example.com", "password"));

            // Perform a URL based appInfo request
            System.out.println(api.appInfo(
                    "https://play.google.com/store/apps/details?id=com.whatsapp&hl=en",
                    "",
                    ""
            ));

            // Perform a package based appInfo request
            System.out.println(api.appInfo(
                    "",
                    "com.alibaba.aliexpresshd",
                    ""
            ));


            // Perform a package based iTunes appInfo request
            // The format of the package is just the ID itself in numeric form
            // Note that country is needed in that case
            System.out.println(api.appInfo(
                    "",
                    "529479190",
                    "us"
            ));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
