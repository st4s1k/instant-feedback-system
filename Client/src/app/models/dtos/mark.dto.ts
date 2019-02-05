import { Mark } from '../mark.model';

export class MarkDTO {
  id: number;
  userId: number;
  email: string;
  mark: number;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(m: MarkDTO): Mark {
    return <Mark>{
      id: m.id,
      userId: m.userId,
      email: m.email,
      mark: m.mark
    };
  }
}
