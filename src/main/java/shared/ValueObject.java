package shared;

import java.util.Objects;

public class ValueObject {
    protected final String value;

    public ValueObject(String value) {
        this.value = value;
    }

    public String value(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
