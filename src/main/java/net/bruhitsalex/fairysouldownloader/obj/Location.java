package net.bruhitsalex.fairysouldownloader.obj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Location {

    HUB("hub"),
    THE_BARN("barn"),
    MUSHROOM_DESERT("mushdesert"),
    GOLD_MINE("goldmine"),
    DEEP_CAVERNS("deepcaverns"),
    DWARVEN_MINES("DwarfMine"),
    SPIDERS_DEN("spiderden"),
    CRIMSON_ISLE("blazefort"),
    THE_END("endermanhovel"),
    THE_PARK("treeground"),
    WINTER_ISLAND("coldjerry"),
    DUNGEON_HUB("dunghubba");

    private String elementId;

}
