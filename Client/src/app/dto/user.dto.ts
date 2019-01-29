export class UserDTO {
  public id: number;
  public type: number;
  public email: string;
  public password: string;

  formData() {
    const fd = new FormData();

    fd.append('email', this.email);
    fd.append('password', this.password);

    return fd;
  }
}
