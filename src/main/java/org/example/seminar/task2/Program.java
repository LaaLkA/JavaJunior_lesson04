package org.example.seminar.task2;

import org.example.seminar.Models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Program {
    public static void main(String[] args) {
         Configuration cfg = new Configuration()
                 .configure("hibernate.cfg.xml")
                 .addAnnotatedClass(Student.class);
         try (SessionFactory sessionFactory = cfg.buildSessionFactory()){
             // Создание сессии
             Session session = sessionFactory.getCurrentSession();

             // Начало транзакции
             session.beginTransaction();

             // Создание объекта
             Student student = Student.create();
             session.save(student);
             System.out.println("Object student was save successfully");


             // Чтение объекта из базы данных
             Student retrivedStudent = session.get(Student.class, student.getId());
             System.out.println("Retrieved student was save successfully");
             System.out.println(student);

             //Обновление объекта
             retrivedStudent.updateAge();
             retrivedStudent.updateName();
             session.update(retrivedStudent);
             System.out.println("Object student was update successfully");
             System.out.println(retrivedStudent);

             // Удаление объекта
             session.delete(retrivedStudent);
             System.out.println("Object student was delete successfully");

             session.getTransaction().commit();

         }catch (Exception e){
             e.printStackTrace();
         }





    }
}
