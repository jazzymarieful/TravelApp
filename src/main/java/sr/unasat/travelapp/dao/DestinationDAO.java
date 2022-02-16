package sr.unasat.travelapp.dao;

import sr.unasat.travelapp.config.JPAConfiguration;
import sr.unasat.travelapp.entities.Destination;
import sr.unasat.travelapp.entities.TransportCompany;
import sr.unasat.travelapp.entities.TravelSegment;
import sr.unasat.travelapp.entities.Traveler;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DestinationDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

//    public DestinationDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public List<Destination> retrieveDestinationList() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select d from Destination d");
        List<Destination> destinationList = query.getResultList();
        entityManager.getTransaction().commit();
        return destinationList;
    }







}
