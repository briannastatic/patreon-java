package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Type("address")
public class Address extends BaseResource {

  public enum MemberField implements Field {
    Addressee("addressee", true),
    Line1("line_1", true),
    Line2("line_2", true),
    PostalCode("postal_code", true),
    City("city", true),
    State("state", true),
    County("country", true),
    PhoneNumber("phone_number", true),
    CreatedAt("created_at", true),
    ;

    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;

    MemberField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    @Override
    public String getPropertyName() {
      return this.propertyName;
    }

    @Override
    public boolean isDefault() {
      return this.isDefault;
    }

    public static Collection<MemberField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

  }

  private String addressee;
  private String line1;
  private String line2;
  private String postalCode;
  private String city;
  private String state;
  private String county;
  private String phoneNumber;
  private String createdAt;

  @Relationship("user")
  private User user;

  @Relationship("campaigns")
  private List<Campaign> campaigns;

  @JsonCreator
  public Address(
    @JsonProperty("addressee") String addressee,
    @JsonProperty("line_1") String line1,
    @JsonProperty("line_2") String line2,
    @JsonProperty("postal_code") String postalCode,
    @JsonProperty("city") String city,
    @JsonProperty("state") String state,
    @JsonProperty("county") String county,
    @JsonProperty("phone_number") String phoneNumber,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("user") User user,
    @JsonProperty("campaigns") List<Campaign> campaigns
  ) {
    this.addressee = addressee;
    this.line1 = line1;
    this.line2 = line2;
    this.postalCode = postalCode;
    this.city = city;
    this.state = state;
    this.county = county;
    this.phoneNumber = phoneNumber;
    this.createdAt = createdAt;
    this.user = user;
    this.campaigns = campaigns;
  }

  public String getAddressee() {
    return addressee;
  }

  public String getLine1() {
    return line1;
  }

  public String getLine2() {
    return line2;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCounty() {
    return county;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public User getUser() {
    return user;
  }

  public List<Campaign> getCampaigns() {
    return campaigns;
  }
}
