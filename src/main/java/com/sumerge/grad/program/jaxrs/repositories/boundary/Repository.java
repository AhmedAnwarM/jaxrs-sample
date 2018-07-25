package com.sumerge.grad.program.jaxrs.repositories.boundary;

import com.sumerge.grad.program.jaxrs.repositories.entity.AbstractEntity;
import com.sumerge.grad.program.jaxrs.repositories.entity.Course;
import com.sumerge.grad.program.jaxrs.repositories.entity.Instructor;
import com.sumerge.grad.program.jaxrs.repositories.entity.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

import static com.sumerge.grad.program.jaxrs.constants.Constants.PERSISTENT_UNIT;

@Stateless
public class Repository {

    @PersistenceContext(unitName = PERSISTENT_UNIT)
    private EntityManager em;

    public Collection<? extends AbstractEntity> getAll(Class<? extends AbstractEntity> clazz) {
        return em.createQuery("SELECT e FROM " + clazz.getName() + " e", clazz).
                getResultList();
    }

    public Collection<Student> getAllStudents() {
        return em.createQuery("SELECT s FROM Student s", Student.class).
                getResultList();
    }

    public Student getStudent(Long id) {
        return em.createQuery("SELECT s FROM Student s" +
                "  WHERE s.id = :id", Student.class).
                setParameter("id", id).
                getSingleResult();
    }

    public Collection<Course> getAllCourses() {
        return em.createQuery("SELECT c FROM Course c", Course.class).
                getResultList();
    }

    public Course getCourse(Long id) {
        return em.find(Course.class, id);
    }

    public Collection<Instructor> getAllInstructors() {
        return em.createQuery("SELECT i FROM Instructor i", Instructor.class).
                getResultList();
    }

    public Instructor getInstructor(Long id) {
        return em.find(Instructor.class, id);
    }

    public AbstractEntity save(AbstractEntity entity) {
        return em.merge(entity);
    }

    public void deleteStudent(Long id) {
        Student student = em.find(Student.class, id);
        if (student == null)
            throw new IllegalArgumentException("Student with ID: " + id + "does not exist in the database");

        em.remove(student);
    }
}
