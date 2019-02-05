import { TestBed } from '@angular/core/testing';

import { UserListDetailResolverService } from './user-list-detail-resolver.service';

describe('UserListDetailResolverService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserListDetailResolverService = TestBed.get(UserListDetailResolverService);
    expect(service).toBeTruthy();
  });
});
