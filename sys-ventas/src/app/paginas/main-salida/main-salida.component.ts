import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';

import { SalidaService } from '../../servicio/salida.service';
import { RepuestoService } from '../../servicio/repuesto.service';

import { Salida } from '../../modelo/Salida';
import { Repuesto } from '../../modelo/Repuesto';

import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MaterialModule } from '../../material/material.module';

@Component({
  selector: 'app-main-salida',
  standalone: true,
  templateUrl: './main-salida.component.html',
  styleUrls: ['./main-salida.component.css'],
  imports: [
    CommonModule,
    RouterLink,
    RouterOutlet,
    DatePipe,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MaterialModule
  ]
})
export class MainSalidaComponent implements OnInit {
  displayedColumns: string[] = [
    'id',
    'repuesto',
    'cantidadEntregada',
    'destinatario',
    'codigo',
    'fechaSalida',
    'estado',
    'accion'
  ];

  dataSource!: MatTableDataSource<Salida>;
  repuestos: Repuesto[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private salidaService: SalidaService,
    private repuestoService: RepuestoService
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.repuestoService.findAll().subscribe(res => {
      this.repuestos = res;

      this.salidaService.findAll().subscribe(data => {
        console.log(data); 
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        
        this.dataSource.filterPredicate = (data: Salida, filter: string) => {
          const texto = filter.trim().toLowerCase();
          const nombreRepuesto = this.nombreRepuesto(data.idRepuesto).toLowerCase();
          return (
            nombreRepuesto.includes(texto) ||
            data.destinatario.toLowerCase().includes(texto) ||
            data.codigo.toLowerCase().includes(texto) ||
            data.estado.toLowerCase().includes(texto)
          );
        };
      });
    });
  }

  nombreRepuesto(idRepuesto: number): string {
    const repuesto = this.repuestos.find(r => r.id === idRepuesto);
    return repuesto ? repuesto.nombre : 'Desconocido';
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  delete(id: number) {
    this.salidaService.delete(id).subscribe(() => {
      this.cargarDatos();
    });
  }
}




