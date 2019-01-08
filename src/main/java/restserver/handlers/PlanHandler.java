package restserver.handlers;

import com.google.gson.Gson;
import database.repository.GebruikerRepository;
import database.repository.PlanningCardRepository;
import logging.LogLevel;
import logging.Logger;
import models.Gebruiker;
import models.PlanningCard;
import restserver.reply.ErrorJson;
import restserver.reply.Reply;
import restserver.reply.Status;
import restserver.requests.Request;

import java.util.ArrayList;
import java.util.List;

public class PlanHandler implements IPlanHandler {

    GebruikerRepository gebruikerRepo = new GebruikerRepository();
    PlanningCardRepository cardRepo = new PlanningCardRepository();
    Gson gson = new Gson();



    @Override
    public Reply temp(Request request){
        return new Reply(Status.OK, request.getData());
    }

    @Override
    public Reply register(Gebruiker gebruiker) {
        try {
            Gebruiker saved = gebruikerRepo.save(gebruiker);

            if (saved.getId() == gebruiker.getId()) {
                return new Reply(Status.OK, gson.toJson(saved));
            }

            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        catch(Exception e) {
            Logger.getInstance().log(e.getMessage(), LogLevel.ERROR);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply login(Gebruiker gebruiker) {
        List<Gebruiker> userResponse = gebruikerRepo.findAll();
        if (userResponse.size() != 0){
            for (Gebruiker u: userResponse){
                if (u.getName().equals(gebruiker.getName()) && u.getPassword().equals(gebruiker.getPassword())){
                    Logger.getInstance().log("User found", LogLevel.INFORMATION);
                    String json = gson.toJson(u);
                    return new Reply(Status.OK, json);
                }
            }
            Logger.getInstance().log("User not found1", LogLevel.INFORMATION);
            ErrorJson errorJson = new ErrorJson("Incorrect username and/or password");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
        Logger.getInstance().log("User not found2", LogLevel.INFORMATION);
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }



    @Override
    public Reply getCardsByUser(int userId){
        List<PlanningCard> cards = cardRepo.findAll();
        List<PlanningCard> result = new ArrayList<>();
        if (cards.size() != 0){
            for (PlanningCard c: cards){
                if (c.getUser().getId() == userId)
                    result.add(c);
            }
            String json = gson.toJson(result);
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    /*@Override
    public Reply addFriend(Gebruiker user, Gebruiker friend) {
        return null;
    }

    @Override
    public Reply removeFriend(Gebruiker user, Gebruiker friend) {
        return null;
    }*/


    @Override
    public Reply createCard(PlanningCard card) {
        PlanningCard saved = cardRepo.save(card);

        if (saved.getId() == card.getId())
            return new Reply(Status.OK, gson.toJson(saved));

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    /*@Override
    public Reply addUserToCard(PlanningCard card, Gebruiker user) {
        return null;
    }*/

    @Override
    public Reply editCard(PlanningCard card) {
        PlanningCard saved = cardRepo.save(card);

        if (saved.getId() == card.getId())
            return new Reply(Status.OK, gson.toJson(saved));

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteCard(PlanningCard card) {
        cardRepo.delete(card);

        if (card != null)
            return new Reply(Status.OK, gson.toJson(new ErrorJson("Deleted")));

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    /*@Override
    public Reply removeUserFromCard(PlanningCard card, Gebruiker user) {
        return null;
    }*/
}
