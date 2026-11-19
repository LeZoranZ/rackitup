package me.creeperscrown.rackitup.compat;

import me.creeperscrown.rackitup.RackItUp;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class ModCompat {
    public static void init(){
        EveryCompatAPI.registerModule(new RackItUpCompatModule(RackItUp.MODID, "riu"));
    }
}
