package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.ScrollElement;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Noteblock extends ScrollBlock {

    private static final ScrollElement<Instrument> instruments = new ScrollElement<>(Arrays.asList(Instrument.values()));

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        NoteBlock data = (NoteBlock) block.getBlockData();

        Instrument in = data.getInstrument();
        in = instruments.scrollItem(in, meta);
        data.setInstrument(in);
        getActionMessage().message(meta.getPlayer(), ChatColor.RED, "Instrument: " + in);
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Collections.singletonList(Material.NOTE_BLOCK);
    }
}
