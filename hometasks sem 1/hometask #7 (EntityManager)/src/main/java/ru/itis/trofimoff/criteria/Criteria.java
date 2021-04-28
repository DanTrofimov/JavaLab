package ru.itis.trofimoff.criteria;

public class Criteria {

    public StringBuilder query = new StringBuilder();

    public static class Builder {
        private Criteria criteria;

        public Builder() {
            criteria = new Criteria();
            criteria.query.append("WHERE ");
        }

        public Builder single(Expression expression) {
            criteria.query
                    .append('(')
                    .append(expression.field)
                    .append(expression.comparingSign)
                    .append(expression.comparingValue)
                    .append(')');
            return this;
        }

        public Builder and(Expression expression) {
            criteria.query.append(" AND ");
            return single(expression);
        }

        public Builder or(Expression expression) {
            criteria.query.append(" OR ");
            return single(expression);
        }

        public Criteria build() {
            return criteria;
        }
    }
}
