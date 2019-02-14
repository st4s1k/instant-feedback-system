import { PaticipantDTO } from './dtos/participant.dto';

export class Participant {
    public id: string;
    public presentationID: string;
    public email: string;

    constructor(obj: Object = {}) {
        Object.assign(this, obj);
    }

    static toDTO(p: Participant): PaticipantDTO {
        return <PaticipantDTO>{
            id: p.id,
            presentationID: p.presentationID,
            email: p.email
        };
    }
}
