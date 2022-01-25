package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.ScrollElement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.BigDripleaf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dripleaf extends ScrollBlock {

    private static final ScrollElement<BigDripleaf.Tilt> tilts = new ScrollElement<>(Arrays.asList(BigDripleaf.Tilt.values()));

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        BigDripleaf data = (BigDripleaf) block.getBlockData();

        BigDripleaf.Tilt tilt = data.getTilt();
        tilt = tilts.scrollItem(tilt, meta);

        data.setTilt(tilt);
        getActionMessage().message(meta.getPlayer(), ChatColor.RED, "Tilt: " + tilt);
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Collections.singletonList(Material.BIG_DRIPLEAF);
    }
}
