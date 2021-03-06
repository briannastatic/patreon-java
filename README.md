# patreon-java
Interact with the Patreon v2 API via OAuth.

Get the artifact from [Maven](http://repo.songoda.com/repository/public/)
```xml
<dependency>
    <groupId>com.patreon</groupId>
    <artifactId>patreon</artifactId>
    <version>1.0.2</version>
</dependency>
```


Step 1. Get your client_id and client_secret
---
Visit the [Patreon Platform Page](https://www.patreon.com/platform)
while logged in as a Patreon creator to register your client.

This will provide you with a `client_id` and a `client_secret`.

Step 2. Use this library
---

## For the Log In with Patreon flow
```java
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.PatreonAPI;
import com.patreon.PatreonOAuth;
import com.patreon.PatreonOAuth;
import com.patreon.resources.User;
import com.patreon.resources.Member;

    ...

String clientId = null; // Get this when you set up your client
String clientSecret = null; // Get this when you set up your client
String redirectUri = null; // Provide this to set up your client

String code = null; // Get this from the query parameter `code`

PatreonOAuth oauthClient = new PatreonOAuth(clientId, clientSecret, redirectUri);
PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code);
//Store the refresh TokensResponse in your data store
String accessToken = tokens.getAccessToken();

PatreonAPI apiClient = new PatreonAPI(accessToken);
JSONAPIDocument<User> userResponse = apiClient.fetchCurrentUser();
User user = userResponse.get();
Log.i(user.getFullName());
List<Member> memberships = user.getMemberships();
if (memberships != null && memberships.size() > 0) {
    Member membership = memberships.get(0);
    Log.i(membership.getPatronStatus());
}
// You should save the user's PatreonOAuth.TokensResponse in your database
// (for refreshing their Patreon data whenever you like),
// along with any relevant user info or pledge info you want to store.
```
