package org.example.seminar.task2;

import org.hibernate.SessionFactory;

import java.lang.module.Configuration;

public class Program {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml");

    }
}
