package com.thuong.repository;

import com.thuong.model.Expenditure;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ExpenditureRepository implements IExpenditureRepository{
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Expenditure> findAll() {
        TypedQuery<Expenditure> query = em.createQuery("select c from Expenditure c", Expenditure.class);
        return query.getResultList();
    }

    @Override
    public Expenditure findById(Long id) {
        TypedQuery<Expenditure> query = em.createQuery("select c from Expenditure c where  c.id=:id", Expenditure.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Expenditure expenditurer) {
        if (expenditurer.getId() != null) {
            em.merge(expenditurer);
        } else {
            em.persist(expenditurer);
        }
    }

    @Override
    public void remove(Long id) {
        Expenditure expenditure = findById(id);
        if (expenditure != null) {
            em.remove(expenditure);
        }
    }
}
