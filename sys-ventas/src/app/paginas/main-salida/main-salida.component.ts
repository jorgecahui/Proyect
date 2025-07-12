import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

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
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { MaterialModule } from '../../material/material.module';

import { FormSalidaComponent } from './form-salida/form-salida.component';

import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

@Component({
  selector: 'app-main-salida',
  standalone: true,
  templateUrl: './main-salida.component.html',
  styleUrls: ['./main-salida.component.css'],
  imports: [
    CommonModule,
    DatePipe,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MaterialModule,
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

  totalSalidas: number = 0;
  totalEntregados: number = 0;
  totalPendientes: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private salidaService: SalidaService,
    private repuestoService: RepuestoService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.repuestoService.findAll().subscribe(res => {
      this.repuestos = res;

      this.salidaService.findAll().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        this.totalSalidas = data.length;
        this.totalEntregados = data.filter(s => s.estado?.toLowerCase() === 'entregado').length;
        this.totalPendientes = data.filter(s => s.estado?.toLowerCase() === 'pendiente').length;

        this.dataSource.filterPredicate = (data: Salida, filter: string) => {
          const texto = filter.trim().toLowerCase();
          const nombreRepuesto = data.nombreRepuesto?.toLowerCase() ?? '';
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

  nombreRepuesto(nombreRepuesto: string): string {
    const repuesto = this.repuestos.find(r => r.nombre === nombreRepuesto);
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

  abrirDialogoEditar(id?: number) {
    const dialogRef = this.dialog.open(FormSalidaComponent, {
      width: '600px',
      maxHeight: '90vh',
      data: id ? { id } : null,
      disableClose: true,
      autoFocus: false,
      panelClass: 'dialog-scrollable'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'guardado') {
        this.cargarDatos();
      }
    });
  }

  generarPDF(salida: Salida) {
    const doc = new jsPDF();

    doc.setFontSize(16);
    doc.text('Detalle de Salida de Repuesto', 14, 20);

    const data: (string | number)[][] = [
      ['ID', salida.id ?? ''],
      ['Código', salida.codigo ?? ''],
      ['Repuesto', salida.nombreRepuesto != null ? this.nombreRepuesto(salida.nombreRepuesto) : 'Desconocido'],
      ['Cantidad Entregada', salida.cantidadEntregada],
      ['Destinatario', salida.destinatario],
      ['Fecha de Salida', new Date(salida.fechaSalida).toLocaleDateString()],
      ['Estado', salida.estado]
    ];

    autoTable(doc, {
      startY: 30,
      head: [['Campo', 'Valor']],
      body: data.map(row => row.map(cell => cell ?? '')) 
    });

    doc.save(`salida-${salida.id}.pdf`);
  }
}








