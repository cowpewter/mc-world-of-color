import { Block } from "./block";
import { Item } from "./item";
import { Recipe } from "./recipe";

export type MCObject = Block | Item | Recipe;

export type Template = (color: string, name: string) => MCObject;
