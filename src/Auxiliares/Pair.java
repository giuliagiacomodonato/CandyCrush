package Auxiliares;

import java.io.Serializable;

public class Pair<A extends Comparable<A>, B> implements Comparable<Pair<A, B>>, Serializable {
    private static final long serialVersionUID = 1L;
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
    public Pair(A first) {
    	this.first = first;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
    @Override
    public int compareTo(Pair<A, B> otherPair) {
        return this.first.compareTo(otherPair.getFirst());
    }

    
}
