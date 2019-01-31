import { TestBed } from '@angular/core/testing';

import { GlobalServUserService } from './global-serv-user.service';

describe('GlobalServUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GlobalServUserService = TestBed.get(GlobalServUserService);
    expect(service).toBeTruthy();
  });
});
