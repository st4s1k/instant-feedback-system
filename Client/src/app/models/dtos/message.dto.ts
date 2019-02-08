import { Message } from '../message.model';

export class MessageDTO {
  id: string;
  email: string;
  message: string;
  type: string;
  anonymity: boolean;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(m: MessageDTO): Message {
    return <Message>{
      id: m.id,
      email: m.email,
      message: m.message,
      type: m.type,
      anonymity: m.anonymity
    };
  }
}
