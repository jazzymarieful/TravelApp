package sr.unasat.travelapp.dao;

import sr.unasat.travelapp.config.JPAConfiguration;
import sr.unasat.travelapp.entities.TravelGroup;
import sr.unasat.travelapp.entities.Traveler;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class TravelerDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

//    public TravelerDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    TravelGroupDAO travelGroupDAO = new TravelGroupDAO();

    public Traveler findTravelerByPassport(String passport) {
//        entityManager.getTransaction().begin();
        String jpql = "select t from Traveler t where t.passport = :passport";
        TypedQuery<Traveler> query = entityManager.createQuery(jpql, Traveler.class);
        Traveler traveler = query.setParameter("passport", passport).getResultList().stream().findFirst().orElse(null);
//        entityManager.getTransaction().commit();
        return traveler;
    }

    public Traveler findTravelerByName(String firstName, String lastName) {
        entityManager.getTransaction().begin();
        String jpql = "select t from Traveler t where t.firstName = :firstName and t.lastName = :lastName";
        TypedQuery<Traveler> query = entityManager.createQuery(jpql, Traveler.class);
        Traveler traveler = query.setParameter("firstName", firstName).setParameter("lastName", lastName).getSingleResult();
        entityManager.getTransaction().commit();
        return traveler;
    }

    public List<Traveler> findAllTravelers() {
        entityManager.getTransaction().begin();
        String jpql = "select t from Traveler t";
        Query query = entityManager.createQuery(jpql);
        List<Traveler> travelerList = query.getResultList();
        entityManager.getTransaction().commit();
        return travelerList;
    }

    public long countTravelers() {
        entityManager.getTransaction().begin();
        String jpql = "select count(t) from Traveler t";
        Query query = entityManager.createQuery(jpql);
        long count = (long) query.getSingleResult();
        entityManager.getTransaction().commit();
        return count;
    }

    public Traveler insertTraveler(Traveler traveler) {
        entityManager.getTransaction().begin();
        entityManager.persist(traveler);
        entityManager.getTransaction().commit();
        System.out.println("Traveler has been inserted");
        return traveler;
    }

    public int updateTraveler(Traveler traveler) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("update Traveler t set t.firstName = :firstName, " +
                "t.lastName = :lastName," +
                "t.age = :age " +
                "where t.passport = :passport");
        query.setParameter("firstName", traveler.getFirstName());
        query.setParameter("lastName", traveler.getLastName());
        query.setParameter("passport", traveler.getPassport());
        query.setParameter("age", traveler.getAge());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public int deleteTraveler(String passport) {
        entityManager.getTransaction().begin();
        int rowsDeleted = 0;
        if (findTravelerByPassport(passport) != null) {
            TravelGroup travelGroup = travelGroupDAO.findTravelGroupByTraveler(passport);
            if (travelGroup.getTravelerCount() > 1) {
                String jpql1 = "delete from Traveler t where t.passport = :passport";
                Query query1 = entityManager.createQuery(jpql1);
                query1.setParameter("passport", passport);
                rowsDeleted = query1.executeUpdate();
                travelGroupDAO.updateTravelGroupDecreaseTravelerCount(travelGroup);
            } else {
                System.out.println("Only one traveler left in travel package \nRemoval of traveler not allowed");
            }
        } else {
            System.out.println("Traveler not found");
        }
        System.out.println("Travelers deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }


}
