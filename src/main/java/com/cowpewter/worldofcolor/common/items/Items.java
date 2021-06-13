package com.cowpewter.worldofcolor.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Items {
  public static final NamedItem[] ITEMS = {
    // Dye Modifiers
    new NamedItem("color_intensifier", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)),
    new NamedItem("color_fader", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)),

    // Dyes
    // Red
    new NamedItem("lightest_red_dye", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)),
    new NamedItem("pastel_red_dye", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)),
    new NamedItem("dark_red_dye", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)),
    new NamedItem("darkest_red_dye", new Item.Properties().tab(ItemGroup.TAB_MATERIALS)),
  };
}
