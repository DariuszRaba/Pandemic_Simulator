import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {simplifiedSimulationDto, simulationDto, simulationList} from "./dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SimulationService {
  private apiUrl = `${environment.apiUrl}`
  simulationId!:number
  constructor(private http: HttpClient) {
  }

  getAllSimulations(): Observable<simplifiedSimulationDto[]> {
    return this.http.get<simplifiedSimulationDto[]>(this.apiUrl + '/all');
  }

  getTheSimulation(id: number): Observable<simulationDto> {
    return this.http.get<simulationDto>(this.apiUrl + '/' + id);

  }

  remove(id: number) {
    return this.http.delete(this.apiUrl + '/' + id);
  }

  simulate(simulation: simulationDto):Observable<simulationDto> {
    const myPostBody = JSON.stringify(simulation);
   return this.http.post<simulationDto>(this.apiUrl + '/simulate',myPostBody,{
       headers: new HttpHeaders({"content-type": "application/json"})
   });
  }

  saveSimulation(simulation: simulationDto):Observable<any> {
    const myPostBody = JSON.stringify(simulation);
   return this.http.post(this.apiUrl + '/add', myPostBody,{
      headers: new HttpHeaders({"content-type": "application/json"})
    });
  }
}
