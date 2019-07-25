package com.comarch.tomasz.kosacki.tagDB;

import com.comarch.tomasz.kosacki.tagDao.TagDao;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import org.mongodb.morphia.Datastore;

import java.util.List;

public class TagDb implements TagDao {

    private Datastore datastore;

    public TagDb(Datastore datastore) {

        this.datastore = datastore;
    }

    @Override
    public TagEntity getTagById(String tagId) {
        return datastore.createQuery(TagEntity.class)
                .field("id").equal(tagId)
                .get();
    }

    @Override
    public List<TagEntity> getTagBy(String tagId, String userId, String tagName) {
        return null;
    }

    @Override
    public void createTag(TagEntity newTag) {

    }

    @Override
    public void deleteTag(TagEntity tag) {

    }

    @Override
    public void updateTag(String tagId, TagEntity updatedValue) {

    }
}
