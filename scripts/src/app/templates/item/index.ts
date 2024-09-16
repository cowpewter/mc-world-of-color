import { Template } from '../index';

import standard from './standard';

export interface Item {
  item: string;
  lang: string;
}

const templates: {[key: string]: Template} = {
  standard,
};

export default templates;
