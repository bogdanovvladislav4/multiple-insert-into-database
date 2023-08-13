package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        String hql1 = "From " + PurchaseList.class.getSimpleName();

        List<PurchaseList> purchaseLists = session.createQuery(hql1).getResultList();

        purchaseLists.forEach(purchaseList -> {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(new LinkedPurchaseListKey(purchaseList.getStudent().getId(),
                    purchaseList.getCourse().getId()));
            linkedPurchaseList.setCourseId(purchaseList.getCourse().getId());
            linkedPurchaseList.setStudentId(purchaseList.getStudent().getId());
            session.save(linkedPurchaseList);
        });
        transaction.commit();
        sessionFactory.close();

    }
}