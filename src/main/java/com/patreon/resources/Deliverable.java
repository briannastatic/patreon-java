package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Type("deliverable")
public class Deliverable extends BaseResource {

  public enum DeliverableField implements Field {
    CompletedAt("completed_at", true),
    DeliveryStatus("delivery_status", true),
    dueAt("due_at", true),
    ;

    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;

    DeliverableField(String propertyName, boolean isDefault) {
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

    public static Collection<DeliverableField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

  }

  private String completedAt;
  private String deliveryStatus;
  private String dueAt;

  @Relationship("campaign")
  private Campaign campaign;

  @Relationship("benefit")
  private Benefit benefit;

  @Relationship("member")
  private Member member;

  @Relationship("user")
  private User user;

  @JsonCreator
  public Deliverable(
    @JsonProperty("completed_at") String completedAt,
    @JsonProperty("delivery_status") String deliveryStatus,
    @JsonProperty("due_at") String dueAt,
    @JsonProperty("campaign") Campaign campaign,
    @JsonProperty("benefit") Benefit benefit,
    @JsonProperty("member") Member member,
    @JsonProperty("user") User user
  ) {
    this.completedAt = completedAt;
    this.deliveryStatus = deliveryStatus;
    this.dueAt = dueAt;
    this.campaign = campaign;
    this.benefit = benefit;
    this.member = member;
    this.user = user;
  }


  public String getCompletedAt() {
    return completedAt;
  }

  public String getDeliveryStatus() {
    return deliveryStatus;
  }

  public String getDueAt() {
    return dueAt;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public Benefit getBenefit() {
    return benefit;
  }

  public Member getMember() {
    return member;
  }

  public User getUser() {
    return user;
  }
}
