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

@Type("benefit")
public class Benefit extends BaseResource {

  public enum BenefitField implements Field {
    Title("title", true),
    Description("description", true),
    BenefitType("benefit_type", true),
    RuleType("rule_type", true),
    CreatedAt("created_at", true),
    DeliveredDeliverablesCount("delivered_deliverables_count", true),
    NotDeliveredDeliverablesCount("not_delivered_deliverables_count", true),
    DeliverablesDueTodayCount("deliverables_due_today_count", true),
    NextDeliverableDueDate("next_deliverable_due_date", true),
    TiersCount("tiers_count", true),
    IsDeleted("is_deleted", true),
    IsPublished("is_published", true),
    IsEnded("is_ended", true),
    AppExternalId("app_external_id", true),
    AppMeta("app_meta", true),
    ;

    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;

    BenefitField(String propertyName, boolean isDefault) {
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

    public static Collection<BenefitField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

  }

  private String title;
  private String description;
  private String benefitType;
  private String ruleType;
  private String createdAt;
  private int deliveredDeliverablesCount;
  private int notDeliveredDeliverablesCount;
  private int deliverablesDueTodayCount;
  private String nextDeliverableDueDate;
  private int tiersCount;
  private boolean isDeleted;
  private boolean isPublished;
  private boolean isEnded;
  private String appExternalId;
  private Object appMeta;

  @Relationship("tiers")
  private List<Tier> tiers;

  @Relationship("deliverables")
  private List<Deliverable> deliverables;

  @Relationship("campaign")
  private Campaign campaign;

  @JsonCreator
  public Benefit(
    @JsonProperty("title") String title,
    @JsonProperty("description") String description,
    @JsonProperty("benefit_type") String benefitType,
    @JsonProperty("rule_type") String ruleType,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("delivered_deliverables_count") int deliveredDeliverablesCount,
    @JsonProperty("not_delivered_deliverables_count") int notDeliveredDeliverablesCount,
    @JsonProperty("deliverables_due_today_count") int deliverablesDueTodayCount,
    @JsonProperty("next_deliverable_due_date") String nextDeliverableDueDate,
    @JsonProperty("tiers_count") int tiersCount,
    @JsonProperty("is_deleted") boolean isDeleted,
    @JsonProperty("is_published") boolean isPublished,
    @JsonProperty("is_ended") boolean isEnded,
    @JsonProperty("app_external_id") String appExternalId,
    @JsonProperty("app_meta") Object appMeta,
    @JsonProperty("tiers") List<Tier> tiers,
    @JsonProperty("deliverables") List<Deliverable> deliverables,
    @JsonProperty("campaign") Campaign campaign
  ) {
    this.title = title;
    this.description = description;
    this.benefitType = benefitType;
    this.ruleType = ruleType;
    this.createdAt = createdAt;
    this.deliveredDeliverablesCount = deliveredDeliverablesCount;
    this.notDeliveredDeliverablesCount = notDeliveredDeliverablesCount;
    this.deliverablesDueTodayCount = deliverablesDueTodayCount;
    this.nextDeliverableDueDate = nextDeliverableDueDate;
    this.tiersCount = tiersCount;
    this.isDeleted = isDeleted;
    this.isPublished = isPublished;
    this.isEnded = isEnded;
    this.appExternalId = appExternalId;
    this.appMeta = appMeta;
    this.tiers = tiers;
    this.deliverables = deliverables;
    this.campaign = campaign;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getBenefitType() {
    return benefitType;
  }

  public String getRuleType() {
    return ruleType;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public int getDeliveredDeliverablesCount() {
    return deliveredDeliverablesCount;
  }

  public int getNotDeliveredDeliverablesCount() {
    return notDeliveredDeliverablesCount;
  }

  public int getDeliverablesDueTodayCount() {
    return deliverablesDueTodayCount;
  }

  public String getNextDeliverableDueDate() {
    return nextDeliverableDueDate;
  }

  public int getTiersCount() {
    return tiersCount;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public boolean isPublished() {
    return isPublished;
  }

  public boolean isEnded() {
    return isEnded;
  }

  public String getAppExternalId() {
    return appExternalId;
  }

  public Object getAppMeta() {
    return appMeta;
  }

  public List<Tier> getTiers() {
    return tiers;
  }

  public List<Deliverable> getDeliverables() {
    return deliverables;
  }

  public Campaign getCampaign() {
    return campaign;
  }
}
