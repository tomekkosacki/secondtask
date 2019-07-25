package com.comarch.tomasz.kosacki.tagDto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TagDto implements Serializable {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String tagName;

    TagDto() {
    }

    public TagDto(String userId, String tagName) {
        this.userId = userId;
        this.tagName = tagName;
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
