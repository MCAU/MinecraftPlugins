package com.ullarah.uchest.data;

public enum ChestType {
    DONATION(new DonationChest()),
    ENCHANTMENT(new EnchantmentChest()),
    EXPERIENCE(new ExperienceChest()),
    HOLD(new HoldChest()),
    MONEY(new MoneyChest()),
    RANDOM(new RandomChest()),
    SHUFFLE(new ShuffleChest()),
    SWAP(new SwapChest()),
    VAULT(new VaultChest());

    public final ChestBase instance;
    public final char shortcut;

    ChestType(ChestBase instance) {
        this.instance = instance;
        this.shortcut = instance.getShortcut();
    }
}
