import { TestBed } from '@angular/core/testing';

import { PresentationDetailResolverService } from './presentation-detail-resolver.service';

describe('PresentationDetailResolverService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PresentationDetailResolverService = TestBed.get(PresentationDetailResolverService);
    expect(service).toBeTruthy();
  });
});
