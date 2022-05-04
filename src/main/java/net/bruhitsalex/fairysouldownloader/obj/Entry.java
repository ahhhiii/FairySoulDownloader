package net.bruhitsalex.fairysouldownloader.obj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Entry {

    private Position position;
    private Location location;
    private String description;
    private String extraInfo;

}
