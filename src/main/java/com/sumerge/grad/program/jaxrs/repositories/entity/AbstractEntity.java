package com.sumerge.grad.program.jaxrs.repositories.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity implements Serializable {

    public abstract Object getId();

    @Override
    public String toString() {
        return "AbstractEntity[ " +
                getId() +
                " ]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
