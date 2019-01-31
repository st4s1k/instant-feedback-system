export class UserDTO {
  public id: number;
  public type: number;
  public email: string;
  public password: string;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }
}
