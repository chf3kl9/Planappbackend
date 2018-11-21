package database.repository;

import models.Gebruiker;

public class GebruikerRepository extends AbstractRepository<Gebruiker, Integer> {

    @Override
    public Class<Gebruiker> getDomainClass(){
        return Gebruiker.class;
    }
}
