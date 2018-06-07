# AffiliTest-Java
AffiliTest's API implemented in Java


## Installation

The API is provided via a JAR file.

Download [here](release/AffiliTest-API.jar)

* Clone this repo with `git clone https://github.com/tsirolnik/AffiliTest-Java.git`
* Open the project, under the AffiliTest folder with Netbeans IDE
* Run or use it as your wish

Note: We use Google's Gson library (We used 2.6.2). We provide it but it can be found [here](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/gson-2.6.2.jar).

## Examples


### Testing
[Example Code](AffiliTest-API/test/examples/ExampleTesting.java)

### App Info
[Example Code](AffiliTest-API/test/examples/ExampleAppInfo.java)

### Calls Left
[Example Code](AffiliTest-API/test/examples/ExampleCallsLeft.java)

### Retrieving HTTP Status codes
  In order to view the redirections' status codes, a query string is needed to be appended to the endpoints.

  Open the [API.php](src/Endpoints.java) file and append `?codes` to the desired endpoint.

  Currently, only `/test` and `/compareToPreview` are the ones which support this feature.

  ```
  ...
  POST https://affilitest.com/api/v1/test?codes
  ...
  HTTP 200 OK
  ...
  {
    error: null,
    meta: {
      codes : [200, 301, 302]
    },
    data: ["example.com", "redirection.com", "destination.com"]
  }

  ```