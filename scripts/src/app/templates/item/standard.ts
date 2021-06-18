import { Item } from '../item';
import { Template } from '../index';
import { toTranslated } from '../../utils';

const template: Template = (color: string, name: string): Item => ({
  item:
`{
  "parent": "item/generated",
  "textures": {
    "layer0": "dyetasticcolors:items/${color}_${name}"
  }
}
`,
  lang:
`  item.dyetasticcolors.${color}_${name}: "${toTranslated(color, name)}",
`
});

export default template;
