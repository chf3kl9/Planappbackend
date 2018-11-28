package restserver.services;

import com.google.gson.Gson;
import logging.Logger;
import models.Gebruiker;
import models.PlanningCard;
import restserver.handlers.IPlanHandler;
import restserver.handlers.PlanHandler;
import restserver.reply.Reply;
import restserver.reply.Status;
import restserver.requests.Request;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/planning")
public class PlanService {
    private IPlanHandler handler = new PlanHandler();
    Gson gson = new Gson();

    @POST
    @Consumes("application/json")
    @Path("/submit")
    public Response submit(String data){
        try {
            Request request = gson.fromJson(data, Request.class);
            Reply reply = handler.temp(request);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("/register")
    public Response register (String data){
        try {
            Gebruiker gebruiker = gson.fromJson(data, Gebruiker.class);
            Reply reply = handler.register(gebruiker);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response login (String data){
        try {
            Gebruiker gebruiker = gson.fromJson(data, Gebruiker.class);
            Reply reply = handler.login(gebruiker);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("/addFriend")
    public Response addFriend (String data){
        try {
            Gebruiker gebruiker = gson.fromJson(data, Gebruiker.class);
            Gebruiker friend = gson.fromJson(data, Gebruiker.class);
            Reply reply = handler.addFriend(gebruiker, friend);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @DELETE
    @Consumes("application/json")
    @Path("/removeFriend")
    public Response removeFriend(String data){
        try {
            Gebruiker gebruiker = gson.fromJson(data, Gebruiker.class);
            Gebruiker friend = gson.fromJson(data, Gebruiker.class);
            Reply reply = handler.removeFriend(gebruiker, friend);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }



    @POST
    @Consumes("application/json")
    @Path("/createCard")
    public Response createCard (String data){
        try {
            PlanningCard card = gson.fromJson(data, PlanningCard.class);
            Reply reply = handler.createCard(card);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("/addUserToCard")
    public Response addUserToCard (String data){

        try {
            PlanningCard card = gson.fromJson(data, PlanningCard.class);
            Gebruiker gebruiker = gson.fromJson(data, Gebruiker.class);
            Reply reply = handler.addUserToCard(card, gebruiker);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Path("/editCard")
    public Response editCard (String data){
        try {
            PlanningCard card = gson.fromJson(data, PlanningCard.class);
            Reply reply = handler.editCard(card);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @DELETE
    @Consumes("application/json")
    @Path("/deleteCard")
    public Response deleteCard(String data){
        try {
            PlanningCard card = gson.fromJson(data, PlanningCard.class);
            Reply reply = handler.deleteCard(card);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

    @DELETE
    @Consumes("application/json")
    @Path("/removeUserFromCard")
    public Response removeUserFromCard(String data){
        try {
            PlanningCard card = gson.fromJson(data, PlanningCard.class);
            Gebruiker gebruiker = gson.fromJson(data, Gebruiker.class);
            Reply reply = handler.removeUserFromCard(card, gebruiker);
            return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
        }
        catch(Exception e){
            Logger.getInstance().log(e);
            return Response.status(400).build();
        }
    }

}