import { MessageDTO } from './dtos/message.dto';

export class Message {
  id: number;
  email: string;
  message: string;
  type: string;
  anonymity: boolean;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(m: Message): MessageDTO {
    return <MessageDTO>{
      id: m.id,
      email: m.email,
      message: m.message,
      type: m.type,
      anonymity: m.anonymity
    };
  }
}
