import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Solicitud } from '../modelo/Solicitud';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService extends GenericService<Solicitud> {

  private entidadSubject = new BehaviorSubject<Solicitud[]>([]);
  private messageChange: Subject<string> = new Subject<string>();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/solicitudes`);
  }

  setEntidadChange(data: Solicitud[]) {
    this.entidadSubject.next(data);
  }

  getEntidadChange() {
    return this.entidadSubject.asObservable();
  }

  setMessageChange(data: string) {
    this.messageChange.next(data);
  }

  getMessageChange() {
    return this.messageChange.asObservable();
  }
}
