package com.comarch.tomasz.kosacki.tagDto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TagDto implements Serializable {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String tagName;
    @NotEmpty
    private String tagValue;

    TagDto() {
    }

    public TagDto(String userId, String tagName, String tagValue) {
        this.userId = userId;
        this.tagName = tagName;
        this.tagValue = tagValue;
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

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
