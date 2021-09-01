package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

import java.util.Collections;
import java.util.List;

public class Spawner extends BaseBlock {
    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        BlockFace face = meta.getFace();

        CreatureSpawner originalSpawner = (CreatureSpawner) block.getState();
        EntityType originalEntityType = originalSpawner.getSpawnedType();
        int originalEntityDelay = originalSpawner.getDelay();
        Block newSpawn = block.getRelative(face.getOppositeFace());
        Material newType = newSpawn.getType();

        if (newType.isAir()) {
            block.setType(newType, false);
            newSpawn.setType(Material.SPAWNER, false);

            BlockState blockState = newSpawn.getState();

            ((CreatureSpawner) blockState).setSpawnedType(originalEntityType);
            ((CreatureSpawner) blockState).setDelay(originalEntityDelay);

            blockState.update(true);
        }
    }

    public List<Material> getPermittedBlocks() {
        return Collections.singletonList(Material.SPAWNER);
    }
}
