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

@Type("campaign")
public class Campaign extends BaseResource {

  public enum CampaignField implements Field {
    CreatedAt("created_at", true),
    CreationName("creation_name", true),
    DiscordServerId("discord_server_id", true),
    GoogleAnalyticsId("google_analytics_id", true),
    ImageSmallUrl("image_small_url", true),
    ImageUrl("image_url", true),
    IsChargedImmediately("is_charged_immediately", true),
    IsMonthly("is_monthly", true),
    IsNsfw("is_nsfw", true),
    MainVideoEmbed("main_video_embed", true),
    MainVideoUrl("main_video_url", true),
    OneLiner("one_liner", true),
    PatronCount("patron_count", true),
    PayPerName("pay_per_name", true),
    PledgeUrl("pledge_url", true),
    PublishedAt("published_at", true),
    Summary("summary", true),
    ThanksEmbed("thanks_embed", true),
    ThanksMsg("thanks_msg", true),
    ThanksVideoUrl("thanks_video_url", true),
    HasRss("has_rss", true),
    HasSentRssNotify("has_sent_rss_notify", true),
    RssFeedTitle("rss_feed_title", true),
    RssArtworkUrl("rss_artwork_url", true),
    ShowEarnings("show_earnings", true),
    URL("url", true),
    ;

    private final String propertyName;
    private final boolean isDefault;

    CampaignField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    public static Collection<CampaignField> getDefaultFields() {
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

  private String createdAt;
  private String creationName;
  private String discordServerId;
  private String googleAnalyticsId;
  private String imageSmallUrl;
  private String imageUrl;
  private boolean isChargedImmediately;
  private boolean isMonthly;
  private boolean isNsfw;
  private String mainVideoEmbed;
  private String mainVideoUrl;
  private String oneLiner;
  private int patronCount;
  private String payPerName;
  private String pledgeUrl;
  private String publishedAt;
  private String summary;
  private String thanksEmbed;
  private String thanksMsg;
  private String thanksVideoUrl;
  private boolean hasRss;
  private boolean hasSentRssNotify;
  private String rssFeedTitle;
  private String rssArtworkUrl;
  private boolean showEarnings;
  private String url;

  @Relationship("tiers")
  private List<Tier> tiers;

  @Relationship("creator")
  private User creator;

  @Relationship("benefits")
  private List<Benefit> benefits;

  @Relationship("goals")
  private List<Goal> goals;

  public Campaign(
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("creation_name") String creationName,
    @JsonProperty("discord_server_id") String discordServerId,
    @JsonProperty("google_analytics_id") String googleAnalyticsId,
    @JsonProperty("image_small_url") String imageSmallUrl,
    @JsonProperty("image_url") String imageUrl,
    @JsonProperty("is_charged_immediately") boolean isChargedImmediately,
    @JsonProperty("is_monthly") boolean isMonthly,
    @JsonProperty("is_nsfw") boolean isNsfw,
    @JsonProperty("main_video_embed") String mainVideoEmbed,
    @JsonProperty("main_video_url") String mainVideoUrl,
    @JsonProperty("one_liner") String oneLiner,
    @JsonProperty("patron_count") int patronCount,
    @JsonProperty("pay_per_name") String payPerName,
    @JsonProperty("pledge_url") String pledgeUrl,
    @JsonProperty("published_at") String publishedAt,
    @JsonProperty("summary") String summary,
    @JsonProperty("thanks_embed") String thanksEmbed,
    @JsonProperty("thanks_msg") String thanksMsg,
    @JsonProperty("thanks_video_url") String thanksVideoUrl,
    @JsonProperty("has_rss") boolean hasRss,
    @JsonProperty("has_sent_rss_notify") boolean hasSentRssNotify,
    @JsonProperty("rss_feed_title") String rssFeedTitle,
    @JsonProperty("rss_artwork_url") String rssArtworkUrl,
    @JsonProperty("show_earnings") boolean showEarnings,
    @JsonProperty("url") String url,
    @JsonProperty("tiers") List<Tier> tiers,
    @JsonProperty("creator") User creator,
    @JsonProperty("benefits") List<Benefit> benefits,
    @JsonProperty("goals") List<Goal> goals
  ) {
    this.createdAt = createdAt;
    this.creationName = creationName;
    this.discordServerId = discordServerId;
    this.googleAnalyticsId = googleAnalyticsId;
    this.imageSmallUrl = imageSmallUrl;
    this.imageUrl = imageUrl;
    this.isChargedImmediately = isChargedImmediately;
    this.isMonthly = isMonthly;
    this.isNsfw = isNsfw;
    this.mainVideoEmbed = mainVideoEmbed;
    this.mainVideoUrl = mainVideoUrl;
    this.oneLiner = oneLiner;
    this.patronCount = patronCount;
    this.payPerName = payPerName;
    this.pledgeUrl = pledgeUrl;
    this.publishedAt = publishedAt;
    this.summary = summary;
    this.thanksEmbed = thanksEmbed;
    this.thanksMsg = thanksMsg;
    this.thanksVideoUrl = thanksVideoUrl;
    this.hasRss = hasRss;
    this.hasSentRssNotify = hasSentRssNotify;
    this.rssFeedTitle = rssFeedTitle;
    this.rssArtworkUrl = rssArtworkUrl;
    this.showEarnings = showEarnings;
    this.url = url;
    this.tiers = tiers;
    this.creator = creator;
    this.benefits = benefits;
    this.goals = goals;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getCreationName() {
    return creationName;
  }

  public String getDiscordServerId() {
    return discordServerId;
  }

  public String getGoogleAnalyticsId() {
    return googleAnalyticsId;
  }

  public String getImageSmallUrl() {
    return imageSmallUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public boolean isChargedImmediately() {
    return isChargedImmediately;
  }

  public boolean isMonthly() {
    return isMonthly;
  }

  public boolean isNsfw() {
    return isNsfw;
  }

  public String getMainVideoEmbed() {
    return mainVideoEmbed;
  }

  public String getMainVideoUrl() {
    return mainVideoUrl;
  }

  public String getOneLiner() {
    return oneLiner;
  }

  public int getPatronCount() {
    return patronCount;
  }

  public String getPayPerName() {
    return payPerName;
  }

  public String getPledgeUrl() {
    return pledgeUrl;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public String getSummary() {
    return summary;
  }

  public String getThanksEmbed() {
    return thanksEmbed;
  }

  public String getThanksMsg() {
    return thanksMsg;
  }

  public String getThanksVideoUrl() {
    return thanksVideoUrl;
  }

  public boolean isHasRss() {
    return hasRss;
  }

  public boolean isHasSentRssNotify() {
    return hasSentRssNotify;
  }

  public String getRssFeedTitle() {
    return rssFeedTitle;
  }

  public String getRssArtworkUrl() {
    return rssArtworkUrl;
  }

  public boolean isShowEarnings() {
    return showEarnings;
  }

  public String getUrl() {
    return url;
  }

  public List<Tier> getTiers() {
    return tiers;
  }

  public User getCreator() {
    return creator;
  }

  public List<Benefit> getBenefits() {
    return benefits;
  }

  public List<Goal> getGoals() {
    return goals;
  }
}
