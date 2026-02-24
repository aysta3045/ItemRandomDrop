package aysta3045.itemrandomdrop;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.ItemEntity;

public class RandomDropEventHandler {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (ItemRandomDrop.enabled && entity instanceof ItemEntity itemEntity) {
                ItemRandomDrop.replaceWithRandomItem(itemEntity);
            }
        });
    }
}