# AffiliTest-Java
AffiliTest's API implemented in Java


## Installation
We provide this API as is, not in a form of a JAR file.

You will need to download Google's Gson library. It can be found [here](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/).

## Examples


### Testing
[Example Code](src/ExampleTesting.java)

### App Info
[Example Code](src/ExampleAppInfo.java)

### Calls Left
[Example Code](src/ExampleCallsLeft.java)

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