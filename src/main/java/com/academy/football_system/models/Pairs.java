package com.academy.football_system.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.Objects;

@Getter
public class Pairs<T, U> {
    private final T first;
    private final U second;

    public Pairs(T first, U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pairs<?, ?> pair = (Pairs<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }

}
