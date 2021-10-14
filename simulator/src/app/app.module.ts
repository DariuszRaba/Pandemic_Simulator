import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { SimulationComponent } from './simulation/simulation.component';
import { SimulationDetailsComponent } from './simulation-details/simulation-details.component';
import {FormsModule} from "@angular/forms";
import { GoBackComponent } from './go-back/go-back.component';
import { CreateSimulationComponent } from './create-simulation/create-simulation.component';

@NgModule({
  declarations: [
    AppComponent,
    SimulationComponent,
    SimulationDetailsComponent,
    GoBackComponent,
    CreateSimulationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
