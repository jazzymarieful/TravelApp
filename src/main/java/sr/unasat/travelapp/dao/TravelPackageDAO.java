package sr.unasat.travelapp.dao;

import sr.unasat.travelapp.config.JPAConfiguration;
import sr.unasat.travelapp.entities.Account;
import sr.unasat.travelapp.entities.TravelGroup;
import sr.unasat.travelapp.entities.TravelPackage;
import sr.unasat.travelapp.reports.RawReport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class TravelPackageDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

//    public TravelPackageDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public TravelPackage retrieveLastTravelPackageByAccount(Account account) {
        entityManager.getTransaction().begin();
        String jpql = "select t from TravelPackage t where t.account.accountId = :accountId order by t.travelPackageId desc";
        TypedQuery<TravelPackage> query = entityManager.createQuery(jpql, TravelPackage.class);
        TravelPackage travelPackage = query.setParameter("accountId", account.getAccountId()).getResultList().stream().findFirst().orElse(null);
        entityManager.getTransaction().commit();
        return travelPackage;
    }

    public TravelPackage insertTravelPackage(TravelPackage travelPackage) {
        entityManager.getTransaction().begin();
        entityManager.persist(travelPackage);
        entityManager.getTransaction().commit();
//        System.out.println("Travel Package has been inserted");
        return travelPackage;
    }


}
