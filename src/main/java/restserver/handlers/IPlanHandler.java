package restserver.handlers;

import models.Gebruiker;
import models.PlanningCard;
import restserver.reply.Reply;
import restserver.requests.Request;

public interface IPlanHandler {

    Reply temp(Request request);
    Reply register(Gebruiker gebruiker);
    Reply login(Gebruiker gebruiker);

    Reply getCardsByUser(int userId);
    //Reply addFriend(Gebruiker user, Gebruiker friend);
    //Reply removeFriend(Gebruiker user, Gebruiker friend);

    Reply createCard(PlanningCard card);
    //Reply addUserToCard(PlanningCard card, Gebruiker user);
    Reply editCard(PlanningCard card);
    Reply deleteCard(PlanningCard card);
    //Reply removeUserFromCard(PlanningCard card, Gebruiker user);
}
