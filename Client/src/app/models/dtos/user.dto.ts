import { User } from '../user.model';

export class UserDTO {
  public id: string;
  public role: string;
  public email: string;
  public password: string;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toModel(u: UserDTO): User {
    return <User>{
      id: u.id,
      role: u.role,
      email: u.email,
      password: u.password
    };
  }
}
