import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaticipantDTO } from '../models/dtos/participant.dto';
import { Participant } from '../models/participant.model';
import { environment } from 'src/environments/environment.prod';

const SERVER_URL = environment.serverUrl;
const PARTICIPANT_API = environment.participantApiRoute;

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  constructor(private http: HttpClient) { }

  addParticipants(participants: Participant[]) {
    return this.http.put<PaticipantDTO>(SERVER_URL + PARTICIPANT_API, participants.map(participant => Participant.toDTO(participant)));
  }

}
