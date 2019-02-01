import { TestBed } from '@angular/core/testing';

import { UserPresentationResolverService } from './user-presentation-resolver.service';

describe('UserPresentationResolverService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserPresentationResolverService = TestBed.get(UserPresentationResolverService);
    expect(service).toBeTruthy();
  });
});
