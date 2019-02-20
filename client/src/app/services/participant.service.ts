import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParticipantDTO } from '../models/dtos/participant.dto';
import { Participant } from '../models/participant.model';
import { environment } from 'src/environments/environment.prod';

const SERVER_URL = environment.serverUrl;
const PARTICIPANTS_API = environment.participantsApiRoute;

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  constructor(private http: HttpClient) { }

  addParticipants(participants: Participant[]) {
    return this.http.put<ParticipantDTO>(SERVER_URL + PARTICIPANTS_API, participants.map(participant => Participant.toDTO(participant)));
  }

  getPresentationParticipants(presentationId: string) {
    return this.http.get<ParticipantDTO[]>(SERVER_URL + PARTICIPANTS_API, {
      params: {
        presentationId: presentationId
      }
    });
  }
}
