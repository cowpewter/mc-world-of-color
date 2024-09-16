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
	"ambientocclusion": false,
	"textures": {
		"particle": "dyetasticcolors:blocks/${color}_${name}",
		"flowerpot": "dyetasticcolors:blocks/${color}_${name}",
		"dirt": "minecraft:block/dirt"
	},
	"elements": [
		{"from": [5, 0, 5],
			"to": [6, 6, 11],
			"faces": {
				"down": {"uv": [5, 5, 6, 11], "texture": "#flowerpot", "cullface": "down"},
				"up": {"uv": [5, 5, 6, 11], "texture": "#flowerpot"},
				"north": {"uv": [10, 10, 11, 16], "texture": "#flowerpot"},
				"south": {"uv": [5, 10, 6, 16], "texture": "#flowerpot"},
				"west": {"uv": [5, 10, 11, 16], "texture": "#flowerpot"},
				"east": {"uv": [5, 10, 11, 16], "texture": "#flowerpot"}
			}
		},
		{"from": [10, 0, 5],
			"to": [11, 6, 11],
			"faces": {
				"down": {"uv": [10, 5, 11, 11], "texture": "#flowerpot", "cullface": "down"},
				"up": {"uv": [10, 5, 11, 11], "texture": "#flowerpot"},
				"north": {"uv": [5, 10, 6, 16], "texture": "#flowerpot"},
				"south": {"uv": [10, 10, 11, 16], "texture": "#flowerpot"},
				"west": {"uv": [5, 10, 11, 16], "texture": "#flowerpot"},
				"east": {"uv": [5, 10, 11, 16], "texture": "#flowerpot"}
			}
		},
		{"from": [6, 0, 5],
			"to": [10, 6, 6],
			"faces": {
				"down": {"uv": [6, 10, 10, 11], "texture": "#flowerpot", "cullface": "down"},
				"up": {"uv": [6, 5, 10, 6], "texture": "#flowerpot"},
				"north": {"uv": [6, 10, 10, 16], "texture": "#flowerpot"},
				"south": {"uv": [6, 10, 10, 16], "texture": "#flowerpot"}
			}
		},
		{"from": [6, 0, 10],
			"to": [10, 6, 11],
			"faces": {
				"down": {"uv": [6, 5, 10, 6], "texture": "#flowerpot", "cullface": "down"},
				"up": {"uv": [6, 10, 10, 11], "texture": "#flowerpot"},
				"north": {"uv": [6, 10, 10, 16], "texture": "#flowerpot"},
				"south": {"uv": [6, 10, 10, 16], "texture": "#flowerpot"}
			}
		},
		{"from": [6, 0, 6],
			"to": [10, 4, 10],
			"faces": {
				"down": {"uv": [6, 12, 10, 16], "texture": "#flowerpot", "cullface": "down"},
				"up": {"uv": [6, 6, 10, 10], "texture": "#dirt"}
			}
		}
	]
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
