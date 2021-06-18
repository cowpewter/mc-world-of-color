import { Block } from '../block';
import { Template } from '../index';
import { toTranslated } from '../../utils';

const template: Template = (color: string, name: string): Block => ({
  blockstate:
`{
  "variants": {
    "": {
      "model": "dyetasticcolors:block/${color}_${name}"
    }
  }
}
`,
  model:
`{
  "parent": "block/cube",
  "textures": {
    "down": "dyetasticcolors:blocks/${color}_${name}",
    "up": "dyetasticcolors:blocks/${color}_${name}",
    "north": "dyetasticcolors:blocks/${color}_${name}",
    "east": "dyetasticcolors:blocks/${color}_${name}",
    "south": "dyetasticcolors:blocks/${color}_${name}",
    "west": "dyetasticcolors:blocks/${color}_${name}",
    "particle": "dyetasticcolors:blocks/${color}_${name}"
  }
}
`,
  item:
`{
    "parent": "dyetasticcolors:block/${color}_${name}",
    "textures": {
      "layer0": "dyetasticcolors:items/${color}_${name}"
    }
  }
`,
  lang:
`  block.dyetasticcolors.${color}_${name}: "${toTranslated(color, name)}",
`
});

export default template;
