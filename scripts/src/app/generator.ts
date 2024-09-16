import inquirer, { Answers } from 'inquirer';
import fs from 'fs/promises';
import path from 'path';
import { exit } from 'process';

import { ensureDir, isBlock, isItem, isRecipe, rmDirRecursive } from './utils';
import { generateAllColors } from './utils/colors';

import { Template } from './templates';
import blockTemplates from './templates/block';
import itemTemplates from './templates/item';
import recipeTemplates from './templates/recipe';

const PATH_TO_OUTPUT = path.join(process.cwd(), 'output');
const PATH_TO_TRANSLATIONS_FILE = `${PATH_TO_OUTPUT}/lang/en_us.txt`;
const PATH_TO_BLOCKSTATES = 'blockstates';
const PATH_TO_MODELS_BLOCK = 'models/block';
const PATH_TO_MODELS_ITEM = 'models/item';
const PATH_TO_RECIPES = 'recipes';

const clearOutput = async () => {
  await rmDirRecursive(PATH_TO_OUTPUT);
}

const writeTemplate = async (data: string, path: string, filename: string) => {
  const paths = path.split('/');
  let currentPath = PATH_TO_OUTPUT;
  for (let i = 0; i < paths.length; i++) {
    currentPath += `/${paths[i]}`;
    ensureDir(currentPath);
  }
  return fs.writeFile(`${currentPath}/${filename}`, data);
}

const writeLang = async (data: string) => {
  return fs.appendFile(PATH_TO_TRANSLATIONS_FILE, data);
}

const runTemplate = async (template: Template, name: string, allColors: boolean) => {
  const colors = generateAllColors(allColors);

  const promises = colors.map((color) => {
    const data = template(color, name);
    const filename = `${color}_${name}.json`;

    if (isBlock(data)) {
      return [
        writeTemplate(data.blockstate, PATH_TO_BLOCKSTATES, filename),
        writeTemplate(data.model, PATH_TO_MODELS_BLOCK, filename),
        writeTemplate(data.item, PATH_TO_MODELS_ITEM, filename),
        writeLang(data.lang),
      ];
    }

    if (isItem(data)) {
      return [
        writeTemplate(data.item, PATH_TO_MODELS_ITEM, filename),
        writeLang(data.lang),
      ];
    }

    if (isRecipe(data)) {
      return [
        writeTemplate(data.recipe, PATH_TO_RECIPES, data.filename),
      ];
    }
  });

  await Promise.all(promises.flat());
}

const Generator = () => {
  // Empty the output folder from the last run
  clearOutput();

  const questions: inquirer.Question[] = [
    {
      name: 'type',
      message: 'What kind of thing do you want to generate? (b)lock|(i)tem|(r)ecipe',
      validate: (input: string) => ['b', 'i', 'r'].indexOf(input) !== -1,
    },
    {
      name: 'template',
      message: 'What the name of the template you want to use?',
      validate: (input: string, answers: Answers) => {
        switch (answers.type) {
          case 'b':
            return blockTemplates.hasOwnProperty(input);
          case 'i':
            return itemTemplates.hasOwnProperty(input);
          case 'r':
            return recipeTemplates.hasOwnProperty(input);
          default:
            return false;
        }
      },
    },
    {
      name: 'name',
      message: 'What is this block called? (in lower_snake_case)',
    },
    {
      name: 'allColors',
      type: 'confirm',
      message: 'Include vanilla colors?',
      default: false,
    },
  ];

  inquirer.prompt(questions)
    .then((answers: Answers) => {
      const { type, template, name, allColors } = answers;
      let templateFunc: Template;
      switch (answers.type) {
        case 'b':
          templateFunc = blockTemplates[template];
          break;
        case 'i':
          templateFunc = itemTemplates[template];
          break;
        case 'r':
          templateFunc = recipeTemplates[template];
          break;
        default:
          throw Error(`Failed to get ${type} template ${template}`);
      }
      runTemplate(templateFunc, name, allColors);
    })
    .catch((error: Error) => {
      console.error(error.message);
      exit();
    });
};

export default Generator;
