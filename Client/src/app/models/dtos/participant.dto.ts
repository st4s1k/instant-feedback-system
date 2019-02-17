import { Participant } from '../participant.model';

export class ParticipantDTO {
    public id: string;
    public presentationId: string;
    public email: string;

    constructor(obj: Object = {}) {
        Object.assign(this, obj);
    }

    static toModel(p: ParticipantDTO): Participant {
        return <Participant>{
            id: p.id,
            presentationId: p.presentationId,
            email: p.email
        };
    }
}
