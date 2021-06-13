package com.cowpewter.worldofcolor.common.items;

import com.cowpewter.worldofcolor.WorldOfColor;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class NamedItem extends Item {
  NamedItem(String name, Item.Properties props) {
    super(props);
    this.setRegistryName(new ResourceLocation(WorldOfColor.ID, name));
  }
}
