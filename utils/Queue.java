package utils;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Queue<E> extends ArrayList<E> {

    public void push(E elem){
        super.add(elem);
    }

    public E pop(){
        if(super.size() <= 0) throw new NoSuchElementException();
        E elem = super.remove(0);
        return elem;
    }

    public E first(){
        return super.get(0);
    }
}