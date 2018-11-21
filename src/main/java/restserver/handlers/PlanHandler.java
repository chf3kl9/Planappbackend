package restserver.handlers;

import restserver.reply.Reply;
import restserver.reply.Status;
import restserver.requests.Request;

public class PlanHandler {

    public Reply temp(Request request){
        return new Reply(Status.OK, request.getData());
    }
}
