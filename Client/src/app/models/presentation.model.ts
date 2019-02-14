import { Message } from './message.model';
import { Mark } from './mark.model';
import { PresentationDTO } from './dtos/presentation.dto';

export class Presentation {

  public id: string;
  public email: string;
  public title: string;
  public description: string;
  public startTime: string;
  public endTime: string;
  public date: string;
  public place: string;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(p: Presentation): PresentationDTO {
    return <PresentationDTO>{
      id: p.id,
      email: p.email,
      title: p.title,
      description: p.description,
      startTime: p.startTime,
      endTime: p.endTime,
      place: p.place,
      date: p.date,
    };
  }

}
