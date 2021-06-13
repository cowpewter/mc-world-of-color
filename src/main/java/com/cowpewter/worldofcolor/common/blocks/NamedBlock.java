package com.cowpewter.worldofcolor.common.blocks;

import com.cowpewter.worldofcolor.WorldOfColor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class NamedBlock extends Block {

  public Item.Properties itemProps;
  public Boolean hasItem = false;

  NamedBlock(String name, AbstractBlock.Properties props)
	{
		super(props);
		this.setRegistryName(WorldOfColor.ID, name);
	}

  public NamedBlock setItemProperties(Item.Properties itemProps)
  {
    this.hasItem = true;
    this.itemProps = itemProps;
    return this;
  }
}
