import { ParticipantDTO } from './dtos/participant.dto';

export class Participant {
    public id: string;
    public presentationId: string;
    public email: string;

    constructor(obj: Object = {}) {
        Object.assign(this, obj);
    }

    static toDTO(p: Participant): ParticipantDTO {
        return <ParticipantDTO>{
            id: p.id,
            presentationId: p.presentationId,
            email: p.email
        };
    }
}
