import { Component } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { RouterLink, RouterOutlet } from '@angular/router';
import {MatNavList} from '@angular/material/list';
import {NgClass} from '@angular/common';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

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
  generatePDF(element: any) {
   const doc = new jsPDF();

   doc.setFontSize(16);
   doc.text(`Solicitud N°: #${element.id}`, 10, 20);
   doc.setFontSize(12);
   doc.text(`Título: ${element.title}`, 10, 30);
   doc.text(`Fecha: ${element.date}`, 10, 40);
   doc.text(`Mecánico: ${element.mechanic}`, 10, 50);
   doc.text(`Prioridad: ${element.priority}`, 10, 60);
   doc.text(`Estado: ${element.status}`, 10, 70);

   doc.save(`solicitud_${element.id}.pdf`);
  }

}
