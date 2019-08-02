package com.comarch.tomasz.kosacki.resources;

import com.codahale.metrics.annotation.Timed;
import com.comarch.tomasz.kosacki.authentication.AuthUser;
import com.comarch.tomasz.kosacki.service.TagService;
import com.comarch.tomasz.kosacki.tagDto.TagDto;
import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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

    @RolesAllowed({"ADMIN"})
    @GET
    @Timed
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagById(@NotNull @PathParam("id") String tagId, @Auth AuthUser user) {

        logger.info("Get tag by id: {}, user name: {}", tagId, user.getName());
        TagDto tagDto = this.tagService.getTagById(tagId);
        return Response.ok(tagDto).build();
    }

    @RolesAllowed({"ADMIN"})
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagBy(@QueryParam("tagId") String tagId,
                             @QueryParam("userId") String userId,
                             @QueryParam("tagName") String tagName,
                             @QueryParam("tagValue") String tagValue,
                             @QueryParam("skip") @Min(0) @DefaultValue("0") int skip,
                             @QueryParam("limit") @Min(0) @DefaultValue("0") int limit,
                             @Auth AuthUser user) {

        logger.info("Get tag by, user name: {}", user.getName());
        List<TagDto> tagDtoList = this.tagService.getTagBy(tagId, userId, tagName, tagValue, skip, limit);
        return Response.ok(tagDtoList).build();
    }

    @PermitAll
    @POST
    @Timed
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(@Valid @NotNull TagDto newTag, @Auth AuthUser user) {

        logger.info("Creating new tag, user name: {}", user.getName());
        this.tagService.createTag(newTag);
        return Response.ok().build();
    }

    @PermitAll
    @DELETE
    @Timed
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteTag(@NotNull @PathParam("id") String tagId, @Auth AuthUser user) {

        logger.info("Delete user id: {}, user name: {}", tagId, user.getName());
        this.tagService.deleteTag(tagId);
        return Response.ok().build();
    }

    @PermitAll
    @PUT
    @Timed
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTag(@NotNull @PathParam("id") String tagId, @Valid @NotNull TagDto updatedValue, @Auth AuthUser user) {

        logger.info("Updating tag with id: {}, user name: {}", tagId, user.getName());
        this.tagService.updateTag(tagId, updatedValue);
        return Response.ok().build();

    }
}
