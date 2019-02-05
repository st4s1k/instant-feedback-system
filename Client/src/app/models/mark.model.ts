import { MarkDTO } from './dtos/mark.dto';

export class Mark {
  id: number;
  userId: number;
  email: string;
  mark: number;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(m: Mark): MarkDTO {
    return <MarkDTO>{
      id: m.id,
      userId: m.userId,
      email: m.email,
      mark: m.mark
    };
  }
}
