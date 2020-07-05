package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Material;

import java.util.List;

public abstract class BaseBlock extends MagicFunctions {

    public BaseBlock() {
        super(false);
    }

    public abstract void process(InteractMeta meta);

    public abstract List<Material> getPermittedBlocks();

}
