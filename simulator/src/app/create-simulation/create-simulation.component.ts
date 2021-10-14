import {Component, Input, OnInit} from '@angular/core';
import {simulationDto} from "../service/dto";
import {extractHostBindings} from "@angular/compiler-cli/src/ngtsc/annotations/src/directive";
import {SimulationService} from "../service/simulation.service";

@Component({
  selector: 'app-create-simulation',
  templateUrl: './create-simulation.component.html',
  styleUrls: ['./create-simulation.component.css']
})
export class CreateSimulationComponent implements OnInit {
  name: string = 'Simulation Name';
  population: number = 0;
  infected: number = 0;
  reproduction: number = 0;
  mortality: number = 0;
  recovery: number = 0;
  death: number = 0;
  simulationTime: number = 0;
  simulation: simulationDto = {name:this.name,
    population: this.population,
    infected: this.infected,
    virusReproductionRate:this.reproduction,
    mortalityRate:this.mortality,
    recoveryTimeFrame:this.recovery,
    deathTimeFrame: this.death,
    simulationTime:this.simulationTime}

  constructor(private simulationService: SimulationService) {
  }

  ngOnInit(): void {

  }


  simulate() {
    this.simulation.name = this.name;
    this.simulation.population = this.population;
    this.simulation.infected = this.infected;
    this.simulation.virusReproductionRate = this.reproduction;
    this.simulation.mortalityRate = this.mortality;
    this.simulation.recoveryTimeFrame = this.recovery;
    this.simulation.deathTimeFrame = this.death;
    this.simulation.simulationTime = this.simulationTime;
   this.simulationService.simulate(this.simulation).subscribe(response =>
     this.simulation = response);
  }

  save() {
    this.simulationService.saveSimulation(this.simulation).subscribe();
  }
}

