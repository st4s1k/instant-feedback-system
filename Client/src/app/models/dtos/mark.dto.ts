import { Mark } from '../mark.model';

export class MarkDTO {
  id: string;
  presentationId: string;
  userId: string;
  value: number;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(m: MarkDTO): Mark {
    return <Mark>{
      id: m.id,
      presentationId: m.presentationId,
      userId: m.userId,
      value: m.value
    };
  }
}
