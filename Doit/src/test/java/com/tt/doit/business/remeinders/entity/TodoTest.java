package com.tt.doit.business.remeinders.entity;

import com.tt.doit.business.reminders.entity.Todo;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoTest {

    @Test
    public void validTodo() {
        Todo todo = new Todo("Caption","Description",11);
        assertTrue(todo.isValid());
    }

    @Test
    public void invalidTodo() {
        Todo todo = new Todo("Caption",null,11);
        assertFalse(todo.isValid());
    }

    @Test
    public void todoWithoutDescription() {
        Todo todo = new Todo(null,null,10);
        assertTrue(todo.isValid());
    }
}
