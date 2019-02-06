import { UserDTO } from './dtos/user.dto';

export class User {
  public id: number;
  public type: string;
  public email: string;
  public password: string;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(u: User): UserDTO {
    return <UserDTO>{
      id: u.id,
      type: u.type,
      email: u.email,
      password: u.password
    };
  }
}
