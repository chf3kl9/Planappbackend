package database.repository;

import models.PlanningCard;

public class PlanningCardRepository extends AbstractRepository<PlanningCard, Integer> {

    @Override
    public Class<PlanningCard> getDomainClass(){
        return PlanningCard.class;
    }
}