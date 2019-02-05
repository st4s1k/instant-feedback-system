import { Message } from './message.model';
import { Mark } from './mark.model';
import { User } from './user.model';
import { PresentationDTO } from '../models/dtos/presentation.dto';

export class Presentation {

  public id: number;
  public email: string;
  public title: string;
  public description: string;
  public startDate: string;
  public place: string;
  public endDate: string;
  public participants: User[];
  public feedback: Message[];
  public marks: Mark[];

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(p: Presentation): PresentationDTO {
    // console.log('presentation.model.toDto: ' + JSON.stringify(p));
    return <PresentationDTO>{
      id: p.id,
      email: p.email,
      title: p.title,
      description: p.description,
      startDate: p.startDate,
      place: p.place,
      endDate: p.endDate,
      participants: p.participants ? p.participants.map(participant => User.toDTO(participant)) : [],
      messages: p.feedback ? p.feedback.map(message => Message.toDTO(message)) : [],
      marks: p.marks ? p.marks.map(mark => Mark.toDTO(mark)) : []
    };
  }

}
