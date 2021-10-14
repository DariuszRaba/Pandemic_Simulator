import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSimulationComponent } from './create-simulation.component';

describe('CreateSimulationComponent', () => {
  let component: CreateSimulationComponent;
  let fixture: ComponentFixture<CreateSimulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSimulationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
