package restserver.services;

import com.google.gson.Gson;
import restserver.handlers.PlanHandler;
import restserver.reply.Reply;
import restserver.requests.Request;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/planning")
public class PlanService {
    private PlanHandler handler = new PlanHandler();

    @POST
    @Consumes("application/json")
    @Path("/submit")
    public Response submit(String data){
        Gson gson = new Gson();
        Request request = gson.fromJson(data, Request.class);
        Reply reply = handler.temp(request);
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }
}