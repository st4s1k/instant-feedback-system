import { Participant } from '../participant.model';

export class PaticipantDTO {
    public id: string;
    public presentationID: string;
    public email: string;

    constructor(obj: Object = {}) {
        Object.assign(this, obj);
    }

    static toModel(p: PaticipantDTO): Participant {
        return <Participant>{
            id: p.id,
            presentationID: p.presentationID,
            email: p.email
        };
    }
}
