package com.demien.swtest.rest;

import com.demien.swtest.dto.GroupDTO;
import com.demien.swtest.model.Group;
import com.demien.swtest.service.GroupService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("/groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Group resource", produces = "application/json")
public class GroupResource {

    @Autowired
    private GroupService groupService;

    public Response OkResponse(Object entity) {
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    public Response NotFoundResponse() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @ApiOperation(value = "Create group.", response = Group.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "group resource ", responseHeaders = {
                    @ResponseHeader(name = "Location", description = "The URL to retrieve created resource", response = String.class)
            })
    })
    public Response createGroup(GroupDTO groupDTO, @Context UriInfo uriInfo) {
        Group result = groupService.add(new Group(groupDTO));
        return OkResponse(result);
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get group by id resource.", response = Group.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group resource found"),
            @ApiResponse(code = 404, message = "Group resource not found")
    })
    public Response getGroup(@ApiParam @PathParam("id") Long id) {
        Group result = groupService.get(id);
        return result == null ? NotFoundResponse() : OkResponse(result);
    }

    @PUT
    @ApiOperation(value = "Update group.", response = Group.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group resource found")
    })
    public Response updateGroup(Group group, @Context UriInfo uriInfo) {
        groupService.update(group.getId(), group);
        Group result = groupService.get(group.getId());
        return OkResponse(result);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete group by id resource.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group resource found"),
            @ApiResponse(code = 404, message = "Group resource not found")
    })
    public Response deleteGroup(@ApiParam @PathParam("id") Long id) {
        Group result = groupService.get(id);
        if (result == null) return NotFoundResponse();
        groupService.delete(id);
        return OkResponse(id);
    }


}
