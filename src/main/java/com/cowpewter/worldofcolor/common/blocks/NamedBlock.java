package com.cowpewter.worldofcolor.common.blocks;

import com.cowpewter.worldofcolor.WorldOfColor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class NamedBlock extends Block implements INamedBlock {

  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;

  NamedBlock(String name, AbstractBlock.Properties props) {
		super(props);
		this.setRegistryName(WorldOfColor.ID, name);
	}

  public Boolean hasItem() {
    return this.hasItem;
  }

  public INamedBlock setItemProperties(Item.Properties itemProps) {
    this.hasItem = true;
    this.itemProps = itemProps;
    return this;
  }

  public Item.Properties getItemProperties() {
    return this.itemProps;
  }
}
