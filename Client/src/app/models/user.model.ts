import { UserDTO } from './dtos/user.dto';

export class User {
  public id: string;
  public role: string;
  public email: string;
  public password: string;

  constructor(obj: Object = {}) {
    Object.assign(this, obj);
  }

  static toDTO(u: User): UserDTO {
    return <UserDTO>{
      id: u.id,
      role: u.role,
      email: u.email,
      password: u.password
    };
  }
}
