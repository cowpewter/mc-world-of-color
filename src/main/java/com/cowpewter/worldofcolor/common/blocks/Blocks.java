package com.cowpewter.worldofcolor.common.blocks;

import java.util.ArrayList;

public class Blocks {
  /**
   * Blocks that have no dependencies, they just need to exist
   *
   * @return ArrayList<INamedBlock>
   */
  public static INamedBlock[] getBlocks() {
    ArrayList<INamedBlock> blockList = new ArrayList<INamedBlock>();

    // Concrete
    blockList.addAll(Concrete.generateAllColors());

    // Concrete Powder
    blockList.addAll(ConcretePowder.generateAllColors());

    // Wool
    blockList.addAll(Wool.generateAllColors());

    // TODO everything else

    return blockList.stream().toArray(INamedBlock[]::new);
  }
}
