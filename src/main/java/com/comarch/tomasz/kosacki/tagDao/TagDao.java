package com.comarch.tomasz.kosacki.tagDao;

import com.comarch.tomasz.kosacki.tagEntity.TagEntity;

import java.util.List;

public interface TagDao {

    TagEntity getTagById(String tagId);

    List<TagEntity> getTagBy(String tagId, String userId, String tagName, String tagValue, int skip, int limit);

    void createTag(TagEntity newTag);

    void deleteTag(TagEntity tag);

    void updateTag(String tagId, TagEntity updatedValue);
}
