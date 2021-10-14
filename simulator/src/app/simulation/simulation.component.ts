import {Component, OnInit} from '@angular/core';
import {SimulationService} from "../service/simulation.service";
import {simulationList} from "../service/dto";

@Component({
  selector: 'app-simulation',
  templateUrl: './simulation.component.html',
  styleUrls: ['./simulation.component.css']
})
export class SimulationComponent implements OnInit {
  simList: simulationList = {};

  constructor(private simulationService: SimulationService) {
  }

  ngOnInit(): void {
    this.getAllSimulations();
  }
  sendI(id:number){
    this.simulationService.simulationId = id;
  }

  getAllSimulations() {
    this.simulationService.getAllSimulations().subscribe(data => this.simList.simulations = data);
  }

  removeSimulation(id: number) {
    this.simulationService.remove(id).subscribe();
    // this.getAllSimulations();
  }

  // getTheSimulations(id: number) {
  //   this.simulationService.getTheSimulation(id).subscribe(data => this.simulationDto = data);
  // }

}
