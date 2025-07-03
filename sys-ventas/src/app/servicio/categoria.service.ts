import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';
import { Categoria } from '../modelo/Categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private url: string = `${environment.HOST}/categorias`;
  private categoriaSubject: BehaviorSubject<Categoria[]> = new BehaviorSubject<Categoria[]>([]);
  categoria$ = this.categoriaSubject.asObservable();

  private messageChange: Subject<string> = new Subject<string>();

  constructor(private http: HttpClient) { }

  
  findAll(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.url);
  }

  
  loadCategorias(): void {
    this.findAll().subscribe(data => {
      this.categoriaSubject.next(data);
    });
  }

  findById(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.url}/${id}`);
  }

  save(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.url, categoria).pipe(
      tap(() => this.loadCategorias())
    );
  }

  update(id: number, categoria: Categoria): Observable<Categoria> {
    return this.http.put<Categoria>(`${this.url}/${id}`, categoria).pipe(
      tap(() => this.loadCategorias())
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`).pipe(
      tap(() => this.loadCategorias())
    );
  }

  setMessageChange(data: string): void {
    this.messageChange.next(data);
  }

  getMessageChange(): Observable<string> {
    return this.messageChange.asObservable();
  }
}

