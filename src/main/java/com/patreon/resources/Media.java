package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Type("tier")
public class Tier extends BaseResource {
  
  public enum TierField implements Field {
    AmountCents("amount_cents", true),
    CreatedAt("created_at", true),
    Description("description", true),
    Remaining("remaining", true),
    RequiresShipping("requires_shipping", true),
    Url("url", true),
    UserLimit("user_limit", true),
    EditedAt("edited_at", true),
    PatronCount("patron_count", true),
    PostCount("post_count", true),
    Published("published", true),
    PublishedAt("published_at", true),
    ImageUrl("image_url", true),
    DiscordRoleIds("discord_role_ids", true),
    Title("title", true),
    UnpublishedAt("unpublished_at", true),
    ;

    private final String propertyName;
    private final boolean isDefault;

    TierField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    public static Collection<TierField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

    @Override
    public String getPropertyName() {
      return this.propertyName;
    }

    @Override
    public boolean isDefault() {
      return this.isDefault;
    }
  }

  private int amountCents;
  private String createdAt;
  private String description;
  private float remaining;
  private boolean requiresShipping;
  private String url;
  private Integer userLimit;
  private String editedAt;
  private int patronCount;
  private int postCount;
  private boolean published;
  private String publishedAt;
  private String imageUrl;
  private List<String> discordRoleIds;
  private String title;
  private String unpublishedAt;

  @Relationship("campaign")
  private Campaign campaign;

  @Relationship("tier_image")
  private Media tierImage;

  @Relationship("benefits")
  private List<Benefit> benefits;

  public Tier(
                 @JsonProperty("amount_cents") int amountCents,
                 @JsonProperty("created_at") String createdAt,
                 @JsonProperty("description") String description,
                 @JsonProperty("remaining") float remaining,
                 @JsonProperty("requires_shipping") boolean requiresShipping,
                 @JsonProperty("url") String url,
                 @JsonProperty("user_limit") Integer userLimit,
                 @JsonProperty("edited_at") String editedAt,
                 @JsonProperty("patron_count") int patronCount,
                 @JsonProperty("post_count") int postCount,
                 @JsonProperty("published") boolean published,
                 @JsonProperty("published_at") String publishedAt,
                 @JsonProperty("image_url") String imageUrl,
                 @JsonProperty("discord_role_ids") List<String> discordRoleIds,
                 @JsonProperty("title") String title,
                 @JsonProperty("unpublished_at") String unpublishedAt,
                 @JsonProperty("campaign") Campaign campaign,
                 @JsonProperty("tier_image") Media tierImage,
                 @JsonProperty("benefits") List<Benefit> benefits
  ) {
    this.amountCents = amountCents;
    this.createdAt = createdAt;
    this.description = description;
    this.remaining = remaining;
    this.requiresShipping = requiresShipping;
    this.url = url;
    this.userLimit = userLimit;
    this.editedAt = editedAt;
    this.patronCount = patronCount;
    this.postCount = postCount;
    this.published = published;
    this.publishedAt = publishedAt;
    this.imageUrl = imageUrl;
    this.discordRoleIds = discordRoleIds;
    this.title = title;
    this.unpublishedAt = unpublishedAt;
    this.campaign = campaign;
  }

  public int getAmountCents() {
    return amountCents;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getDescription() {
    return description;
  }

  public float getRemaining() {
    return remaining;
  }

  public boolean isRequiresShipping() {
    return requiresShipping;
  }

  public String getUrl() {
    return url;
  }

  public Integer getUserLimit() {
    return userLimit;
  }

  public String getEditedAt() {
    return editedAt;
  }

  public int getPatronCount() {
    return patronCount;
  }

  public int getPostCount() {
    return postCount;
  }

  public boolean isPublished() {
    return published;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public List<String> getDiscordRoleIds() {
    return discordRoleIds;
  }

  public String getTitle() {
    return title;
  }

  public String getUnpublishedAt() {
    return unpublishedAt;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public Media getTierImage() {
    return tierImage;
  }

  public List<Benefit> getBenefits() {
    return benefits;
  }
}
