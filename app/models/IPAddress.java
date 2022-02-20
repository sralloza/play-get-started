package models;

import lombok.Data;

@Data
public class IPAddress {
    private final String address;

    @Override
    public String toString() {
        return String.format("{\"ip\":\"%s\"}", this.address);
    }
}
