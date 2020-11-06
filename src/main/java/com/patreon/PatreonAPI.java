package com.patreon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.patreon.resources.Address;
import com.patreon.resources.Campaign;
import com.patreon.resources.Member;
import com.patreon.resources.RequestUtil;
import com.patreon.resources.Tier;
import com.patreon.resources.User;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatreonAPI {
  /**
   * The base URI for requests to the patreon API. This may be overridden (e.g. for testing) by passing
   * -Dpatreon.rest.uri="https://my.other.server.com" as jvm arguments
   */
  public static final String BASE_URI = System.getProperty("patreon.rest.uri", "https://www.patreon.com");

  private static final Logger LOG = LoggerFactory.getLogger(PatreonAPI.class);
  private final String accessToken;
  private final RequestUtil requestUtil;
  private ResourceConverter converter;

  /**
   * Create a new instance of the Patreon API. You only need <b>one</b> of these objects unless you are using the API
   * with multiple tokens
   *
   * @param accessToken The "Creator's Access Token" found on
   *                    <a href="https://www.patreon.com/platform/documentation/clients">the patreon client page</a>
   *                    <b>OR</b> OAuth access token
   */
  public PatreonAPI(String accessToken) {
    this(accessToken, new RequestUtil());
  }

  /**
   * For use in test.
   */
  PatreonAPI(String accessToken, RequestUtil requestUtil) {
    this.accessToken = accessToken;
    this.requestUtil = requestUtil;

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.converter = new ResourceConverter(
      objectMapper,
      User.class,
      Campaign.class,
      Member.class,
      Tier.class
    );
    this.converter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
  }

  public JSONAPIDocument<User> fetchCurrentUser() throws IOException {
    URIBuilder pathBuilder = new URIBuilder().setPath("identity");

    addFieldsParam(pathBuilder, User.class, new HashSet<>(User.UserField.getDefaultFields()));

    return converter.readDocument(
      getDataStream(pathBuilder.toString()),
      User.class
    );
  }

  public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
    URIBuilder pathBuilder = new URIBuilder()
      .setPath("campaigns")
      .addParameter("include", "tiers,creator,benefits,goals");

    addFieldsParam(pathBuilder, Campaign.class, new HashSet<>(Campaign.CampaignField.getDefaultFields()));
    addFieldsParam(pathBuilder, Tier.class, new HashSet<>(Tier.TierField.getDefaultFields()));

    return converter.readDocumentCollection(
      getDataStream(pathBuilder.toString()),
      Campaign.class
    );
  }

  public List<Member> fetchAllMembers(String campaignId) throws IOException {
    Set<Member> members = new HashSet<>();
    String cursor = null;
    while (true) {
      JSONAPIDocument<List<Member>> pledgesPage = fetchPageOfMembers(campaignId, 15, cursor);
      members.addAll(pledgesPage.get());
      cursor = getNextCursorFromDocument(pledgesPage);
      if (cursor == null) {
        break;
      }
    }
    return new ArrayList<>(members);
  }

  public JSONAPIDocument<List<Member>> fetchPageOfMembers(String campaignId, int pageSize, String pageCursor) throws IOException {
    URIBuilder pathBuilder = new URIBuilder()
      .setPath(String.format("campaigns/%s/members", campaignId))
      .addParameter("include", "currently_entitled_tiers,address,campaign,user")
      .addParameter("page[count]", String.valueOf(pageSize));
    if (pageCursor != null) {
      pathBuilder.addParameter("page[cursor]", pageCursor);
    }
    addFieldsParam(pathBuilder, Member.class, new HashSet<>(Member.MemberField.getDefaultFields()));
    addFieldsParam(pathBuilder, User.class, new HashSet<>(User.UserField.getDefaultFields()));
    addFieldsParam(pathBuilder, Tier.class, new HashSet<>(Tier.TierField.getDefaultFields()));
    addFieldsParam(pathBuilder, Address.class, new HashSet<>(Address.AddressField.getDefaultFields()));

    return converter.readDocumentCollection(
      getDataStream(pathBuilder.toString()),
      Member.class
    );
  }

  public String getNextCursorFromDocument(JSONAPIDocument document) {
    Links links = document.getLinks();
    if (links == null) {
      return null;
    }
    Link nextLink = links.getNext();
    if (nextLink == null) {
      return null;
    }
    String nextLinkString = nextLink.toString();
    try {
      List<NameValuePair> queryParameters = URLEncodedUtils.parse(new URI(nextLinkString), "utf8");
      for (NameValuePair pair : queryParameters) {
        String name = pair.getName();
        if (name.equals("page[cursor]")) {
          return pair.getValue();
        }
      }
    } catch (URISyntaxException e) {
      LOG.error(e.getMessage());
    }
    return null;
  }

  private InputStream getDataStream(String suffix) throws IOException {
    return this.requestUtil.request(suffix, this.accessToken);
  }

  /**
   * Add fields[type]=fieldName,fieldName,fieldName as a query parameter to the request represented by builder
   *
   * @param builder A URIBuilder building a request to the API
   * @param type    A BaseResource annotated with {@link com.github.jasminb.jsonapi.annotations.Type}
   * @param fields  A list of fields to include.  Only fields in this list will be retrieved in the query
   * @return builder
   */
  private URIBuilder addFieldsParam(URIBuilder builder, Class<? extends BaseResource> type, Collection<? extends Field> fields) {
    List<String> fieldNames = new ArrayList<>();
    for (Field f : fields) {
      fieldNames.add(f.getPropertyName());
    }
    String typeStr = BaseResource.getType(type);
    builder.addParameter("fields[" + typeStr + "]", String.join(",", fieldNames));

    return builder;
  }


  /**
   * For debug.
   *
   * @param inputStream
   * @return
   * @throws IOException
   */
  public static String readString(InputStream inputStream) throws IOException {

    ByteArrayOutputStream into = new ByteArrayOutputStream();
    byte[] buf = new byte[4096];
    for (int n; 0 < (n = inputStream.read(buf)); ) {
      into.write(buf, 0, n);
    }
    into.close();
    return new String(into.toByteArray(), "UTF-8");
  }

}
