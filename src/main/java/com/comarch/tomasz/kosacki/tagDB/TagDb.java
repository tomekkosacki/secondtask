package com.comarch.tomasz.kosacki.tagDB;

import com.comarch.tomasz.kosacki.tagDao.TagDao;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TagDb implements TagDao {

    private Datastore datastore;

    public TagDb(Datastore datastore) {

        this.datastore = datastore;
    }

    @Override
    public TagEntity getTagById(String tagId) {
        return datastore.createQuery(TagEntity.class)
                .field("tagId").equal(tagId)
                .get();
    }

    @Override
    public List<TagEntity> getTagBy(String tagId, String userId, String tagName, String tagValue) {

        List<Criteria> criteriaList = new ArrayList<>();
        Query<TagEntity> query = datastore.createQuery(TagEntity.class);

        if(tagId != null){
            criteriaList.add(query.criteria("tagId").equal(tagId));
        }
        if (userId != null) {
            criteriaList.add(query.criteria("userId").equal(userId));
        }
        if (tagName != null) {
            criteriaList.add(query.criteria("tagName").equal(tagName));
        }
        if (tagValue != null) {
            criteriaList.add(query.criteria("tagValue").equal(tagValue));
        }
        query.and(criteriaList.toArray(new Criteria[0]));
        return query.order()
                .asList();
    }

    @Override
    public void createTag(TagEntity newTag) {

        this.datastore.save(newTag);
    }

    @Override
    public void deleteTag(TagEntity tagToDelete) {

        TagEntity tag = datastore.createQuery(TagEntity.class)
                .field("tagId").equal(tagToDelete.getTagId())
                .get();
        this.datastore.delete(tag);
    }

    @Override
    public void updateTag(String tagId, TagEntity updatedValue) {

    }
}
