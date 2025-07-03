import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment.development";
import { Producto, ProductoReport } from "../modelo/Producto";
import { BehaviorSubject, Observable, Subject, tap } from "rxjs";
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class ProductoService extends GenericService<Producto> {

  private productosSubject = new BehaviorSubject<ProductoReport[]>([]);
  productos$ = this.productosSubject.asObservable();

  private productoSeleccionadoSubject = new BehaviorSubject<ProductoReport | null>(null);
  productoSeleccionado$ = this.productoSeleccionadoSubject.asObservable();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/productos`);
  }

  loadProductos(): void {
    this.http.get<ProductoReport[]>(this.url).subscribe(data => {
      this.productosSubject.next(data);
    });
  }

  findByIdOT(id: number) {
    return this.http.get<ProductoReport>(this.url + `/${id}`);
  }

  seleccionarProducto(producto: ProductoReport) {
    this.productoSeleccionadoSubject.next(producto);
  }
  
  listPageable(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.url}/pageable?page=${page}&size=${size}`);
  }
}
