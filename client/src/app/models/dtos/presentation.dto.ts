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
  public avgMark: number;
  public voteCount: number;
  public started: boolean;
  public finished: boolean;

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
      avgMark: p.avgMark,
      voteCount: p.voteCount,
      started: p.started,
      finished: p.finished
    };
  }

}
