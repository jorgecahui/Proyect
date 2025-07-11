import { Component, OnInit, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { Salida } from '../../../modelo/Salida';
import { SalidaService } from '../../../servicio/salida.service';
import {switchMap, tap} from 'rxjs';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MaterialModule } from '../../../material/material.module';
import { RepuestoService } from '../../../servicio/repuesto.service';
import { Repuesto } from '../../../modelo/Repuesto';
import {CommonModule, formatDate} from '@angular/common';
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
      estado: [''],
      nombreRepuesto: ['', Validators.required]

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
        estado: 'PENDIENTE',
        nombreRepuesto: this.data.nombreRepuesto
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
        estado: data.estado,
        nombreRepuesto:data.nombreRepuesto
      });
    });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      console.error('Errores:', this.getFormErrors());
      return;
    }

    const salida = {
      ...this.form.value,
      // Asegura que idRepuesto sea number (no string)
      idRepuesto: Number(this.form.value.idRepuesto),
      cantidadEntregada: Number(this.form.value.cantidadEntregada),
      destinatario: this.form.value.destinatario.trim(),
      codigo: this.form.value.codigo || '',
      fechaSalida: formatDate(this.form.value.fechaSalida, 'yyyy-MM-dd', 'en-US'),
      estado: this.form.value.estado

    };
    console.log('Datos a guardar:', salida);
    const operation = this.isEdit ?
      this.salidaService.update(salida.id, salida) :
      this.salidaService.save(salida);

    operation.pipe(
      tap(() => {
        const mensaje = this.isEdit ?
          'Salida actualizada correctamente' :
          'Salida creada correctamente';
        this.salidaService.setMensajeCambio(mensaje);
      }),
      switchMap(() => this.salidaService.findAll())
    ).subscribe({
      next: (data) => {
        this.salidaService.setEntidadCambio(data);
        this.dialogRef.close('guardado');
      },
      error: (error) => {
        console.error('Error al guardar:', error);
        this.salidaService.setMensajeCambio('Error al guardar la salida');
      }
    });
  }


  cancelar() {
    this.dialogRef.close();
  }
  private getFormErrors() {
    const errors: { [key: string]: any } = {}; // ✅ define firma de índice
    Object.keys(this.form.controls).forEach(key => {
      const control = this.form.get(key);
      if (control?.errors) {
        errors[key] = control.errors;
      }
    });
    return errors;
  }

}







