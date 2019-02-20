import { Message } from '../message.model';

export class MessageDTO {
  id: string;
  presentationId: string;
  userId: string;
  email: string;
  text: string;
  type: string;
  anonymous: boolean;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(m: MessageDTO): Message {
    return <Message>{
      id: m.id,
      presentationId: m.presentationId,
      userId: m.userId,
      email: m.email,
      text: m.text,
      type: m.type,
      anonymous: m.anonymous
    };
  }
}
