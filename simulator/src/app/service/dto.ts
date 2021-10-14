export interface Dto {

}

interface populationDto {
  infected:number;
  exposedPersons:number;
  deadPersons:number;
  healers:number;
}

export interface simulationDto {
  id?:number;
  name:string;
  population:number;
  infected:number;
  virusReproductionRate:number;
  mortalityRate:number;
  recoveryTimeFrame:number;
  deathTimeFrame:number;
  simulationTime:number;
  populationList?:populationDto[]
}

export interface simulationList{
  simulations?:simplifiedSimulationDto[];
}
export interface simplifiedSimulationDto {
  id:number;
  name:string;
}
