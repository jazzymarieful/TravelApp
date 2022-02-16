package sr.unasat.travelapp.dao;

import sr.unasat.travelapp.config.JPAConfiguration;
import sr.unasat.travelapp.entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

    public boolean verifyAccount(String username, String password) {
        boolean isVerified = false;
        List<Account> accountList;
        entityManager.getTransaction().begin();
        String jpql = "select a from Account a where a.username = :username and a.password = :password";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        accountList = query.setParameter("username", username).setParameter("password", password).getResultList();
        if (!accountList.isEmpty()) {
            isVerified = true;
        } else {
            System.out.println("Account couldn't be verified");
        }
        entityManager.getTransaction().commit();
        return isVerified;
    }

    public Account retrieveAccount(String username, String password) {
        List<Account> accountList;
        Account account = null;
        entityManager.getTransaction().begin();
        String jpql = "select a from Account a where a.username = :username and a.password = :password";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        accountList = query.setParameter("username", username).setParameter("password", password).getResultList();
        if (!accountList.isEmpty()) {
            account = accountList.get(0);
        } else {
            System.out.println("Account couldn't be retrieved");
        }
        entityManager.getTransaction().commit();
        return account;
    }


}
