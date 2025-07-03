import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Recepcion } from '../../modelo/Recepcion';
import { RecepcionService } from '../../servicio/recepcion.service';
import { RepuestoService } from '../../servicio/repuesto.service';
import { Repuesto } from '../../modelo/Repuesto';

@Component({
  selector: 'app-main-recepcion',
  templateUrl: './main-recepcion.component.html',
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    MatChipsModule,
    MatTooltipModule,
    RouterModule,
    DatePipe  
  ],
  styleUrls: ['./main-recepcion.component.css']
})
export class MainRecepcionComponent implements OnInit {

  displayedColumns: string[] = ['id', 'repuesto', 'cantidadRecibida', 'proveedor', 'codigo', 'fechaRecepcion', 'estado', 'accion'];
  dataSource: MatTableDataSource<Recepcion> = new MatTableDataSource<Recepcion>();

  repuestos: Repuesto[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private recepcionService: RecepcionService,
    private repuestoService: RepuestoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.repuestoService.findAll().subscribe((reps: Repuesto[]) => {
      this.repuestos = reps;
      this.listarRecepciones();
    });
  }

  listarRecepciones() {
    this.recepcionService.findAll().subscribe(data => {
      this.dataSource.data = data;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  getNombreRepuesto(id: number): string {
    const rep = this.repuestos.find(r => r.id === id);
    return rep ? rep.nombre : 'Desconocido';
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = filterValue;
  }

  delete(id: number) {
    this.recepcionService.delete(id).subscribe(() => {
      this.listarRecepciones();
    });
  }

  validar(id: number) {
    this.recepcionService.validarRecepcion(id).subscribe(() => {
      this.listarRecepciones();
    });
  }
}



