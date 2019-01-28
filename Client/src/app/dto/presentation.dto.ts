import { Time } from '@angular/common';

export class PresentationDTO {

    private id: number;
    public userId: number;
    public title: string;
    public description: string;
    public time: Time;
    public duration: Time;
    public location: string;
    public emailInvitations: string[];
    public mark: number;

    constructor(obj: Object = {}) {
        Object.assign(this, obj);
    }

}
