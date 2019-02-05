import { UserDTO } from './user.dto';
import { MessageDTO } from './message.dto';
import { MarkDTO } from './mark.dto';
import { Presentation } from '../presentation.model';

export class PresentationDTO {

  public id: number;
  public email: string;
  public title: string;
  public description: string;
  public startDate: string;
  public place: string;
  public endDate: string;
  public participants: UserDTO[];
  public messages: MessageDTO[];
  public marks: MarkDTO[];

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(p: PresentationDTO): Presentation {
    // console.log('presentation.dto.toModel: ' + JSON.stringify(p));
    return <Presentation>{
      id: p.id,
      email: p.email,
      title: p.title,
      description: p.description,
      startDate: p.startDate,
      place: p.place,
      endDate: p.endDate,
      participants: p.participants ? p.participants.map(participant => UserDTO.toModel(participant)) : [],
      feedback: p.messages ? p.messages.map(message => MessageDTO.toModel(message)) : [],
      marks: p.marks ? p.marks.map(mark => MarkDTO.toModel(mark)) : []
    };
  }

}
