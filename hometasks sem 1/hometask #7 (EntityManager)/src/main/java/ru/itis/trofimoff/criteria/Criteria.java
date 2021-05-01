package ru.itis.trofimoff.criteria;

import ru.itis.trofimoff.helpers.EntityHelper;

public class Criteria {

    public StringBuilder query = new StringBuilder();

    public static class Builder {
        private Criteria criteria;

        public Builder() {
            criteria = new Criteria();
            criteria.query.append("WHERE ");
        }

        public Builder single(Expression expression) {
            String comparingResult = "";
            if (expression.comparingValue.length == 1) {
                comparingResult = expression.comparingValue[0];
            } else {
                comparingResult = EntityHelper.convertMultipleComparingValues(expression.comparingValue);
            }
            criteria.query
                    .append('(')
                    .append(expression.field)
                    .append(" ")
                    .append(expression.comparingSign)
                    .append(" ")
                    .append(comparingResult)
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

        public Builder clear() {
            criteria.query = new StringBuilder();
            return this;
        }

        public Criteria build() {
            return criteria;
        }
    }
}
