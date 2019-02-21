package panda.repository;

import panda.domain.entities.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {

    private final EntityManager entityManager;



    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Receipt save(Receipt entity) {


        Receipt ent = entity;


        this.entityManager.clear();
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();


        return entity;
    }

    @Override
    public List<Receipt> findAll() {

       return this.entityManager.createQuery("SELECT r FROM Receipt r",Receipt.class).getResultList();

    }

    @Override
    public Receipt findById(String s) {
        Receipt receipt =
                this.entityManager
                        .createQuery("SELECT r FROM Receipt r WHERE r.id = :id ",Receipt.class)
                        .setParameter("id",s).getSingleResult();

        return receipt;
    }

    @Override
    public Long size() {
        return (long) this.entityManager.createQuery("SELECT r FROM Receipt r",Receipt.class).getResultList().size();
    }
}
