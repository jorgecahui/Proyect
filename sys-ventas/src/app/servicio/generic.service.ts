import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export class GenericService<T> {
  constructor(protected http: HttpClient, protected url: string) {}

  findAll(): Observable<T[]> {
    return this.http.get<T[]>(this.url);
  }

  findById(id: number): Observable<T> {
    return this.http.get<T>(`${this.url}/${id}`);
  }

  save(obj: T): Observable<T> {
    return this.http.post<T>(this.url, obj);
  }

  update(id: number, obj: T): Observable<void> {
    return this.http.put<void>(`${this.url}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}

