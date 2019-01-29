import { Time } from '@angular/common';

export class PresentationDTO {

  public id: number;
  public userId: number;
  public title: string;
  public description: string;
  public time: Time;
  public duration: Time;
  public location: string;
  public emailInvitations: string[];
  public mark: number;

  formData() {
    const fd = new FormData();

    fd.append('id', this.id.toString());
    fd.append('userId', this.userId.toString());
    fd.append('title', this.title);
    fd.append('description', this.description);
    fd.append('time', this.time.toString());
    fd.append('duration', this.duration.toString());
    fd.append('location', location.toString());
    fd.append('emailInvitations', this.emailInvitations.toString());
    fd.append('mark', this.mark.toString());

    return fd;
  }

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

}
