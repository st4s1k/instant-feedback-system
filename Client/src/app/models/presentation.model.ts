export class Presentation {

  public id: number;
  public email: string;
  public title: string;
  public description: string;
  public startDate: string;
  public place: string;
  public endDate: string;
  public participants: {
    email: string
  }[];
  public feedback: {
    email: string,
    message: string,
    type: string,
    anonimity: boolean
  }[];
  public marks: {
    userId: number,
    email: string,
    mark: number
  }[];

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

}
