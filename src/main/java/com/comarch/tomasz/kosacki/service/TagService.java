package com.comarch.tomasz.kosacki.service;

import com.comarch.tomasz.kosacki.mapper.TagMapper;
import com.comarch.tomasz.kosacki.serviceException.AppException;
import com.comarch.tomasz.kosacki.serviceException.DuplicateTagNameException;
import com.comarch.tomasz.kosacki.serviceException.NullArgumentException;
import com.comarch.tomasz.kosacki.serviceException.TagEntityNotFoundException;
import com.comarch.tomasz.kosacki.tagDao.TagDao;
import com.comarch.tomasz.kosacki.tagDto.TagDto;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TagService {

    private TagDao tagDao;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private TagMapper mapper;

    public TagService(TagDao tagDao, TagMapper mapper) {

        this.tagDao = tagDao;
        this.mapper = mapper;
    }

    public TagDto getTagById(String tagId) throws AppException {

        if (tagId == null) {
            logger.error("Argument is null");
            throw new NullArgumentException();
        }
        TagEntity tagEntity = findTagById(tagId);
        if (tagEntity != null) {
            return this.mapper.tagEntityToTagDto(tagEntity);
        }
        logger.error("Tag id: {} not found", tagId);
        return new TagDto();
    }

    public List<TagDto> getTagBy(String tagId, String userId, String tagName, String tagValue) throws AppException {

        List<TagEntity> tagEntityList = this.tagDao.getTagBy(tagId, userId, tagName, tagValue);
        if (tagEntityList != null && !tagEntityList.isEmpty()) {
            return this.mapper.tagEntityListToTagDtoList(tagEntityList);
        }
        logger.error("Tag not found");
        return Collections.emptyList();
    }

    public void createTag(TagDto newTag) {

        if (newTag == null) {
            logger.error("Argument is null");
            throw new NullArgumentException();
        }
        TagEntity tagEntity = this.mapper.tagDtoToTagEntity(newTag);

        String newTagId = UUID.randomUUID().toString();
        tagEntity.setTagId(newTagId);
        try {
            this.tagDao.createTag(tagEntity);
        } catch (DuplicateKeyException ex) {
            logger.error("Tag already exist with that name: {} for this user: {}", tagEntity.getTagName(), tagEntity.getUserId());
            throw new DuplicateTagNameException();
        }
    }

    public void deleteTag(String tagId) throws AppException {

        if (tagId == null) {
            logger.error("Argument is null");
            throw new NullArgumentException();
        }
        TagEntity tagEntity = findTagById(tagId);
        if (tagEntity != null) {
            this.tagDao.deleteTag(tagEntity);
        } else {
            logger.error("Tag id: {} not found", tagId);
            throw new TagEntityNotFoundException(tagId);
        }
    }

    public void updateTag(String tagId, TagDto updatedValue) throws AppException {

        if (tagId == null || updatedValue == null) {
            logger.error("Argument is null");
            throw new NullArgumentException();
        }
        if (findTagById(tagId) != null) {
            TagEntity tagEntity = this.mapper.tagDtoToTagEntity(updatedValue);
            try {
                this.tagDao.updateTag(tagId, tagEntity);
            } catch (DuplicateKeyException ex) {
                logger.error("Tag already exist with that name: {} for this user: {}", tagEntity.getTagName(), tagEntity.getUserId());
                throw new DuplicateTagNameException();
            }
        } else {
            logger.error("Tag id: {} not found", tagId);
            throw new TagEntityNotFoundException(tagId);
        }
    }

    private TagEntity findTagById(String tagId) {
        return this.tagDao.getTagById(tagId);
    }
}
