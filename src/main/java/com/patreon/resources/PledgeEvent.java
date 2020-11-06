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

@Type("pledge_event")
public class PledgeEvent extends BaseResource {

  public enum DeliverableField implements Field {
    Type("type", true),
    Date("date", true),
    PaymentStatus("payment_status", true),
    TierTitle("tier_title", true),
    TierId("tier_id", true),
    AmountCents("amount_cents", true),
    CurrencyCode("currency_code", true),
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

  private String type;
  private String date;
  private String paymentStatus;
  private String tierTitle;
  private String tierId;
  private int amountCents;
  private String currencyCode;

  @Relationship("patron")
  private User patron;

  @Relationship("campaign")
  private Campaign campaign;

  @Relationship("tier")
  private Tier tier;

  @JsonCreator
  public PledgeEvent(
    @JsonProperty("type") String type,
    @JsonProperty("date") String date,
    @JsonProperty("payment_status") String paymentStatus,
    @JsonProperty("tier_title") String tierTitle,
    @JsonProperty("tier_id") String tierId,
    @JsonProperty("amount_cents") int amountCents,
    @JsonProperty("currency_code") String currencyCode,
    @JsonProperty("patron") User patron,
    @JsonProperty("campaign") Campaign campaign,
    @JsonProperty("tier") Tier tier
  ) {
    this.type = type;
    this.date = date;
    this.paymentStatus = paymentStatus;
    this.tierTitle = tierTitle;
    this.tierId = tierId;
    this.amountCents = amountCents;
    this.currencyCode = currencyCode;
    this.patron = patron;
    this.campaign = campaign;
    this.tier = tier;
  }

  @Override
  public String getType() {
    return type;
  }

  public String getDate() {
    return date;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public String getTierTitle() {
    return tierTitle;
  }

  public String getTierId() {
    return tierId;
  }

  public int getAmountCents() {
    return amountCents;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public User getPatron() {
    return patron;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public Tier getTier() {
    return tier;
  }
}
