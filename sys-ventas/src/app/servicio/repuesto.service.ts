import { Injectable } from "@angular/core";
import { GenericService } from "./generic.service";
import { Repuesto } from "../modelo/Repuesto";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class RepuestoService extends GenericService<Repuesto> {
  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/api/repuestos`);
  }
}