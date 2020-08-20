package pl.jsildatk.shortener.resource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.http.HttpServerRequest;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import pl.jsildatk.shortener.dto.ShortenerDTO;
import pl.jsildatk.shortener.dto.SuccessfulResponse;
import pl.jsildatk.shortener.service.ShortenerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static pl.jsildatk.shortener.service.ShortenerService.EXISTS_PATH;
import static pl.jsildatk.shortener.service.ShortenerService.REDIRECT_TO_URL_PATH;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class ShortenerResource {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    
    @Inject
    ShortenerService shortenerService;
    
    @Context
    HttpServerRequest request;
    
    @GET
    @Path(EXISTS_PATH)
    public Response checkIfNameExists(@PathParam String name) throws JsonProcessingException {
        return Response.ok(OBJECT_MAPPER.writeValueAsString(shortenerService.nameExists(name)))
                .build();
    }
    
    @GET
    @Path(REDIRECT_TO_URL_PATH)
    public Response redirectToUrl(@PathParam String name) throws JsonProcessingException {
        final String url = shortenerService.getUrl(name);
        if ( url == null ) {
            return Response.status(404)
                    .entity(OBJECT_MAPPER.writeValueAsString("Name: " + name + " was not found"))
                    .build();
        }
        return Response.temporaryRedirect(URI.create(url))
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShortener(ShortenerDTO shortenerDTO) throws JsonProcessingException {
        final boolean wasSaved = shortenerService.addNew(shortenerDTO);
        if ( wasSaved ) {
            return Response.status(201)
                    .entity(OBJECT_MAPPER.writeValueAsString(
                            new SuccessfulResponse("Shortener has been created!", request.host() + "/api/" + shortenerDTO.getName())))
                    .build();
        }
        return Response.status(400)
                .entity(OBJECT_MAPPER.writeValueAsString("Shortener with that name already exists"))
                .build();
    }
    
}
