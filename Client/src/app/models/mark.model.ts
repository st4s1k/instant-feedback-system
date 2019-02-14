import { MarkDTO } from './dtos/mark.dto';

export class Mark {
  id: string;
  presentationId: string;
  userId: string;
  value: number;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(m: Mark): MarkDTO {
    return <MarkDTO>{
      id: m.id,
      presentationId: m.presentationId,
      userId: m.userId,
      value: m.value
    };
  }
}
