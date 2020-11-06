package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Type("media")
public class Media extends BaseResource {

  public enum MediaEvent implements Field {
    FileName("file_name", true),
    SizeBytes("size_bytes", true),
    Mimetype("mimetype", true),
    State("state", true),
    OwnerType("owner_type", true),
    OwnerId("owner_id", true),
    OwnerRelationship("owner_relationship", true),
    UploadExpiresAt("upload_expires_at", true),
    UploadUrl("upload_url", true),
    UploadParameters("upload_parameters", true),
    DownloadUrl("download_url", true),
    ImageUrls("image_urls", true),
    CreatedAt("created_at", true),
    Metadata("metadata", true),
    ;

    private final String propertyName;
    private final boolean isDefault;

    MediaEvent(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    public static Collection<MediaEvent> getDefaultFields() {
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

  private String fileName;
  private int sizeBytes;
  private String mimetype;
  private String state;
  private String ownerType;
  private String ownerId;
  private String ownerRelationship;
  private String uploadExpiresAt;
  private String uploadUrl;
  private Object uploadParameters;
  private String downloadUrl;
  private Object imageUrls;
  private String createdAt;
  private Object metadata;

  public Media(
    @JsonProperty("file_name") String fileName,
    @JsonProperty("size_bytes") int sizeBytes,
    @JsonProperty("mimetype") String mimetype,
    @JsonProperty("state") String state,
    @JsonProperty("owner_type") String ownerType,
    @JsonProperty("owner_id") String ownerId,
    @JsonProperty("owner_relationship") String ownerRelationship,
    @JsonProperty("upload_expires_at") String uploadExpiresAt,
    @JsonProperty("upload_url") String uploadUrl,
    @JsonProperty("upload_parameters") Object uploadParameters,
    @JsonProperty("download_url") String downloadUrl,
    @JsonProperty("image_urls") Object imageUrls,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("metadata") Object metadata
  ) {
    this.fileName = fileName;
    this.sizeBytes = sizeBytes;
    this.mimetype = mimetype;
    this.state = state;
    this.ownerType = ownerType;
    this.ownerId = ownerId;
    this.ownerRelationship = ownerRelationship;
    this.uploadExpiresAt = uploadExpiresAt;
    this.uploadUrl = uploadUrl;
    this.uploadParameters = uploadParameters;
    this.downloadUrl = downloadUrl;
    this.imageUrls = imageUrls;
    this.createdAt = createdAt;
    this.metadata = metadata;
  }

  public String getFileName() {
    return fileName;
  }

  public int getSizeBytes() {
    return sizeBytes;
  }

  public String getMimetype() {
    return mimetype;
  }

  public String getState() {
    return state;
  }

  public String getOwnerType() {
    return ownerType;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getOwnerRelationship() {
    return ownerRelationship;
  }

  public String getUploadExpiresAt() {
    return uploadExpiresAt;
  }

  public String getUploadUrl() {
    return uploadUrl;
  }

  public Object getUploadParameters() {
    return uploadParameters;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public Object getImageUrls() {
    return imageUrls;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public Object getMetadata() {
    return metadata;
  }
}
