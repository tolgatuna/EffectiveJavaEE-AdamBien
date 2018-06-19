package com.tt.doit.business.reminders.boundary;

import com.tt.doit.business.reminders.entity.Todo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TodoManager {

    @PersistenceContext
    EntityManager entityManager;

    public Todo findById(long id) {
        return this.entityManager.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return this.entityManager.createNamedQuery(Todo.findAll, Todo.class).getResultList();
    }

    public Todo save(Todo todo) {
        Todo saved = this.entityManager.merge(todo);
        return saved;

    }

    public void delete(long id) {
        try {
            Todo reference = this.entityManager.getReference(Todo.class, id);
            this.entityManager.remove(reference);
        } catch (EntityNotFoundException ex) {
            // we want to remove item and if item can not be found, that is not important for us for delete operation.
            System.out.println("Entity Not Found. Ex: "+ ex.toString());
        }
    }

    public Todo updateStatus(long id, boolean done) {
        Todo todo = this.findById(id);
        if(todo == null)
            return null;
        todo.setDone(done);
        return todo;
    }
}
