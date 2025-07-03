import { Component, OnInit } from '@angular/core';
import { MaterialModule } from "../../../material/material.module";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Categoria } from "../../../modelo/Categoria";
import { Marca } from "../../../modelo/Marca";
import { UnidadMedida } from "../../../modelo/UnidadMedida";
import { Observable } from "rxjs";
import { ProductoService } from "../../../servicio/producto.service";
import { MarcaService } from "../../../servicio/marca.service";
import { CategoriaService } from "../../../servicio/categoria.service";
import { ActivatedRoute, Router, Params, RouterLink } from "@angular/router";
import { MatSnackBar } from "@angular/material/snack-bar";
import { UnidadmedidaService } from "../../../servicio/unidadmedida.service";
import { Producto } from "../../../modelo/Producto";
import { AsyncPipe, NgForOf, NgIf, CommonModule } from "@angular/common";

@Component({
  selector: 'app-formx-producto',
  standalone: true,
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    NgIf,
    RouterLink,
    NgForOf,
    AsyncPipe
  ],
  templateUrl: './formx-producto.component.html',
  styleUrl: './formx-producto.component.css'
})
export class FormxProductoComponent implements OnInit {

  productForm: FormGroup = new FormGroup({});
  categorias: Categoria[] = [];
  marcas: Marca[] = [];
  unidadMedidas$!: Observable<UnidadMedida[]>;

  id: number = 0;
  isEdit: boolean = false;

  constructor(
    private serviceProducto: ProductoService,
    private servicioMarca: MarcaService,
    private servicioCategoria: CategoriaService,
    private serviceUnitMed: UnidadmedidaService,
    private route: ActivatedRoute,
    private router: Router,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.initFormGroup();

    this.servicioMarca.findAll().subscribe(data => this.marcas = data);
    this.servicioCategoria.findAll().subscribe(data => this.categorias = data);
    this.unidadMedidas$ = this.serviceUnitMed.findAll();

    this.route.params.subscribe((params: Params) => {
      if (params['id']) {
        this.id = +params['id'];
        this.isEdit = true;
        this.initForm();
      }
    });
  }

  initFormGroup(): void {
    this.productForm = new FormGroup({
      idProducto: new FormControl(null),
      nombre: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(70)]),
      pu: new FormControl(0, [Validators.required]),
      puOld: new FormControl(0, [Validators.required]),
      utilidad: new FormControl(0, [Validators.required]),
      stock: new FormControl(0, [Validators.required]),
      stockOld: new FormControl(0, [Validators.required]),
      categoria: new FormControl(null, [Validators.required]),
      marca: new FormControl(null, [Validators.required]),
      unidadMedida: new FormControl(null, [Validators.required])
    });
  }

  initForm(): void {
    this.serviceProducto.findByIdOT(this.id).subscribe(data => {
      this.productForm.setValue({
        idProducto: data.idProducto,
        nombre: data.nombre,
        pu: data.pu,
        puOld: data.puOld,
        utilidad: data.utilidad,
        stock: data.stock,
        stockOld: data.stockOld,
        categoria: data.categoria.idCategoria,
        marca: data.marca.idMarca,
        unidadMedida: data.unidadMedida.idUnidad
      });
    });
  }

  operar(): void {
    const product: Producto = { ...this.productForm.value };

    const operacion = this.isEdit
      ? this.serviceProducto.update(product.idProducto, product)
      : this.serviceProducto.save(product);

    operacion.subscribe({
      next: () => {
        this.serviceProducto.loadProductos();
        const mensaje = this.isEdit ? 'Se ha Modificado correctamente' : 'Se ha Creado correctamente';
        this.toastMsg(mensaje);
        this.router.navigate(['pages/product']);
      },
      error: (err) => {
        console.error(err);
        this.toastMsg('Error en la operación');
      }
    });
  }

  toastMsg(msg: string): void {
    this._snackBar.open(msg, 'INFO', {
      duration: 2000,
      verticalPosition: 'top',
      horizontalPosition: 'right'
    });
  }

}


