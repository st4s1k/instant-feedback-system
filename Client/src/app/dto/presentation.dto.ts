import { Time } from '@angular/common';

export class PresentationDTO {

  public id: number;
  public userId: number;
  public title: string;
  public description: string;
  public time: string;
  public duration: string;
  public date: string;
  public location: string;
  public emailInvitations: string[];
  public mark: string;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

}
