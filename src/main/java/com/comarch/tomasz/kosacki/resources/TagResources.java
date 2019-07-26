package com.comarch.tomasz.kosacki.resources;

import com.codahale.metrics.annotation.Timed;
import com.comarch.tomasz.kosacki.mapper.TagMapper;
import com.comarch.tomasz.kosacki.service.TagService;
import com.comarch.tomasz.kosacki.tagDto.TagDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        return Response.ok(this.mapper.tagEntityToTagDto(this.tagService.getTagById(tagId))).build();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagBy(@QueryParam("tagId") String tagId,
                             @QueryParam("userId") String userId,
                             @QueryParam("tagName") String tagName,
                             @QueryParam("tagValue") String tagValue){
        logger.info("Get tag by");
        return Response.ok(this.mapper.tagEntityListToTagDtoList(this.tagService.getTagBy(tagId, userId, tagName, tagValue))).build();
    }

    @POST
    @Timed
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(@Valid @NotNull TagDto newTag) {

        logger.info("Creating new tag");
        this.tagService.createTag(this.mapper.tagDtoToTagEntity(newTag));
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
}
