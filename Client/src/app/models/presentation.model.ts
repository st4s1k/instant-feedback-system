import { Time } from '@angular/common';

export class Presentation {

    id: number;
    userId: number;
    title: string;
    description: string;
    time: Time;
    duration: Time;
    location: string;
    emailInvitations: string[];
    mark: number;

}
