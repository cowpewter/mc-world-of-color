import { Template } from '../index';

export interface Recipe {
  filename: string;
  recipe: string;
}

const templates: {[key: string]: Template} = {
};

export default templates;
