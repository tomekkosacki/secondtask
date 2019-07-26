package com.comarch.tomasz.kosacki.service;

import com.comarch.tomasz.kosacki.serviceException.AppException;
import com.comarch.tomasz.kosacki.serviceException.NullArgumentException;
import com.comarch.tomasz.kosacki.serviceException.TagEntityNotFoundException;
import com.comarch.tomasz.kosacki.tagDB.TagDb;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class TagService {

    private TagDb tagDb;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public TagService() {
    }

    public TagService(TagDb tagDb) {

        this.tagDb = tagDb;
    }

    public TagEntity getTagById(String tagId) throws AppException {

        if (tagId == null) {
            logger.error("TagId is null");
            throw new NullArgumentException();
        }
        TagEntity tagEntity = findTagById(tagId);
        if (tagEntity != null) {
            return tagEntity;
        }
        logger.error("Tag not found");
        throw new TagEntityNotFoundException(tagId);
    }

    public List<TagEntity> getTagBy(String tagId, String userId, String tagName, String tagValue) throws AppException {

        List<TagEntity> tagEntityList = this.tagDb.getTagBy(tagId, userId, tagName, tagValue);
        if (!tagEntityList.isEmpty()) {
            return tagEntityList;
        }
        logger.error("User not found");
        throw new TagEntityNotFoundException("");
    }

    public void createTag(TagEntity newTag) {

        if (newTag == null) {
            logger.error("Argument can not be null");
            throw new NullArgumentException();
        }

        String newTagId;
        do {
            newTagId = UUID.randomUUID().toString();
        } while (findTagById(newTagId) != null);

        newTag.setTagId(newTagId);

        this.tagDb.createTag(newTag);
    }

    public void deleteTag(String tagId) throws AppException {

        if (tagId == null) {
            logger.error("Argument can not be null");
            throw new NullArgumentException();
        }
        TagEntity tagEntity = findTagById(tagId);
        if (tagEntity != null) {
            this.tagDb.deleteTag(tagEntity);
        } else {
            logger.error("Tag not found");
            throw new TagEntityNotFoundException(tagId);
        }
    }

    private TagEntity findTagById(String tagId) {
        return this.tagDb.getTagById(tagId);
    }
}
