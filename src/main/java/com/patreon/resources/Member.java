package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;
import com.patreon.resources.shared.SocialConnections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Type("member")
public class Member extends BaseResource {

  public enum MemberField implements Field {
    PatronStatus("patron_status", true),
    IsFollower("is_follower", true),
    FullName("full_name", true),
    Email("email", true),
    PledgeRelationshipStart("pledge_relationship-start", true),
    LifetimeSupportCents("lifetime_support_cents", true),
    CampaignLifetimeSupportCents("campaign_lifetime_support_cents", true),
    CurrentlyEntitledAmountCents("currently_entitled_amount_cents", true),
    LastChargeDate("last_charge_date", true),
    LastChargeStatus("last_charge_status", true),
    Note("note", true),
    WillPayAmountCents("will_pay_amount_cents", true),
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

  private String patronStatus;
  private boolean isFollower;
  private String fullName;
  private String email;
  private String pledgeRelationshipStart;
  private int lifetimeSupportCents;
  private int campaignLifetimeSupportCents;
  private int currentlyEntitledAmountCents;
  private String lastChargeDate;
  private String lastChargeStatus;
  private String note;
  private int willPayAmountCents;

  @Relationship("address")
  private Address address;

  @Relationship("campaign")
  private Campaign campaign;

  @Relationship("currently_entitled_tiers")
  private List<Tier> currentlyEntitledTiers;

  @Relationship("user")
  private User user;

  @Relationship("pledge_history")
  private List<PledgeEvent> pledgeHistory;

  @JsonCreator
  public Member(
    @JsonProperty("parton_status") String patronStatus,
    @JsonProperty("is_follower") boolean isFollower,
    @JsonProperty("full_name") String fullName,
    @JsonProperty("email") String email,
    @JsonProperty("pledge_relationship_start") String pledgeRelationshipStart,
    @JsonProperty("lifetime_support_cents") int lifetimeSupportCents,
    @JsonProperty("campaign_lifetime_support_cents") int campaignLifetimeSupportCents,
    @JsonProperty("currently_entitled_amount_cents") int currentlyEntitledAmountCents,
    @JsonProperty("last_charge_date") String lastChargeDate,
    @JsonProperty("last_charge_status") String lastChargeStatus,
    @JsonProperty("note") String note,
    @JsonProperty("will_pay_amount_cents") int willPayAmountCents,
    @JsonProperty("address") Address address,
    @JsonProperty("campaign") Campaign campaign,
    @JsonProperty("currently_entitled_tiers") List<Tier> currentlyEntitledTiers,
    @JsonProperty("user") User user,
    @JsonProperty("pledge_history") List<PledgeEvent> pledgeHistory
  ) {
    this.patronStatus = patronStatus;
    this.isFollower = isFollower;
    this.fullName = fullName;
    this.email = email;
    this.pledgeRelationshipStart = pledgeRelationshipStart;
    this.lifetimeSupportCents = lifetimeSupportCents;
    this.campaignLifetimeSupportCents = campaignLifetimeSupportCents;
    this.currentlyEntitledAmountCents = currentlyEntitledAmountCents;
    this.lastChargeDate = lastChargeDate;
    this.lastChargeStatus = lastChargeStatus;
    this.note = note;
    this.willPayAmountCents = willPayAmountCents;
    this.address = address;
    this.campaign = campaign;
    this.currentlyEntitledTiers = currentlyEntitledTiers;
    this.user = user;
    this.pledgeHistory = pledgeHistory;
  }

  public String getPatronStatus() {
    return patronStatus;
  }

  public boolean isFollower() {
    return isFollower;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public String getPledgeRelationshipStart() {
    return pledgeRelationshipStart;
  }

  public int getLifetimeSupportCents() {
    return lifetimeSupportCents;
  }

  public int getCampaignLifetimeSupportCents() {
    return campaignLifetimeSupportCents;
  }

  public int getCurrentlyEntitledAmountCents() {
    return currentlyEntitledAmountCents;
  }

  public String getLastChargeDate() {
    return lastChargeDate;
  }

  public String getLastChargeStatus() {
    return lastChargeStatus;
  }

  public String getNote() {
    return note;
  }

  public int getWillPayAmountCents() {
    return willPayAmountCents;
  }

  public Address getAddress() {
    return address;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public List<Tier> getCurrentlyEntitledTiers() {
    return currentlyEntitledTiers;
  }

  public User getUser() {
    return user;
  }

  public List<PledgeEvent> getPledgeHistory() {
    return pledgeHistory;
  }
}
