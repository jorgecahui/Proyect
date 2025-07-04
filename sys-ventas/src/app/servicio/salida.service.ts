import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Salida } from '../modelo/Salida';
import { Observable, Subject, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SalidaService {
  private url: string = 'http://localhost:6161/api/salidas';

  private entidadCambio = new Subject<Salida[]>();
  private mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) {}

  getEntidadChange(): Observable<Salida[]> {
    return this.entidadCambio.asObservable();
  }

  setEntidadChange(lista: Salida[]) {
    this.entidadCambio.next(lista);
  }

  getMessageChange(): Observable<string> {
    return this.mensajeCambio.asObservable();
  }

  setMessageChange(mensaje: string) {
    this.mensajeCambio.next(mensaje);
  }

  findAll(): Observable<Salida[]> {
    return this.http.get<Salida[]>(this.url);
  }

  findById(id: number): Observable<Salida> {
    return this.http.get<any>(`${this.url}/${id}`).pipe(
      map(data => ({
        id: data.id,
        idRepuesto: data.repuesto.id,
        cantidadEntregada: data.cantidadEntregada,
        destinatario: data.destinatario,
        codigo: data.codigo,
        fechaSalida: new Date(data.fechaSalida),
        estado: data.estado,
        nombreRepuesto: data.repuesto.nombre || ''
      }))
    );
  }

  save(salida: Salida): Observable<Salida> {
    return this.http.post<Salida>(this.url, salida);
  }

  update(id: number, salida: Salida): Observable<void> {
    return this.http.put<void>(`${this.url}/${id}`, salida);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}




