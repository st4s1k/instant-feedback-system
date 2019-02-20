import { MessageDTO } from './dtos/message.dto';

export class Message {
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

  static toDTO(m: Message): MessageDTO {
    return <MessageDTO>{
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
