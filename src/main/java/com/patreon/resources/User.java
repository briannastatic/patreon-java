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

@Type("user")
public class User extends BaseResource {

  /**
   * Metadata about fields in User
   */
  public enum UserField implements Field {
    FullName("full_name", true),
    FirstName("first_name", true),
    LastName("last_name", true),
    DiscordId("discord_id", true),
    Twitch("twitch", true),
    Vanity("vanity", true),
    Email("email", true),
    About("about", true),
    FacebookId("facebook_id", true),
    ImageUrl("image_url", true),
    ThumbUrl("thumb_url", true),
    Youtube("youtube", true),
    Twitter("twitter", true),
    Facebook("facebook", true),
    Created("created", true),
    Url("url", true),
    SocialConnections("social_connections", true),
    IsEmailVerified("is_email_verified", true),
    CanSeeNsfw("can_see_nsfw", true),
    HidePledges("hide_pledes", true),
    LikeCount("like_count", false),
    CommentCount("comment_count", false),
      ;

    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;

    UserField(String propertyName, boolean isDefault) {
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

    public static Collection<UserField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

  }

  private String fullName;
  private String firstName;
  private String lastName;
  private String discordId;
  private String twitch;
  private String vanity;
  private String email;
  private String about;
  private String facebookId;
  private String imageUrl;
  private String thumbUrl;
  private String youtube;
  private String twitter;
  private String facebook;
  private Date created;
  private String url;
  private SocialConnections socialConnections;
  private boolean isEmailVerified;
  private boolean canSeeNsfw;
  private boolean hidePledges;

  //Optional properties
  private Integer likeCount;
  private Integer commentCount;

  @Relationship("memberships")
  private List<Member> memberships;

  @Relationship("campaign")
  private Campaign campaign;

  @JsonCreator
  public User(
               @JsonProperty("full_name") String fullName,
               @JsonProperty("first_name") String firstName,
               @JsonProperty("list_name") String lastName,
               @JsonProperty("discord_id") String discordId,
               @JsonProperty("twitch") String twitch,
               @JsonProperty("vanity") String vanity,
               @JsonProperty("email") String email,
               @JsonProperty("about") String about,
               @JsonProperty("facebook_id") String facebookId,
               @JsonProperty("image_url") String imageUrl,
               @JsonProperty("thumb_url") String thumbUrl,
               @JsonProperty("youtube") String youtube,
               @JsonProperty("twitter") String twitter,
               @JsonProperty("facebook") String facebook,
               @JsonProperty("created") Date created,
               @JsonProperty("url") String url,
               @JsonProperty("social_connections") SocialConnections socialConnections,
               @JsonProperty("is_email_verified") boolean isEmailVerified,
               @JsonProperty("can_see_nsfw") boolean canSeeNsfw,
               @JsonProperty("hide_pledges") boolean hidePledges,
               @JsonProperty("like_count") Integer likeCount,
               @JsonProperty("comment_count") Integer commentCount,
               @JsonProperty("memberships") List<Member> memberships,
               @JsonProperty("campaign") Campaign campaign
  ) {
    this.fullName = fullName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.discordId = discordId;
    this.twitch = twitch;
    this.vanity = vanity;
    this.email = email;
    this.about = about;
    this.facebookId = facebookId;
    this.imageUrl = imageUrl;
    this.thumbUrl = thumbUrl;
    this.youtube = youtube;
    this.twitter = twitter;
    this.facebook = facebook;
    this.created = created;
    this.url = url;
    this.socialConnections = socialConnections;
    this.isEmailVerified = isEmailVerified;
    this.canSeeNsfw = canSeeNsfw;
    this.hidePledges = hidePledges;
    this.likeCount = likeCount;
    this.commentCount = commentCount;
    this.memberships = memberships;
    this.campaign = campaign;
  }

  public String getFullName() {
    return fullName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getDiscordId() {
    return discordId;
  }

  public String getTwitch() {
    return twitch;
  }

  public String getVanity() {
    return vanity;
  }

  public String getEmail() {
    return email;
  }

  public String getAbout() {
    return about;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getThumbUrl() {
    return thumbUrl;
  }

  public String getYoutube() {
    return youtube;
  }

  public String getTwitter() {
    return twitter;
  }

  public String getFacebook() {
    return facebook;
  }

  public Date getCreated() {
    return created;
  }

  public String getUrl() {
    return url;
  }

  public SocialConnections getSocialConnections() {
    return socialConnections;
  }

  public boolean getIsEmailVerified() {
    return isEmailVerified;
  }

  public boolean isCanSeeNsfw() {
    return canSeeNsfw;
  }

  public boolean isHidePledges() {
    return hidePledges;
  }

  /**
   * @return The number of likes of for this user, or null if this field wasn't requested
   */
  public Integer getLikeCount() {
    return likeCount;
  }

  /**
   * @return The number of comments for this user, or null if the field wasn't requested
   */
  public Integer getCommentCount() {
    return commentCount;
  }

  public List<Member> getMemberships() {
    return memberships;
  }

  public Campaign getCampaign() {
    return campaign;
  }
}
