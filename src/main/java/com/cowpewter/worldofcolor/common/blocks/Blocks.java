package com.cowpewter.worldofcolor.common.blocks;

import net.minecraft.block.material.MaterialColor;

public class Blocks {
  public static final NamedBlock[] BLOCKS = {
    /* Concrete */
    // Red
    new Concrete("lightest_red_concrete", MaterialColor.COLOR_RED),
    new Concrete("pastel_red_concrete", MaterialColor.COLOR_RED),
    new Concrete("dark_red_concrete", MaterialColor.COLOR_RED),
    new Concrete("darkest_red_concrete", MaterialColor.COLOR_RED),
    // Orange
    new Concrete("lightest_orange_concrete", MaterialColor.COLOR_ORANGE),
    new Concrete("pastel_orange_concrete", MaterialColor.COLOR_ORANGE),
    new Concrete("dark_orange_concrete", MaterialColor.COLOR_ORANGE),
    new Concrete("darkest_orange_concrete", MaterialColor.COLOR_ORANGE),
  };
}
