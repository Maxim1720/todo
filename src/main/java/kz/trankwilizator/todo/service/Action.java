package kz.trankwilizator.todo.service;

public interface Action<T> {

    void execute(T t);

}
