import { MessageDTO } from './message.dto';
import { MarkDTO } from './mark.dto';
import { Presentation } from '../presentation.model';

export class PresentationDTO {

  public id: string;
  public email: string;
  public title: string;
  public description: string;
  public startTime: string;
  public endTime: string;
  public date: string;
  public place: string;
  public participants: string[];
  public messages: MessageDTO[];
  public marks: MarkDTO[];

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(p: PresentationDTO): Presentation {
    return <Presentation>{
      id: p.id,
      email: p.email,
      title: p.title,
      description: p.description,
      startTime: p.startTime,
      endTime: p.endTime,
      place: p.place,
      date: p.date,
      participants: p.participants,
      feedback: p.messages ? p.messages.map(message => MessageDTO.toModel(message)) : [],
      marks: p.marks ? p.marks.map(mark => MarkDTO.toModel(mark)) : []
    };
  }

}
