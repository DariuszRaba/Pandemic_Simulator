import {Component, Input, OnInit} from '@angular/core';
import {SimulationService} from "../service/simulation.service";
import {simulationDto} from "../service/dto";
import { Location } from '@angular/common';


@Component({
  selector: 'app-simuladion-details',
  templateUrl: './simulation-details.component.html',
  styleUrls: ['./simulation-details.component.css']
})
export class SimulationDetailsComponent implements OnInit {
  id!:number;
  simulationDto! : simulationDto;
  constructor(private simulationService: SimulationService) { }

  ngOnInit(): void {
    this.id = this.simulationService.simulationId;
    this.getTheSimulation(this.id)
  }

  getTheSimulation(id: number) {
    this.simulationService.getTheSimulation(id).subscribe(data => this.simulationDto = data);
  }

  removeSimulation(id: number) {
  this.simulationService.remove(id).subscribe();

  }
}
