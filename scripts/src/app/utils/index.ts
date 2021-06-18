import fs from 'fs/promises';
import { Block } from '../templates/block';
import { Item } from '../templates/item';
import { Recipe } from '../templates/recipe';
import { MCObject } from '../templates';

export const ensureDir = async (path: string) => {
  try {
    await fs.mkdir(path);
  } catch (err) {
    if (err.code !== 'EEXIST') {
      throw err;
    }
  }
  return true;
}

export const ensureFile = async (path: string) => {
  const temp = await fs.open(path, 'a');
  await temp.close();
  return true;
}

export const rmDirRecursive = async (path: string) => {
  const pathStat = await fs.stat(path);
  if (pathStat.isDirectory()) {
    const files = await fs.readdir(path);
    const promises: any[] = files.map(async (file) => {
      const filePath = `${path}/${file}`;
      const fileStat = await fs.stat(filePath);
      if (fileStat.isDirectory()) {
        return rmDirRecursive(filePath);
      } else {
        return fs.unlink(filePath);
      }
    });
    return Promise.all(promises);
  }
}

const capitalize = (s: string) =>  s.charAt(0).toUpperCase() + s.slice(1);

export const toTranslated = (color: string, name: string): string => {
  const colors = color.split('_');
  const names = name.split('_');

  return colors.concat(names).map(s => capitalize(s)).join(' ');
}

export const isBlock = (obj: MCObject): obj is Block =>
  (obj as Block).blockstate !== undefined;

export const isItem = (obj: MCObject): obj is Item =>
  (obj as Item).item !== undefined;

export const isRecipe = (obj: MCObject): obj is Recipe =>
  (obj as Recipe).recipe !== undefined;
