import { TestBed } from '@angular/core/testing';

import { PresentationListDetailResolverService } from './presentation-list-detail-resolver.service';

describe('PresentationListDetailResolverService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PresentationListDetailResolverService = TestBed.get(PresentationListDetailResolverService);
    expect(service).toBeTruthy();
  });
});
