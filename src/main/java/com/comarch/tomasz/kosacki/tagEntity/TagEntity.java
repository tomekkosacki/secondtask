package com.comarch.tomasz.kosacki.tagEntity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

@Entity
public class TagEntity {

    @Id
    @Indexed(options = @IndexOptions(unique = true))
    private String tagId;
    private String userId;
    private String tagName;

    public TagEntity() {
    }

    public TagEntity(String tagId, String userId, String tagName) {
        this.tagId = tagId;
        this.userId = userId;
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
