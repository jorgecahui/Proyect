export interface Repuesto {
  idRepuesto: number;
  nombre: string;
  stockActual: string;
  codigo: string;
  ubicacion: string;
  estado: string;
  stockMinimo: string;

  /* claves foráneas */
  categoria: number;     // id_categoria
  marca: number;         // id_marca
  unidadMedida: number;  // id_unidad
}
