package br.edu.ufrn.foodium.domain.service.recommendation;

public class Tuple implements Comparable<Tuple> {
    private Float index;
    private Object value;

    public Tuple(Float index, Object value) {
        this.index = index;
        this.value = value;
    }

    public Float getIndex() {
        return index;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(Tuple o) {
        return this.index.compareTo(o.getIndex());
    }
}
