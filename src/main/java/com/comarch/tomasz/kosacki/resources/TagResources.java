package com.comarch.tomasz.kosacki.resources;

import com.codahale.metrics.annotation.Timed;
import com.comarch.tomasz.kosacki.mapper.TagMapper;
import com.comarch.tomasz.kosacki.service.TagService;
import com.comarch.tomasz.kosacki.tagDto.TagDto;
import com.comarch.tomasz.kosacki.tagEntity.TagEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tags")
public class TagResources {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private TagMapper mapper;
    private TagService tagService;

    public TagResources(TagMapper mapper, TagService tagService) {
        this.mapper = mapper;
        this.tagService = tagService;
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagById(@NotNull @PathParam("id") String tagId) {

        logger.info("Get tag by id: {}", tagId);
        TagEntity tagEntity = this.tagService.getTagById(tagId);
        TagDto tagDto = this.mapper.tagEntityToTagDto(tagEntity);
        return Response.ok(tagDto).build();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagBy(@QueryParam("tagId") String tagId,
                             @QueryParam("userId") String userId,
                             @QueryParam("tagName") String tagName,
                             @QueryParam("tagValue") String tagValue){
        logger.info("Get tag by");
        List<TagEntity> tagEntityList = this.tagService.getTagBy(tagId, userId, tagName, tagValue);
        List<TagDto> tagDtoList = this.mapper.tagEntityListToTagDtoList(tagEntityList);
        return Response.ok(tagDtoList).build();
    }

    @POST
    @Timed
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(@Valid @NotNull TagDto newTag) {

        logger.info("Creating new tag");
        TagEntity tagEntity = this.mapper.tagDtoToTagEntity(newTag);
        this.tagService.createTag(tagEntity);
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteTag(@NotNull @PathParam("id") String tagId) {

        logger.info("Delete user id: {}", tagId);
        this.tagService.deleteTag(tagId);
        return Response.ok().build();
    }

    @PUT
    @Timed
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTag(@NotNull @PathParam("id") String tagId, @Valid @NotNull TagDto updatedValue) {

        logger.info("Updating tag with id: {}", tagId);
        TagEntity tagEntity = this.mapper.tagDtoToTagEntity(updatedValue);
        this.tagService.updateTag(tagId, tagEntity);
        return Response.ok().build();

    }
}
