package restserver.handlers;

import com.google.gson.Gson;
import database.repository.GebruikerRepository;
import models.Gebruiker;
import models.PlanningCard;
import restserver.reply.ErrorJson;
import restserver.reply.Reply;
import restserver.reply.Status;
import restserver.requests.Request;

import java.util.List;

public class PlanHandler implements IPlanHandler {

    GebruikerRepository repository = new GebruikerRepository();
    Gson gson = new Gson();

    @Override
    public Reply temp(Request request){
        return new Reply(Status.OK, request.getData());
    }

    @Override
    public Reply register(Gebruiker gebruiker) {
        Gebruiker saved = repository.save(gebruiker);

        if (saved.getId() != 0)
            return new Reply(Status.OK, gson.toJson(saved));

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply login(Gebruiker gebruiker) {
        List<Gebruiker> userResponse = repository.findAll();
        if (userResponse.size() != 0){
            for (Gebruiker u: userResponse){
                if (u.getName().equals(gebruiker.getName()) && u.getPassword().equals(gebruiker.getPassword())){
                    String json = gson.toJson(u);
                    return new Reply(Status.OK, json);
                }
            }
            ErrorJson errorJson = new ErrorJson("Incorrect username and/or password");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply addFriend(Gebruiker user, Gebruiker friend) {
        return null;
    }

    @Override
    public Reply removeFriend(Gebruiker user, Gebruiker friend) {
        return null;
    }




    @Override
    public Reply createCard(PlanningCard card) {
        return null;
    }

    @Override
    public Reply addUserToCard(PlanningCard card, Gebruiker user) {
        return null;
    }

    @Override
    public Reply editCard(PlanningCard card) {
        return null;
    }

    @Override
    public Reply deleteCard(PlanningCard card) {
        return null;
    }

    @Override
    public Reply removeUserFromCard(PlanningCard card, Gebruiker user) {
        return null;
    }
}
