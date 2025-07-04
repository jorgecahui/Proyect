import {Repuesto} from '../modelo/Repuesto';
import { HttpClient } from '@angular/common/http';
import {GenericService} from './generic.service';
import {Injectable, Inject} from '@angular/core';
import { environment } from '../../environments/environment';
import {BehaviorSubject, Observable} from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class RepuestoService extends GenericService<Repuesto>{
  private repuestosSubject = new BehaviorSubject<Repuesto[]>([]);
  private messageSubject = new BehaviorSubject<string>('');

  constructor(protected  override http: HttpClient) {
    super(http, `${environment.HOST}/api/repuesto`);
  }
  // Observable de la lista de repuestos
  getRepuestos$(): Observable<Repuesto[]> {
    return this.repuestosSubject.asObservable();
  }

  // Actualizar lista de repuestos
  updateRepuestosList(repuestos: Repuesto[]): void {
    this.repuestosSubject.next(repuestos);
  }

  // Mensajes del servicio
  getMessages$(): Observable<string> {
    return this.messageSubject.asObservable();
  }

  sendMessage(message: string): void {
    this.messageSubject.next(message);
  }

  // Métodos específicos para repuestos
  buscarPorNombre(nombre: string): Observable<Repuesto[]> {
    return this.http.get<Repuesto[]>(`${this.url}/buscar?nombre=${nombre}`);
  }

  actualizarStock(id: number, cantidad: number): Observable<Repuesto> {
    return this.http.patch<Repuesto>(`${this.url}/${id}/stock`, { cantidad });
  }

  getRepuestosBajoStock(): Observable<Repuesto[]> {
    return this.http.get<Repuesto[]>(`${this.url}/bajo-stock`);
  }

  // Sobreescribir findAll para actualizar el subject
  override findAll(): Observable<Repuesto[]> {
    return this.http.get<Repuesto[]>(this.url).pipe(
      tap(repuestos => this.updateRepuestosList(repuestos))
    );
  }

}
