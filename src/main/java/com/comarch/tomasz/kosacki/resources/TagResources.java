package com.comarch.tomasz.kosacki.resources;

import com.codahale.metrics.annotation.Timed;
import com.comarch.tomasz.kosacki.service.TagService;
import com.comarch.tomasz.kosacki.tagDto.TagDto;
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
    private TagService tagService;

    public TagResources(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagById(@NotNull @PathParam("id") String tagId) {

        logger.info("Get tag by id: {}", tagId);
        TagDto tagDto = this.tagService.getTagById(tagId);
        return Response.ok(tagDto).build();
    }

    @GET
    @Timed
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagByUserId(@NotNull @PathParam("id") String userId) {

        logger.info("Get tag by user id: {}", userId);
        List<TagDto> tagDto = this.tagService.getTagsByUserId(userId);
        return Response.ok(tagDto).build();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagBy(@QueryParam("tagId") String tagId,
                             @QueryParam("userId") String userId,
                             @QueryParam("tagName") String tagName,
                             @QueryParam("tagValue") String tagValue) {

        logger.info("Get tag by");
        List<TagDto> tagDtoList = this.tagService.getTagBy(tagId, userId, tagName, tagValue);
        return Response.ok(tagDtoList).build();
    }

    @POST
    @Timed
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(@Valid @NotNull TagDto newTag) {

        logger.info("Creating new tag");
        this.tagService.createTag(newTag);
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
        this.tagService.updateTag(tagId, updatedValue);
        return Response.ok().build();

    }
}
