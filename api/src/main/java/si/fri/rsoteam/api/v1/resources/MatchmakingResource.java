package si.fri.rsoteam.api.v1.resources;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rsoteam.lib.dtos.MatchmakingDto;
import si.fri.rsoteam.services.beans.MatchmakingBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("/matchmaking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatchmakingResource {

    private Logger log = LogManager.getLogger(MatchmakingResource.class.getName());

    @Inject
    private MatchmakingBean matchmakingsBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get list of matchmakings", description = "Returns list of matchmaking enteries.")
    @APIResponses({
            @APIResponse(
                    description = "Returned successfully list matchmaking",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = MatchmakingDto.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )
    })
    @Log(LogParams.METRICS)
    public Response getMatchmakings() {
        return Response.ok(matchmakingsBean.getAllMatches()).build();
    }

    @GET
    @Path("/{objectId}")
    @Operation(summary = "Get matchmaking by id.", description = "Returns specific matchmaking.")
    @APIResponses({
            @APIResponse(
                    description = "Returned successfully specific matchmaking entry",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = MatchmakingDto.class, type = SchemaType.ARRAY))
            )
    })
    @Log(LogParams.METRICS)
    public Response getMatchmakingById(@PathParam("objectId") Integer id) {
        return Response.ok(matchmakingsBean.getMatchmaking(id)).build();
    }

    @POST
    @Operation(summary = "Create new matchmaking entry ", description = "Create new matchmaking entry")
    @APIResponses({
            @APIResponse(
                    description = "Created successfully matchmaking entry",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = MatchmakingDto.class, type = SchemaType.ARRAY))
            )
    })
    @Log(LogParams.METRICS)
    public Response createMatchmaking(MatchmakingDto matchmakingDto) {
        return Response.status(201).entity(matchmakingsBean.createMatchmaking(matchmakingDto)).build();
    }

    @PUT
    @Path("{objectId}")
    @Operation(summary = "Update matchmaking.", description = "Updates specific matchmaking by id.")
    @APIResponses({
            @APIResponse(
                    description = "Updated successfully matchmaking entry",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = MatchmakingDto.class, type = SchemaType.ARRAY))
            )
    })
    @Log(LogParams.METRICS)
    public Response updateMatchmaking(@PathParam("objectId") Integer id, MatchmakingDto eventDto) {
        return Response.status(201).entity(matchmakingsBean.updateMatchmaking(eventDto, id)).build();
    }

    @DELETE
    @Path("{objectId}")
    @Operation(summary = "Delete matchmaking.", description = "Delete specific matchmaking by id.")
    @APIResponses({
            @APIResponse(
                    description = "Deleted successfully matchmaking entry",
                    responseCode = "204",
                    content = @Content(schema = @Schema(implementation = MatchmakingDto.class, type = SchemaType.ARRAY))
            )
    })
    @Log(LogParams.METRICS)
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        matchmakingsBean.deleteMatchmaking(id);
        return Response.status(204).build();
    }
}
