import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SimulationComponent} from "./simulation/simulation.component";
import {AppComponent} from "./app.component";
import {SimulationDetailsComponent} from "./simulation-details/simulation-details.component";
import {CreateSimulationComponent} from "./create-simulation/create-simulation.component";

const routes: Routes = [
  {
    path: 'all',
    component: SimulationComponent
  },
  {
    path: 'all/details',
    component: SimulationDetailsComponent
  },
  {
    path: 'new',
    component: CreateSimulationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
