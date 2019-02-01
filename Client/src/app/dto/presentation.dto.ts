import { Time } from '@angular/common';

export class PresentationDTO {

  public id: number;
  public email: string;
  public title: string;
  public description: string;
  public startDate: string;
  public endDate: string;
  public location: string;
  public participants: {
    id: number,
    email: string
  }[] = [];
  public messages: {
    email: string,
    message: string,
    type: string,
    anon: boolean
  }[] = [];
  public marks: {
    email: string,
    mark: number
  }[] = [];

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

}
