package ru.itis.trofimoff.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Expression {
    public String field;
    public String comparingSign;
    public String comparingValue;
}
