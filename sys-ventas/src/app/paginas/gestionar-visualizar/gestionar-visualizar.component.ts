import { Component } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { RouterLink, RouterOutlet } from '@angular/router';
import {MatNavList} from '@angular/material/list';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-gestionar-visualizar',
  standalone: true,
  imports: [
    MaterialModule,
    MatNavList,
    NgClass,
    RouterLink,
    RouterOutlet,
  ],
  templateUrl: './gestionar-visualizar.component.html',
  styleUrls: ['./gestionar-visualizar.component.css'],
})
export class GestionarVisualizarComponent {
  requestData = [
    { id: 1264, title: 'Cambio de correa', date: '13/04/2025', status: 'Pendiente', statusColor: 'yellow', priority: 'Alta', mechanic: 'Juan Pérez' },
    { id: 1265, title: 'Cambio de aceite', date: '20/04/2025', status: 'Aprobado', statusColor: 'green', priority: 'Media', mechanic: 'María García' },
    { id: 1266, title: 'Cambio de faro LED', date: '13/04/2025', status: 'No aprobado', statusColor: 'red', priority: 'Baja', mechanic: 'Carlos López' },
    { id: 1267, title: 'Revisión de frenos', date: '25/04/2025', status: 'Pendiente', statusColor: 'yellow', priority: 'Alta', mechanic: 'Ana Martín' },
    { id: 1268, title: 'Cambio de filtro de aire', date: '28/04/2025', status: 'Aprobado', statusColor: 'green', priority: 'Baja', mechanic: 'Luis Rodríguez' }
  ];

  displayedColumns: string[] = ['id', 'title', 'date', 'mechanic', 'priority', 'status', 'actions'];

  getPriorityClass(priority: string): string {
    return {
      'Alta': 'priority-high',
      'Media': 'priority-medium',
      'Baja': 'priority-low',
    }[priority] || '';
  }

  getStatusClass(color: string): string {
    return {
      'yellow': 'status-pending',
      'green': 'status-approved',
      'red': 'status-denied',
    }[color] || '';
  }
}
