package dataStructures;

import java.util.Objects;

public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pair && Objects.equals(left, ((Pair<L,R>)obj).left) &&
                Objects.equals(right, ((Pair<L,R>)obj).right);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(left) + Objects.hashCode(right);
    }

    @Override
    public String toString() {
        return left + "::" + right;
    }
}
