import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';

import { PresentationService } from '../services/presentation.service';
import { Presentation } from '../models/presentation.model';
import { ActivatedRoute } from '@angular/router';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Message } from '../models/message.model';
import { Mark } from '../models/mark.model';
import { GlobalServUserService } from '../services/global-serv-user.service';
import { MarkService } from '../services/mark.service';
import { MessageService } from '../services/message.service';
import { MessageDTO } from '../models/dtos/message.dto';
import { environment } from 'src/environments/environment.prod';
import {MarkDTO} from '../models/dtos/mark.dto';

@Component({
  selector: 'app-presentation-page',
  templateUrl: './presentation-page.component.html',
  styleUrls: ['./presentation-page.component.scss']
})
export class PresentationPageComponent implements OnInit {

  TYPE_FEEDBACK = environment.msgTypeFeedback;
  TYPE_QUESTION = environment.msgTypeQuestion;

  presentation: Presentation;
  feedback: Message[];

  currentRate = 0;
  submittedRate = false;
  submittedFeedback = false;
  feedbackAdded = false;
  canVote = false;
  userMark: Mark;
  userId: string;
  ratingsCount = 0;
  editingMessage = -1;

  authenticated = localStorage.getItem('sessionID');

  type: FormControl = this.fb.control(this.TYPE_FEEDBACK);
  anonymous: FormControl = this.fb.control(false);
  feedbackBox: FormControl = this.fb.control('', Validators.required);
  @ViewChild('fbb') feedbackBoxView: ElementRef;

  feedbackFormGroup: FormGroup = this.fb.group({
    feedbackBox: this.feedbackBox,
    type: this.type,
    anonymity: this.anonymous
  });

  constructor(
    private ps: PresentationService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private gs: GlobalServUserService,
    private ms: MarkService,
    private msgSrv: MessageService
  ) {
    this.gs.SessionID.subscribe((sessId) => this.authenticated = sessId);
  }

  ngOnInit() {

    this.userId = localStorage.getItem('userId');

    this.route.data.subscribe((data: {
      presentation: Presentation,
    }) => {
      this.presentation = data.presentation;

      if (this.userId) {
        this.ms.getUserMark(this.userId)
          .pipe(first()).subscribe(markDto => {
          if (markDto) {
            this.userMark = MarkDTO.toModel(markDto);
          } else {
            this.canVote = true;
          }
        });
      } else {
        this.canVote = false;
      }

      this.msgSrv.getPresentationMessages(this.presentation.id)
        .pipe(first()).subscribe(messagesDto => {
        if (messagesDto) {
          this.feedback = messagesDto.map(messageDto =>
            MessageDTO.toModel(messageDto));
        }
      });
    });
  }

  submitRate(rate: number) {

    this.submittedRate = true;
    this.canVote = false;
    this.userMark = new Mark(<Mark>{
      presentationId: this.presentation.id,
      value: rate
    });
    this.currentRate = rate;

    this.ms.addMark(new Mark(<Mark>{
      userId: localStorage.getItem('userId'),
      presentationId: this.presentation.id,
      value: rate
    })).pipe(first()).subscribe( avgMark => this.presentation.avgMark = avgMark,
      error => {
        alert('Error!');
        console.log('Error!: ' + JSON.stringify(error));
      }
    );
  }

  editMessage(i: number) {
    alert('editing message ' + i);

    this.editingMessage = i;

    this.feedbackBox.setValue(this.feedback[i].text);
    this.anonymous.setValue(this.feedback[i].anonymous);
    this.type.setValue(this.feedback[i].type);

    this.feedbackBoxView.nativeElement.focus();
  }

  deleteMessage(i: number) {
    if (confirm('Are you sure, you want to delete this message?')) {
      alert(`Deleting message[${i}].id = ${this.feedback[i].id}`);
      this.msgSrv.deleteMessage(this.feedback[i].id);
    }
  }

  leaveFeedback() {

    this.submittedFeedback = true;

    if (this.feedbackFormGroup.invalid) {
      return;
    }

    this.sendMessage(new Message(<Message>{
      userId: localStorage.getItem('userId'),
      presentationId: this.presentation.id,
      email: (this.anonymous.value ? 'anonymous' : localStorage.getItem('email')),
      text: this.feedbackBox.value,
      type: this.type.value,
      anonymous: this.anonymous.value
    })).pipe(first()).subscribe(messageDto => {
        const message = MessageDTO.toModel(messageDto);
        if (this.editingMessage >= 0) {
          this.feedback[this.editingMessage] = message;
        } else {
          if (!this.feedback) {
            this.feedback = [];
          }
          this.feedback.push(message);
        }
        alert('Succes!:' + JSON.stringify(messageDto));
      }, error => {
        alert('Error!: ' + error);
      }
    );



    this.feedbackFormGroup.reset({
      feedbackBox: '',
      type: this.TYPE_FEEDBACK,
      anonymity: false
    });

    this.submittedFeedback = false;
  }

  sendMessage(currentFeedback: Message) {
    if (this.editingMessage >= 0) {
      return this.msgSrv.updateMessage(currentFeedback);
    } else {
      return this.msgSrv.addMessage(currentFeedback);
    }
  }
}
