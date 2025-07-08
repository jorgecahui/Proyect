import { Component, OnInit, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { Salida } from '../../../modelo/Salida';
import { SalidaService } from '../../../servicio/salida.service';
import { switchMap } from 'rxjs';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MaterialModule } from '../../../material/material.module';
import { RepuestoService } from '../../../servicio/repuesto.service';
import { Repuesto } from '../../../modelo/Repuesto';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-form-salida',
  standalone: true,
  templateUrl: './form-salida.component.html',
  styleUrls: ['./form-salida.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatSelectModule
  ]
})
export class FormSalidaComponent implements OnInit {
  form!: FormGroup;
  isEdit: boolean = false;
  id!: number;
  repuestos: Repuesto[] = [];

  constructor(
    private fb: FormBuilder,
    private salidaService: SalidaService,
    private repuestoService: RepuestoService,
    private dialogRef: MatDialogRef<FormSalidaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      id: [null],
      idRepuesto: ['', Validators.required],
      cantidadEntregada: [0, [Validators.required, Validators.min(1)]],
      destinatario: ['', Validators.required],
      codigo: [''],
      fechaSalida: [new Date(), Validators.required],
      estado: ['']
    });

    this.listarRepuestos();

    if (this.data?.id) {
      this.id = this.data.id;
      this.isEdit = true;
      this.initForm();
    } else if (this.data) {
      this.form.patchValue({
        idRepuesto: this.data.idRepuesto ?? '',
        cantidadEntregada: this.data.cantidadRecibida ?? 0,
        destinatario: this.data.destinatario ?? '',
        codigo: this.data.codigo ?? '',
        fechaSalida: new Date(),
        estado: 'PENDIENTE'
      });
    }
  }

  listarRepuestos() {
    this.repuestoService.findAll().subscribe((data: Repuesto[]) => {
      this.repuestos = data;
    });
  }

  initForm() {
    this.salidaService.findById(this.id).subscribe(data => {
      this.form.setValue({
        id: data.id,
        idRepuesto: data.idRepuesto,
        cantidadEntregada: data.cantidadEntregada,
        destinatario: data.destinatario,
        codigo: data.codigo,
        fechaSalida: new Date(data.fechaSalida),
        estado: data.estado
      });
    });
  }

  guardar() {
    const salida: Salida = this.form.value;

    if (this.isEdit) {
      this.salidaService.update(this.id, salida).pipe(
        switchMap(() => this.salidaService.findAll())
      ).subscribe(data => {
        this.salidaService.setEntidadChange(data);
        this.salidaService.setMessageChange('Actualizado correctamente');
        this.dialogRef.close('guardado');
      });
    } else {
      this.salidaService.save(salida).pipe(
        switchMap(() => this.salidaService.findAll())
      ).subscribe(data => {
        this.salidaService.setEntidadChange(data);
        this.salidaService.setMessageChange('Creado correctamente');
        this.dialogRef.close('guardado');
      });
    }
  }

  cancelar() {
    this.dialogRef.close();
  }
}







