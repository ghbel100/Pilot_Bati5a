package hhu.propra.bati5a.domain.service;

public class MatchRating<T> {
    private final T entity;
    private final int score;

    public MatchRating(T entity, int score) {
        this.entity = entity;
        this.score = score;
    }

    public T getEntity() {
        return entity;
    }

    public int getScore() {
        return score;
    }
}
